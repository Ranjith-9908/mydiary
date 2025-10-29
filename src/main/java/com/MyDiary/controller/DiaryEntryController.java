package com.MyDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyDiary.entity.DiaryEntry;
import com.MyDiary.entity.User;
import com.MyDiary.service.DiaryEntryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DiaryEntryController {

	@Autowired
	private DiaryEntryService diaryEntryService;

	// ✅ Show all diary entries
	@GetMapping("/entries")
	public String viewEntries(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/login";
		}

		List<DiaryEntry> entries = diaryEntryService.getEntriesByUser(loggedInUser);
		model.addAttribute("entries", entries);
		return "entries"; // entries.jsp
	}

	// ✅ Show add new entry page
	@GetMapping("/addEntry")
	public String showAddEntryPage() {
		return "addEntry"; // addEntry.jsp
	}

	// ✅ Handle add entry form
	@PostMapping("/saveEntry")
	public String saveEntry(@RequestParam String title, @RequestParam String content, HttpSession session) {

		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/login";
		}

		diaryEntryService.addEntry(loggedInUser, title, content);
		return "redirect:/entries";
	}

	// ✅ Delete entry
	@PostMapping("/deleteEntry/{id}")
	public String deleteEntry(@PathVariable Long id) {
		diaryEntryService.deleteEntry(id);
		return "redirect:/entries";
	}

	// ✅ Show the edit page for a specific entry
	@GetMapping("/editEntry/{id}")
	public String showEditEntryPage(@PathVariable Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			System.out.println("logged user null");
			return "redirect:/login";
		}

		DiaryEntry entry = diaryEntryService.getEntryById(id);
		if (entry == null || !entry.getUser().getUserId().equals(loggedInUser.getUserId())) {
			System.out.println("logged user entryyyy null");
			return "redirect:/entries"; // prevent editing others’ entries
		}

		model.addAttribute("entry", entry);
		System.out.println("logged user entry showing");
		return "editEntry"; // renders editEntry.html
	}

	// ✅ Handle the update form submission
	@PostMapping("/updateEntry/{id}")
	public String updateEntry(@PathVariable Long id, @RequestParam String title, @RequestParam String content,
			HttpSession session) {

		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/login";
		}

		diaryEntryService.updateEntry(id, loggedInUser, title, content);
		return "redirect:/entries";
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
