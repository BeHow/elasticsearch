package com.behow.elasticsearch.entity;

public class CsdnBlog {

	private String author ;
	private String content;
	private String tag ;
	private String view;
	private String title;
	private String date;
	
	
	
	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public String getView() {
		return view;
	}



	public void setView(String view) {
		this.view = view;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "CsdnBlog [author=" + author + ", content=" + content + ", tag=" + tag + ", view=" + view + ", title="
				+ title + ", date=" + date + "]";
	}
	
	
}
