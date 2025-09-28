package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.StudyLog;
import com.example.demo.repository.StudyLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyLogServiceImpl implements StudyLogList {
	
	private final StudyLogRepository repository;

	@Override
	public List<StudyLog> findBySubject(String subject) {
		
		List<StudyLog> list = repository.selectByNameWildcard(subject);
		
		return list;
	}

}
