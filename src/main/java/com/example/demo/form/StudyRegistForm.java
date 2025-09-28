package com.example.demo.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class StudyRegistForm {

    @NotNull(message = "必須項目です")
    @Size(max = 50, message = "50文字以内で入力してください")
    private String subject;

    private Integer studyTime;

    @Size(max = 200, message = "200文字以内で入力してください")
    private String memo;

    @NotNull(message = "開始時間を入力してください")
    private LocalDateTime startTime;

    @NotNull(message = "終了時間を入力してください")
    private LocalDateTime endTime;
}
