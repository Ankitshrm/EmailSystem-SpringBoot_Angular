package com.springboot.emailsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.emailsys.models.MailRequest;
import com.springboot.emailsys.services.MailServices;

@RestController
public class MainControllers {
	
	@Autowired
	private MailServices services;

	@GetMapping("/welcome")
	public String welcome() {
		return "this is welcome page";
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody MailRequest mailRequest){
		System.out.println(mailRequest);
		boolean status= this.services.sendEmail(mailRequest.getTo(),mailRequest.getSubject(), mailRequest.getText());
		if (status) {
			return ResponseEntity.ok("done");
		}else {
			return ResponseEntity.internalServerError().body("Something went wrong!!");
		}
		
	}
}
