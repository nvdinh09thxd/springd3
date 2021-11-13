package edu.vinaenter.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {

	@Autowired
	private ServletContext servletContext;

	@GetMapping("upload")
	public String upload() {
		return "upload";
	}

	@PostMapping("upload")
	public String upload(@RequestParam("picture") List<MultipartFile> list, Model model) {
		List<String> listFileName = new ArrayList<>();
		if (list.size() > 0) {
			String dirUpload = servletContext.getRealPath("WEB-INF/resources/uploads");
			File fileUpload = new File(dirUpload);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}
			for (MultipartFile multipartFile : list) {
				String fileName = multipartFile.getOriginalFilename();
				String filePath = dirUpload + File.separator + fileName;
				System.out.println("filePath: " + filePath);
				try {
					multipartFile.transferTo(new File(filePath));
					listFileName.add(fileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(listFileName.get(0));
		model.addAttribute("listFileName", listFileName);
		return "upload";
	}

}
