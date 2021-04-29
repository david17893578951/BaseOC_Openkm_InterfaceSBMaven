package com.openkm.base.controller.catalog;

import com.openkm.base.service.CatalogService;
import com.openkm.base.util.PrincipalUtils;
import com.openkm.sdk4j.bean.Document;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/catalog")
public class catalog {
	private static Logger log = LoggerFactory.getLogger(catalog.class);
	
	@Autowired
	private CatalogService catalogService;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("roles", PrincipalUtils.getRoles());
		System.out.println("hola estoy aqui1");
		try {
			List<Document> documents = catalogService.getChildren();
			System.out.println("soy documentos"+documents);
			System.out.println("soy documentos"+documents.size());
			for (Document document : documents) {
				System.out.println(document.getPath());
				System.out.println(document.NAME);
				System.out.println(document.TYPE);
				System.out.println(document.TITLE);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("hola estoy aqui2");
		return "catalog/index";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNotFound(Exception ex) {
		log.error("Not found", ex);
	}
}
