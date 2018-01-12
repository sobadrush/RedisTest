<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
		  <%--insert css here--%>
		  <%--@import url('');--%>
	</style>
	<script>
		  <%--insert JScript here--%>
		  <%--<script src=""></script>--%>
	</script>
  </head>
  <body>
  	  
  	  <h1 style="color: blue">index.jsp</h1>
  
  	  <form action="<%=request.getContextPath()%>/redisServlet.do" method="post">
	      長網址：<input type="text" name="longURL" style="width:50em;" value="http://www.runoob.com/redis/redis-java.html"/>
	      <input type="submit" value="產生短網址"/>
	      <input type="hidden" name="myAction" value="genUrl">  
  	  </form>
  	  
  	  <p/><p/>
  	  
  	  <form action="<%=request.getContextPath()%>/redisServlet.do" method="get">
<!-- 	      短網址：<input type="text" name="shortenURL" value="https://1qaz25.ptt.cc/"/><br/> -->
<%-- 	      短網址：<input type="text" name="shortenURL" value="${requestScope.generated_UrlShort}${requestScope.existedVO['shortUrl']}"/> --%>
	      短網址：<input type="text" name="shortenURL" value="${requestScope.existedVO['shortUrl']}"/>
	      <input type="submit" value="Click"/>
  	  </form>
  	  
  	  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript">
      	$(function(){
			<%if(request.getAttribute("errMsg") != null){%> 
					alert( "${requestScope.errMsg}" );
			<%}%>    
      	})
      </script>
  </body>
</html>
