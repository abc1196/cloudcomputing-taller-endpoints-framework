/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not  use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cloudcomputing.dimayor.controller;

import com.example.echo.Message;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(
	name = "team",
	version = "v1",
	namespace = @ApiNamespace(
			ownerDomain = "dimayor.surge.sh",
			ownerName = "",
			packagePath = ""),
	issuers = {
			@ApiIssuer(
					name = "firebase",
					issuer = "https://securetoken.google.com/YOUR-PROJECT-ID",
					jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
			}
)
public class TeamApiV1 {

	
	@ApiMethod(name = "test", path="test", httpMethod= ApiMethod.HttpMethod.GET)
	public Message test() {
		return new Message().setMessage("Is working!");
	}
	
	@ApiMethod(name = "test_with_parameter", path = "test/{n}", httpMethod= ApiMethod.HttpMethod.GET)
	public Message echoPathParameter(@Named("n") String n) {
		return new Message().setMessage(n);
	}
		
}
