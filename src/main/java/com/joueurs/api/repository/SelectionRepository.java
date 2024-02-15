package com.joueurs.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.joueurs.api.entity.Selection;

public interface SelectionRepository extends JpaRepository<Selection, Long> {

	
	List<Selection> findByConfederation(@Param("confederationName")String confederationName);

	Optional<Selection> findByName(@Param("selectionName")String selectionName);

	
}
