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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.social.config.filter.TransactionFilter;
import com.social.services.AppUserDetailsService;


//@Configurable
//@EnableWebSecurity

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:application_env.properties")
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
	@Qualifier("applicationAuthenticationSuccessHandler")
	ApplicationAuthenticationSuccessHandler applicationAuthenticationSuccessHandler;

	
	
	/**
	 * The custom Success-Handler to handle failed authentication event.
	 */
	@Autowired
	@Qualifier("applicationAuthenticationFailureHandler")
	ApplicationAuthenticationFailureHandler applicationAuthenticationFailureHandler;
	
	
	@Autowired
	@Qualifier("applicationLogOutSuccessHandler")
	ApplicationLogOutSuccessHandler applicationLogOutSuccessHandler;
	
	
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
	
	/**
	 * Rest Entry Point to secure all Un-Authorised requests
	 */
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	
	
	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()		
		.authorizeRequests()
		.antMatchers("/account/register").permitAll()
		.antMatchers("/account/login").permitAll()
		.antMatchers("/logout").permitAll()
		.antMatchers("/dashboard/getmodules").hasAnyRole("USER")
		.anyRequest().authenticated() 
		.and()
		.logout().logoutUrl("/logout").logoutSuccessHandler(codLogOutSuccessHandler)    
		.permitAll()
		 .and()
		.httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
		.csrf().disable()
		.rememberMe().rememberMeServices(rememberMeServices()); 
	}
	*/
	// This method is used for override HttpSecurity of the web Application.
		// We can specify our authorization criteria inside this method.
	
	@Override
		protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf()
	 	.disable() 
	 		 .exceptionHandling()
		     .authenticationEntryPoint(restAuthenticationEntryPoint)
		     .and()
		     .authorizeRequests() 
		     	  .antMatchers(env.getProperty("URL.LOGIN")).permitAll()
			      .antMatchers(env.getProperty("URL_REGISTER")).permitAll()
			      .antMatchers(env.getProperty("URL.LOGOUT")).permitAll()
			      .antMatchers("/test/*").permitAll()
			      .antMatchers("/dashboard/getmodules").hasAnyRole("USER")
		          .anyRequest().authenticated() 
		          .and()
	         
	         
	       
		     	.formLogin()
		     	 .loginPage(env.getProperty("URL.LOGIN")) 
		         .loginProcessingUrl(env.getProperty("URL.LOGIN"))
		         .usernameParameter("username")
		         .passwordParameter("password")
		         
		         .successHandler(applicationAuthenticationSuccessHandler)
		       
		         .failureHandler(applicationAuthenticationFailureHandler)
	         
	         .and()
	         	.logout().logoutUrl(env.getProperty("URL.LOGOUT")).logoutSuccessHandler(applicationLogOutSuccessHandler).permitAll()
	         
	        		
	         .and() 
	         	//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
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
	@Bean(name = "applicationRememberMeService")
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
	@Bean(name = "applicationPersistentTokenRepository")
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setCreateTableOnStartup(false);
		db.setDataSource(dataSource);

		return db;
	}
	

}
