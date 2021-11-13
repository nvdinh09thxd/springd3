package edu.vinaenter.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
	private String id;
	private String title;
	private String author;
	private Date createBy;
	private String detail;
	private String picture;
	private int status;

}
