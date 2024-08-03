package com.springbootwithjasperreport.service;

import java.sql.Connection;
import java.util.*;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.*;


@Service
public class JReportService {

	@Autowired
	protected DataSource locaDataSource;

	public byte[] getAddressById(Integer id) throws Exception {

		JasperReport jasperReport = JasperCompileManager.compileReport(
				"D:\\Sprin Boot Projects\\Pdf Generation Using Spring Boot\\Springboot Jasper Report With Database\\src\\main\\resources\\Address.jrxml");

		Map<String, Object> parameters =new HashMap<>();
		parameters.put("id", id);
		Connection connection=locaDataSource.getConnection();		
		
		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parameters,connection);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
