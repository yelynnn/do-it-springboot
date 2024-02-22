package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {

		Question q1=new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2=new Question();
		q1.setSubject("스프링 부트 모델 질문입니다.");
		q1.setContent("id는 자동으로 생성되나요?");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

		Optional<Question> oq=this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q=oq.get();

		Answer a=new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);

		for(int i=1;i<=300;i++){
			String subject=String.format("테스트 데이터입니다:[%03d]",i);
			String content="내용 없음";
			this.questionService.create(subject,content,null);
		}

	}

}
