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
public class JoueurCreateDTO {

	private long id;

	private String name;
	
	private String prenom;
	
	private Date dateNaissance;
	
	private long poste;
	
	private int nbrPointObtenu;
	
	private int classement;
	
	private long selection;
	
	private String anneeRecompense;
	
	private long club;
	
}
