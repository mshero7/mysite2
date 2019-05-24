<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<c:if test="${authUser eq null }">
				<h4>비회원 글쓰기 (비회원은 작성후 삭제가 불가능합니다.)</h4>
				<form action="${pageContext.servletContext.contextPath }/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name" id="name" required="required"></td>
							<td>비밀번호</td><td><input type="password" name="password" id="password" required="required"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content" required="required"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
								
				</c:if>
				<c:if test="${authUser ne null }">
				<h4>회원 글쓰기</h4>
				<form action="${pageContext.servletContext.contextPath }/guestbook/add" method="post" onclick="checkform()">
					<table>
						<tr>
							<td>이름</td><td><input type="text" id="name" name="name" hidden="true" value="${authUser.name }">${authUser.name }</td>
							<td>비밀번호</td><td><input type="password" name="password" id="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>				
				
				</c:if>
				<ul>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<li>
							<table>
								<tr>
									<td>[${count - status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<c:if test="${authUser.name == vo.name }">
										<td><a href="${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no }">삭제</a></td>
									</c:if>
								</tr>
								<tr>
									<td colspan="4">${fn:replace(vo.contents, newLine, "<br>") }</td>
								</tr>
							</table>
							<br>
						</li>						
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>