package com.joueurs.api.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TitreDTO {
	
	private int id ;

	private String name;
	
	private String description;
	
	private String confederation;
	private String competition;
	
	private String type;
	
	private String anneeTitre;
}
