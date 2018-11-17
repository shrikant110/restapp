package com.social.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.social.services.AppUserDetailsService;


//@Configurable
//@EnableWebSecurity

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@PropertySource("classpath:application.properties")

@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:applicationclient.properties")
})
// Modifying or overriding the default spring boot security.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	

	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	
	/**
	 * The custom Success-Handler to handle successful authentication event.
	 */
	@Autowired
	@Qualifier("codAuthenticationSuccessHandler")
	CodAuthenticationSuccessHandler codAuthenticationSuccessHandler;

	
	
	/**
	 * The custom Success-Handler to handle failed authentication event.
	 */
	@Autowired
	@Qualifier("codAuthenticationFailureHandler")
	CodAuthenticationFailureHandler codAuthenticationFailureHandler;
	
	
	@Autowired
	@Qualifier("codLogOutSuccessHandler")
	CodLogOutSuccessHandler codLogOutSuccessHandler;
	
	
	/**
	 * Data-Source used by Remember-Me Service to generate/update/delete Tokens to database table PERSISTENT_LOGINS 
	 * through a PersisitentTokenRepositoryImpl
	 */
	@Autowired
	private DataSource dataSource;
	
	
	

	// This method is for overriding the default AuthenticationManagerBuilder.
	// We can specify how the user details are kept in the application. It may
	// be in a database, LDAP or in memory.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService);
	}

	// this configuration allow the client app to access the this api 
	// all the domain that consume this api must be included in the allowed o'rings 
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins(env.getProperty("CLIENT_IPS").split(","));
	          
	        }
	    };
	}
	
	
	/**
	 * Spring Security's core Authentication Manager
	 */
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	
	
	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()		
		// starts authorizing configurations
		.authorizeRequests()
		// ignoring the guest's urls "
		.antMatchers("/account/register").permitAll()
		.antMatchers("/account/login").permitAll()
		.antMatchers("/logout").permitAll()
		// authenticate all remaining URLS
		//.antMatchers("/dashboard/**").hasAnyRole("USER")
		.antMatchers("/dashboard/getmodules").hasAnyRole("USER")
		.anyRequest().authenticated() 
		
		//.anyRequest().fullyAuthenticated()
		.and()
        /* "/logout" will log the user out by invalidating the HTTP Session,
        * cleaning up any {link rememberMe()} authentication that was configured, */
		.logout().logoutUrl("/logout").logoutSuccessHandler(codLogOutSuccessHandler)    
		.permitAll()
		//.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
        .and()
		// enabling the basic authentication
		.httpBasic().and()
		// configuring the session on the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable()
		.rememberMe().rememberMeServices(rememberMeServices()); 
	}
	
	

	/**
	 * Returns remember me Authentication provider instance
	 */
	@Bean
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		return new RememberMeAuthenticationProvider(env.getProperty("SPRINGKEY"));

	}
	
	
	/**
	 * Returns remember me Authentication filter instance
	 */
	@Bean
	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
		return new RememberMeAuthenticationFilter(authenticationManagerBean(), rememberMeServices());
	}

	
	/**
	 * Returns a persistent token based remember me service instance
	 * @return
	 */
	@Bean(name = "CodRememberMeService")
	public PersistentTokenBasedRememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(env.getProperty("SPRINGKEY"), appUserDetailsService, persistentTokenRepository());
		rememberMeServices.setAlwaysRemember(true);
		rememberMeServices.setTokenValiditySeconds(Integer.parseInt(env.getProperty("TOKEN_VALIDITY")));
		return rememberMeServices;
	}

	
	/**
	 * Persistent token repository to add/delete tokens in Database PERSISTENT_LOGINS(default) table. 
	 * @return
	 */
	@Bean(name = "codPersistentTokenRepository")
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setCreateTableOnStartup(false);
		db.setDataSource(dataSource);

		return db;
	}
	

}
