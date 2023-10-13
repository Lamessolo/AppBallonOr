package com.joueurs.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="surnom")
	private String surnom;
	
	@Column(name="description")
	private String description;
	
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

	@ManyToMany
	@JoinTable(name="joueur_titre",
	 joinColumns = @JoinColumn(name="joueur_id"),
	 inverseJoinColumns = @JoinColumn (name="titre_id"))
	private Set<Titre> assignedTitres = new HashSet<>();
	
	

	public int getAge() {
		
		return JoueurHelpers.calculAgeJoueurRecompense(dateNaissance,anneeRecompense);
	}

}
