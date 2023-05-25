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
	private final Long validDuration;

	public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.duration}") Long validDuration) {
		this.secretKey = secretKey;
		this.validDuration = validDuration;
	}

	public String createJwt(UserEntity userEntity) {
		Claims claims = Jwts.claims();
		claims.put("id", userEntity.getId());
		claims.put("email", userEntity.getEmail());
		claims.put("name", userEntity.getName());
		claims.put("tel", userEntity.getTel());
		claims.put("role", userEntity.getRole());
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + validDuration))
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

	public Long getId(String token) {
		return Long.valueOf(Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get("id")
			.toString());
	}

	public String getName(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get("name")
			.toString();
	}

	public String getEmail(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get("email")
			.toString();
	}

	public String getTel(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get("tel")
			.toString();
	}

	public Role getRole(String token) {
		return Role.from(Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get("role")
			.toString());
	}

}
