<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<script type="text/javascript">
	function confirmDel(id) {
		var isOk = confirm('Đồng ý xóa đơn hàng có ID: ' + id);
		return isOk;
	}
</script>
<c:url value="/" var="rootpath" />
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Bảng hiển thị dữ liệu</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Khách hàng</th>
								<th>Ngày đặt hàng</th>
								<th>Số điện thoại</th>
								<th>Địa chỉ</th>
								<th>Tổng tiền</th>

								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="e" items="${list}">
								<tr class="odd gradeX">
									<td>${e.id}</td>
									<td>${e.user.fullname}</td>
									<td><f:formatDate value="${e.orderDate}"
											pattern="dd/MM/yyy" /></td>
									<td>${e.telephone}</td>
									<td>${e.address}</td>
									<td><f:formatNumber value="${e.amount}" pattern="#,###" />
										VNĐ</td>

									<td><a class="btn btn-sm btn-info"
										href="${rootpath}admin/order/edit/${e.id}">Edit</a> <a
										class="btn btn-sm btn-danger"
										onclick="return confirmDel('${e.id}');"
										href="${rootpath}admin/order/delete/${e.id}">Delete</a> <a
										class="btn btn-sm btn-primary"
										href="${rootpath}admin/order/_details/${e.id}">Details</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>