package com.geogenie.user.service.config;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.geogenie.user.service.business.IUserService;
import com.geogenie.user.service.security.AjaxAuthenticationSuccessHandler;

/**
 *
 * The Spring Security configuration for the application - its a form login config with authentication via session cookie (once logged in),
 * with fallback to HTTP Basic for non-browser clients.
 *
 *
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSecurityConfig.class);

    @Autowired
    private IUserService userService;
    
    

   
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(
				new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		LOGGER.info("### Inside AppSecurityConfig.configure ###");
		//CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
		//http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);

		/*http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {

			@Override
			public boolean matches(HttpServletRequest request) {
				LOGGER.info("### Inside RequestMatcher.matches ###");
				LOGGER.info("### Request to Validate [ Method = "+request.getMethod() +" , URL = "+ request.getRequestURI() + " ]");
			
				String gMobileHeader = request.getHeader(MOBILE_APP_HEADER);
				LOGGER.info("Mobile APP Header :: "+gMobileHeader);
				if(gMobileHeader!=null && gMobileHeader.equalsIgnoreCase("true")){
					
					return false;
				}

				return true;
			}
		}).and(). */
		
            http.authorizeRequests()
            .antMatchers("/resources/public/**").permitAll()
            .antMatchers("/resources/img/**").permitAll()
            .antMatchers("/resources/bower_components/**").permitAll()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
            .formLogin()
            .defaultSuccessUrl("/resources/calories-tracker.html")
            .loginProcessingUrl("/authenticate")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
            .loginPage("/resources/public/login.html")
            .and()
            .httpBasic()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/resources/public/login.html")
            .permitAll();

        //if ("true".equals(System.getProperty("httpsOnly"))) {
            LOGGER.info("launching the application in HTTPS-only mode");
            http.requiresChannel().anyRequest().requiresSecure();
        //}
        
        LOGGER.info("### Done with configuring security ###");
    }
}
