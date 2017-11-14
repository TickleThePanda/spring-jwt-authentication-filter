package uk.co.ticklethepanda.spring.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationService {

    @Autowired
    private JwtTokenConfig jwtTokenConfig;

    private Gson gson = new GsonBuilder().create();

    public Authentication getAuthentication(HttpServletRequest servletRequest) {

        String header = servletRequest.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")) {

            String authToken = header.replace("Bearer ", "");
            DecodedJWT jwt = jwtTokenConfig.getVerifier().verify(authToken);

            String payload = new String(Base64.getDecoder().decode(jwt.getPayload()));

            JwtPayload jwtPayload = gson.fromJson(payload, JwtPayload.class);

            List<GrantedAuthority> authorities = jwtPayload.getRoles().stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(null, null, authorities);

        }

        return null;

    }

}
