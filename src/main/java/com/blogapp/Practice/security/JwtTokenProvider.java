package com.blogapp.Practice.security;

import com.blogapp.Practice.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    //method to create JWT TOKEN
    public String generateToken(Authentication authentication){
        String userName= authentication.getName();

        Date currentDate=new Date();
        Date expirationDate=new Date(currentDate.getTime()+jwtExpirationDate);
        String jwtToken= Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getKey())
                .compact();
        return jwtToken;
    }

    private Key getKey(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from JWT Token
    public String getUserName(String token){
       Claims claims= Jwts.parserBuilder().setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
       return claims.getSubject();


    }

    //validate jwt Token
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey())
                    .build()
                    .parse(token);
        }catch(MalformedJwtException ex){
            throw new BlogApiException(ex.getMessage());
        }catch(ExpiredJwtException ex){
            throw new BlogApiException(ex.getMessage());
        }catch(UnsupportedJwtException ex){
            throw new BlogApiException(ex.getMessage());
        }
        return true;

    }
}
