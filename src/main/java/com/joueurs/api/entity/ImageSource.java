package com.joueurs.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tbl_imageSource")
@NoArgsConstructor
@AllArgsConstructor
public class ImageSource {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	 @Column(name="fileName")
    private String fileName;
	 
	 @Column(name="filePath")
    private String filePath;
	

}
