package com.paytakcode.inventorymanager.api.v1.util;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

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
 * @Version 0.2.0
 * @Since 2023-05-23 오후 2:42
 */
@Slf4j
@Component
public class JwtUtil {

	@Getter
	private final String secretKey;
	private final Long validDuration;

	@Getter
	private final CopyOnWriteArrayList<String> invalidatedJwtList = new CopyOnWriteArrayList<>();

	public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.duration}") Long validDuration) {
		this.secretKey = secretKey;
		this.validDuration = validDuration;
	}

	public void invalidateToken(String jwt) {
		invalidatedJwtList.add(jwt);
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

	public boolean isExpired(String jwt) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.getExpiration()
			.before(new Date());
	}

	public boolean isInvalidated(String jwt){
		return invalidatedJwtList.contains(jwt);
	}

	public Long getId(String jwt) {
		return Long.valueOf(Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.get("id")
			.toString());
	}

	public String getName(String jwt) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.get("name")
			.toString();
	}

	public String getEmail(String jwt) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.get("email")
			.toString();
	}

	public String getTel(String jwt) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.get("tel")
			.toString();
	}

	public Role getRole(String jwt) {
		return Role.from(Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody()
			.get("role")
			.toString());
	}

}
