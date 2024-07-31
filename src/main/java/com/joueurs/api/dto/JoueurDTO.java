package com.joueurs.api.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoueurDTO implements Serializable {

	private Long id;

	private String name;
	
	private String prenom;
	
	private Date dateNaissance;
	
	private String surnom;
	
	private String description;
		
	private PosteDTO poste;
	
	private String imageUrl;
	
	private String imageUrlSelection;
	
	private int nbrPointObtenu;
	
	private int rate;
	
	private int classement;
	
	private SelectionDTO selection;
	
	private CompteDTO compte;
		
	private ConfederationDTO confederation;
	
	private String anneeRecompense;
	
	private ClubDTO club;
	
	private int age;
	
}


