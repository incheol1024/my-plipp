package net.plipp.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.plipp.domain.User;
import net.plipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		logger.info(userId + password);
		if (user == null) {
			return "redirect:/users/loginForm";
		}
		if (!password.equals(user.getPassword())) {
			return "redirect:/users/loginForm";
		}

		session.setAttribute("sessionedUser", user);

		return "redirect:/";
	}

	@PostMapping("")
	public String create(User user) {
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");

		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}

		User sessionedUser = (User) tempUser;

		if (!sessionedUser.getId().equals(id)) {
			throw new IllegalAccessError("꺼저라");
		}

		model.addAttribute("user", userRepository.findOne(sessionedUser.getId()));
		return "user/updateForm";
	}

	@PostMapping("/{id}")
	public String updateUser(@PathVariable Long id, User updatedUser, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");

		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}

		User sessionedUser = (User) tempUser;

		if (!sessionedUser.getId().equals(id)) {
			throw new IllegalAccessError("You can't update another user");
		}
		
		User user = userRepository.findOne(id);
		user.update(updatedUser);
		userRepository.save(user);
		return "users";
	}

}
