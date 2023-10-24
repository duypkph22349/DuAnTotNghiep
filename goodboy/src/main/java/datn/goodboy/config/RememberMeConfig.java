// package datn.goodboy.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.AuthenticationServiceException;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.RememberMeServices;
// import org.springframework.stereotype.Component;

// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class RememberMeConfig implements RememberMeServices {
//   @Autowired
//   JwtTokenProvider jwtTokenProvider;

//   @Value("${max-age-token-cookie}")
//   private int maxAge;

//   private static final String COOKIE_NAME = "someSessionId";

//   @Override
//   public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
//     Cookie[] cookies = request.getCookies();

//     if (cookies == null) {
//       throw new AuthenticationServiceException("No cookies found");
//     }

//     Cookie sessionCookie = findCookie(cookies, COOKIE_NAME);

//     if (sessionCookie == null || sessionCookie.getValue().isEmpty()) {
//       throw new AuthenticationServiceException("Invalid Token");
//     }

//     JWTAuthenticationToken jwtAuthentication = new JWTAuthenticationToken(sessionCookie.getValue(), null, null);
//     return jwtAuthentication;
//   }

//   @Override
//   public void loginFail(HttpServletRequest request, HttpServletResponse response) {
//     Cookie sessionCookie = findCookie(request.getCookies(), COOKIE_NAME);
//     if (sessionCookie != null) {
//       sessionCookie.setMaxAge(0);
//       response.addCookie(sessionCookie);
//     }
//   }

//   @Override
//   public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
//       Authentication successfulAuthentication) {
//     String token = jwtTokenProvider.generateToken(successfulAuthentication);

//     Cookie sessionCookie = findCookie(request.getCookies(), COOKIE_NAME);
//     if (sessionCookie == null) {
//       sessionCookie = new Cookie(COOKIE_NAME, token);
//     }

//     sessionCookie.setValue(token);
//     sessionCookie.setMaxAge(maxAge);
//     sessionCookie.setPath("/");
//     response.addCookie(sessionCookie);
//   }

//   private Cookie findCookie(Cookie[] cookies, String name) {
//     if (cookies != null) {
//       for (Cookie cookie : cookies) {
//         if (name.equals(cookie.getName())) {
//           return cookie;
//         }
//       }
//     }
//     return null;
//   }
// }
