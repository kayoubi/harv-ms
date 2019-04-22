package edu.microservices.springboot.securitysrv.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author khaled
 */
@Configuration
@EnableAuthorizationServer
//once we add EnableAuthorizationServer we need to define AuthorizationServerConfigurer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }
}
