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
#container { width: 1000px; height: 900px; margin: 0px auto; text-align: center; }
#table { width:900px; font-size: 21px; margin: 30px auto;}
#table th {width: 250px;  padding: 25px 50px 25px 40px; }
#table td {width : 650px; }
.form-control { width: 200px; }
.date { height: 30px; font-size: 15px; }
#btn_modify, #btn_cancle { margin: 0px 30px 0px 30px; }

</style>
<script type="text/javascript">

$(function () {
	$("#btn_modify").click(function () {
		chkNull();
	});//click
});

function chkNull() {
	if( $("#chkIn").val() == null || $("#chkIn").val() == "" ){
		alert("체크인 날짜를 확인해주세요.");
		return;
	}//end if
	
	if( $("#chkOut").val() == null || $("#chkOut").val() == "" ){
		alert("체크아웃 날짜를 확인해주세요.");
		return;
	}//end if
	
	//confirm("예약 정보를 변경하시겠습니까 ?");
	var result = confirm("예약 정보를 변경하시겠습니까 ?");
	
 	if( result ){
		$("#updateFrm").submit();
	} 
	
}//chkNull

$(function () {
	$("#btn_modify").click(function () {
		$("#updateFrm").submit();
	});//click
});//ready    
 
</script>
</head>
<body>
<div id="container">
<h1 style="font-weight: bold;">예약 변경</h1>
<form action="booking_modify.do"  method="post" id="updateFrm" >
<table id="table">
<tr>
	<th>예약번호</th>
	<td> <input type="text" class="form-control" readonly="readonly" id="bookingNo" name="bookingNo" value="<c:out value="${ bookingInfo.bookingNo }"/>"></td>
</tr>
<tr>
	<th>예약자명</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ bookingInfo.memberName }"/>" ></td>
</tr>
<tr>
	<th>연락처</th>
	<td> <input type="text" class="form-control" readonly="readonly"  value="<c:out value="${ bookingInfo.tel }"/>" > </td>
</tr>
<tr>
	<th>생년월일</th>
	<td> <input type="text" class="form-control"  readonly="readonly"  value="<c:out value="${ bookingInfo.birth }"/>" > </td>
</tr>
<tr>
	<th>체크인 /<br/>체크아웃</th>
	<td align="left">
	     <input type="date" class="date" id="chkIn" name="chkIn"  value="<c:out value="${ bookingInfo.chkIn }"/>" /> ~ 
	     <input type="date"  class="date" id="chkOut" name="chkOut"  value="<c:out value="${ bookingInfo.chkOut }"/>" />
	</td>
</tr>
<tr>
	<th>객실명</th>
	<td align="left">
			<select class="form-control" readonly="readonly" >
			  <option hidden=""><c:out value="${ bookingInfo.roomName }"/></option>
			  <option>디럭스</option>
			  <option>스위트</option>
			</select>
	</td>
</tr>
<tr>
	<th>예약상태</th>
		<td align="left">
 			<select class="form-control"  id="status" name="bookingStatus" > 
			  <option hidden=""  value=<c:out value="${ bookingInfo.bookingStatus }"/> >
			 	<c:out value="${ bookingInfo.bookingStatus }"/>
			  </option>
			  <option value="Y">Y : 예약완료</option>
			  <option value="C">C : 취소대기</option>
			  <option value="N">N : 취소완료</option>
			</select>
	</td>
</tr>
<tr>
	<th>투숙인원</th>
	<td><input type="text" class="form-control" readonly="readonly" value="<c:out value="${ bookingInfo.bookingPeople }"/>" /> </td>
</tr>
<tr>
	<th>예약날짜</th>
	<td> <input type="text" class="form-control"  readonly="readonly" value="<c:out value="${ bookingInfo.bookingDate }"/>" /> </td>
</tr>
<tr>
		<td colspan="2">
			<br/><br/>
			<button type="button" class="btn btn-primary" id="btn_modify" >수정</button>
			<button type="button" class="btn btn-default" id="btn_cancle" onclick="window.close()" >취소</button>
		</td>
</tr>
</table>
</form>
</div>
</body>
</html>