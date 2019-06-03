<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	var form_check = false;
	
	$(function() {
		$('#email').change(function() {
			$('#check-button').show();
			$('#check-image').hide();
			form_check = false;
		});
		
		$('#check-button').click(function() {
			var email = $('#email').val();
			console.log(email);

			if (email == '') {
				return;
			}

			/* Ajax 통신!*/
			$.ajax({
				url : "/mysite2/user/api/checkemail?email=" + email,
				type : "GET",
				dataType : "json",
				data : "",
				success : function(response) {
					if (response.result != "success") {
						console.error(response.message);
						return;
					}

					if (response.data == false) {
						$('#check-button').hide();
						$('#check-image').show();
						form_check=true;
					}

					if (response.data == true) {
						alert('이미 존재하는 이메일 입니다.');
						$('#email').focus();
						$('#email').val("");

						return;
					}
				},
				error : function(xhr, error) {
					// info debug log error인지 설정가능
					console.error("error " + error);
				}
			});
			console.log(email);
		});
	});
</script>

</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="user">

				<form:form
					modelAttribute="userVo"
					id="join-form" 
					name="joinForm" 
					method="post"
					action="${pageContext.servletContext.contextPath}/user/join">
					<input type="hidden" name="a" value="join"> 
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value="">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="font-weight:bold; text-align:left; padding:0">
							<strong style="color: red"> <spring:message
									code="${errors.getFieldError( 'name' ).codes[0] }"
									text="${errors.getFieldError( 'name' ).defaultMessage }" />
							</strong>
						</c:if>
					</spring:hasBindErrors>


					<label class="block-label" for="email">이메일</label> 
						<form:input path="email" id="email"/> 
						<input type="button" id="check-button" value="중복체크"> 
						<img style="display: none" id="check-image"	src="${pageContext.servletContext.contextPath}/assets/images/check.png" />
						<p style="font-weight: bold;">
							<form:errors path="email"/>
						</p>
					<label class="block-label">패스워드</label> 
						<input name="password" type="password" value="">
						<p style="font-weight: bold;">
							<form:errors path="password"/>
						</p>
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input id="form_submit" type="submit" value="가입하기">
				</form:form>
				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp' />
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>