package com.ssocio.oauth2.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor  //추가 디폴트 생성자를 만든다. 이놈이 필요한이유는 JPA 덕분 
public class AccessTokenInfo implements Serializable {
	private static final long serialVersionUID = 4758809932301500377L;
	String access_token;
	String token_type;
	String refresh_token;
	long expires_in;
	String scope;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" access_token=").append(access_token).append(", token_type=")
				.append(token_type).append(", refresh_token=").append(refresh_token).append(", expires_in=")
				.append(expires_in).append(", scope=").append(scope);
		return builder.toString();
	}
}
