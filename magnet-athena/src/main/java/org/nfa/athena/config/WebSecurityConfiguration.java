package org.nfa.athena.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void init(WebSecurity web) {
		web.ignoring().antMatchers("/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
		// http.antMatcher("/**").authorizeRequests()
		// .antMatchers("/greeting/oneUserByName").permitAll()
		// .anyRequest().authenticated();
	}

}