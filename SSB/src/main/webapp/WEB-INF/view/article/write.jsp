<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Header -->
<jsp:include page="/WEB-INF/view/common/header.jsp" />

<div id="writeDiv">
	<div>
		<form:form commandName="articleVO" method="post" action="/SSB/doWriteAction">
			<table>
				<tr>
					<th>Writer</th>
					<th>Subject</th>
					<th>Description</th>
				</tr>
				<tr>
					<td>
						<input type="text" id="writer" name="writer" value="${ articleVO.writer }" />
						<form:errors path="writer" />
					</td>
					<td>
						<input type="text" id="subject" name="subject" value="${ articleVO.subject }" />
						<form:errors path="subject" />
					</td>
					<td>
						<input type="text" id="description" name="description" value="${ articleVO.description }" />
						<form:errors path="description" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Write" />
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/view/common/footer.jsp" />