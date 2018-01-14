<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%> 
 
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>test_JSTL_sql.jsp</title>
    <style>
		table {
		    border-collapse: collapse;
		    width: 100%;
		}
		
		th, td {
		    text-align: left;
		    padding: 8px;
		}
		
		tr:nth-child(even){background-color: #f2f2f2}
		
		th {
		    background-color: #009FCC;
		    color: white;
		}
	</style>
  </head>
  <body>
      <h1>test_JSTL_sql</h1>
      <c:set var="table_dept" value="dept_TB14" scope="page"/>
      <c:set var="table_emp"  value="emp_TB14"  scope="page"/>
      <span><b>\${pageScope['table_dept']}</b> >>> <strong>${pageScope['table_dept']}</strong></span><p/>
      <span><b>\${pageScope.table_emp}    </b>    >>> <strong>${pageScope.table_emp}</strong></span>
      
      <hr style="height:20px;background:black;"/>
      
  	  <sql:setDataSource 
  	  	  var="ds" 
	  	  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
	  	  url="jdbc:sqlserver://localhost:1433;databaseName=DB_Emp_Dept" 
	  	  user="sa" 
	  	  password="sa123456"/>
  	  
  	  <sql:query dataSource="${ds}" var="result_dept">
            SELECT * FROM ${table_dept}
      </sql:query> 
      
      <sql:query dataSource="${ds}" var="result_emp" sql="SELECT * FROM ${table_emp}"></sql:query>
	
	  <table border="1">
	  	<thead>
	  	  <tr>
		    <th>deptno</th>
		    <th>dname</th>
		    <th>loc</th>
		    <th>測試default value</th>
		  </tr>
	  	</thead>
	  	<tbody>
	  	  <c:forEach var="rs" items="${result_dept.rows}">
			<tr>
				<td><c:out value="${rs.deptno}" default="empty"/></td>
				<td><c:out value="${rs.dname}"  default="empty"/></td>
				<td><c:out value="${rs.loc}"    default="empty"/></td>
				<td><c:out value="${rs.fuck}"   default="empty"/></td>
			</tr>	  	  	
	  	  </c:forEach>
	  	</tbody>
	  </table>  
	  	
	  <hr style="height:20px;background:black;"/>
	   
	  <table border="1">
	  	<thead>
	  	  <tr>
		    <th>empno</th>
		    <th>ename</th>
		    <th>job</th>
		    <th>hiredate</th>
		  </tr>
	  	</thead>
	  	<tbody>
	  	  <c:forEach var="rs" items="${result_emp.rows}">
			<tr>
				<td><c:out value="${rs.empno}"     default="empty"/></td>
				<td><c:out value="${rs.ename}"     default="empty"/></td>
				<td><c:out value="${rs.job}"       default="empty"/></td>
				<td><c:out value="${rs.hiredate}"  default="empty"/></td>
			</tr>	  	  	
	  	  </c:forEach>
	  	</tbody>
	  </table>  	
	    
  </body>
</html>



