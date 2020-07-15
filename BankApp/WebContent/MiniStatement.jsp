<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
		<title>Insert title here</title>
			<style>
				table, tr, th, td {
					border: 1px solid black;
					width: 40%;
					text-align: center;
					cellpadding="5";
					cellspacing="5";
					}
			</style>
</head>
<body>
	
		
			<table>
			<thead>	<tr bgcolor="#E6E6FA">			
					<th>Account Number</th>
					<th>Balance</th>
					<th>Transf Amount</th>
					
				</tr>
			</thead>
			<c:forEach var="s" items="${list}">
			<tbody>	
			<tr>
					<td>
						<h5>
							<c:out value="${s.getAccno()}"></c:out>
						</h5>
					</td>
					<td>
						<h5>
							<c:out value="${s.getBaccno()}"></c:out>
						</h5>
					</td>
					<td>
						<h5>
							<c:out value="${s.getTamount()}"></c:out>
						</h5>
					</td>
					
				</tr>
			</tbody>
				</c:forEach>
			</table>
		
</body>	


</html>