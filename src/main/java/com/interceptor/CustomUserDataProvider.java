package com.interceptor;

import com.predic8.membrane.core.Router;
import com.predic8.membrane.core.interceptor.authentication.session.UserDataProvider;

import java.util.Map;

public class CustomUserDataProvider implements UserDataProvider {

    @Override
    public void init(Router router) {

    }

    @Override
    public Map<String, String> verify(Map<String, String> postData) {
        return null;
    }
}
