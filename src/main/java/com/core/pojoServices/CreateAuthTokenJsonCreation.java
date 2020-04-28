package com.core.pojoServices;

import com.core.pojoServices.pojo.CreateAuthToken;

public class CreateAuthTokenJsonCreation {

    public CreateAuthToken mapCreateAuthToken(String username, String password) {

        CreateAuthToken createAuthToken = new CreateAuthToken();
        createAuthToken.setUsername(username);
        createAuthToken.setPassword(password);

        return createAuthToken;
    }
}
