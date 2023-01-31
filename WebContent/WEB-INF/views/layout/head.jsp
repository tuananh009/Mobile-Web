<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/<c:url value ="static"/>/images/icon/favicon.ico">
<title>${param.titlePage}</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.6.3/css/all.min.css">




<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>


<link href="${pageContext.request.contextPath}/static/css/styles.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/static/js/slide_show.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/dac_biet.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/tintuc_sk.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/scoll.js">




<script>
	$(document).ready(function() {
		$(".danh_muc").click(function() {
			$(".list_danh_muc").slideToggle(200);
		});

	});
</script>



