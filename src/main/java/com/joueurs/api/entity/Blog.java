package com.joueurs.api.entity;

import java.util.Date;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_blog")
@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public  int id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="description")
	public String description;
	
	@Column(name="content")
	public String content;
	
	@Column(name="image_url")
	public String imageUrl;
	
	@Column(name="url_handle")
	public String urlHandle;
	
	@Column(name="author")
	public String author;
	
	
	@Column(name="isVisible")
	public boolean isVisible;
	
	@Column(name="date_published")
	public Date publishedDate;
	
	
}
