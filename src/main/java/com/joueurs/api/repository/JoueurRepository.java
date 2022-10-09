package com.joueurs.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.joueurs.api.entity.Joueur;

public interface JoueurRepository extends PagingAndSortingRepository<Joueur, Long> {

}
