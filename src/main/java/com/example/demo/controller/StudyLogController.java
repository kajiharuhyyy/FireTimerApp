package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.StudyLog;
import com.example.demo.form.StudyRegistForm;
import com.example.demo.repository.StudyLogRepository;
import com.example.demo.service.StudyLogList;

import lombok.RequiredArgsConstructor;

@Controller	
@RequiredArgsConstructor
public class StudyLogController {

	private final StudyLogRepository repository;
	private final StudyLogList service;

	// 新規登録フォーム表示
	@GetMapping("/logs/new")
	public String showCreateForm(Model model) {
	    model.addAttribute("studyRegistForm", new StudyRegistForm());
	    return "new-log";
	}

	// 新規登録処理
	@PostMapping("/logs/new")
	public String registerLog(@ModelAttribute StudyRegistForm form) {
	    StudyLog log = new StudyLog();
	    log.setStartTime(form.getStartTime());
	    log.setEndTime(form.getEndTime());
	    log.setSubject(form.getSubject());
	    log.setMemo(form.getMemo());
	    log.setCreatedAt(LocalDateTime.now());
	    
	    repository.save(log);
	    return "redirect:/logs";
	}


	// 検索フォーム表示
	@GetMapping("/logs")
	public String showSearchForm(Model model) {
	    model.addAttribute("studyRegistForm", new StudyRegistForm());
	    return "studylog-list";
	}

	// 検索実行
	@PostMapping("/logs")
	public String searchLogs(@ModelAttribute StudyRegistForm form, Model model) {
	    List<StudyLog> list = service.findBySubject(form.getSubject());
	    model.addAttribute("studyLogs", list);
	    model.addAttribute("subject", form.getSubject());
	    return "studylog-list";
	}
}
