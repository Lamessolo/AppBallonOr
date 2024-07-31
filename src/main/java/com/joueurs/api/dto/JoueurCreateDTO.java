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

	private String name;
	
	private String prenom;
	
	private Date dateNaissance;
	
	private String surnom;
	
	private String description;
	
	private String anneeRecompense;
	
	private String imageUrl;
	
	private String imageUrlSelection;
	
	private long poste;
	
	private int nbrPointObtenu;
	
	private int classement;
	
	private int rate;
	
	private long compte;
	
	private long selection;
	
	private long confederation;
	
	private long club;
	
}
