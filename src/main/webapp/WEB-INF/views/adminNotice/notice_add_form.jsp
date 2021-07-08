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

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
<style type="text/css">
#container { width: 1000px; height: 900px; margin: 0px auto; text-align: center; }
#table { width:900px; font-size: 15px; margin: 30px auto; border-spacing: 5px; border-collapse: separate;} 
.form-control { width: 200px; }
.date { height: 30px; font-size: 15px; }
#btn_add , #btn_cancle { margin: 0px 30px 0px 30px; }
#noticeTitle { width: 845px }
#preview { width: 900px; height: 500px }
#noticeDate { float: right; }

</style>
<script type="text/javascript">


$(function () {
	$("#btn_add").click(function () {
		chkNull();
	});//click
});

function chkNull() {
	      
	if( $("#noticeClass").val() == null || $("#noticeClass").val() == "" ){
		alert("분류를 선택해주세요.");
		$("#noticeClass").focus();
		return;
	}//end if
	if( $("#noticeTitle").val() == null || $("#noticeTitle").val() == "" ){
		alert("제목을 입력해주세요.");
		$("#noticeTitle").focus();
		return;
	}//end if
	
	var result = confirm("공지사항을 추가 하시겠습니까 ?");
	
 	if( result ){
		$("#Frm").attr("action","notice_add.do").submit();
	} 
	
}//chkNull

$(function(){
	 $('#summernote').summernote({
	        placeholder: '내용을 입력해주세요.',
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
<h1 style="font-weight: bold;">공지사항 추가</h1>
<form  method="post" id="Frm" >
<table id="table" >
	<tr>
		<td colspan="2" >
			<select class="form-control"  name="noticeClass"  id="noticeClass" style="float: right;">
			  <option hidden=""  value="" > 
			  </option>
			  <option value="NOTICE">NOTICE</option>
			  <option value="EVENT">EVENT</option>
			</select>
		</td>
	</tr>
<tr>
	<th>제목 &nbsp;:&nbsp;</th>
	<td>
		<input type="text" class="form-control" name="noticeTitle"  id="noticeTitle" /> 
	</td>
</tr>
<tr>
	<td colspan="2" style="text-align: left"> 
		<textarea id="summernote" name="noticeContent" >
		</textarea>
		<div style=" float: left; ">
			<img src=""  id="preview" onerror="this.src='http://211.63.89.133/sinna2_admin/common/images/notice/noimg.jpg' "/>
		</div>
		<input type="file" class="form-control form-control-sm"  id="imgSelector"  name="file_noticeImg" accept="image/*" style="width: 300px;" />
	</td>
</tr>
<tr>
		<td  colspan="2">
			<button type="button" class="btn btn-primary" id="btn_add" >추가</button>
			<button type="button" class="btn btn-default" id="btn_cancle" onclick="window.close()" >취소</button>
		</td>
</tr>
</table>
</form>
</div>
</body>
</html>