package com.epam.www.auth;

import com.epam.www.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Farkas on 2017.03.04..
 */
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    private String secret = "   S0M3S3CR3T";

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public UserDTO parseToken(String token) {
        UserDTO userDTO = new UserDTO();

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            userDTO.setEmail(body.getSubject());
//            userDTO.setId(Long.parseLong((String) body.get("userId")));
//            userDTO.setRole((String) body.get("role"));

        } catch (JwtException | ClassCastException e) {
            LOGGER.error("Cannot parse jwt token. " + e.getMessage());
        }
        return userDTO;
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param userDTO the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(UserDTO userDTO) {
        Claims claims = Jwts.claims().setSubject(userDTO.getEmail());
//        claims.put("userId", u.getId() + "");
//        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
