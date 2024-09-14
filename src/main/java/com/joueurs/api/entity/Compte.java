package com.joueurs.api.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tbl_compte")
@AllArgsConstructor
@NoArgsConstructor
public class Compte {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	 @Column(name="name")
	    private String name;
	 
	 @Column(name="prenom")
	    private String prenom;
	    
	 @Column(name="date_naissance")
	    private Date dateNaissance;

	    @OneToMany(mappedBy = "compte")
	    private Set<Joueur> joueurs;
}
