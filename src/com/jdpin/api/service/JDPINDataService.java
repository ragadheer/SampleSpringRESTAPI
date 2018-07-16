package com.jdpin.api.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.jdpin.api.exception.CustomServiceException;

@Service("jdPINDataService")
public class JDPINDataService {

	private final Path fileStorageLocation=Paths.get("C:/JDPower/")
            .toAbsolutePath().normalize();
	
	public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new CustomServiceException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new CustomServiceException("File not found " + fileName, ex);
        }
    }
}
