package com.ilovejava.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.tuple.component.CompositeBasedAssociationAttribute;
import org.hibernate.type.descriptor.java.CalendarTypeDescriptor.CalendarMutabilityPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ilovejava.entity.User;
import com.ilovejava.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userServece;
	
	@SuppressWarnings("rawtypes")
	@PostMapping (value="/saveUsers" , consumes= {MediaType.MULTIPART_FORM_DATA_VALUE}, produces= "application/json")
	public ResponseEntity saveUsers(@RequestParam(value="files") MultipartFile[] files){
		
		for(MultipartFile file : files) {
			userServece.saveUsers(file);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value ="/getAllUsers" , produces = "application/json")
	public CompletableFuture<ResponseEntity> findAllUsers(){
		return userServece.findAllUsers().thenApply(ResponseEntity :: ok);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value ="/getUsersByThread" , produces = "application/json")
	public ResponseEntity findUsersByThread(){
		CompletableFuture<List<User>> user1= userServece.findAllUsers();
		CompletableFuture<List<User>> user2= userServece.findAllUsers();
		CompletableFuture<List<User>> user3= userServece.findAllUsers();
		
		CompletableFuture.allOf(user1,user2,user3).join();
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
}
