package edu.vinaenter.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.models.News;

@Controller
@RequestMapping("news")
public class IndexNewsController {

	@SuppressWarnings("unchecked")
	@GetMapping("list")
	public String list(Model model, HttpSession session) {
		List<News> listNews = new ArrayList<>();
		if (session.getAttribute("listNews") != null) {
			listNews = (List<News>) session.getAttribute("listNews");
		}
		model.addAttribute("datas", listNews);
		return "news/list";
	}
}
