<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<!-- bootstrap -->
<link href="http://211.63.89.133/sinna2_admin/common/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">

<!-- CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- bootstrap -->
<script src="http://211.63.89.133/sinna2_admin/common/bootstrap-3.3.2/js/bootstrap.min.js"></script>
    
<style type="text/css">
#container{ width:1200px; height:900px; margin: 50px auto; }

#label { width: 200px; height: 25px; font-size: 20px; }
#con_date { width:1200px; height:50px; background-color: #E0E0E0; text-align: center; }

#table { font-size: 30px; text-align: center; }
#table td { vertical-align: middle; }
.tableMenu { width: 400px; height: 150px; background-color: #EAEAEA; font-weight: bold;  }
.tableResult { width: 400px; height: 150px;  }

</style>
<script type="text/javascript">

/*  $(function() {
	if (sessionStorage.getItem("loginId") == null) {
		window.history.back();
	}
});//ready  */

</script>

</head>
<body>
<div id="wrap">
 <jsp:include page="/WEB-INF/views/adminLayout/admin_header.jsp"/>  
	<div id="container">
		<div>
			<h1><span class="label label-default" id="label">예약 정보</span></h1>
		</div>
		<div id="con_date" >
			<h1><span>
			<fmt:formatDate value="<%= new Date() %>" pattern="yyyy년MM월dd일"/>
			</span> 예약현황입니다.</h1>
		</div>
		<div style="margin: 50px auto;">
			<table class="table table-bordered table-hover table-condensed"  id="table" > 
				<tr>
					<td class="tableMenu" >당일 받은 예약</td>
					<td class="tableResult">
						<c:out value="${ todayBooking }"/>
					건</td>
					<td class="tableMenu">당일 투숙 매출</td>
					<td class="tableResult">
						<c:out value="${ todaySales }"/>
					원</td>
				</tr>
				<tr>
					<td class="tableMenu" >당일 이용 투숙객</td>
					<td class="tableResult">
						<c:out value="${ todayGuest }"/>
					팀</td>
					
					<td class="tableMenu">내일 이용 투숙객</td>
					<td class="tableResult">
						<c:out value="${ tomorrowGuest }"/>
					팀</td>
				</tr>
				<tr>
					<td class="tableMenu" >이번달 이용 투숙객</td>
					<td class="tableResult">
						<c:out value="${ thisMonthGuest }"/>
					팀</td>
					
					<td class="tableMenu">이번달 투숙 매출</td>
					<td class="tableResult">
						<c:out value="${ thisMonthSales }"/>
					 원</td>
				</tr>
				<tr>
					<td class="tableMenu" >지난달 이용 투숙객</td>
					<td class="tableResult">
						<c:out value="${ lastMonthGuest }"/>			
					팀</td>
					
					<td class="tableMenu">지난달 투숙 매출</td>
					<td class="tableResult">
						<c:out value="${ lastMonthSales }"/>					
					원</td>
					
				</tr>
			</table>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/adminLayout/admin_footer.jsp"/>  
</div>
</body>
</html>