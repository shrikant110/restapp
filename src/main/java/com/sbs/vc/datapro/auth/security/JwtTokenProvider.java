package com.sbs.vc.datapro.auth.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author Shrikant.Kushwaha
 *
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;
    
    @Value("${app.jwtExpirationRemembermeInMs}")
    private long jwtExpirationRemembermeInMs;


    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public String generateToken(Authentication authentication,boolean isRememberMe) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        long expirationInMs=jwtExpirationInMs;
        
        if(isRememberMe) {
        	expirationInMs=jwtExpirationRemembermeInMs;
        	System.out.println("jwtExpirationRemembermeInMs");
        }

        System.out.println("jwtExpiration"+isRememberMe+"<>"+expirationInMs);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public Long getTokenEpiration(boolean isRememberMe) {
    	long expirationInMs=jwtExpirationInMs;
        if(isRememberMe) {
        	expirationInMs=jwtExpirationRemembermeInMs;
        	System.out.println("jwtExpirationRemembermeInMs");
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);
        
        return expiryDate.getTime();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        }  catch (NumberFormatException ex) {
            logger.error("token is not valid.");
        }catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        } 
        return false;
    }
}
