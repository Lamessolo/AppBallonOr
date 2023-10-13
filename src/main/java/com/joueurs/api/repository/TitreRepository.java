package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.Titre;

@Repository
public interface TitreRepository extends JpaRepository<Titre, Integer> {

	
}
