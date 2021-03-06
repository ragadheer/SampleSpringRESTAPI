package com.jdpin.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdpin.api.security.JDPINUserPrincipal;
import com.jdpin.api.security.User;
import com.jdpin.api.service.JDPINDataService;

@RestController
@RequestMapping(value = "/jdpin")
public class FileDownloadController {
	public static final Logger logger = Logger.getLogger(FileDownloadController.class);
	@Autowired
    private JDPINDataService jdPINDataService;

	@RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JDPINUserPrincipal principal =(JDPINUserPrincipal) auth.getPrincipal();
		User user = principal.getUser();
		logger.debug("downloadFile() started");
		// Load file as Resource
        Resource resource = jdPINDataService.loadFileAsResource("JDPIN.txt");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
          //  logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
