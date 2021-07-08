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

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
<style type="text/css">
#container { width: 1000px; height: 900px; margin: 0px auto; text-align: center; }
#table { width:900px; font-size: 15px; margin: 30px auto; border-spacing: 5px; border-collapse: separate;} 
.form-control { width: 200px; }
.date { height: 30px; font-size: 15px; }
#btn_modify { margin: 0px 20px 0px 20px; }
#btn_delete { margin: 0px 20px 0px 20px; }
#noticeTitle { width: 845px }
#preview { width: 900px; height: 500px }
#noticeDate { float: right; }

</style>
<script type="text/javascript">

$(function () {
	$("#btn_modify").click(function () {
		chkNull();
	});//click
	$("#btn_delete").click(function () {
		var result = confirm("글을 삭제 하시겠습니까 ?");
		
	 	if( result ){
			$("#Frm").attr("action","notice_remove.do").submit();
		} 
		
	});//click
});

function chkNull() {
	      
	if( $("#noticeTitle").val() == null || $("#noticeTitle").val() == "" ){
		alert("제목을 입력해주세요.");
		return;
	}//end if
	
	var result = confirm("공지사항을 변경 하시겠습니까 ?");
	
 	if( result ){
		$("#Frm").attr("action","notice_modify.do").submit();
	} 
	
}//chkNull

$(function(){
	 $('#summernote').summernote({
	        placeholder: '이미지를 포함한 공지사항을 작성해주세요.',
	        tabsize: 2,
	        height: 600,
	        width:900,
	        toolbar: [
	          ['style', ['style']],
	          ['font', ['bold', 'underline', 'clear']],
	          ['color', ['color']],
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ]
	      });//summernote
});//ready 

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
<h1 style="font-weight: bold;">공지사항 변경</h1>
<form  method="post" id="Frm" >
<table id="table" >
	<tr>
		<th>No.</th>
			<td>
				<input type="text" class="form-control" readonly="readonly" id="noticeNo" name="noticeNo"
						value="<c:out value="${ noticeInfo.noticeNo }"/>"/>
			</td>
		<td style="float: right;">
			<select class="form-control"  name="noticeClass">
			  <option hidden=""  value="<c:out value="${ noticeInfo.noticeClass }"/>"> 
					<c:out value="${ noticeInfo.noticeClass }"/>
			  </option>
			  <option value="NOTICE">NOTICE</option>
			  <option value="EVENT">EVENT</option>
			</select>
		</td>
	</tr>
<tr>
	<th>제목 &nbsp;:&nbsp;</th>
	<td colspan="2">
		<input type="text" class="form-control" name="noticeTitle"  id="noticeTitle" value="<c:out value="${ noticeInfo.noticeTitle }"/>"/> 
	</td>
</tr>
<tr>
	<td colspan="3" style="text-align: left"> 
		<textarea id="summernote" name="noticeContent" onerror="">
			<c:out value="${ noticeInfo.noticeContent }"/>
		</textarea>
		<div style=" float: left; ">
			<img src="<c:out value="${ noticeInfo.noticeImg }"/>" id="preview" name="img_roomImg" onerror=" this.src='http://211.63.89.133/sinna2_admin/common/images/notice/noimg.jpg' "/>
		</div>
		<input type="file" class="form-control form-control-sm"  id="imgSelector"  name="file_noticeImg" accept="image/*" style="width: 300px;" />
		<input type= "hidden" name="hidden_noticeImg" value="<c:out value="${ noticeInfo.noticeImg }"/>"/>
	</td>
</tr>
<tr>
	<th colspan="3" ><span style="float: right;">작성일자</span></th>
</tr>
<tr>
	<td colspan="3">
		<input type="text" class="form-control" id="noticeDate"  name="noticeDate" readonly="readonly" value="<c:out value="${ noticeInfo.noticeDate }"/>" />
	</td>
</tr>
<tr>
		<td colspan="3">
			<button type="button" class="btn btn-primary" id="btn_modify" >수정</button>
			<button type="button" class="btn btn-default" id="btn_cancle" onclick="window.close()" >취소</button>
			<button type="button" class="btn btn-danger" id="btn_delete" >삭제</button>
		</td>
</tr>
</table>
</form>
</div>
</body>
</html>