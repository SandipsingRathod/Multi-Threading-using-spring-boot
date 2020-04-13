package com.ilovejava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ilovejava.entity.User;

public class CsvFileUtil {
	
	Logger logger = LoggerFactory.getLogger(CsvFileUtil.class);
	
	public List<User> parseCsvFile(MultipartFile file){
		List<User> usersList = new ArrayList<User>();
		
		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			
			String line;
			while ((line = br.readLine()) != null) {
				final String[] data = line.split(",");
				User user = new User();
				user.setName(data[0]);
				user.setEmail(data[1]);
				user.setGender(data[2]);
				usersList.add(user);
		     }
			return usersList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("###################  file is not parsed  ################");
			e.printStackTrace();
		}
		return usersList;
		
		
	}
}
