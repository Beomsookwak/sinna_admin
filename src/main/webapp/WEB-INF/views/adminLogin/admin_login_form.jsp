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
#header { background-color: #273861; height: 110px; color: #FFFFFF; text-align: center; font-size: 70px; }
#container{ width: 1200px; height: 700px;  margin :100px auto; }
#footer { background-color: #273861; height: 110px; color: #FFFFFF; text-align: center; font-size: 15px; }

#con_logo{ margin-left: 450px; margin-bottom: 100px; }
#table_login{ margin: 0px auto; }

#id { width: 300px; height: 50px; margin: 0 10px 10px 0}
#pass { width: 300px; height: 50px; }
#btn_login { width: 120px; height: 120px;  }

#login_warning {color:#ff0000; font-size:12px;" }

</style>
<script type="text/javascript">
$(function(){
	$("#id").keydown(function(evt){ 
		if(evt.which == 13){
			chkNull();
		}//end if
	});	
	$("#pass").keydown(function(evt){ 
		if(evt.which == 13){
			chkNull();
		}//end if
	});	
	$("#btn_login").click(function(){ 
		chkNull();
	});	
});

function chkNull(){
	if( $("#id").val() ==""){
		alert("아이디는 필수 입력");
		$("#id").focus();
		return;
	}//end if
	if( $("#pass").val() ==""){
		alert("비밀번호는 필수 입력");
		$("#pass").focus();
		return;
	}//end if
	$("#loginFrm").submit();
}//chkNull
</script>
</head>
<body>
<div id="wrap">
<form action="admin_login.do" method="post" id="loginFrm">
	<div id="header">
			<strong>관리자 모드</strong>
	</div>
	<div id="container">
		<div id="con_logo">
			<img src="http://211.63.89.133/sinna_admin/common/images/logo/admin_logo.png" style="width: 300px; height: 300px;" />
		</div>
		<div id="con_login">
			<table id="table_login" >
				<tr>
					<td style="width: 300px;">
						<input type="text" name="adminId"  id="id" class="form-control" placeholder="관리자 아이디"  tabindex="1"/>
					</td>
					<td rowspan="2">
						<input type="button" class="btn btn-primary" value="로그인"  id="btn_login"  name="btn_login" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="adminPass" id="pass" class="form-control" placeholder="관리자 비밀번호"  tabindex="2"/>
					</td>
				</tr>
				<tr>
					<td>
					<div id="login_warning">
							<c:out value="${ loginFail }"/>
					</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>	
<jsp:include page="/WEB-INF/views/adminLayout/admin_footer.jsp"/>  
	
</div>
</body>
</html>