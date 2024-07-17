package com.springbootwithjasperreport.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springbootwithjasperreport.model.Address;
import com.springbootwithjasperreport.repository.AddressRepository;
import com.springbootwithjasperreport.service.JReportService;

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
	public ResponseEntity<Map<String, String>> getReport() {
        try {
            byte[] pdfBytes = service.generatePdfReport();
            String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

            Map<String, String> response = new HashMap<>();
            response.put("pdf", base64Pdf);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	/*public void createPDF(HttpServletResponse response) throws IOException, JRException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		service.exportJasperReport(response);
	}*/
}
