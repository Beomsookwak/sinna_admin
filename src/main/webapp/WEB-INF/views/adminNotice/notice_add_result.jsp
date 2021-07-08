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

</style>
<script>
<c:choose>
<c:when test="${ rowCnt eq 1 }">
	alert("공지사항이 추가되었습니다.");
</c:when>
<c:otherwise>
	alert("공지사항을 입력 중 문제가 발생하였습니다.");
</c:otherwise>
</c:choose>

opener.document.location.reload();
self.close();
</script>
</head>
<body>
<div>
 
</div>
</body>
</html>