<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value = "/" var ="rootpath"/>
<br/>
<jsp:include page = "../layout/head.jsp">
	<jsp:param name="titlePage" value="Quên mật khẩu" />
</jsp:include>/>
<jsp:include page ="../layout/header.jsp"/>
<jsp:include page ="../layout/menu.jsp"/>
<div class="container">
	<div class=dang-nhap>
		<h2>QUÊN MẬT KHẨU</h2>
		<h4 class="label label-success">${message}</h4>
		<form action="${rootpath}account/forgot" method="post">
			<div class="form-group">
				<label>Tên tài khoản:</label> <input name="id" class="form-control" />
			</div>
			<div class="form-group">
				<label>Email:</label> <input name="email" class="form-control" />
			</div>
			<div class="form-group">
				<button class="btn btn-success">Gửi thông tin</button>
			</div>
		</form>
	</div>
</div>
<br/>
<br/>

<style>
.dang-nhap{
	background-color: white;
	padding: 30px 120px;
	margin: 0 auto;
	width: 60%;
}
</style>
<jsp:include page ="../layout/footer.jsp"/>
