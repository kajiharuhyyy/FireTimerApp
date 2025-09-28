package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.StudyLog;

public interface StudyLogList {
	
	List<StudyLog> findBySubject(String subject);

}
