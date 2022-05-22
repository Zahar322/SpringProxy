package com.config;

import com.membrane.CustomServiceProxy;
import com.membrane.CustomTarget;
import com.predic8.membrane.core.HttpRouter;
import com.predic8.membrane.core.config.security.SSLParser;
import com.predic8.membrane.core.interceptor.LogInterceptor;
import com.predic8.membrane.core.rules.AbstractServiceProxy;
import com.predic8.membrane.core.rules.ServiceProxy;
import com.predic8.membrane.core.rules.ServiceProxyKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.util.Constants.HOST;
import static com.util.Constants.PORT;
import static com.util.Constants.SSL_ENABLED;

@Configuration
public class ProxyConfig {

    @Bean
    public Map<String, String> properties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(HOST, "api.vk.com");
        properties.put(PORT, "443");
        properties.put(SSL_ENABLED, "true");
        return properties;
    }

    @Bean
    public AbstractServiceProxy.Target target(Map<String, String> properties) {
        SSLParser sslParser = new SSLParser();
        sslParser.setIgnoreTimestampCheckFailure(true);
        return new CustomTarget(properties, sslParser);
    }

    @Bean
    public ServiceProxy serviceProxy(Map<String, String> properties, AbstractServiceProxy.Target target) {
        String hostname = "*";
        String method = "*";
        String path = ".*";
        int listenPort = 8100;
        ServiceProxyKey key = new ServiceProxyKey(hostname, method, path, listenPort);
        CustomServiceProxy customServiceProxy = new CustomServiceProxy(properties, key, target);
        customServiceProxy.getInterceptors().add(new LogInterceptor());
        return customServiceProxy;
    }

    @Bean
    public HttpRouter httpRouter(ServiceProxy serviceProxy) throws Exception {
        HttpRouter httpRouter = new HttpRouter();
        httpRouter.add(serviceProxy);
        httpRouter.init();
        return httpRouter;
    }
}
