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


package org.apache.isis.remoting.exchange;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.isis.alternatives.remoting.common.exchange.OpenSessionRequest;
import org.apache.isis.core.metamodel.encoding.EncodabilityContractTest;
import org.apache.isis.core.metamodel.encoding.Encodable;

public class AuthenticationRequestEncodabilityTest extends EncodabilityContractTest {

	protected Encodable createEncodable() {
		return new OpenSessionRequest("sven", "pass");
	}

	@Override
	protected void assertRoundtripped(
			Object decodedEncodable,
			Object originalEncodable) {
		OpenSessionRequest decoded = (OpenSessionRequest) decodedEncodable;
		OpenSessionRequest original = (OpenSessionRequest) originalEncodable;
		
		assertThat(decoded.getId(), is(equalTo(original.getId())));
		assertThat(decoded.getResponse(), is(equalTo(original.getResponse())));
		
	}
}
