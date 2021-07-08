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
#container { width: 1000px; height: 800px; margin: 0px auto; text-align: center; }
#table { width:900px; font-size: 21px; margin: 30px auto;}
#table th {width: 250px;  padding: 25px 50px 25px 40px; }
#table td {width : 650px; }
.form-control { width: 200px; }
.date { height: 30px; font-size: 15px; }
#preview{ height: 350px; width: 650px }
#btn_modify, #btn_cancle { margin: 0px 30px 0px 30px; }
</style>
<script type="text/javascript">

$(function () {
	
	$("#btn_add").click(function () {
		chkNull();
	});//click
});

function chkNull() {
	if( $("#text_roomName").val() == null || $("#text_roomName").val() == "" ){
		alert("객실이름을 입력해주세요.");
		$("#text_roomName").focus();
		return;
	}//end if
	if( $("#text_roomArea").val() == null || $("#text_roomArea").val() == "" ){
		alert("객실면적을 입력해주세요.");
		$("#text_roomArea").focus();
		return;
	}//end if
	if( $("#text_roomPrice").val() == null || $("#text_roomPrice").val() == "" ){
		alert("객실요금을 입력해주세요.");
		$("#text_roomPrice").focus();
		return;
	}//end if
	if( !$("#imgSelector").val() ){
		alert("객실이미지를 입력해주세요.");
		return;
	}//end if
	
	var result = confirm("객실 정보를 추가 하시겠습니까 ?");
	
 	if( result ){
		$("#roomFrm").submit();
	}//end if
}//chkNull

/* 이미지 썸네일 */
$(function () {
	$("#imgSelector").change(function(){
	//alert("z")
	setImageFromFile(this, '#preview');
	});
});//ready
/* 이미지 썸네일 */
function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(expression).attr("src", e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }//end if
}//setImageFromFile
</script>
</head>
<body>
<div id="container">
<h1 style="font-weight: bold;">객실 추가</h1>
<form action="room_add.do" method="post" id="roomFrm">
<table id="table">
<tr>
	<th>객실이름</th>
	<td><input type="text" class="form-control"  id="text_roomName"  name="roomName"  maxlength="10"> </td>
</tr>
<tr>
	<th>면적</th>
	<td> <input type="text" class="form-control"  id="text_roomArea"  name="roomArea" maxlength="3"> </td>
</tr>
<tr>
	<th>최대인원</th>
	<td align="left">
		<select class="form-control" id="select_roomPeople"  name="roomPeople" >
			  <option value="1">1명</option>
			  <option value="2" selected="selected">2명</option>
		</select>
	</td>
</tr>
<tr>
	<th>요금</th>
	<td> <input type="text" class="form-control"  id="text_roomPrice" name="roomPrice" maxlength="15"></td>
</tr>
<tr>
	<th>객실이미지</th>
	<td align="left" >
		<div style=" float: left; ">
			<img id="preview" />
		</div>
		<input type="file" class="form-control form-control-sm"  id="imgSelector"  name="file_roomImg" accept="image/*" style="width: 300px;" />
	</td>
</tr>
<tr>
		<td colspan="2">
			<br/><br/>
			<button type="button" class="btn btn-primary" id="btn_add" >추가</button>
			<button type="button" class="btn btn-default" id="btn_cancle" onclick="window.close()">취소</button>
		</td>
</tr>
</table>
</form>
</div>
</body>
</html>