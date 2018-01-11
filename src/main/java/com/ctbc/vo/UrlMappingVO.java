package com.ctbc.vo;

import java.io.Serializable;

public class UrlMappingVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String shortUrl;
	private String realUrl;
	
	public UrlMappingVO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	
}
