package com.joueurs.api.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.joueurs.api.entity.Poste;
import com.joueurs.api.entity.Selection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoueurDTO implements Serializable {

	private long id;

	private String name;
	
	private String prenom;
	
	private Date dateNaissance;
	
	private PosteDTO poste;
	
	private int nbrPointObtenu;
	
	private int classement;
	
	private SelectionDTO selection;
	
	private String anneeRecompense;
	
	private ClubDTO club;
}
