<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${authUser eq null }">
		<c:redirect url="/board"></c:redirect>
	</c:if>
	<div id="container">

		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/write">
					<input type = "hidden" name = "a" value="write">
					<input type="text" name="user_no" value="${authUser.no }" hidden="true"/>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목${vo.depth }</td>
							<td><input type="text" id="title" name="title"></td>
						</tr>
						<tr>
							<td>
								<textarea id="contents" name="contents"></textarea>
							</td>
							<td class="label" hidden="true">
								<input type="text" id="depth" name="depth" value="${vo.depth+1}">
								
							</td>
							<td class="label" hidden="true">
								<input type="text" id="group_no" name="group_no" value="${vo.group_no }">
							</td>
							<td class="label" hidden="true">
								<input type="text" id="order_no" name="order_no" value="${vo.order_no }">
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>