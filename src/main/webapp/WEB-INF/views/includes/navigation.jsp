<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<c:choose>
					<c:when test="${authUser eq null }">
						<c:set var="name" value="GUEST"/>
					</c:when>
					<c:when test="${authUser ne null }">
						<c:set var="name" value="${authUser.name}"/>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test='${param.menu == "main" }'>
						<li class="selected"><a href="${pageContext.servletContext.contextPath }"><c:out value="${name }"></c:out></a></li>
						<li><a href="${pageContext.servletContext.contextPath }/guestbook">방명록</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
					</c:when>
					<c:when test='${param.menu == "guestbook" }'>
						<li><a href="${pageContext.servletContext.contextPath }"><c:out value="${name }"></c:out></a></li>
						<li class="selected"><a href="${pageContext.servletContext.contextPath }/guestbook">방명록</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
					</c:when>
					<c:when test='${param.menu == "board" }'>
						<li><a href="${pageContext.servletContext.contextPath }"><c:out value="${name }"></c:out></a></li>
						<li><a href="${pageContext.servletContext.contextPath }/guestbook">방명록</a></li>
						<li class="selected"><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath }"><c:out value="${name }"></c:out></a></li>
						<li><a href="${pageContext.servletContext.contextPath }/guestbook">방명록</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
					</c:otherwise>					
				</c:choose>
			</ul>
		</div>