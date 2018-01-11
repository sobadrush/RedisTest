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

	public UrlMappingVO(String shortUrl, String realUrl) {
		super();
		this.shortUrl = shortUrl;
		this.realUrl = realUrl;
	}
	
	public UrlMappingVO(Integer id, String shortUrl, String realUrl) {
		super();
		this.id = id;
		this.shortUrl = shortUrl;
		this.realUrl = realUrl;
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

	@Override
	public String toString() {
		return "UrlMappingVO [id=" + id + ", shortUrl=" + shortUrl + ", realUrl=" + realUrl + "]";
	}
	
}
