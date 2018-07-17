package com.jdpin.api.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdpin.api.domain.Country;

@RestController
public class JDPINController {
	public static final Logger logger = Logger.getLogger(JDPINController.class);
	private static final int BUFFER_SIZE = 4096;

	private String filePath = "C:/JDPower/JDPIN.txt";

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> getCountries() {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();
		return new ResponseEntity<List<Country>>(listOfCountries, HttpStatus.OK);
	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public Country getCountryById(@PathVariable int id) {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();

		for (Country country : listOfCountries) {
			if (country.getId() == id)
				return country;
		}

		return null;
	}

	@RequestMapping(value = "/downloadFileNotWorking", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
			HttpServletRequest request) {
		// Load file as Resource
		Resource resource = null;

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(
					resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			// logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + resource.getFilename()
								+ "\"").body(resource);
	}

	@RequestMapping(value = "/textOutput", produces = MediaType.TEXT_PLAIN_VALUE)
	public String heartbeat2() {
		 logger.debug("heartbeat2 started()");
		return "JDPower|PIN|Service";
	}

	// Utiliy method to create country list.
	public List<Country> createCountryList() {
		Country indiaCountry = new Country(1, "India");
		Country chinaCountry = new Country(4, "China");
		Country nepalCountry = new Country(3, "Nepal");
		Country bhutanCountry = new Country(2, "Bhutan");

		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}
	
	public void writeRecordsIntoFile() throws IOException{
		File fout = new File(filePath);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("ID|SQUISH_VIN");
		bw.newLine();
		for (int i = 0; i < 10; i++) {
			bw.write(i+"|Vin"+i);
			bw.newLine();
		}
	 
		bw.close();
	}

	public void writeIntoFile() {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = "This is the content to write into file\n";

			fw = new FileWriter(filePath);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response) throws IOException {
		logger.debug("downloadFile() started");
		// construct the complete absolute path of the file
		writeRecordsIntoFile();
		String fullPath = filePath;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = "application/octet-stream";
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}
}
