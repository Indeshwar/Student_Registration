package com.web.student_register.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
@Component
public class JWTTokenHelper {
    @Value("${jwt.auth.app}")
    private String appName;

    @Value("${jwt.auth.secret_key}")
    private String secretKey;

    @Value("${jwt.auth.expires_in}")
    private int expireIn;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String generateToken(String userName) throws InvalidKeyException, NoSuchAlgorithmException {
        String token = Jwts.builder()
                .setIssuer(appName)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();

        return token;
    }

    public Date generateExpirationDate(){
        return new Date(new Date().getTime() + expireIn*1000) ;
    }

    public String getToken(HttpServletRequest request){
        String authHeader = getHeaderFromHeader(request);
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

    private String getHeaderFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header;
    }

    public Claims getAllClaimFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch(Exception e){
            claims = null;
        }

        return claims;
    }

    public String getUserNameFromToken(String token){
        String userName;
        try{
            final Claims claims = getAllClaimFromToken(token);
            userName = claims.getSubject();

        }catch(Exception e){

            userName = null;
        }
        return userName;
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUserNameFromToken(token);
        if(userName != null && userName.equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true;
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        Date expiryDate = getExpireDateFromToken(token);
        return expiryDate.before(new Date());
    }

    private Date getExpireDateFromToken(String token) {
        Date expiryDate;
        try{
            final Claims claims = getAllClaimFromToken(token);
            expiryDate = claims.getExpiration();
        }catch(Exception e){
            expiryDate = null;
        }
        return expiryDate;
    }
}
