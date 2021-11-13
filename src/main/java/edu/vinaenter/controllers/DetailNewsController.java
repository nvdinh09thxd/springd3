package edu.vinaenter.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.models.News;

@Controller
@RequestMapping("news")
public class DetailNewsController {

	private static final String MSG_ERR = "ID not found!";

	@SuppressWarnings("unchecked")
	@GetMapping("detail/{id}")
	public String detail(@PathVariable String id, Model model, RedirectAttributes re, HttpSession session) {
		List<News> listNews = new ArrayList<>();
		if (session.getAttribute("listNews") != null) {
			listNews = (List<News>) session.getAttribute("listNews");
		}
		News findNews = null;
		for (News news : listNews) {
			if (id.equals(news.getId())) {
				findNews = news;
				break;
			}
		}
//		if not find news detail then redirect to the list
		if (findNews == null) {
			re.addFlashAttribute("msg", MSG_ERR);
			return "redirect:/news/list";
		}
		model.addAttribute("news", findNews);
		return "news/detail";
	}
}
