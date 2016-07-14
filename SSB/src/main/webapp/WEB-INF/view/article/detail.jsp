<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Header -->
<jsp:include page="/WEB-INF/view/common/header.jsp" />

<script type="text/javascript">

	$(document).ready(function(){
		$("#modifyBtn").click(function(){
			location.href = "/SSB/modify/${articleVO.articleId}";
		});
		$("#listBtn").click(function(){
			location.href = "/SSB/list";
		});
	});

</script>

<div id="listDiv">
	<div>
		<table>
			<tr>
				<th>ArticleId</th>
				<th>ArticleNumber</th>
				<th>Writer</th>
				<th>Subject</th>
				<th>Description</th>
				<th>CreatedDate</th>
				<th>ModifiedDate</th>
			</tr>
			<tr>
				<td>${ articleVO.articleId }</td>
				<td>${ articleVO.articleNumber }</td>
				<td>${ articleVO.writer }</td>
				<td>${ articleVO.subject }</td>
				<td>${ articleVO.description }</td>
				<td>${ articleVO.createdDate }</td>
				<td>${ articleVO.modifiedDate }</td>
			</tr>
			<tr>
				<td><button id="modifyBtn">Modify</button></td>
				<td><button id="listBtn">List</button></td>
			</tr>
		</table>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/view/common/footer.jsp" />