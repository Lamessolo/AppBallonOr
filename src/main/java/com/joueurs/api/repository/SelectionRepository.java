package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.Selection;

public interface SelectionRepository extends JpaRepository<Selection, Long> {

}
