package com.joueurs.api.repository;

import java.util.Date;
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
	
	Page<Joueur> findByRate(@Param("rateNbr")long byRate, PageRequest pageable);
	
	Page<Joueur> findByAnneeRecompense(@Param("year")String year, PageRequest pageable);
	
	Page<Joueur> findBySelectionId(@Param("id")long selectionId, PageRequest pageable);
	
	Page<Joueur> findByClassement(@Param("classement")long classement,PageRequest pageable);
	
	Page<Joueur> findByConfederationId(@Param("id")long ConfederationId, PageRequest pageable);
	
	Page<Joueur> findByCompteId(@Param("id")long CompteId, PageRequest pageable);
	
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

	@Query("SELECT j FROM Joueur j WHERE (:posteId = 0 OR j.poste.id = :posteId) AND (:anneeRecompense IS NULL OR j.anneeRecompense = :anneeRecompense) AND (:classement = 0  OR j.classement = :classement)")
	Page<Joueur> findJoueurByAnneeRecompenseAndPosteIdAndClassement(@Param("posteId")long posteId,@Param("classement")int classement,@Param("anneeRecompense")String anneeRecompense, PageRequest pageable);
	   
	@Query("SELECT j FROM Joueur j WHERE (:posteId = 0 OR j.poste.id = :posteId) AND (:classement = 0 OR j.classement = :classement)")
	Page<Joueur> findJoueurByPosteIdAndClassement(@Param("posteId")long posteId,@Param("classement")int classement, PageRequest pageable);

	
	@Query("SELECT j FROM Joueur j WHERE j.classement = 1")
	Page<Joueur> findVainqueurBallondOr(PageRequest pageable);

	
	@Query("SELECT j FROM Joueur j WHERE j.club.pays = :paysName")
	Page<Joueur> findByPays(String paysName, PageRequest pageable);

	
	 @Query("SELECT j FROM Joueur j WHERE MONTH(j.dateNaissance) = :month")
	Page<Joueur> findByMonthAndDay(@Param("month") int month,PageRequest pageable);
	 
	 
	 Page<Joueur> findByDateNaissance(Date dateNaissance,PageRequest pageable);
	 
	 
	 
	 // Méthode pour récupérer un liste de joueurs par leurs identifiants
	  @Query("SELECT j FROM Joueur j WHERE j.id IN :ids")
	  Page<Joueur> findByIds(@Param("ids") List<Integer> ids, PageRequest pageable);
	
	

			
			

			


		
}
