package com.joueurs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joueurs.api.entity.Joueur;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {

	
	@Query("SELECT j FROM Joueur j  WHERE " 
			+ "j.name LIKE CONCAT ('%',:name,'%')"
		    + " OR j.prenom LIKE CONCAT ('%', :name,'%')")
			List<Joueur> SearchByNameOrPrenom(@Param("name")String name);
			
			
			@Query(value= "SELECT * FROM tbl_joueur j  WHERE " 
			+ " j.name LIKE CONCAT ('%',:name,'%')"
			+ " OR j.prenom LIKE CONCAT ('%', :name,'%')", nativeQuery = true)
			List<Joueur> SearchByNameOrPrenomSQL(@Param("name")String name);
}
