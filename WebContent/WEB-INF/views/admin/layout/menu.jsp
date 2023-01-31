<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<jsp:include page = "../layout/head.jsp"/>
<c:url value="/" var ="rootpath"/>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<!-- /.navbar-header -->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div> <!-- /input-group -->
				</li>				
				<c:choose>
					<c:when test="${empty sessionScope.user }"> </c:when>
					<c:otherwise>
						<li>
							<a href="${rootpath}admin/category/index"><i class="fa fa-tags fa-fw"></i> Quản lý loại sản phẩm</a>
						</li>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${empty sessionScope.user }"> </c:when>
					<c:otherwise>
						<li>
							<a href="${rootpath}admin/product/index"><i class="fa fa-inbox fa-fw"></i> Quản lý sản phẩm</a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.user }"> </c:when>
					<c:otherwise>
						<li>
							<a href="${rootpath}admin/customer/index"><i class="fa fa-users fa-fw"></i> Quản lý người dùng</a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.user }"> </c:when>
					<c:otherwise>
						<li>
							<a href="${rootpath}admin/order/index"><i class="fa fa-shopping-cart fa-fw"></i> Quản lý đơn hàng</a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.user }"> </c:when>
					<c:otherwise>
						<li><a href="#"><i class="fa fa-dashboard fa-fw"></i> Quản lý thống kê<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${rootpath}admin/inventory/index">Tồn kho theo loại</a></li>
								<li><a href="${rootpath}admin/report/revenue-by-category">Doanh thu theo loại</a></li>
								<li><a href="${rootpath}admin/report/revenue-by-customer">Doanh thu theo khách hàng</a></li>
								<li><a href="${rootpath}admin/report/revenue-by-month">Doanh thu theo tháng</a></li>
								<li><a href="${rootpath}admin/report/revenue-by-quarter">Doanh thu theo quý</a></li>
								<li><a href="${rootpath}admin/report/revenue-by-year">Doanh thu theo năm</a></li>
							</ul> <!-- /.nav-second-level -->
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>



