<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Header -->
<jsp:include page="/WEB-INF/view/common/header.jsp" />

<div id="writeDiv">
	<div>
		<form:form commandName="articleVO" method="post" action="/SSB/doWriteAction">
		<table>
			<tr>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
			</tr>
			<tr>
				<td>
					<input type="text" id="writer" name="writer" value="${ articleVO.writer }"/>
					<form:errors path="writer" />
				</td>
				<td>
					<input type="text" id="subject" name="subject" value="${ articleVO.subject }"/>
					<form:errors path="subject" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea type="text" id="description" name="description">${ articleVO.description }</textarea>
					<form:errors path="description" />
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/view/common/footer.jsp" />