package com.aleandro.portifolio.models;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleandro.portifolio.service.FileService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(nullable = false,length = 150)
	private String nome;
	@Column(nullable = false,length = 300)
	private String description;
	@Column(nullable = true)
	private String image;
	@Column(nullable = false,length = 300)
	private String details;
	


	public UserModel(String nome, String description, String image, String details) {
		super();
		this.nome = nome;
		this.description = description;
		if (!image.isBlank() && image!=null){
		this.image = image;
		}else {
			this.image=null;
		}
		this.details = details;
	}


	public UserModel() {
		super();
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	@Override
	public String toString() {
		return "UserModel [id=" + id + ", nome=" + nome + ", description=" + description + ", image=" + image
				+ ", details=" + details + "]";
	} 

}
