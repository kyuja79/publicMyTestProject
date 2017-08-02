package com.ssocio.oauth2.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor  //추가 디폴트 생성자를 만든다. 이놈이 필요한이유는 JPA 덕분 
public class AuthorizationInfo implements Serializable {
	
	private static final long serialVersionUID = -8706904083767268362L;
	String grant_type;
	String code;
	String scope;
	String client_id;
	String client_secret;
	String redirect_uri;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" grant_type=").append(grant_type).append(", code=").append(code)
				.append(", scope=").append(scope).append(", client_id=").append(client_id).append(", client_secret=")
				.append(client_secret).append(", redirect_uri=").append(redirect_uri);
		return builder.toString();
	}
}
