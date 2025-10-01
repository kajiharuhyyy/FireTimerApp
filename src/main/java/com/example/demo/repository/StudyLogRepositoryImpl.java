package com.example.demo.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudyLog;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudyLogRepositoryImpl implements StudyLogRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(StudyLog log) {
	    String sql = "INSERT INTO study_log (start_time, end_time, subject, memo, created_at) VALUES (?, ?, ?, ?, ?)";
	    jdbcTemplate.update(sql,	
	        log.getStartTime(),
	        log.getEndTime(),
	        log.getSubject(),	
	        log.getMemo(),
	        log.getCreatedAt()
	    );
	}
	
	@Override
	public List<StudyLog> findAll() {
	    String sql = "SELECT * FROM study_log ORDER BY start_time DESC";
	    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
	    List<StudyLog> result = new ArrayList<>();

	    for (Map<String, Object> row : list) {
	        StudyLog log = new StudyLog();
	        log.setId(((Number) row.get("id")).longValue());

	        // Timestamp → LocalDateTime（安全に変換）
	        log.setStartTime(toLocalDateTime(row.get("start_time")));
	        log.setEndTime(toLocalDateTime(row.get("end_time")));
	        log.setCreatedAt(toLocalDateTime(row.get("created_at")));

	        log.setSubject((String) row.get("subject"));
	        log.setMemo((String) row.get("memo"));
	        result.add(log);
	    }

	    return result;
	}

	private LocalDateTime toLocalDateTime(Object value) {
	    if (value instanceof Timestamp) {
	        return ((Timestamp) value).toLocalDateTime();
	    } else if (value instanceof LocalDateTime) {
	        return (LocalDateTime) value;
	    } else {
	        throw new IllegalArgumentException("変換できません: " + value);
	    }
	}






	@Override
	public List<StudyLog> selectByNameWildcard(String subject) {
        
		String sql = "SELECT * FROM study_log WHERE subject LIKE ? ORDER BY start_time DESC";

            String p = "%" + subject + "%"; //プレースホルダの値

            //　SQLで検索　（プレースホルダ：p）
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p); 

            // 値の取得→結果の格納
            List<StudyLog> result = new ArrayList<StudyLog>();
            for (Map<String, Object> row :list) {
                StudyLog log = new StudyLog();
                // 結果の初期化
    	        log.setId(((Number) row.get("id")).longValue());
    	        log.setStartTime(((Timestamp) row.get("start_time")).toLocalDateTime());
    	        log.setEndTime(((Timestamp) row.get("end_time")).toLocalDateTime());
    	        log.setSubject((String) row.get("subject"));
    	        log.setMemo((String) row.get("memo"));
    	        log.setCreatedAt(((Timestamp) row.get("created_at")).toLocalDateTime());
    	        result.add(log);
            }
                
            return result;
	}

}
