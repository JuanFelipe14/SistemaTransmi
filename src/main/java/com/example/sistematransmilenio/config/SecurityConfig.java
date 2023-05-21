package com.example.sistematransmilenio.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@ConditionalOnProperty(value = "keycloak.enabled", matchIfMissing = true)
// https://stackoverflow.com/a/51671755
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    /**
     * Registers the Keycloa    kAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/bus/save").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.POST, "/bus/add").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.DELETE, "/bus/delete/**").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.PUT, "/bus/edit").hasAnyRole("COORDINADOR","UNIVERSAL")

                .antMatchers(HttpMethod.POST, "/conductor/**").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.PUT, "/conductor/**").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.DELETE, "/conductor/**").hasAnyRole("COORDINADOR","UNIVERSAL")
                .antMatchers(HttpMethod.GET, "/conductor/**").hasAnyRole("COORDINADOR","UNIVERSAL")



                .antMatchers(HttpMethod.POST, "/ruta/add").hasAnyRole("ADMINISTRADOR","UNIVERSAL")
                .antMatchers(HttpMethod.DELETE, "/ruta/delete/**").hasAnyRole("ADMINISTRADOR","UNIVERSAL")
                .antMatchers(HttpMethod.PUT, "/ruta/edit").hasAnyRole("ADMINISTRADOR","UNIVERSAL")

                .antMatchers(HttpMethod.POST, "/estacion/add").hasAnyRole("ADMINISTRADOR","UNIVERSAL")
                .antMatchers(HttpMethod.DELETE, "/estacion/delete/**").hasAnyRole("ADMINISTRADOR","UNIVERSAL")
                .antMatchers(HttpMethod.PUT, "/estacion/edit").hasAnyRole("ADMINISTRADOR","UNIVERSAL")

                .antMatchers(HttpMethod.GET, "/horario/**").hasAnyRole("COORDINADOR","UNIVERSAL")


                .anyRequest().permitAll();
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().sameOrigin(); // To allow opening /h2
    }

}