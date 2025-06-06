package configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
	// DIs
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfigs(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	// Filter Chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
					.csrf(customiser -> customiser.disable())
					.authorizeHttpRequests(request -> request
							.requestMatchers("/api/login", "/api/register").permitAll()
							.anyRequest().authenticated())
					
					//form login
					//.formLogin(Customizer.withDefaults()) sends all form, 
					.httpBasic(Customizer.withDefaults()) // for rest we use http basic
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.build();
	}
	
	/*// InMemory
	public UserDetailsService userDeatail() {
		UserDetails user1 = User.builder()
				.username("Vishal")
				.password("{noop}pass123")
				.roles("USER","ADMIN","MANAGER")
				.build();
		
		return new InMemoryUserDetailsManager(user1);
	}*/
	
	// DaoProvider
	@Bean // used for validating user
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
		provider.setUserDetailsService(this.userDetailsService);
		return provider;
	}
	
	// AuthManger
}
