package com.springbootwithjasperreport.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springbootwithjasperreport.service.JReportService;

@RestController
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private JReportService jReportService;

	@GetMapping("/exportdata/{id}")
	public ResponseEntity<Map<String, String>> getPdfReportById(@PathVariable("id") Integer id) throws Exception {

		byte[] pdfBytes = jReportService.getAddressById(id);

		String base64pdf = Base64.getEncoder().encodeToString(pdfBytes);

		Map<String, String> response = new HashMap<>();

		response.put("pdf", base64pdf);

		return ResponseEntity.ok(response);

	}
}
