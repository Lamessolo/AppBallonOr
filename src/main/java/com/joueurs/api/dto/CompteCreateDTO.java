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
public class CompteCreateDTO  implements Serializable {

	 private String name;	 	
     private String prenom;	    
	 private Date dateNaissance;
}
