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
#container { width: 1000px; height: 650px; margin: 0px auto; text-align: center; }
#table { width:900px; font-size: 21px; margin: 30px auto;}
#table th {width: 250px;  padding: 25px 50px 25px 40px; }
#table td {width : 650px; }
.form-control { width: 200px; }
.date { height: 30px; font-size: 15px; }
#btn_modify, #btn_cancle { margin: 0px 30px 0px 30px; }
</style>

<script type="text/javascript">

$(function () {
	$("#btn_remove").click(function () {
		
		var result = confirm("회원 정보를 삭제 하시겠습니까?");
		
		if( result ){
			$("#memberFrm").submit();
		}
	});//click
});//ready

</script>
</head>
<body>
<div id="container">
<h1 style="font-weight: bold;">회원 상세정보</h1>
<form action="member_remove.do" method="post"  id="memberFrm">
<table id="table">
<tr>
	<th>아이디</th>
	<td> <input type="text" class="form-control" readonly="readonly" name="memberId" value="<c:out value="${ memberInfo.memberId }"/>" ></td>
</tr>
<tr>
	<th>이메일</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ memberInfo.email }"/>" > </td>
</tr>
<tr>
	<th>이름</th>
	<td> <input type="text" class="form-control"  readonly="readonly"  value="<c:out value="${ memberInfo.memberName }"/>" > </td>
</tr>
<tr>
	<th>생년월일</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ memberInfo.birth }"/>" > </td>
</tr>
<tr>
	<th>연락처</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ memberInfo.tel }"/>" > </td>
</tr>
<tr>
	<th>가입일</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ memberInfo.signDate }"/>" > </td>
</tr>
<tr>
		<td colspan="2">
			<br/><br/>
			<button type="button" class="btn btn-danger" id="btn_remove" >삭제</button>
			<button type="button" class="btn btn-default" id="btn_cancle" onclick="window.close()">취소</button>
		</td>
</tr>
</table>
</form>
</div>
</body>
</html>