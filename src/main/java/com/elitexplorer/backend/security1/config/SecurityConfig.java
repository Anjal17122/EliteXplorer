package com.elitexplorer.backend.security1.config;

import com.elitexplorer.backend.security1.method.JWTAuthenticationFilter;
import com.elitexplorer.backend.security1.method.JWTAuthorizationFilter;
import com.elitexplorer.backend.security1.service.MyUserDetailsService;
import com.elitexplorer.backend.security1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private TokenService tokenService;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/login").permitAll().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), tokenService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), tokenService));
		// http.cors().configurationSource(request -> new
		// CorsConfiguration().applyPermitDefaultValues());

//		http.headers().frameOptions().sameOrigin();
		http
				// ...
				.headers()
				.addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy","frame-ancestors https://*.addon.com.np http://localhost:5000 https://*.gov.np"));

//		http
//				// ...
//				.headers()
//				.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.ALLOW_FROM));
	}

	/*
	 * @Bean public BCryptPasswordEncoder passwordEncoder() { return
	 * bCryptPasswordEncoder;
	 * }
	 */
}
