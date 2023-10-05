package datn.goodboy.config;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

        User principal;
        String token;

        public JWTAuthenticationToken( String token, User principal, Collection<? extends GrantedAuthority> authorities ) {
            super( authorities );
            this.token = token;
            this.principal = principal;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return principal;
        }

        public void setToken( String token ) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
}