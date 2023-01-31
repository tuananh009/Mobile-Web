<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<jsp:include page="../layout/head.jsp" />
<c:url value="/" var="rootpath" />
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${rootpath}admin/home/index"><img
			style="z-index: 0; margin-top: -10px; padding: 0px 0px 0px 20px"
			src="${pageContext.request.contextPath}/<c:url value ="static"/>/images/logo_small.png"
			width="18%" /></a>
	</div>
	<!-- /.navbar-header -->
	
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
		
			<c:choose>
				<c:when test="${empty sessionScope.user }">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
						<i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
					</a>
				</c:when>			
				<c:otherwise>
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
						<i class="fa fa-user fa-fw"></i>
						<i>Xin chào: ${sessionScope.user.id}</i>					
					</a>
				</c:otherwise>	
			</c:choose>
			
			<ul class="dropdown-menu dropdown-user">
				<li><a href="${rootpath}admin/profile"><i class="fa fa-user fa-fw"></i> Thông tin cá nhân</a></li>
				<li><a href="${rootpath}admin/change"><i class="fa fa-key fa-fw"></i> Thay đổi mật khẩu</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.user }">
						<li><a href="${rootpath}admin/account/login"><i class="fa fa-sign-out fa-fw"></i> Login</a></li>
					</c:when>
					<c:otherwise>
							<li><a href="${rootpath}admin/logout"><i class="fa fa-sign-out fa-fw"></i> Đăng xuất</a></li>

					</c:otherwise>
				</c:choose>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-static-side -->
</nav>



