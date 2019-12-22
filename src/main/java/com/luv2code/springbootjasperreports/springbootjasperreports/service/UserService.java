package com.luv2code.springbootjasperreports.springbootjasperreports.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.luv2code.springbootjasperreports.springbootjasperreports.model.User;

@Service
public class UserService {
		
	public List<User> getUsers() {
		return getUsersList();
	}
	
	private List<User> getUsersList() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User(1, "Kiran", 29, "Kar", "kbs71190@gmail.com"));
		users.add(new User(2, "Karan", 28, "Kar", "kbs711901@gmail.com"));
		users.add(new User(3, "Pooja", 25, "Mh", "kbs711902@gmail.com"));
		users.add(new User(4, "Anu", 27, "Kar", "kbs711903@gmail.com"));
		users.add(new User(5, "Ash", 28, "Kar", "kbs711904@gmail.com"));
		return users;
	}
	
	public String exportReport(String format) throws FileNotFoundException, JRException {
		List<User> usersList = getUsers();
		String path = "/home/kiran/Documents/Jasper";
		File file = ResourceUtils.getFile("classpath:users.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(usersList);
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("luv2code", "kiran");
		
		JasperPrint jasperPr = JasperFillManager.fillReport(jasper, param, ds);
		
		if(format.equals("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPr, path+"//users.html");
		} else if (format.equals("pdf")) {
			JasperExportManager.exportReportToHtmlFile(jasperPr, path+"//users.pdf");
		}
		
		return "path: "+path;
	}
}
