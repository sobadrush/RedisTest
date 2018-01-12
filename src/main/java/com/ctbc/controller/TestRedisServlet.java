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

import _01_GlobalParam.GlobalParam;
import redis.clients.jedis.Jedis;

// Link >>> http://localhost:8080/ShortURL_By_Redis/redisServlet.do
@WebServlet(name = "redisServlet", urlPatterns = { "/redisServlet.do" })
public class TestRedisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private static Jedis jedis = new Jedis("127.0.0.1", 6379);// 連接本機的 Redis 服務
	private static Jedis jedis = new Jedis(GlobalParam.HOST_IP, GlobalParam.HOST_PORT);// 連接本機的 Redis 服務
	private UrlDAO urlDAO;

	@Override
	public void init() throws ServletException {
		this.urlDAO = new UrlDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doGet() ===============");
		request.removeAttribute("errMsg");
		String shortenURL = request.getParameter("shortenURL").trim();
		
		if (jedis.get(shortenURL) != null) {
			// 若在Redis中找得到 【短網址】
			String longUrl = jedis.get(shortenURL);
			response.sendRedirect(longUrl);
			return;
		} else {
			request.setAttribute("errMsg", "短網址不存在，請輸入長網址產生！");
			request.getRequestDispatcher("/index.jsp").forward(request, response);// 將產生的短網址傳給index.jsp
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=============== redisServlet : doPost() ===============");

		if ("genUrl".equals(request.getParameter("myAction"))) {
			String urlLong = request.getParameter("longURL");

			int count = urlDAO.getNumOfLongUrl(urlLong);

			if (count <= 0 /*DB中不存在長網址，產生短網址*/) {
				UUID uuid = java.util.UUID.randomUUID();
				String uuidStr = uuid.toString().split("-")[0];
				String urlShort = "https://" + uuidStr + ".ptt.cc"; // <<< concate 短網址
				urlDAO.addUrlPair(new UrlMappingVO(urlShort, urlLong));// <<< 新增到DB

				jedis.set(urlShort, urlLong);// <<<<<< 設定到Redis緩存

				request.setAttribute("generated_UrlShort", urlShort);
				request.getRequestDispatcher("/index.jsp").forward(request, response);// 將產生的短網址傳給index.jsp
				return;
			} else {
				UrlMappingVO vo = urlDAO.getShortUrl(urlLong);
				request.setAttribute("existedVO", vo);
				request.getRequestDispatcher("/index.jsp").forward(request, response);// 將產生的短網址傳給index.jsp
				return;
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("............ 執行finalize() ..............");
		jedis = null;
	}

}
