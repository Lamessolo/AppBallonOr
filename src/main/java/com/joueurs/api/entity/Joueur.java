package com.joueurs.api.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="surnom")
	private String surnom;
	
	@Column(name="description", length = 1000)
	private String description;
	
	@Column(name="date_naissance")
	private Date dateNaissance;
	
	
	@ManyToOne // Remplacez @OneToOne par @ManyToOne 1...* Joueurs -> 1 poste
	@JoinColumn(name="poste_id", referencedColumnName="id", unique = false)
	private Poste poste;
	
	@Column(name="nbr_point_obtenu")
	private int nbrPointObtenu;
	
	@Column(name="rate", nullable=false, columnDefinition = "int default 1")
	private int rate;
	
	
	@Column(name="classement")
	private int classement;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="image_url_selection")
	private String imageUrlSelection;
	
	
	@ManyToOne // Remplacez @OneToOne par @ManyToOne 1...* Joueurs -> 1 Selection
	@JoinColumn(name="selection_id", unique = false)
	private Selection selection;
	
	@ManyToOne // Remplacez @OneToOne par @ManyToOne 1...* Joueurs -> 1 Confederation
	@JoinColumn(name="confederation_id", unique = false, columnDefinition = "int default 1")
	private Confederation confederation;
	
	@Column(name="annee_Recompense")
	private String anneeRecompense;
	
	
	@ManyToOne // Remplacez @OneToOne par @ManyToOne 1...* Joueurs -> 1 Club
	@JoinColumn(name="club_id", referencedColumnName="id", unique = false)
	private Club club;

	
	@ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;

	public int getAge() {
		
		return JoueurHelpers.calculAgeJoueurRecompense(dateNaissance,anneeRecompense);
	}

}
