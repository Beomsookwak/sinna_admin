<%@page import="java.text.DecimalFormat"%>
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
#container{ width:1200px; height:800px; margin: 50px auto; }

#label1{ width: 200px; height: 25px; font-size: 20px; }
#label2 { width: 200px; height: 25px; font-size: 20px; }

#btn_search { width: 40px; height: 34px; }
#text_search { width: 200px; float: left; }
#select_search { width: 120px; height: 34px; float: left; }

#table { height : 300px; font-size: 15px; text-align: center; display: block; overflow: auto; }
#table td { vertical-align: middle; }
.tableMenu { width: 200px; height: 50px; background-color: #EAEAEA; font-weight: bold; }
.tableResult { width: 200px; height: 50px;  }

#con_img { margin: 20px auto; font-size: 25px; font-weight: bold; }
.img { width: 100px; height: 100px; margin-left: 100px;  }
#con_calender { border: 1px solid #333; width: 1200px; height: 600px; }
#con_cal_btn { float: right; margin-top: 15px; }

</style>
<script type="text/javascript">

function popupModify(){
	window.open("", "popup", "width=1000, height=1000, top=100, left=350"); 
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
					
					$.each(jsonObject.data,function(i,jsonRoom){
					output += "<tr><td class='tableResult'>"+
					"<input type='hidden' name='thisRoom' value='"+
					jsonRoom.room_no+"'/>"+
					jsonRoom.room_no+"</td><td class='tableResult'>"+
					jsonRoom.room_name+"</td><td class='tableResult'>"+				
					jsonRoom.room_area+"</td><td class='tableResult'>"+				
					jsonRoom.room_people+"</td><td class='tableResult'>"+				
					jsonRoom.room_price+"</td><td class='tableResult'>"+				
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
	$("#btn_add").click(function () {
		window.open("http://211.63.89.133/sinna2_admin/adminRoom/room_add_form.do","객실추가","width=1000,height=1000, top=100, left=350");
	});//click
});//ready  

</script>
</head>
<body>
<div id="wrap">
 <jsp:include page="/WEB-INF/views/adminLayout/admin_header.jsp"/>  
	<div id="container">
		<div>
			<h1><span class="label label-default" id="label1">객실 리스트</span></h1>
		</div>
		
		<div style="margin: 20px auto;">
			<button type="button" class="btn btn-default" id="btn_search">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
			
			<select class="form-control" id="select_search">
			  <option>객실번호</option>
			  <option>객실명</option>
			</select>
			<input type="text" class="form-control" id="text_search"/>
		
      		<div style="float: right;">
      			<button type="button" class="btn btn-primary" id="btn_add" onclick="popupAdd()">객실추가</button>
      		</div>
		</div>
	<form  action="room_one.do" method="post" id="popFrm" name="popFrm"> 
		<div style="margin: 10px auto;">
			<table class="table table-bordered table-hover"  id="table">
				<thead>
					<tr>
						<td class="tableMenu">객실번호</td>
						<td class="tableMenu">객실명</td>
						<td class="tableMenu">면적</td>
						<td class="tableMenu">최대인원</td>
						<td class="tableMenu">요금</td>
						<td class="tableMenu">수정하기</td>
					</tr> 
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</form>
	</div>
</div>
 <jsp:include page="/WEB-INF/views/adminLayout/admin_footer.jsp"/>  
</body>
</html>