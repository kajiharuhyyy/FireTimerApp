package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.StudyLog;

public interface StudyLogRepository{
	
	List<StudyLog> selectByNameWildcard(String subject);
	void save(StudyLog log);

}
