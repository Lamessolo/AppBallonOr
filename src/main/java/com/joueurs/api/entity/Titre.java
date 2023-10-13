package com.joueurs.api.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tbl_titre")
@AllArgsConstructor
@NoArgsConstructor
public class Titre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_titre")
	private int id ;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="confederation")
	private String confederation;
	
	@Column(name="competition")
	private String competition;
	
	@Column(name="type")
	private String type;
	
	@Column(name="anneeTitre")
	private String anneeTitre;
	
	@JsonIgnore
	@ManyToMany(mappedBy="assignedTitres")
	private List<Joueur> joueurs = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Titre [id=" + id + ", name=" + name + ", description=" + description + ", confederation="
				+ confederation + ", type=" + type + "]";
	}
	
	
}
