# SSH Tunnel NG

A user-friendly, fast, and cross-platform SSH tunnel manager.

SSH Tunnel NG is the next generation of the discontinued SSH Tunnel (https://github.com/jfifield/sshtunnel).

What is ssh tunneling? \
https://en.wikipedia.org/wiki/Tunneling_protocol#Secure_Shell_tunneling \
https://www.ssh.com/academy/ssh/tunneling

<img src="img/sshtunnel-ng_screenshot.png" width=70% height=70%>

## Features

1. Manage multiple sessions and tunnels.
2. Local and remote tunnels supported.
3. Bind local ports to any local address.
4. Basic (username/password) and private key authentications
5. Configurable encryption ciphers
6. SSH compression
7. Quick connect/disconnect from tray.
8. Background connection monitoring (automatic reconnection; no hanging sessions)
9. Lightweight (small memory footprint of ~16 MB RAM)
10. Multi-platform (it runs on JVM)
11. Portable/standalone installation (can be run from external disk or USB flash drive)

## Download

### 0.5.8

* [Linux (64-bit JVM)](https://github.com/agung-m/sshtunnel-ng/releases/download/0.5.8/sshtunnel-0.5.8-dist-linux-64.zip)
* [Windows (64-bit JVM)](https://github.com/agung-m/sshtunnel-ng/releases/download/0.5.8/sshtunnel-0.5.8-dist-windows-64.zip)

## Requirements

Java Runtime Environment (JRE) 6.0 or greater

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## Contact
https://agungmulya.com

--------------------------------------------------------------------------------

## Building

  For the current platform:

  `mvn clean assembly:assembly`

  For a different platform for example:

  `mvn -P +linux,-windows clean assembly:assembly`

  Support platforms: windows, windows-64, linux, linux-64

## Running

  Unzip the target distribution.

  `java -jar sshtunnel-VERSION.jar`

## Changes

### 0.5.8
* connection progress bar
* bug fixes

### 0.5.7
* configurable ciphers
* compression

### 0.5.6
* support SSH private key

### 0.5.5
* a background thread that monitor and reconnect disconnected session automatically
 
### 0.5.3
* added support for linux & windows 64-bit
* updated swt libraries

### 0.5.2
* allow non-default port to be specified on a session
* notification when connection fails (host can't be found, auth fails, etc)
* allow session/tunnel panels to be resized (and persisted)
* sessions are now sorted by name
* tunnels are now sorted by local address & local port
