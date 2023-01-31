<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<c:url value ="/" var ="rootpath"/>
<jsp:include page="../layout/head.jsp" />
<div class="container">
	<jsp:include page="../layout/header.jsp" />
	<div class="row">
		<div class="col-md-3">
			<jsp:include page="../layout/menu.jsp" />
		</div>
		<div class="col-md-9">
			<!-- 			<table class="table table-hover"> -->
			<!-- 				<thead> -->
			<!-- 					<tr> -->
			<!-- 						<th>Mã SP</th> -->
			<!-- 						<th>Tên sản phẩm</th> -->
			<!-- 						<th>Giá tiền</th> -->
			<!-- 						<th>Giảm giá</th> -->
			<!-- 						<th>Số lượng</th> -->
			<!-- 						<th>Tổng tiền</th> -->
			<!-- 						<th></th> -->
			<!-- 					</tr> -->
			<!-- 				</thead> -->
			<!-- 				<tbody> -->
			<%-- 					<c:forEach var="e" items="${details}"> --%>
			<!-- 						<tr> -->
			<%-- 							<td>${e.product.id}</td> --%>
			<%-- 							<td>${e.product.name}</td> --%>
			<%-- 							<td><f:formatNumber value="${e.unitPrice}" pattern="#,###" /> --%>
			<!-- 							</td> -->
			<%-- 							<td><f:formatNumber value="${e.discount}" type="percent" /></td> --%>
			<%-- 							<td>${e.quantity}</td> --%>
			<%-- 							<td><f:formatNumber --%>
			<%-- 									value="${e.unitPrice * e.quantity * (1 - e.discount)}" --%>
			<%-- 									pattern="#,###" /></td> --%>
			<!-- 						</tr> -->
			<%-- 					</c:forEach> --%>
			<!-- 				</tbody> -->
			<!-- 			</table> -->
			<table width="100%" height ="50%">
				<c:forEach var="e" items="${details}">
					<caption>
						<h1>Chi tiết đơn hàng số ${e.product.id}</h1>
					</caption>
					<tr class ="borderRow">
						<th>&nbsp Mã Sản Phẩm</th>
						<td></td>
						<td> &nbsp ${e.product.id}</td>
					</tr>
					<tr class ="borderRow">
						<th>&nbsp Tên sản phẩm</th>
						<td></td>
						<td>&nbsp ${e.product.name}</td>
					</tr>
					<tr class ="borderRow">
						<th>&nbsp Giá tiền</th>
						<td></td>
						<td>&nbsp<f:formatNumber value="${e.unitPrice}" pattern="#,###" /></td>
					</tr>
					<tr class ="borderRow">
						<th>&nbsp Giảm giá</th>
						<td></td>
						<td>&nbsp<f:formatNumber value="${e.discount}" type="percent" /></td>
					</tr>
					<tr class ="borderRow">
						<th>&nbsp Số lượng</th>
						<td></td>
						<td>&nbsp${e.quantity}</td>
					</tr>
					<tr class ="borderRow">
						<th>&nbsp Tổng tiền</th>
						<td></td>
						<td>&nbsp<f:formatNumber
								value="${e.unitPrice * e.quantity * (1 - e.discount)}"
								pattern="#,###" /></td>
					</tr>

				</c:forEach>
			</table>
			<br/>
			<a  class="btn btn-sm btn-success"  href = "${rootpath}admin/order/index">Quay Lại</a>
		</div>
	</div>
</div>
<style>
	.borderRow{
		border: 1px solid #66ffcc;
	}
</style>
