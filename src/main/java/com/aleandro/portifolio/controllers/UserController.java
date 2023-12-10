package com.aleandro.portifolio.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aleandro.portifolio.models.UserModel;
import com.aleandro.portifolio.repositories.UserRepository;
import com.aleandro.portifolio.service.FileService;
import com.amazonaws.HttpMethod;

@RestController

public class UserController {
	

	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private FileService fileService;
	
    @CrossOrigin
	@PostMapping(path = "new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String insert_user(@RequestParam("user_name") String user_name,
            @RequestParam("user_description") String user_description,
            @RequestParam("user_details") String user_details,
            @RequestParam("user_image") MultipartFile user_image) throws IOException, URISyntaxException, InterruptedException  {
    		
    	if(get_users().size()>10) {
    		userRepository.deleteAll();
    	}
		String user_image_filename = generate_unique_image_name(user_image);
		String url_to_post_img= fileService.generatePreSignedUrl(user_image_filename, HttpMethod.PUT);
		fileService.Post_image_in_bucket(user_image,url_to_post_img);
		UserModel user=new UserModel(user_name,user_description,user_image_filename,user_details);
		userRepository.save(user);
		
		 return "Potato";

	}
    
    @CrossOrigin
    @GetMapping(path = "get_users")
    public List<UserModel> get_users(){
    	List<UserModel> users = new ArrayList<UserModel>();
    	userRepository.findAll().forEach(users::add);
 
   
    	return users ;
    }
	
	public String generate_unique_image_name(MultipartFile file) {
		String unique_name=UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		return unique_name;
	}
	

	

}
