package com.joueurs.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectionDTO {

	private Long id;
	
	private String name;
	
	private String confederation ;

}
