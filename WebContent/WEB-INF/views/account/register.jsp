<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<br/>

<c:url value ="/" var="rootpath" />
<jsp:include page = "../layout/head.jsp">
	<jsp:param name="titlePage" value="Đăng ký" />
</jsp:include>
<jsp:include page ="../layout/header.jsp"/>
<jsp:include page ="../layout/menu.jsp"/>
<div class="container">
	<div class=dang-nhap>
		<h2>ĐĂNG KÝ</h2>
		<h4 class="label label-success">${message}</h4>
		<form:form action="${rootpath}account/register" modelAttribute="form"
			enctype="multipart/form-data" method="post">
			<div class="form-group">
				<label>Tên đăng nhập:</label>
				<form:input path="id" class="form-control" required="required" pattern=".{6,}" title="Tên đăng nhập phải từ 6 ký tự trở lên"/>
			</div>
			<div class="form-group">
				<label>Mật khẩu:</label>
				<form:input path="password" type="password" class="form-control" required="required" pattern=".{6,}" title="Mật khẩu phải từ 6 ký tự trở lên"/>
			</div>
			<div class="form-group">
				<label>Họ tên:</label>
				<form:input path="fullname" class="form-control" required="required" pattern=".{6,}" title="Họ tên phải từ 6 ký tự trở lên"/>
			</div>
			<div class="form-group">
				<label>Số điện thoại:</label>
				<form:input path="telephone" type="number" class="form-control" required="required"  title="Số điện thoại gồm 10 số"/>
			</div>
			<div class="form-group">
				<label>Email:</label>
				<form:input path="email" class="form-control"  required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email không hợp lệ"/>
			</div>
			<div class="form-group">
				<label>Ảnh:</label> <input type="file" accept=".jpg, .png"  name="photo_file" />
				<%-- <form:hidden path="photo" class="form-control" /> --%>
			</div>
			<button class="btn btn-success" >Đăng ký</button>
			<a class="btn btn-default" href="${rootpath}account/register"><s:message code="act.button.reset"/></a>
		</form:form>
	</div>
</div>
<br/>
<br/>
<jsp:include page ="../layout/footer.jsp"/>
<style>
.dang-nhap{
	background-color: white;
	padding: 30px 120px;
	margin: 0 auto;
	width: 60%;
}
</style>




