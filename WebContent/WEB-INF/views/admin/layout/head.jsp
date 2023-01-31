<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/<c:url value ="static"/>/images/icon/favicon.ico">
<title>Trang Quản Trị Happy Shop</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="//cdn.ckeditor.com/4.15.1/basic/ckeditor.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>




<link
	href="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.min.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/static/jqueryui/jquery-ui.min.js"></script>

<link
	href="${pageContext.request.contextPath}/static/css/styles.admin.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/Login/css/main.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/Login/css/util.css"
	rel="stylesheet" />
<%-- <script
	src="${pageContext.request.contextPath}/static/js/estore.admin.js"></script> --%>
<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/static/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/static/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath}/static/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<!-- <link -->
<%-- 	href="${pageContext.request.contextPath}/static/bower_components/datatables-responsive/css/dataTables.responsive.css" --%>
<!-- 	rel="stylesheet"> -->

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/static/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/static/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/Login/vendor/daterangepicker/daterangepicker.css">



<script
	src="${pageContext.request.contextPath}/static/bower_components/datatables-plugins/pagination/extjs.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/datatables-plugins/pagination/input.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/datatables-plugins/pagination/four_button.js"></script>
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/bootstrap/js/popper.js"></script>
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/daterangepicker/moment.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script
	src="${pageContext.request.contextPath}/static/Login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/static/Login/js/main.js"></script>

<script
	src="${pageContext.request.contextPath}/static/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>

<script
	src="${pageContext.request.contextPath}/static/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->



<%-- <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script> --%>


<script>
// 	$(document).ready(function() {
// 		$('#dataTables-example').DataTable({
// 			responsive : true,
// 			paging: false,
// 		    searching: false
// 		});
// 	});
if ( $.fn.dataTable.isDataTable( '#dataTables-example' ) ) {
    table = $('#dataTables-example').DataTable();
    paging: false
}
else {
    table = $('#dataTables-example').DataTable( {
        paging: false
    } );
}
</script>

<script>
	$(document).ready(function() {
		var editor = '';
		editor = CKEDITOR.replace('description');

	});
</script>

<!-- Flot Charts JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot/excanvas.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot/jquery.flot.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot/jquery.flot.pie.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot/jquery.flot.resize.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot/jquery.flot.time.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/flot-data.js"></script>