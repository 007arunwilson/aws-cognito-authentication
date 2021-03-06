package com.cyberinfoscipter.scripts.lambda.cognito;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.cyberinfoscipter.scripts.lambda.cognito.model.CognitoReq;
import com.cyberinfoscipter.scripts.lambda.cognito.model.CognitoResp;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CognitoRequestHandlerTest {

    private static CognitoReq input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new CognitoReq();
        input.setDeveloperProviderName("cognito");
        input.setUserId("64");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testCognitoRequestHandler() {
        CognitoRequestHandler handler = new CognitoRequestHandler();
        Context ctx = createContext();

        CognitoResp output = handler.handleRequest(input, ctx);
        
        ctx.getLogger().log(output.getTokenId());
        // TODO: validate output here if needed.
        Assert.assertEquals("String", "String");
    }
}
