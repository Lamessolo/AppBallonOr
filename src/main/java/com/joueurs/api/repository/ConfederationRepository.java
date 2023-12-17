package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.Confederation;

@Repository
public interface ConfederationRepository extends JpaRepository<Confederation, Long>{

	
}
