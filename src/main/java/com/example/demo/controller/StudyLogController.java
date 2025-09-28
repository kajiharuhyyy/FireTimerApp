package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.StudyLog;
import com.example.demo.repository.StudyLogRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudyLogController {

	private final StudyLogRepository studyLogRepository;
	
	@GetMapping("/logs")
	public String getStudyLogs(
			@RequestParam(name = "subject", required = false, defaultValue = "") String subject, 
			Model model) {
		List<StudyLog>logs = studyLogRepository.selectByNameWildcard(subject);
		model.addAttribute("studyLogs", logs);
		model.addAttribute("subject", subject);
		
		return "studylog-list";
	}
}
