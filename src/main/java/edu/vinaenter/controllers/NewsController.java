package edu.vinaenter.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.models.News;

@Controller
@RequestMapping("news")
public class NewsController {

	private List<News> listNews = new ArrayList<>();

	private static final String MSG_ERR = "ID not found!";

	@SuppressWarnings("unchecked")
	@GetMapping("list")
	public String list(Model model, HttpSession session) {
		if (session.getAttribute("listNews") != null) {
			listNews = (List<News>) session.getAttribute("listNews");
		}
		model.addAttribute("datas", listNews);
		return "news/list";
	}

	@GetMapping("detail/{id}")
	public String detail(@PathVariable String id, Model model, RedirectAttributes re) {
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

	@GetMapping("add")
	public String add(Model model) {
		return "news/add";
	}

//	@PostMapping("add-news")
//	public String addNews(@ModelAttribute News news, Model model) {
//		String uuid = UUID.randomUUID().toString();
//		news.setId(uuid);
//		news.setCreateBy(new Date());
//		listNews.add(news);
//		
//		return "redirect:/news/list";
//	}
	@PostMapping("add-news")
	public String addNews(@ModelAttribute News news, @RequestParam CommonsMultipartFile file, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("");
		String filename = file.getOriginalFilename();

//		handle upload file
		try {
			byte barr[] = file.getBytes();

			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "" + filename));
			bout.write(barr);
			bout.flush();
			bout.close();

		} catch (Exception e) {
			System.out.println("ERR: " + e);
		}
		String uuid = UUID.randomUUID().toString();
		news.setId(uuid);
		news.setCreateBy(null);
		news.setPicture(filename);
		listNews.add(news);
		return "redirect:/news/list";
	}
}
