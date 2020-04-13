package com.ilovejava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ilovejava.entity.User;
import com.ilovejava.repository.UserRepository;
import com.ilovejava.util.CsvFileUtil;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Async
	public CompletableFuture<List<User>> saveUsers(MultipartFile file){
		
		long startTime = System.currentTimeMillis();
		List<User> users = new CsvFileUtil().parseCsvFile(file);
		logger.info("###############   saving list of users of size {} "+users.size()+" by Thread "+Thread.currentThread().getName()+" #################");
		users = userRepo.saveAll(users);
		long endTime = System.currentTimeMillis();
		logger.info("#################  Total time taken :"+endTime+" :::: "+startTime+" ########################");
		return CompletableFuture.completedFuture(users);
		
	}
	
	@Async
	public CompletableFuture<List<User>> findAllUsers() {
		logger.info(" #############  getting all users by thread  #####################"+Thread.currentThread().getName());
		List<User> users = userRepo.findAll();
		return CompletableFuture.completedFuture(users);
	}

}
