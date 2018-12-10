package or.kr.project.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import or.kr.project.mvc.dao.projectDao;
import or.kr.project.word.WordBuilder;

@Controller
public class WordController {
	private WordBuilder wb;
	
	@Autowired
	private projectDao dao;
	
	public WordController() {
		wb = new WordBuilder();
	}
	
	@RequestMapping(value="/download")
	public String downloadWord(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/*Map<String, Object> map = new HashMap<>();*/
		
		wb.wordCD(/*map,*/req, resp);
		return "redirect:/";
	}
}
