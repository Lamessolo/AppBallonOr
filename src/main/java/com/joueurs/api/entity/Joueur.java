package com.joueurs.api.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.joueurs.api.utils.JoueurHelpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name="tbl_joueur")
@AllArgsConstructor
@NoArgsConstructor
public class Joueur implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="date_naissance")
	private Date dateNaissance;
	
	@OneToOne 
	@JoinColumn(name="poste_id", referencedColumnName="id")
	private Poste poste;
	
	@Column(name="nbr_point_obtenu")
	private int nbrPointObtenu;
	
	@Column(name="classement")
	private int classement;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@OneToOne 
	@JoinColumn(name="selection_id", referencedColumnName="id")
	private Selection selection;
	
	@Column(name="annee_Recompense")
	private String anneeRecompense;
	
	@OneToOne 
	@JoinColumn(name="club_id", referencedColumnName="id")
	private Club club;
	
	public int getAge() {
		
		return JoueurHelpers.calculAgeJoueurRecompense(dateNaissance,anneeRecompense);
	}

}
