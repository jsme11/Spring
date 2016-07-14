<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Header -->
<jsp:include page="/WEB-INF/view/common/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#writeBtn").click(function() {
			location.href = "/SSB/write";
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
			<c:forEach items="${ articleListVO.articleList }" var="articleVO">
				<tr>
					<td>${ articleVO.articleId }</td>
					<td>${ articleVO.articleNumber }</td>
					<td>${ articleVO.writer }</td>
					<td><a href="/SSB/detail/${ articleVO.articleId }">${ articleVO.subject }</a></td>
					<td>${ articleVO.description }</td>
					<td>${ articleVO.createdDate }</td>
					<td>${ articleVO.modifiedDate }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">
					<form id="pagingForm">${ articleListVO.paging.getPagingList("pageNo", "[@]", "Prev", "Next", "pagingForm")}</form>
				</td>
			</tr>
			<tr>
				<td><button id="writeBtn">Write</button></td>
			</tr>
		</table>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/view/common/footer.jsp" />