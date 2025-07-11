package com.test.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain setSecurityFilterChain(HttpSecurity http) throws Exception {

		// URI 한글깨짐 방지
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		http
			.csrf((csrfConfig) -> csrfConfig.disable())
			.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
					.requestMatchers("/*").permitAll()
					.anyRequest().authenticated());

		return http.build();
	}

}
