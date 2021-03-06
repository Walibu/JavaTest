/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.examples.library.global;

import org.apache.wicket.IClusterable;

/**
 * Trivial user model for example application
 * 
 * @author Jonathan Locke
 */
public final class User implements IClusterable {
	// The user's name
	private String name;

	/**
	 * @return User name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param string
	 *            User name
	 */
	public final void setName(final String string) {
		name = string;
	}

}
