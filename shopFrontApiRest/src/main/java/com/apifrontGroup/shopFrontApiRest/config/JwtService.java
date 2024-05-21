package com.apifrontGroup.shopFrontApiRest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="a8Tst54Ye8y9vijR3Vcwf4P0hIU3W846r0kTjyiLHPsf7QAPwNWk3VC1gLGmM3f5";
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object>extraClaims,UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+ 1000L *60*60*24*365)).
                signWith(getSingKey(), SignatureAlgorithm.HS256).compact();
    }
    public String getUserName(String token){
        System.out.println("llegue aca " + "to " );

        try {
            return getClaim(token, Claims::getSubject);
        } catch (Exception e) {
            System.err.println("Error al obtener el subject del token: " + e.getMessage());
            return null; // O toma otra acción según tus necesidades
        }
    }
    public <T> T getClaim(String token, Function<Claims,T>claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaims (String token ){
        return Jwts.
                parserBuilder().
                setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token).
                getBody();
    }

    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = getUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
