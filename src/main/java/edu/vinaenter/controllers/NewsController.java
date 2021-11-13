package edu.vinaenter.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.models.News;

@Controller
@RequestMapping("news")
public class NewsController {

	@Autowired
	private ServletContext servletContext;

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

	@PostMapping("add-news")
	public String addNews(@RequestParam("picture") List<MultipartFile> list,
//			@ModelAttribute News news, // not ok
			@RequestParam String title, @RequestParam String author, @RequestParam String detail,
			@RequestParam int status) {
//		 handle upload file
		String fileName = "";
		if (list.size() > 0) {
			String dirUpload = servletContext.getRealPath("WEB-INF/resources/uploads");
			File fileUpload = new File(dirUpload);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}
			for (MultipartFile multipartFile : list) {
				fileName = multipartFile.getOriginalFilename();
				String filePath = dirUpload + File.separator + fileName;
				try {
					multipartFile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		String uuid = UUID.randomUUID().toString();
		News news = new News(uuid, title, author, new Date(), detail, fileName, status);
		listNews.add(news);

		return "redirect:/news/list";
	}
}
