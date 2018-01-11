package com.ctbc.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctbc.dao.UrlDAO;
import com.ctbc.vo.UrlMappingVO;

import redis.clients.jedis.Jedis;

// Link >>> http://localhost:8080/ShortURL_By_Redis/redisServlet.do
@WebServlet(name = "redisServlet", urlPatterns = { "/redisServlet.do" })
public class TestRedisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Jedis jedis = new Jedis("127.0.0.1", 6379);// 連接本機的 Redis 服務
	private UrlDAO urlDAO;

	@Override
	public void init() throws ServletException {
		this.urlDAO = new UrlDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doGet() ===============");
		String shortenURL = request.getParameter("shortenURL");
		System.out.println(shortenURL);

		if (jedis.get(shortenURL) != null) {
			// 若在Redis中找得到 【短網址】
			String longUrl = jedis.get(shortenURL);
			response.sendRedirect(longUrl);
			return;
		} else {

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doPost() ===============");

		if ("genUrl".equals(request.getParameter("myAction"))) {
			String urlLong = request.getParameter("longURL");
			UUID uuid = java.util.UUID.randomUUID();
			String uuidStr = uuid.toString().split("-")[0];
			String urlShort = "https://" + uuidStr + ".ptt.cc"; // <<< concate 短網址
			urlDAO.addUrlPair(new UrlMappingVO(urlShort, urlLong));// <<< 新增到DB
			
			jedis.set(urlShort, urlLong);// 設定到Redis
			
			request.setAttribute("generated_UrlShort", urlShort);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
	}

}
