package com.joueurs.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.Compte;


@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
	
	
	Optional<Compte> findByNameAndPrenom(String name, String prenom);

}
