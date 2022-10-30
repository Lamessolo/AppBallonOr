package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.Joueur;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {

}
