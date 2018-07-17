package com.jdpin.api.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.jdpin.api.exception.CustomServiceException;

@Service("jdPINDataService")
public class JDPINDataService {
	public static final Logger logger = Logger.getLogger(JDPINDataService.class);
	
	private final Path fileStorageLocation=Paths.get("C:/JDPower/")
            .toAbsolutePath().normalize();
	
	public Resource loadFileAsResource(String fileName) {
		logger.debug("loadFileAsResource() started for fileName:"+fileName);
		
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	logger.debug("File not found " + fileName);
                throw new CustomServiceException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
        	logger.error("File not found " + fileName);
            throw new CustomServiceException("File not found " + fileName, ex);
        }
    }
}
