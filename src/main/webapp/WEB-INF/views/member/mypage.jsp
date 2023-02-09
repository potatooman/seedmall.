<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/top.jsp"%>

<style>
td:first-child {
	text-align: center;
}

h2 {
	font-size: calc(1.325rem + .9vw);
	margin: 20px 0px 40px 0px;
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: bold;
	/* color: #39b559; */
	text-align: center;
}
</style>

<div class="d-flex">
	<%@ include file="/WEB-INF/views/member/mypageSidebar.jsp"%>
	<div class="container" style="width: 600px; margin-top: 50px; height: 50.7em;">
		<h2>마이페이지</h2>
		<table class="table">
			<c:if test="${loginUser.member.grade ne '열매회원' }">
				<tr>
					<td width="80px;" class="m1" style="border:0;">
						<b>등급</b>
					</td>
					<td class="m2" style="border:0;">
						<img width="30px" src="${myctx}/resources/grade_images/${loginUser.member.grade}.png">
						${loginUser.member.grade}
						<span style="color: gray; font-size: 0.9em">
							( 다음 등급까지 <fmt:formatNumber value="${nextGrade.gpoint-loginUser.member.mileage}" pattern="###,###" />마일리지 남았습니다 )
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="progress ml-2" style="width: 100%; height: 20px">
							<div class="progress-bar" role="progressbar" style="width: <fmt:formatNumber value="${loginUser.member.mileage/nextGrade.gpoint}" type="percent"/>;" aria-valuenow="${loginUser.member.mileage}" aria-valuemin="0" aria-valuemax="${nextGrade.gpoint}">
								<fmt:formatNumber value="${loginUser.member.mileage/nextGrade.gpoint}" type="percent" />
							</div>
						</div>
					</td>
				</tr>
			</c:if>
			<c:if test="${loginUser.member.grade eq '열매회원' }">
				<tr>
					<td width="80px;" class="m1">
						<b>등급</b>
					</td>
					<td class="m2">
						<img width="30px" src="${myctx}/resources/grade_images/${loginUser.member.grade}.png"> ${loginUser.member.grade}
					</td>
				</tr>
			</c:if>
			<tr>
				<td width="80px;" class="m1">
					<b>이름</b>
				</td>
				<td class="m2">${loginUser.member.mname}</td>

			</tr>
			<tr>
				<td width="80px;" class="m1">
					<b>이메일</b>
				</td>
				<td class="m2">${loginUser.member.email}</td>

			</tr>
			<tr>
				<td width="80px;" class="m1">
					<b>연락처</b>
				</td>
				<td class="m2">${loginUser.member.mhp1}-${loginUser.member.mhp2} - ${loginUser.member.mhp3}</td>
			</tr>
			<tr>
				<td width="80px;" class="m1" rowspan="">
					<b>주소</b>
				</td>
				<td class="m2">${loginUser.member.mpost}</td>
			</tr>
			<tr>
				<td width="80px;" class="m1" rowspan=""></td>
				<td class="m2">${loginUser.member.maddr1}<br>${loginUser.member.maddr2}</td>
			</tr>
			<tr>
				<td colspan="2" class="m3 text-center" style="border: 0;">
					<a class="btn btn-primary" href="mypage/infoForm">내 정보 수정</a> <a class="btn btn-primary" href="mypage/editPassForm">비밀번호 변경</a>
				</td>
			</tr>

		</table>
	</div>
	<div style="width: 230px;"></div>
</div>

<%@ include file="/WEB-INF/views/foot.jsp"%>