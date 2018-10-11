package net.plipp.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	
	@GetMapping("/welcometo")
	public String welcome(Model model) {

		model.addAttribute("name", "hwang");
		model.addAttribute("value", "1000000000000");
		model.addAttribute("in_ca", true);
		model.addAttribute("taxed_value", 1000);
		
		List<MyUser> userList = Arrays.asList(new MyUser("juno"), new MyUser("mars"));
		//model.addAttribute("userList", userList);
		
		//model.addAttribute("person", new MyUser("sun").name);
		 
		return "welcome";
	}
}
