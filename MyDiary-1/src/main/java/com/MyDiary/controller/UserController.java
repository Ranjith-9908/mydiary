package com.MyDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyDiary.entity.User;
import com.MyDiary.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String showLoginPage1() {
		System.out.println("helllo  page opened");

		return "login"; //
	}

	// ✅ Show login page
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

		User user = userService.validateLogin(email, password);
		if (user != null) {
			session.setAttribute("loggedInUser", user); // ✅ Store user in session
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}
	}

	// ✅ Show registration page
	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "register"; //
	}

	// ✅ Handle registration form
	@PostMapping("/registerUser")
	public String registerUser(User user, Model model) {
		try {
			userService.registerUser(user);
			model.addAttribute("message", "Registration successful! Please login.");
			return "login";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}
	}

	// ✅ Handle login
	@PostMapping("/loginUser")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		User user = userService.validateLogin(username, password);

		if (user != null) {
			session.setAttribute("loggedInUser", user);
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Invalid username or password!");
			return "login";
		}
	}

	// ✅ Show home page (after login)
	@GetMapping("/home")
	public String homePage(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", loggedInUser);
		return "home"; //
	}

	// ✅ Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("logout page ");
		session.invalidate();
		return "redirect:/";
	}
}


//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.MyDiary.entity.DiaryEntry;
//import com.MyDiary.entity.User;
//import com.MyDiary.service.DiaryEntryService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class DiaryEntryController {
//
//	@Autowired
//	private DiaryEntryService diaryEntryService;
//
//	// ✅ Show all diary entries
//	@GetMapping("/entries")
//	public String viewEntries(HttpSession session, Model model) {
//		User loggedInUser = (User) session.getAttribute("loggedInUser");
//		if (loggedInUser == null) {
//			return "redirect:/login";
//		}
//
//		List<DiaryEntry> entries = diaryEntryService.getEntriesByUser(loggedInUser);
//		model.addAttribute("entries", entries);
//		return "entries"; // entries.jsp
//	}
//
//	// ✅ Show add new entry page
//	@GetMapping("/addEntry")
//	public String showAddEntryPage() {
//		return "addEntry"; // addEntry.jsp
//	}
//
//	// ✅ Handle add entry form
//	@PostMapping("/saveEntry")
//	public String saveEntry(@RequestParam String title, @RequestParam String content, HttpSession session) {
//
//		User loggedInUser = (User) session.getAttribute("loggedInUser");
//		if (loggedInUser == null) {
//			return "redirect:/login";
//		}
//
//		diaryEntryService.addEntry(loggedInUser, title, content);
//		return "redirect:/entries";
//	}
//
//	// ✅ Delete entry
//	@PostMapping("/deleteEntry/{id}")
//	public String deleteEntry(@PathVariable Long id) {
//		diaryEntryService.deleteEntry(id);
//		return "redirect:/entries";
//	}
//
//	// ✅ Show the edit page for a specific entry
//	@GetMapping("/editEntry/{id}")
//	public String showEditEntryPage(@PathVariable Long id, Model model, HttpSession session) {
//		User loggedInUser = (User) session.getAttribute("loggedInUser");
//		if (loggedInUser == null) {
//			System.out.println("logged user null");
//			return "redirect:/login";
//		}
//
//		DiaryEntry entry = diaryEntryService.getEntryById(id);
//		if (entry == null || !entry.getUser().getUserId().equals(loggedInUser.getUserId())) {
//			System.out.println("logged user entryyyy null");
//			return "redirect:/entries"; // prevent editing others’ entries
//		}
//
//		model.addAttribute("entry", entry);
//		System.out.println("logged user entry showing");
//		return "editEntry"; // renders editEntry.html
//	}
//
//	// ✅ Handle the update form submission
//	@PostMapping("/updateEntry/{id}")
//	public String updateEntry(@PathVariable Long id, @RequestParam String title, @RequestParam String content,
//			HttpSession session) {
//
//		User loggedInUser = (User) session.getAttribute("loggedInUser");
//		if (loggedInUser == null) {
//			return "redirect:/login";
//		}
//
//		diaryEntryService.updateEntry(id, loggedInUser, title, content);
//		return "redirect:/entries";
//	}
