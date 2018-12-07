<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String data1 = "this is data1";
pageContext.setAttribute("data2", data1);
%>
<h3>data1: <%=data1%></h3>
<h3>data1: ${data1}</h3>
<h3>data2: ${data2}</h3>

<%@ page import="model.*" %>
<%@ page import="model.dao.*" %>
<%
ProductDAO productDao = new ProductDAOJdbc();
ProductBean product = productDao.findByPrimaryKey(1);
out.println("<h3>product="+product+"</h3>");

java.util.List<ProductBean> products = productDao.findAll();
out.println("<h3>products="+products+"</h3>");

CustomerDAO customerDao = new CustomerDAOJdbc();
CustomerBean customer = customerDao.findByPrimaryKey("Carol");
out.println("<h3>customer="+customer+"</h3>");

boolean update = customerDao.update(
		"EEE".getBytes(), "ellen@iii.org.tw", new java.util.Date(0), "Ellen");
out.println("<h3>update="+update+"</h3>");
%>

<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%
Context ctx = new InitialContext();
DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
Connection conn = dataSource.getConnection();
Statement stmt = conn.createStatement();
ResultSet rset = stmt.executeQuery("select * from dept");
while(rset.next()) {
	String col1 = rset.getString(1);
	String col2 = rset.getString(2);
	out.println("<h3>"+col1+", "+col2+"</h3>");
}
rset.close();
stmt.close();
conn.close();
%>

</body>
</html>