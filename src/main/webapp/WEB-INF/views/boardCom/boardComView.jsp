<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/top.jsp"%>

<script>
const edit = function(){
	bf.action="update";
	bf.submit();
	
}

const delete1 = function(){
	bf.action="delete";
	alert('삭제되었습니다');
	bf.submit();
}

const boardLike = function(){
	let cidx = ${boardCom.cidx};
	
	$.ajax({
		url:'${myctx}/user/boardCom/like',
		type:'post',
		data:'cidx='+cidx,
		dataType:'json',
		cache:false,
		success:function(res){
			if(res>0){
				$('#likebtn').attr("disabled", true);
			}
		},
		error:function(err){
			if(err.status==200){
				alert('로그인 후 이용해주세요');
				location.href='${myctx}/login';
				return;
			}
			alert('err:'+err.status);
		}
		
	})
}
const deleteRe = function(re_cidx){
	$.ajax({
		url:'${myctx}/user/boardReDel',
		type:'post',
		data:'re_cidx='+re_cidx,
		dataType:'json',
		cache:false,
		success:function(res){
			if(res>0){
				location.reload();
			}
		},
		error:function(err){
			if(err.status==200){
				alert('로그인 후 이용해주세요');
				location.href='${myctx}/login';
				return;
			}
			alert('err:'+err.status);
		}
		
	})
}
</script>

<style>
td{
	vertical-align: middle;
}
td:first-child{
text-align: center;
}
</style>
<div class="container mt-3" style="width:1000px; height: auto;" >
<div style="text-align: center;">
		<a href="${myctx}/boardComList"><img src="${myctx}/assets/Cbanner3.jpg" style="width: 550px;   margin: 20px 0;"></a>
	</div>
<form name="bf" id="bf" role="form" method="POST" >
   <input type="hidden" name="midx" value="${loginUser.member.midx}">
   <input type="hidden" name="cidx" value="${boardCom.cidx}">     
    <table class="table" style="width:900px; margin:auto;">
       <tr>
          <td style="width:10%; background:#0000000d;"><b>제목</b></td>
          <td style="width:90%;" colspan="2">
           ${boardCom.ctitle }
          </td>
       </tr>
       <tr>
          <td style="width:10%; background:#0000000d;"><b>작성자</b></td>
          <td style="width:90%" colspan="2">
          ${boardCom.email}
          </td>
       </tr>       
       <tr>
          <td style="width:10%;background:#0000000d;"><b>추천수</b></td>
          <td style="width:75%" >
           	${boardCom.clikenum}
          </td>
          <td><button class="btn btn-outline-danger" id="likebtn" type="button" onclick="boardLike()">좋아요!</button></td>
       </tr>
       <tr>
          <td style="width:10%;  background:#0000000d;"><b>조회수</b></td>
          <td style="width:90%" colspan="2">
           	${boardCom.creadnum}
          </td>
       </tr>
       <tr>
          <td style="width:10%;  background:#0000000d;"><b>내용</b></td>
          <td style="width:90%" colspan="2">
           ${boardCom.ccontent}
          </td>
       </tr>

      </table>
      <c:if test="${loginUser.member.midx eq boardCom.midx}">
      <div class="text-center">
      		<button type="button" type="button" class="btn btn-success" onclick="edit()"style="margin:20px;">수정</button>
            <button type="button" type="button" class="btn btn-danger" onclick="delete1()">삭제</button>
            </div>
      </c:if>
      
</form>
</div>
<div class="container">
<hr size="10">
<c:if test="${loginUser ne 'anonymousUser'}">
<div>
<form action="${myctx}/user/boardRe" method="post">
	<input type="hidden" name="cidx" value="${boardCom.cidx}">
	<input type="hidden" name="midx" value="${loginUser.member.midx}">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<textarea class="form-control" name="re_ccontent" rows="3" cols="10" style="width:80%;  display: block; margin-left: auto; margin-right: auto;" required></textarea>
	<div class="text-center">
		<button class="btn btn-warning m-3" onclick="location.href='${myctx}/boardComList'">목록</button>
		<button class="btn btn-info m-3">댓글 등록</button>
	</div>
</form>
</div>
<hr>
</c:if>
<table class="table">
	<c:forEach var="reply" items="${boardCom.comReArr}">
	<tr>
		<td style="text-align: left">
		<div> 
		
			${reply.email} <small>${reply.re_cdate}</small>
			<c:if test="${loginUser.member.midx eq reply.midx }">
				<a class="btn btn-danger btn-sm" onclick="deleteRe('${reply.re_cidx}')">x</a>
			</c:if>
		</div>
		<div>
		 <b>${reply.re_ccontent}</b> 
		</div>
		</td>
	</tr>
	</c:forEach>
</table>
</div>
<%@ include file="/WEB-INF/views/foot.jsp"%>