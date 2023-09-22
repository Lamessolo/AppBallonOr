package com.joueurs.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.Joueur;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {

	Page<Joueur> findByClubId(@Param("id") long id, PageRequest pageable);
	
	Page<Joueur> findByPosteId(@Param("id") long id, Pageable pageable);
	
	Page<Joueur> findByAnneeRecompense(@Param("byanneeRecompense")String byanneeRecompense, PageRequest pageable);
	
	Page<Joueur> findByClassement(@Param("classement")int classement,PageRequest pageable);
	
	@Query("SELECT j FROM Joueur j  WHERE " 
			+ "j.name LIKE CONCAT ('%',:name,'%')"
		    + " OR j.prenom LIKE CONCAT ('%', :name,'%')")
	Page<Joueur> SearchByNameOrPrenom(@Param("name")String motClef,PageRequest pageable);
		
			
			@Query(value= "SELECT * FROM tbl_joueur j  WHERE " 
			+ "j.name LIKE CONCAT ('%',:name,'%')"
			+ " OR j.prenom LIKE CONCAT ('%', :name,'%')", nativeQuery = true)
			List<Joueur> SearchByNameOrPrenomSQL(@Param("name")String name);


			List<Joueur> findByPosteId(@Param("id") long posteId);
			
			
			@Query("SELECT j FROM Joueur j  WHERE " 
					+ "anneeRecompense LIKE (:annee)")
			List<Joueur> searchJoueurByAnnee (@Param("annee") String annee);

	@Query("SELECT j FROM Joueur j WHERE (:posteId IS NULL OR j.poste.id = :posteId) AND (:anneeRecompense IS NULL OR j.anneeRecompense = :anneeRecompense) AND (:classement IS NULL OR j.classement = :classement)")
		Page<Joueur> findJoueurByAnneeRecompenseAndPosteIdAndClassement(@Param("posteId")Long posteId,@Param("classement")Long classement,@Param("anneeRecompense")String anneeRecompense, PageRequest pageable);
	              

			

			
			

			


		
}
