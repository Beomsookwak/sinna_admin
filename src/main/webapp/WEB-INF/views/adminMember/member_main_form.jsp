<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

#table { height : 500px; font-size: 15px; text-align: center; display: block; overflow: auto; }
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
	window.open("", "popup", "width=1000, height=700, top=100, left=350"); 
	document.popFrm.target ="popup";             //새창에서 지정한 value옵션으로 타겟을 지정
}//popup_modify .

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
			$.each(jsonObject.data,function(i,jsonMember){
				output += "<tr><td class='tableResult'>"+
				"<input type='hidden' name='thisMember' value='"+
				jsonMember.member_id+"'/>"+
				jsonMember.member_id+"</td><td class='tableResult'>"+
				jsonMember.email+"</td><td class='tableResult'>"+
				jsonMember.member_name+"</td><td class='tableResult'>"+				
				jsonMember.birth+"</td><td class='tableResult'>"+				
				jsonMember.tel+"</td><td class='tableResult'>"+				
				"<button type='submit' class='btn btn-primary' name='btnNo' onclick='popupModify()' value='"+i+"'>변경</button></td></tr>";
			});//each
			
			if( jsonObject.dataCnt == 0 ){
				output += "<tr><td colspan='6'>검색 결과가 없습니다.</td></tr>"
			}//end if  
			
			$("#table>tbody").append(output);
			
			}else{
				$("#test").html("<strong>서버에서 문제가 발생하였습니다.</strong>");
			}
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

});//ready  

</script>
</head>
<body>
<div id="wrap">
<jsp:include page="/WEB-INF/views/adminLayout/admin_header.jsp"/>  
	<div id="container">
		<div>
			<h1><span class="label label-default" id="label1">회원 리스트</span></h1>
		</div>
		<div style="margin: 20px auto; ">
			<button type="button" class="btn btn-default" id="btn_search">
				<span class="glyphicon glyphicon-search" aria-hidden="true" ></span>
			</button>
			
			<select class="form-control" id="select_search">
			  <option>아이디</option>
			  <option>연락처</option>
			</select>
			<input type="text" class="form-control" id="text_search"/>
		</div>
	<form  action="member_one.do" method="post" id="popFrm" name="popFrm"> 
		<div style="margin: 10px auto;">
			<table class="table table-bordered table-hover"  id="table">
				<thead>
					<tr>
						<td class="tableMenu">아이디</td>
						<td class="tableMenu">이메일</td>
						<td class="tableMenu">이름</td>
						<td class="tableMenu">생년월일</td>
						<td class="tableMenu">연락처</td>
						<td class="tableMenu">상세정보</td>
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