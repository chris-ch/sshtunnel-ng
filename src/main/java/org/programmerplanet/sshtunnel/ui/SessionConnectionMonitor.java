package org.programmerplanet.sshtunnel.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Display;
import org.programmerplanet.sshtunnel.model.ConnectionManager;
import org.programmerplanet.sshtunnel.model.Session;

/**
 * 
 * @author <a href="agungm@outlook.com">Mulya Agung</a>
 */

public class SessionConnectionMonitor implements Runnable {

	private static final Log log = LogFactory.getLog(SessionConnectionMonitor.class);

	private static final int DEF_MONITOR_INTERVAL = 10000;
	private static final SessionConnectionMonitor INSTANCE = new SessionConnectionMonitor();

	private final Object lock = new Object();
	private Thread thread;
	private Boolean threadStopped;
	private final int monitorInterval;
	private final Map<String, Session> sessions;
	private SshTunnelComposite sshTunnelComposite;

	public SessionConnectionMonitor() {
		this(DEF_MONITOR_INTERVAL);
	}

	public SessionConnectionMonitor(int monitorInterval) {
		sessions = new ConcurrentHashMap<>();
		threadStopped = false;
		this.monitorInterval = monitorInterval;
	}

	public void addSession(String name, Session session) {
		sessions.put(name, session);
	}

	public void removeSession(String name) {
		sessions.remove(name);
	}

	public void run() {
		if (log.isWarnEnabled()) {
			log.warn("Connection monitor is now running..");
		}
		while (!threadStopped) {
			boolean anyRemoved = false;
			Iterator<Entry<String, Session>> it = sessions.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Session> entry = it.next();
				if (!ConnectionManager.Companion.isConnected(entry.getValue())) {
					ConnectionManager.Companion.disconnect(entry.getValue());
					if (log.isWarnEnabled()) {
						log.warn("Session " + entry.getKey() + " has disconnected.");
					}
					if (sshTunnelComposite != null) {
						final Session s = entry.getValue();
						Display.getDefault().asyncExec(() -> sshTunnelComposite.showDisconnectedMessage(s));
				}
				it.remove();
				if (!anyRemoved)
					anyRemoved = true;
				}
			}
			if (sshTunnelComposite != null && anyRemoved) {
				Display.getDefault().asyncExec(() -> sshTunnelComposite.connectionStatusChanged());
			}
			try {
				Thread.sleep(monitorInterval);
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
	}
	
	public void setSshTunnelComposite(SshTunnelComposite sshTunnelComposite) {
		this.sshTunnelComposite = sshTunnelComposite;
	}

	public void startMonitor() {
		synchronized (lock) {
			if (thread == null) {
				thread = new Thread(this);
				threadStopped = false;
				thread.start();
			}
		}
	}

	public void stopMonitor() {
		synchronized (lock) {
			if (thread != null) {
				threadStopped = true;
				thread = null;
				
				if (log.isWarnEnabled()) {
					log.warn("Connection monitor is stopped.");
				}
			}
		}
	}

	public static SessionConnectionMonitor getInstance() {
		return INSTANCE;
	}

}
