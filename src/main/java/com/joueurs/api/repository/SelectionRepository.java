package com.joueurs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.joueurs.api.entity.Selection;

public interface SelectionRepository extends JpaRepository<Selection, Long> {

	
	Page<Selection> findByConfederation(@Param("confederation")String confederation, PageRequest pageable);

}
