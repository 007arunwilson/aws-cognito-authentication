package com.cyberinfoscipter.scripts.lambda.cognito;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityAsyncClient;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClientBuilder;

import java.util.HashMap;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cyberinfoscipter.scripts.lambda.cognito.model.CognitoReq;
import com.cyberinfoscipter.scripts.lambda.cognito.model.CognitoResp;;

public class CognitoRequestHandler implements RequestHandler<CognitoReq, CognitoResp> {
	
	AmazonCognitoIdentityAsyncClient aCIC;
	GetOpenIdTokenForDeveloperIdentityRequest tokenRequest;
    @Override
    public CognitoResp handleRequest(CognitoReq req, Context context) {
    	
    	String envType = System.getenv("ENV_TYPE");
    	
    	AmazonCognitoIdentity client = null;
    	
    	if(envType!= null && envType.contentEquals("LOCAL")) {
             client = AmazonCognitoIdentityClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ProfileCredentialsProvider("cyberinfoscripter"))
                    .build();
    	}else {
             client = AmazonCognitoIdentityClientBuilder.defaultClient();	
    	}
    	
         tokenRequest = 
        		  new GetOpenIdTokenForDeveloperIdentityRequest();
         
         String identityPoolId = System.getenv("AWS_IDENTITY_POOL_ID");
         
         tokenRequest.setIdentityPoolId(identityPoolId);
         HashMap<String, String> map = new HashMap<String, String>();
         
         map.put(req.getDeveloperProviderName(), req.getUserId()); // Developer provider name & backend User Id 
         
         tokenRequest.setLogins(map);
         tokenRequest.setTokenDuration(1000l);
         
         GetOpenIdTokenForDeveloperIdentityResult result 
         = client.getOpenIdTokenForDeveloperIdentity(tokenRequest);
         String identityId = result.getIdentityId();
         String tokenId = result.getToken();
         
     	 CognitoResp resp = new CognitoResp();
         resp.setIdentityPoolId(identityPoolId);
         resp.setIdentityId(identityId);
         resp.setTokenId(tokenId);
         
        // TODO: implement your handler
        return resp;
    }

}
