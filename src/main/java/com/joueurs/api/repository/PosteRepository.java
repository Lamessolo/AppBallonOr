package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.Poste;

public interface PosteRepository extends JpaRepository<Poste, Long> {

}
