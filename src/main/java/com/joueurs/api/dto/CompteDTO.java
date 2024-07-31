package com.joueurs.api.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteDTO implements Serializable {

	  private Long id;
		 	
	  private String name;
		 	
      private String prenom;

	  private Date dateNaissance;
	  
	  
	
}
