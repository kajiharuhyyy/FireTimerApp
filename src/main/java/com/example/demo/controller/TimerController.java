package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimerController {
	
	@GetMapping("/timer")
	public String showTimer(
		@RequestParam(defaultValue = "4") int sets,
		@RequestParam(defaultValue = "25") int work,
		@RequestParam(defaultValue = "5") int rest,
		Model model) {
		
		model.addAttribute("sets", sets);
		model.addAttribute("work", work);
		model.addAttribute("rest", rest);
	    return "timer";
	}
	
	@PostMapping("/reset")
	public String resetTimer() {
	    return "redirect:/timer";
	}
	
	@PostMapping("/pause")
	public String pauseTimer() {
	    return "redirect:/timer";
	}
	
	@PostMapping("/resume")
	public String resumeTimer() {
	    return "redirect:/timer";
	}

}
	