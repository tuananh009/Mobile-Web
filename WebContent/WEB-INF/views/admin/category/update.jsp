<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/" var="rootpath" />

<jsp:include page="../layout/head.jsp" />
<div class="container">
	<jsp:include page="../layout/header.jsp" />
	<div class="row">
		<div class="col-md-3">
			<jsp:include page="../layout/menu.jsp" />
		</div>
		<div class="col-md-9">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Cập nhật sản phẩm</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<h4 class="label label-success">${message}${param.message}</h4>

			<br />
			<form:form action="${rootpath}admin/category/update"
				modelAttribute="category" method="post">
				<div class="form-group">
					<label>ID:</label>
					<form:input path="id" class="form-control"/>
				</div>
				<div class="form-group">
					<label>Tên loại:</label>
					<form:input type = "text" path="name" class="form-control" required="required"
						/>
				</div>
				<div class="form-group">
					<label>Tên hãng:</label>
					<form:input path="nameVN" class="form-control"/>
				</div>
				<div class="form-group">
					<button class="btn btn-warning"
						type="submit">Update</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<script>
	
</script>