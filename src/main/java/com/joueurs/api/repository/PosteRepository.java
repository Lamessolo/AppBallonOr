package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.Poste;

@Repository
public interface PosteRepository extends JpaRepository<Poste,Long> {

}
