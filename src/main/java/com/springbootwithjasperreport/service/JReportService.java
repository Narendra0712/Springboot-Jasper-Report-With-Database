package com.springbootwithjasperreport.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbootwithjasperreport.model.Address;
import com.springbootwithjasperreport.repository.AddressRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JReportService {

	@Autowired
	private AddressRepository repository;

	public byte[] generatePdfReport() throws Exception {
		List<Address> address = repository.findAll();
		// Compile the Jasper report from .jrxml to .jasper
		JasperReport jasperReport = JasperCompileManager.compileReport(
				"D:\\Sprin Boot Projects\\Pdf Generation Using Spring Boot\\Springboot Jasper Report With Database\\src\\main\\resources\\Address.jrxml");

		// Parameters for report
		Map<String, Object> parameters = new HashMap<>();
		// Add parameters as needed

		// DataSource
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);
		// Fill the report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		// Export the report to a byte array
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/*
	 * public void exportJasperReport(HttpServletResponse response) throws
	 * JRException, IOException { List<Address> address = repository.findAll();
	 * //Get file and compile it File file =
	 * ResourceUtils.getFile("classpath:Address.jrxml"); JasperReport jasperReport =
	 * JasperCompileManager.compileReport(file.getAbsolutePath());
	 * JRBeanCollectionDataSource dataSource = new
	 * JRBeanCollectionDataSource(address); Map<String, Object> parameters = new
	 * HashMap<>(); parameters.put("createdBy", "Narendra"); //Fill Jasper report
	 * JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	 * parameters, dataSource); //Export report
	 * JasperExportManager.exportReportToPdfStream(jasperPrint,response.
	 * getOutputStream()); }
	 */
}
