package com.joueurs.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTAuthResponse {

	private String accessToken ;
	
	private String tokenType= "Bearer ";
	
	
	
}
