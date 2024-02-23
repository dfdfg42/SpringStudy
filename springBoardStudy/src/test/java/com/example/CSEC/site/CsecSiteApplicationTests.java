package com.example.CSEC.site;

import com.example.CSEC.site.question.Question;
import com.example.CSEC.site.question.QuestionRepository;
import com.example.CSEC.site.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class CsecSiteApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa(){
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content);


		}
	}

}
