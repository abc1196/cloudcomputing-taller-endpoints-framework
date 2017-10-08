package com.juliana.cloud;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiIssuerAudience;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Email;

/** The Echo API which Endpoints will be exposing. */
//[START echo_api_annotation]
@Api(
 name = "echo",
 version = "v1",
 namespace =
   @ApiNamespace(
     ownerDomain = "dimayor.cloud.com",
     ownerName = "dimayor.cloud.com",
     packagePath = ""
   ),
 // [START_EXCLUDE]
 issuers = {
   @ApiIssuer(
     name = "firebase",
     issuer = "https://securetoken.google.com/tallerdimayor",
     jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
 }
 // [END_EXCLUDE]
 )
//[END echo_api_annotation]

public class DimayorAPI {

	@ApiMethod(
		      path = "teams",
		      httpMethod = ApiMethod.HttpMethod.GET,
		      issuerAudiences = {@ApiIssuerAudience(name = "teams", audiences = {"tallerdimayor"})}
		      )
		  public String getTeamsInfo() throws UnauthorizedException {
		    System.out.println("Get teams info");
		    return null;
		}
}
