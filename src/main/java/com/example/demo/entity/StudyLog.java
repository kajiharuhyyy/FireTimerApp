package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyLog {

	private int id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String subject;
	private String memo;
	private LocalDateTime createdAt = LocalDateTime.now();

}
