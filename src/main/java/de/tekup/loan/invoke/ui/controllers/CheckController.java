package de.tekup.loan.invoke.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.loan.invoke.client.SoapClient;

import de.tekup.loan.soap.api.consume.loaneligebilty.StudentRequest;
import de.tekup.loan.soap.api.consume.loaneligebilty.WhiteTestResponse;

@Controller
public class CheckController {
	@Autowired
	private SoapClient client;
	
	@GetMapping("/check/student")
	public String checkForm(Model model) {
		StudentRequest request = new StudentRequest();
		//request.setCibilScore(500);
		model.addAttribute("request", request);
		return "request";
	}
	
	@PostMapping("/check/student")
	public String LaonEligebel(@ModelAttribute("request") StudentRequest request,
			Model model) {
		WhiteTestResponse response = client.getStudent(request);
		model.addAttribute("response",response);
		return "response";
	}

}
