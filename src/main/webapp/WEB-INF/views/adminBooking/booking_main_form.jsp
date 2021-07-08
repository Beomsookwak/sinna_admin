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


<!-- CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- bootstrap -->
<link href="http://211.63.89.133/sinna2_admin/common/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap -->
<script src="http://211.63.89.133/sinna2_admin/common/bootstrap-3.3.2/js/bootstrap.min.js"></script>
    
<style type="text/css">
#container{ width:1200px; height:1600px; margin: 50px auto; }

#label1{ width: 200px; height: 25px; font-size: 20px; }
#label2 { width: 200px; height: 25px; font-size: 20px; }

#btn_search { width: 40px; height: 34px; }
#text_search { width: 200px; float: left; }
#select_search { width: 120px; height: 34px; float: left; }

#table { height : 450px; font-size: 15px; text-align: center; display: block; overflow: auto; }
#table td { vertical-align: middle; }
.tableMenu { width: 135px; height: 50px; background-color: #EAEAEA; font-weight: bold; }
.tableResult { width: 135px; height: 50px;  }

#con_img { margin: 30px auto; font-size: 25px; font-weight: bold; }
.img { width: 80px; height: 80px; margin-left: 100px;  }
.cal_img { margin-left: 10px;  }
#con_calender { border: 1px solid #fff; width: 1200px; height: 650px; }
#con_cal_btn { float: right; margin-top: 15px; }
#img_cal { margin: -15px auto; }

/* 기본스타일  */	
	#table_cal{ background-color: #FFFFFF; margin: 30px auto;}
	
	#table_cal  tr{height: 81px;}
	#table_cal  td{width: 100px; text-align: right; font-size: 15pt; font-family: D2coding;}
/* 타이틀 스타일 */
	#table_cal  th {font-size: 20pt; font-weight: bold; border : #fff; color: #000000; font-family: D2coding; text-align: center; }

/* 요일 스타일 */
	#table_cal  td.sunday{ text-align: center; font-weight: bold; color: red; font-family: D2coding; }
	#table_cal  td.saturday{ text-align: center; font-weight: bold; color: blue; font-family: D2coding; }
	#table_cal  td.etcday{ text-align: center; font-weight: bold; color: black; font-family: D2coding; }

/* 날짜 스타일 */
	#table_cal  td.sun{ text-align: right; font-size: 15pt; color: red; font-family: D2coding; vertical-align: top;}
	#table_cal  td.sat{ text-align: right; font-size: 15pt; color: blue; font-family: D2coding; vertical-align: top;}
	#table_cal  td.etc{ text-align: right; font-size: 15pt; color: black; font-family: D2coding; vertical-align: top;}

	#table_cal  td.redbefore{ text-align: right; font-size: 12pt; color: red; font-family: D2coding; vertical-align: top;}
	#table_cal  td.before{ text-align: right; font-size: 12pt; color: gray; font-family: D2coding; vertical-align: top;}

/* 출력 */
	.cal_result { text-align: left; font-size: 14px; display: inline-block; width: 95%; text-align: left }
</style>
<script type="text/javascript">

function popupModify(){
	window.open("", "popup", "width=1000, height=1000, top=200, left=350"); 
	document.popFrm.target ="popup";             //새창에서 지정한 value옵션으로 타겟을 지정
}//popup_modify 

var param = "text_search="+$("#text_search").val();

function search() {
	var param = "text_search="+$("#text_search").val();
	$.ajax({
		url: "use_search.do",
		type: "post",
		data : param,
		dataType : "json",
		error : function(xhr){
			console.log("에러코드 : "+ xhr.status);	
			alert("에러")
		},
		success : function( jsonObject ){
			
			if( jsonObject.resultFlag ){
				$("#table>tbody").empty();
					var output="";
					
					$.each(jsonObject.data,function(i,jsonBooking){
						output += "<tr><td class='tableResult'>"+
						"<input type='hidden' name='thisBooking' value='"+
						jsonBooking.booking_no+"'/>"+
						jsonBooking.booking_no+"</td><td class='tableResult'>"+
						jsonBooking.member_name+"</td><td class='tableResult'>"+				
						jsonBooking.tel+"</td><td class='tableResult'>"+				
						jsonBooking.birth+"</td><td class='tableResult'>"+				
						jsonBooking.chk_in+"</td><td class='tableResult'>"+				
						jsonBooking.chk_out+"</td><td class='tableResult'>"+				
						jsonBooking.room_name+"</td><td class='tableResult'>"+				
						jsonBooking.booking_status+"</td><td class='tableResult'>"	+
						"<button type='submit' class='btn btn-primary' name='btnNo' onclick='popupModify()' value='"+i+"'>변경</button></td></tr>";
						});//each
				
  					if( jsonObject.dataCnt == 0 ){
						output += "<tr><td colspan='9'>검색 결과가 없습니다.</td></tr>"
					}//end if  
			
			 $("#table>tbody").append(output);
			}else{
				alert("서버에 문제");
			}//end else
		}//success
	});//ajax	
}//search 

$(function () {
	search();
	$("#btn_search").click(function () {
		search();
		$("#text_search").val("");
	});//click  
	$("#text_search").keydown(function(evt){ 
		if(evt.which == 13){
			search();
			$("#text_search").val("");
		}//end if
	});//keydown	
});//ready  */
	
</script>
</head>
<body>
<div id="wrap">
 <jsp:include page="/WEB-INF/views/adminLayout/admin_header.jsp"/>  
	<div id="container">
		<div>
			<h1><span class="label label-default" id="label1">예약 수정/관리</span></h1>
		</div>
		<div style="margin: 20px auto;">
			<button type="button" class="btn btn-default" id="btn_search">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
			
			<select class="form-control" id="select_search">
			  <option>예약번호</option>
			  <option>체크인</option>
			  <option>예약상태</option>
			</select>
			<input type="text" class="form-control" id="text_search" name= "text_search" />
		</div>
   <form action="booking_one.do"  method="post" id="popFrm" name="popFrm">
		<div style="margin: 10px auto;">
			<table class="table table-bordered table-hover"  id="table">
				<thead>
				<tr>
					<td class="tableMenu">예약번호</td>
					<td class="tableMenu">예약자명</td>
					<td class="tableMenu">연락처</td>
					<td class="tableMenu">생년월일</td>
					<td class="tableMenu">체크인</td>
					<td class="tableMenu">체크아웃</td>
					<td class="tableMenu">객실명</td>
					<td class="tableMenu">예약상태</td>
					<td class="tableMenu">수정하기</td>
				</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
   </form>    
<div style="margin: 30px auto;">
	<h1><span class="label label-default" id="label2">월별 예약 현황</span></h1>
</div>
<div id="con_img" >
	<img src="http://211.63.89.133/sinna2_admin/common/images/cal/ok.png" class="img" />예약 완료 : <c:out value="${ Y }"/> 건
	<img src="http://211.63.89.133/sinna2_admin/common/images/cal/wait.png" class="img" />취소 대기 : <c:out value="${ C }"/> 건
	<img src="http://211.63.89.133/sinna2_admin/common/images/cal/cancle.png" class="img" />취소 완료 : <c:out value="${ N }"/> 건
</div>
<div id="con_calender">
		<!-- 달력 만들기 -->
<table width ="700" align ="center" border ="1" cellpadding="5" cellspacing="0" id="table_cal" class="table table-bordered table-striped" >
	<tr>
<!-- 이전달 버튼 만들기 -->
		<th>
		<button type="button" class="btn btn-default" aria-label="Left Align"
			onclick="history.pushState(null, null, location.href='?year=<c:out value="${ year }"/>&month=<c:out value="${ month-1 }"/>'); ">
 			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
		</button>
		</th>
		
<!-- 제목 만들기 -->
		<th id = "title" colspan = "5">
		<%-- <%=year%>년  <%=month%>월 --%>
		 <c:out value="${ year }"/>년  <c:out value="${ month }"/>월 
		</th>
		
<!-- 다음달 버튼 만들기 -->
		<th>
		<button type="button" class="btn btn-default" aria-label="Left Align" 
			onclick="history.pushState(null, null, location.href='?year=<c:out value="${ year }"/>&month=<c:out value="${ month+1 }"/>'); ">
 			<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
		</button>
		</th>
	</tr>
<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
	<tr>
		<td class = "sunday">일</td>
		<td class = "etcday">월</td>
		<td class = "etcday">화</td>
		<td class = "etcday">수</td>
		<td class = "etcday">목</td>
		<td class = "etcday">금</td>
		<td class = "saturday">토</td>
	</tr>
	<tr>
		<c:out value="${ monthStatusResult }" escapeXml='false'/>
		<c:out value="${ dayStatusResult }" escapeXml='false'/>
	</tr>	
</table>
	<div id="img_cal" > 
	<span style="text-align: left;"></span>
		<img src="http://211.63.89.133/sinna2_admin/common/images/cal/ok_cal.png" class="cal_img" />예약완료 
		<img src="http://211.63.89.133/sinna2_admin/common/images/cal/wait_cal.png" class="cal_img" />취소대기
		<img src="http://211.63.89.133/sinna2_admin/common/images/cal/cancle_cal.png" class="cal_img" />취소완료
	</div>
</div>

	</div>
 <jsp:include page="/WEB-INF/views/adminLayout/admin_footer.jsp"/>  
</div>
</body>
</html>