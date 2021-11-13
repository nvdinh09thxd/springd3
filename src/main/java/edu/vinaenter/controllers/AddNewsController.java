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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.vinaenter.models.News;

@Controller
@RequestMapping("news")
public class AddNewsController {

	@Autowired
	private ServletContext servletContext;

	@GetMapping("add")
	public String add(Model model) {
		return "news/add";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("add-news")
	public String addNews(@RequestParam("picture") List<MultipartFile> list,
//			@ModelAttribute News news, // not ok
			@RequestParam String title, @RequestParam String author, @RequestParam String detail,
			@RequestParam int status, HttpSession session) {
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
		List<News> listNews = new ArrayList<>();
		String uuid = UUID.randomUUID().toString();
		News news = new News(uuid, title, author, new Date(), detail, fileName, status);
		if (session.getAttribute("listNews") != null) {
			listNews = (List<News>) session.getAttribute("listNews");
		}
		listNews.add(news);
		if (session.getAttribute("listNews") == null) {
			session.setAttribute("listNews", listNews);
		}

		return "redirect:/news/list";
	}
}
