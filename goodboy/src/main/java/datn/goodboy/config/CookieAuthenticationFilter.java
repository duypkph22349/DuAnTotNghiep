// package datn.goodboy.config;

// import java.io.IOException;

// import org.springframework.security.authentication.AuthenticationServiceException;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.RequestMatcher;
// import org.springframework.util.StringUtils;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class CookieAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

//   public CookieAuthenticationFilter(RequestMatcher requestMatcher) {
//     super(requestMatcher);
//     setAuthenticationManager(super.getAuthenticationManager());
//   }

//   @Override
//   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//       throws AuthenticationException, IOException, ServletException {

//     String token = "";

//     // get token from a Cookie
//     Cookie[] cookies = request.getCookies();

//     if (cookies == null || cookies.length < 1) {
//       throw new AuthenticationServiceException("Invalid Token");
//     }

//     Cookie sessionCookie = null;
//     for (Cookie cookie : cookies) {
//       if (("someSessionId").equals(cookie.getName())) {
//         sessionCookie = cookie;
//         break;
//       }
//     }

//     // TODO: move the cookie validation into a private method
//     if (sessionCookie == null || sessionCookie.getValue().isEmpty()) {
//       throw new AuthenticationServiceException("Invalid Token");
//     }
//     JWTAuthenticationToken jwtAuthentication = new JWTAuthenticationToken(sessionCookie.getValue(), null, null);
//     return jwtAuthentication;
//   }

//   @Override
//   public void doFilter(ServletRequest req, ServletResponse res,
//       FilterChain chain) throws IOException, ServletException {
//     super.doFilter(req, res, chain);
//   }
// }
