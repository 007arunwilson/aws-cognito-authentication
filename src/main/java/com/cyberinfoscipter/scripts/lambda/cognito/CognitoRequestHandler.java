package com.cyberinfoscipter.scripts.lambda.cognito;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CognitoRequestHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Cognito Function invoked with Input : " + input);

        // TODO: implement your handler
        return "Cognito Function Innoked";
    }

}
