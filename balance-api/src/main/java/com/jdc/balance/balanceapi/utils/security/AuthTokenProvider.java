package com.jdc.balance.balanceapi.utils.security;

import com.jdc.balance.balanceapi.utils.exceptions.AuthTokenAccessExpiredException;
import com.jdc.balance.balanceapi.utils.exceptions.AuthTokenInvalidException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class AuthTokenProvider {

    @Value("${app.token.issuer}")
    private String issuer;
    @Value("${app.token.access-life}")
    private int accessLife;
    @Value("${app.token.refresh-life}")
    private int refreshLife;

    public enum Type {
        Access, Refresh
    }

    private static final String ROLE = "rol";
    private static final String TOKEN_TYPE = "typ";
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();

    public String generateToken(Type type, Authentication authentication) {

        var issueAt = new Date();

        return Jwts.builder()
                .subject(authentication.getName())
                .claim(TOKEN_TYPE, type.name())
                .claim(ROLE, authentication.getAuthorities().stream()
                        .map(a -> a.getAuthority())
                        .collect(Collectors.joining(","))
                )
                .issuer(issuer)
                .issuedAt(issueAt)
                .expiration(getExpiration(issueAt, type))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Authentication parseToken(Type type, String token) {
        try {
            var payload = Jwts.parser()
                    .requireIssuer(issuer)
                    .verifyWith(SECRET_KEY)
                    .build().parseSignedClaims(token).getPayload();

            var tokenType = payload.get(TOKEN_TYPE, String.class);

            if(!type.name().equals(tokenType)) {
                throw new AuthTokenInvalidException("%s token has been used for %s token".formatted(tokenType, type));
            }

            var username = payload.getSubject();
            var roleString = payload.get(ROLE, String.class);
            var authorities = Arrays.stream(roleString.split(","))
                    .map(role -> new SimpleGrantedAuthority(role))
                    .toList();

            return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
        } catch (JwtException e) {
            if(e instanceof ExpiredJwtException && type == Type.Access) {
                throw new AuthTokenAccessExpiredException("Access token is expired.", e);
            }
            throw new AuthTokenInvalidException("Invalid token", e);
        }
    }

    private Date getExpiration(Date issueAt, Type type) {
        var tokenLife = type == Type.Access ? accessLife : refreshLife;
        var calendar = Calendar.getInstance();
        calendar.setTime(issueAt);
        calendar.add(Calendar.MINUTE, tokenLife);
        return calendar.getTime();
    }

}
