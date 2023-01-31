<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value ="/" var="rootpath"/>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form:form action="${rootpath}admin/category/index" modelAttribute="entityCa" role="form">
							<div class="form-group">
								<label>Tên loại:</label>
								<form:input path="name" class="form-control" required="required" pattern=".{4,}" title="Tên loại phải từ 4 ký tự trở lên"/>
							</div>
							<div class="form-group">
								<label>Tên hãng:</label>
								<form:input path="nameVN" class="form-control" pattern=".{4,}" title="Tên hãng phải từ 4 ký tự trở lên"/>
							</div>
							<div class="form-group">
								<button class="btn btn-primary" formaction="${rootpath}admin/category/create">Create</button>
								<button class="btn btn-warning" formaction="${rootpath}admin/category/update">Update</button>
								<button class="btn btn-danger" formaction="${rootpath}admin/category/delete">Delete</button>
								<a class="btn btn-default" href="${rootpath}admin/category/index">Reset</a>
							</div>

						</form:form>
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>