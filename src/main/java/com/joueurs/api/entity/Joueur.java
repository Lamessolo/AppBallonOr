package com.joueurs.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_joueur")
@Data
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
	
	@Column(name="poste")
	private String poste;
	
	@Column(name="nbr_point_obtenu")
	private int nbrPointObtenu;
	
	@Column(name="classement")
	private int classement;
	
	@Column(name="selection")
	private String selection;
	
	@Column(name="annee")
	private String annee;
	
	@Column(name="club")
	private String club;
	

}
