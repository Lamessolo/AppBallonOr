package com.joueurs.api.dto;

import java.sql.Date;
import java.util.List;

import com.joueurs.api.entity.Titre;

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
	
	private String imageUrl;
	
	private long poste;
	
	private int nbrPointObtenu;
	
	private int classement;
	
	private long selection;
	
	private String anneeRecompense;
	
	private List<Titre> titres;
	
	private long club;
	
}
