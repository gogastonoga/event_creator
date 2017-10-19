package com.capgemini.wolimierz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String SIGNING_KEY = "MaYzkSjmkzPC57L";//TODO change to @Value
    public static final Integer ENCODING_STRENGTH = 256;
    private static final String SECURITY_REALM = "spring-boot";


    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());//TODO
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/api-docs/**").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/events", "/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/content", "/health", "/mappings").permitAll()
                .and()
                .httpBasic()
                .realmName(SECURITY_REALM)
                .and()
                .csrf()
                .disable();*/
        http
              //  .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/api-docs/**").permitAll()
                .antMatchers("/wolimierz/**").authenticated()
                .and()
                .httpBasic()
                .realmName(SECURITY_REALM)
                .and()
                .csrf()
                .disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, "/wolimierz/events")
                .and().ignoring()
                .antMatchers(HttpMethod.GET, "/wolimierz/content", "/wolimierz/health", "/wolimierz/mappings", "/wolimierz/media**")
                .and().ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

  /*  @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "10.42.96.238:4200"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");*//* configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));*//*
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("10.42.96.238:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }*/

    /*@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/events", "/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/content", "/health", "/mappings").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/events").hasAuthority("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/content/**").hasAuthority("ADMIN")
                .anyRequest().fullyAuthenticated().and()
                .httpBasic()
*//*                .and().formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("email").permitAll()
                .and().logout().logoutUrl("/logout").deleteCookies("remember-me").permitAll()
                .and().rememberMe()*//*
                .and().csrf().disable();
    }*/

    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/
}
