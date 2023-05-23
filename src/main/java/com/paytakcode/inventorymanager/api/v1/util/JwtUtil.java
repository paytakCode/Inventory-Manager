package com.paytakcode.inventorymanager.api.v1.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON Web Token Util
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-23 오후 2:42
 */
@Slf4j
@Component
public class JwtUtil {

	@Getter
	private final String secretKey;
	private static final Long VALID_DURATION = 1000 * 60 * 60L;

	public JwtUtil(@Value("${jwt.secret}") String secretKey) {
		this.secretKey = secretKey;
	}

	public String createJwt(UserEntity userEntity) {
		Claims claims = Jwts.claims();
		claims.put("email", userEntity.getEmail());
		claims.put("name", userEntity.getName());
		claims.put("role", userEntity.getRole());
		log.info("[createJwt] userEntity: {}, secretKey: {}", userEntity, secretKey);
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + VALID_DURATION))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();

	}

	public boolean isExpired(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getExpiration()
			.before(new Date());
	}

	public String getName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("name").toString();
	}

	public String getEmail(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email").toString();
	}

	public Role getRole(String token) {
		return Role.from(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role").toString());
	}

}
