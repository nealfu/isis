/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.remoting.server;

import java.io.IOException;

import org.apache.isis.alternatives.remoting.common.client.ClientConnection;
import org.apache.isis.alternatives.remoting.common.exchange.Request;
import org.apache.isis.alternatives.remoting.common.facade.ServerFacade;
import org.apache.isis.alternatives.remoting.common.marshalling.ServerMarshaller;
import org.apache.isis.core.commons.exceptions.IsisException;

/**
 * Acts as the mediator between the {@link ServerMarshaller} (which pulls stuff off the transport and pushes stuff on)
 * and the {@link ServerFacade}, ie the rest of the Apache Isis System.
 * 
 * @see ClientConnection
 */
public interface ServerConnection {

    ServerFacade getServerFacade();

    Request readRequest() throws IOException;

    void sendResponse(Object response) throws IOException;

    void sendError(IsisException exception) throws IOException;

}
