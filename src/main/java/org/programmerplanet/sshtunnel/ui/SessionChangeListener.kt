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
package org.programmerplanet.sshtunnel.ui

import org.programmerplanet.sshtunnel.model.Session

/**
 *
 * @author [Joseph Fifield](jfifield@programmerplanet.org)
 */
interface SessionChangeListener {
    fun sessionAdded(session: Session)

    fun sessionChanged(session: Session)

    fun sessionRemoved(session: Session)

    fun sessionSelectionChanged(session: Session)
}
