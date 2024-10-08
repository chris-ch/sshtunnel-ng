/*
 * Copyright 2009 Joseph Fifield
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.programmerplanet.sshtunnel.model

/**
 * Represents a session to an ssh host.
 *
 * @author [Joseph Fifield](jfifield@programmerplanet.org)
 * @author [Mulya Agung](agungm@outlook.com)
 */
data class Session(
    var sessionName: String,
    var hostname: String? = null,
    var port: Int = DEFAULT_PORT,
    var username: String? = null,
    var password: String? = null,
    var identityPath: String? = null,
    var passPhrase: String? = null,
    var isCompressed: Boolean = false,
    var ciphers: String? = null,
    var debugLogPath: String? = null,
    val tunnels: MutableList<Tunnel> = mutableListOf()
) : Comparable<Session> {

    override fun toString() = buildString {
        append("Session ($sessionName: $username@$hostname")
        if (port != DEFAULT_PORT) append(":$port")
        append(")")
    }

    override fun compareTo(other: Session) = sessionName.compareTo(other.sessionName)

    companion object {
        private const val DEFAULT_PORT = 22
    }
}