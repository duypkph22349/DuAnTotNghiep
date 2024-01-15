package datn.goodboy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import datn.goodboy.security.repo.AccountInforRepository;
import datn.goodboy.security.repo.EmployeeInfoRepository;
import datn.goodboy.security.service.AccountInfoService;
import datn.goodboy.security.service.EmployeInfoService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SpringSecurityConfig {

  @Value("${max-login-token-time}")
  private int maxAge;

  @Bean
  AuthenticationManager authenticationManager() {
    List<AuthenticationProvider> listProviders = new ArrayList<>();
    listProviders.add(authenticationNVProvider());
    listProviders.add(authenticationKHProvider());
    ProviderManager providerManagers = new ProviderManager(listProviders);
    return providerManagers;
  }

  @Autowired
  EmployeeInfoRepository nvifrepository;

  EmployeInfoService nhanVienServer() {
    return new EmployeInfoService(nvifrepository);
  }

  @Bean
  AuthenticationProvider authenticationNVProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(nhanVienServer());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Autowired
  AccountInforRepository khifrepository;

  AccountInfoService KhachHangServer() {
    return new AccountInfoService(khifrepository);
  }

  @Bean
  JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  AuthenticationProvider authenticationKHProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(KhachHangServer());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  // mã hóa mật khẩu
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

  @Bean
  AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
      throws Exception {
    http
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/sendresetpasswordcode", "/resetpasswordcode", "/resetpassword", "/test/**")
              .permitAll();
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/shop/product/**").permitAll();
        })
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/sendvertifyemail", "/vertifyemail").authenticated();
        })
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/user/passwordchange", "/user/change_password","/shop/profile").hasAnyAuthority("USER");
        })
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/admin/*/delete/**").hasAnyAuthority("ADMIN");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/admin/**").hasAnyAuthority("STAFF", "ADMIN");
        })
        // .authorizeHttpRequests((authorize) -> {
        // authorize.requestMatchers("/shop/**").hasAnyAuthority("USER");
        // })
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().permitAll();
        })
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/singin")
            .failureUrl("/login-fail")
            .successHandler(new CustomAuthenticationSuccessHandler(KhachHangServer(), nhanVienServer()))
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll())
        .logout(
            formLogin -> formLogin
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll())
        .rememberMe((remember) -> remember.key("fefe").tokenValiditySeconds(maxAge)
            .rememberMeCookieName("remember-token")
            .userDetailsService(KhachHangServer()))
        .rememberMe((remember) -> remember.key("faewfaewf").tokenValiditySeconds(maxAge)
            .rememberMeCookieName("remember-admin-token")
            .userDetailsService(nhanVienServer()))
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling((exceptionHandling) -> {
          exceptionHandling.accessDeniedHandler(accessDeniedHandler());
        })
        .httpBasic(Customizer.withDefaults())
        .authenticationManager(authManager);
    return http.build();
  }

}
