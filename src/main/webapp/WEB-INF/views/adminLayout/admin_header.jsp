<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
#header {  height: 110px; min-width: 1800px; background-color: #273861; font-size: 20px; font-weight: bold; } 

#head_logo { float: left; }
#img_logo { width: 300px; height: 100px; }
a:visited { color: #FFFFFF; }

#head_menu { width: 1200px; margin: 0px auto;  }
#head_menu li { padding : 50px 50px 0px 50px;  }
#head_menu_right { float: right; padding: 50px 0px 0px 0px; }

</style>
<script type="text/javascript">
</script>

</head>
<body>
	<div id="header">
		<div id=head_logo>
			<img src="http://211.63.89.133/sinna2_admin/common/images/logo/hotel_logo_white.png"  id="img_logo"/>
		</div>
		<div id="head_menu_right">
			<ul class="nav nav-pills" >
			<li class="disabled" ><a href="#"><c:out value="${ loginId }" /></a></li> 
			<li><a href="<c:url value="/adminLogin/admin_logout.do" />">로그아웃</a></li>
  			</ul>
		</div>
		<div id="head_menu">
			<ul class="nav nav-pills" >
  				<li><a href="http://211.63.89.133/sinna2_admin/adminHome/admin_home_form.do" >홈</a></li>
				<li><a href="http://211.63.89.133/sinna2_admin/adminBooking/booking_main_form.do" >예약관리</a></li>
				<li><a href="http://211.63.89.133/sinna2_admin/adminRoom/room_main_form.do" >객실관리</a></li>
				<li><a href="http://211.63.89.133/sinna2_admin/adminMember/member_main_form.do" >회원관리</a></li>
				<li><a href="http://211.63.89.133/sinna2_admin/adminNotice/notice_main_form.do" >공지사항 관리</a></li>
			</ul>
		</div>
	</div>
</body>
</html>