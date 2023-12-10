package com.aleandro.portifolio.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aleandro.portifolio.models.UserModel;

public interface UserRepository  extends CrudRepository<UserModel, Integer>{
	

}
