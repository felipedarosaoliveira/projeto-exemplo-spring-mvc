package br.com.treinamento.tasks.infra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.treinamento.tasks.domain.user.User;
import br.com.treinamento.tasks.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		Map<String,String> viewModel = new HashMap<>();
		viewModel.put("email", "");
		viewModel.put("password", "");
		viewModel.put("errorMessage", "");
		model.addAttribute("viewModel", viewModel);
		return "login/index";
	}
	
	@PostMapping("/login")
	public String login (@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
		Map<String,String> viewModel = new HashMap<>();
		
		User user = service.authenticate(email,password);
		if(user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", user);
			return "redirect:/";
		}
		
		viewModel.put("email", email);
		viewModel.put("password", password);
		viewModel.put("errorMessage", "Login ou senha inválido");
		model.addAttribute("viewModel", viewModel);
		return "login/index";
		
	}
	

}
