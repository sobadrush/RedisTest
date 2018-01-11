package com.ctbc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

// Link >>> http://localhost:8080/ShortURL_By_Redis/redisServlet.do
@WebServlet(name = "redisServlet", urlPatterns = { "/redisServlet.do" })
public class TestRedisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Jedis jedis = new Jedis("127.0.0.1", 6379);// 連接本機的 Redis 服務

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doGet() ===============");
		String shortenURL = request.getParameter("shortenURL");
		System.out.println(shortenURL);

		if (null == jedis.get(shortenURL)) {
			// 
			
		}else {
			// 若在Redis中找得到 【短網址】
			String longUrl = jedis.get(shortenURL);
			response.sendRedirect(longUrl);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doPost() ===============");
		doGet(request, response);
	}

}
