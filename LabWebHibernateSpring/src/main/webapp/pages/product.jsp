<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../css/main.css" />

<title>Product</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
$(document).ready(function() {
	$('input[name="id"]').blur(function() {
		$.ajax({
			method: "GET",
			url: contextPath+"/pages/products.view",
			data: "id="+$('input[name="id"]').val(),
			dataType: "json",
			cache: false,
			async: true,
			success: function(json) {
				$(".error").first().append(json[0].text);
				if(json[0].hasMoreData) {
					$("input[name='id']").val(json[1].id);
					$("input[name='name']").val(json[1].name);
					$("input[name='price']").val(json[1].price);
					$("input[name='make']").val(json[1].make);
					$("input[name='expire']").val(json[1].expire);
				}
			}
		});
	});
	$("input[name='id']").focus(function() {
		clearForm();
		$(".error").first().html("");
	});
});
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>

<h3>Welcome </h3>

<h3>Product Table</h3>

<form action="<c:url value="/pages/product.controller" />" method="post">
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="id" value="${param.id}"></td>
		<td><span class="error">${errors.id}</span></td>
	</tr>
	<tr>
		<td>Name : </td>
		<td><input type="text" name="name" value="${param.name}"></td>
		<td></td>
	</tr>
	<tr>
		<td>Price : </td>
		<td><input type="text" name="price" value="${param.price}"></td>
		<td><span class="error">${errors.price}</span></td>
	</tr>
	<tr>
		<td>Make : </td>
		<td><input type="text" name="make" value="${param.make}"></td>
		<td><span class="error">${errors.make}</span></td>
	</tr>
	<tr>
		<td>Expire : </td>
		<td><input type="text" name="expire" value="${param.expire}"></td>
		<td><span class="error">${errors.expire}</span></td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		<td>
			<input type="submit" name="prodaction" value="Delete">
			<input type="submit" name="prodaction" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>

<h3><span class="error">${errors.action}</span></h3>

<c:if test="${not empty delete}">
	<c:if test="${delete}">
		<h3>Delete product table success : 1 row deleted</h3>
	</c:if>
	<c:if test="${not delete}">
		<h3>Delete product table success : 0 row deleted</h3>
	</c:if>
	<script type="text/javascript">clearForm()</script>
</c:if>

<c:if test="${not empty insert}">
	<h3>Insert product table success</h3>
	<table border="1">
		<tr><td>Id</td><td>${insert.id}</td></tr>
		<tr><td>Name</td><td>${insert.name}</td></tr>
		<tr><td>Price</td><td>${insert.price}</td></tr>
		<tr><td>Make</td><td>${insert.make}</td></tr>
		<tr><td>Expire</td><td>${insert.expire}</td></tr>
	</table>
	<script type="text/javascript">clearForm()</script>
</c:if>

<c:if test="${not empty update}">
	<h3>Update product table success</h3>
	<table border="1">
		<tr><td>Id</td><td>${update.id}</td></tr>
		<tr><td>Name</td><td>${update.name}</td></tr>
		<tr><td>Price</td><td>${update.price}</td></tr>
		<tr><td>Make</td><td>${update.make}</td></tr>
		<tr><td>Expire</td><td>${update.expire}</td></tr>
	</table>
	<script type="text/javascript">clearForm()</script>
</c:if>

</body>
</html>