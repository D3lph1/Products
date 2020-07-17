package ru.ssau.practice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ru.ssau.practice.repository.user.UserRepository;
import ru.ssau.practice.service.auth.FailureLoginHandler;
import ru.ssau.practice.service.auth.RepositoryUserDetailsService;
import ru.ssau.practice.service.auth.SuccessLoginHandler;
import ru.ssau.practice.service.auth.SuccessLogoutHandler;
import ru.ssau.practice.service.http.ApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final int BCRYPT_ROUNDS = 4;

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/**")
    );

    private final UserRepository userRepository;

    private final SuccessLoginHandler successLoginHandler;

    private final FailureLoginHandler failureLoginHandler;

    private final SuccessLogoutHandler successLogoutHandler;

    private final ObjectMapper json;

    public SecurityConfig(
            UserRepository userRepository,
            SuccessLoginHandler successLoginHandler,
            FailureLoginHandler failureLoginHandler,
            SuccessLogoutHandler successLogoutHandler,
            ObjectMapper json
    )
    {
        this.userRepository = userRepository;
        this.successLoginHandler = successLoginHandler;
        this.failureLoginHandler = failureLoginHandler;
        this.successLogoutHandler = successLogoutHandler;
        this.json = json;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.authorizeRequests()
                .antMatchers("/.~~spring-boot!~/restart").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login").anonymous()
                .antMatchers("/signup").anonymous()

                .and()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
                .and()
                .authorizeRequests()

                .requestMatchers(PROTECTED_URLS)
                .authenticated()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successLoginHandler)
                .failureHandler(failureLoginHandler)
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(successLogoutHandler)

                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return new RepositoryUserDetailsService(repository);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(userRepository));

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider());

        return new ProviderManager(providers);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        encoderMap.put("bcrypt", new BCryptPasswordEncoder(BCRYPT_ROUNDS));

        return new DelegatingPasswordEncoder("bcrypt", encoderMap);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return (req, res, e) -> {
            res.setContentType("application/json; charset=UTF-8");
            res.setStatus(HttpStatus.FORBIDDEN.value());
            res.getWriter().write(json.writeValueAsString(ApiResponse.fail("forbidden")));
        };
    }
}
