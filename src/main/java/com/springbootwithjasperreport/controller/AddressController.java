package com.springbootwithjasperreport.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootwithjasperreport.model.Address;
import com.springbootwithjasperreport.repository.AddressRepository;
import com.springbootwithjasperreport.service.JReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressRepository repository;
	@Autowired
	private JReportService service;

	@GetMapping("/getAddress")
	public List<Address> getAddress() {
		List<Address> address = (List<Address>) repository.findAll();
		return address;
	}

	@GetMapping("/jasperpdf/export")
	public void createPDF(HttpServletResponse response) throws IOException, JRException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		service.exportJasperReport(response);
	}
}
