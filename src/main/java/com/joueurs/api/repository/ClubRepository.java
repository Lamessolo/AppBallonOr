package com.joueurs.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joueurs.api.entity.Club;
import com.joueurs.api.entity.Joueur;

public interface ClubRepository extends JpaRepository<Club, Long> {

	@Query("SELECT c FROM Club c WHERE (:pays IS NULL OR c.pays = :pays)")
	Page<Club> findByPays(@Param("pays") String pays,PageRequest pageable);
	
	@Query("SELECT c FROM Club c WHERE (:pays IS NULL OR c.pays = :pays)")
	List<Club> findByPays(@Param("pays") String pays);
	
	@Query("SELECT c FROM Club c  WHERE " 
			+ "c.name LIKE CONCAT ('%',:term,'%')")
	List<Club> searchByName(@Param("term")String term);
}
