<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/top.jsp"%>
<!-- <link href='https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css' rel='stylesheet'> -->
<!-- <link href='https://use.fontawesome.com/releases/v5.8.1/css/all.css' rel='stylesheet'> -->
<!-- <script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script> -->
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;800&display=swap')
	;

@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);

.container .product-item {
	min-height: 450px;
	border: none;
	overflow: hidden;
	position: relative;
	border-radius: 0
}

.container .product-item .product {
	width: 100%;
	height: 350px;
	position: relative;
	overflow: hidden;
	cursor: pointer
}

.container .product-item .product img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.container .product-item .product .icons .icon {
	width: 40px;
	height: 40px;
	background-color: #fff;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	transition: transform 0.6s ease;
	transform: rotate(180deg);
	cursor: pointer
}

.container .product-item .product .icons .icon:hover {
	background-color: #10c775;
	color: #fff
}

.container .product-item .product .icons .icon:nth-last-of-type(3) {
	transition-delay: 0.2s
}

.container .product-item .product .icons .icon:nth-last-of-type(2) {
	transition-delay: 0.15s
}

.container .product-item .product .icons .icon:nth-last-of-type(1) {
	transition-delay: 0.1s
}

.container .product-item:hover .product .icons .icon {
	transform: translateY(-60px)
}

.container .product-item .tag {
	text-transform: uppercase;
	font-size: 0.75rem;
	font-weight: 500;
	position: absolute;
	top: 10px;
	left: 20px;
	padding: 0 0.4rem;
}

.container .product-item .title {
	font-size: 0.95rem;
	letter-spacing: 0.5px
}

.container .product-item .fa-star {
	font-size: 0.65rem;
	color: #ff0000;
}

.container .product-item .price {
	margin-top: 10px;
	margin-bottom: 10px;
	font-weight: 600;
}

.list-group-item.active {
	background-color: #dee2e6;
	border-color: #adb5bd;
}

.list-group-item {
	position: relative;
	display: block;
	padding: 0.3rem .5rem;
}

.fw-800 {
	font-weight: 800;
}

.NEW {
	background-color: #208f20 !important;
	color: #fff;
}

.bg-black {
	background-color: #1f1d1d;
	color: #fff
}

.BEST {
	background-color: #bb3535;
	color: #fff
}

.HIT {
	background-color: #ffc107 !important;
	color: #fff;
}

div#upCategory button {
	
}
/* ?????? */
.tip {
	position: relative;
	display: #555;
}

.tip .tiptext {
	visibility: hidden; /* ???????????? ????????? ?????? ????????? ?????? */
	/*  width: 120px;      */
	width: 350px; /* ?????? ????????? ????????? ?????? */
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	position: absolute; /* ?????? ????????? ?????? */
	z-index: 1;
	opacity: 0;
	transition: opacity 0.3s;
}

.tip:hover .tiptext {
	visibility: visible;
	opacity: 1; /* hover ????????? ????????? ????????? ????????? */
	color: #d1d1d1;
}

.tip .tiptext::after {
	content: " "; /* ???????????? ?????? ?????? */
	position: absolute; /* ?????? ?????? ?????? */
	border-style: solid;
	border-width: 5px; /* ????????? ????????? 5px ??? ?????? */
}

.tip .tip-bottom {
	width: 350px;
	top: 150%;
	right: 0;
	margin-left: -60px; /* ????????? ??????*/
}

.tip .tip-bottom::after {
	bottom: 100%; /* ????????? ????????? ????????? ????????? ?????? */
	right: 15%; /* ??????????????? 15% ????????? ????????? ?????? */
	margin-left: -5px;
	/* ???????????? ??????????????? ????????? ?????? */
	border-color: transparent transparent #555 transparent;
}

.tip {
	text-decoration: underline;
	text-underline-position: under;
}

h2, h4 {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 700;
	color: #39b559;
}
/* -------------------------------------------- */
a {
	text-decoration: none;
	color: #404040;
}

.wrap { /*  ?????? ???????????? ?????? */
	background: #98e0ad;
	height: 50px;
}

.menu1>div { /*  ???????????? ????????? ??????, ??????, ?????? */
	float: left;
	width: 130px;
	height: 50px;
	line-height: 50px;
	text-align: center;
	position: relative;
	font-weight: 600;
}

.menu1>div:first-child { /*  ???????????? ????????? ??????, ??????, ?????? */
	margin-left: 7.6em;
}

.menu1>div:hover .sub {
	display: block;
	color: black;
	font-weight: 600;
}

.menu1>div a { /*  ???????????? */
	display: block;
	font-weight: 500;
	font-size: 15px;
	color: #0000008c;
}

.menu1>div a:hover { /*  ???????????? ?????? ????????????????????? */
	background: #e2e4e6;
	color: #1e1e25;
	font-weight: 600;
	font-size: 16px;
}

.menu1 .sub { /* ???????????? ????????? ????????????*/
	position: absolute;
	top: 50px;
	width: 130px;
	display: none;
	z-index: 1;
}

.menu1 .sub div { /* ???????????? ?????? */
	background: none;
	border: 0;
}

.menu1 .sub div a { /* ???????????? ?????? */
	background: #f2f2f5;
	font-size: 12px;
}

.menu1 .sub div a:hover { /* ???????????? ?????? ?????????????????????  */
	background: #93939f;
	font-size: 12px;
	font-weight: 700;
}
</style>

<script type='text/javascript'>
var token = $("meta[name='_csrf']").attr("content"); 
var header = $("meta[name='_csrf_header']").attr("content");
var name = $("#userName").val();
	const changeSort = function(sort) {
		const urlParams = new URL(location.href).searchParams;

		const keyword = urlParams.get('findKeyword');
		const upcg = urlParams.get('upcg');
		const downcg = urlParams.get('downcg');

		if (keyword) {
			sortF.findKeyword.value = keyword;
		}
		if (upcg) {
			sortF.upcg.value = upcg;
		}
		if (downcg) {
			sortF.downcg.value = downcg;
		}
		//alert(keyword);
		sortF.submit();
	}

	// html dom ??? ??? ????????? ??? ????????????.
	$(document).ready(function() {
		// memu ????????? ?????? ????????? ?????? a ????????? ???????????????
		$(".menu>a").click(function() {
			// ?????? ????????? ????????? a ?????? ?????????
			// a ?????? ????????? ul ????????? hide ????????? ????????? ????????? ????????? ??????.
			$(this).next("div").toggleClass("hide");
		});
	});
	
	const prodRead = function(pidx){
		let url = "prod/read";
		
		$.ajax({
			url: url,
			type:'post',
			data:'pidx='+pidx,
			dataType:'json',
			cache:false,
			beforeSend : function(xhr){
				xhr.setRequestHeader(header, token);
			}
			,
			success:function(res){
				//alert(res);
				if(res>0){
					location.href="prod/"+pidx;
				}
			},
			error:function(err){
				alert("err:"+err.status);
			}
		})
	}
</script>

<body oncontextmenu='return false' class='snippet-body'>
	<div class="container">
		<div class="main">
			<!-- ?????? ???????????? ?????? -->
			<div class="menu1" style="">
				<!-- ?????? ???????????? ?????? -->
				<c:forEach var="k" begin="1" end="4">
					<c:forEach var="upcategory" items="${upcategory}">
						<c:if test="${upcategory.upCg_code eq k }">
							<div class="menu2">
								<a href="#">${upcategory.upCg_name}</a>
								<div class="sub">
									<!-- ????????????  -->
									<div class="list-group-item <c:if test='${paging.upcg eq k and empty paging.downcg }'>active</c:if>">
										<a class="btn btn-default" href="${myctx}/prod?upcg=${k}">????????????</a>
									</div>
									<c:forEach var="downcategory" items="${downcategory}">
										<c:if test="${downcategory.upCg_code eq k }">
											<div class="list-group-item <c:if test='${downcategory.upCg_code eq paging.upcg and downcategory.downCg_code eq paging.downcg}'>active</c:if>">
												<a class="btn btn-default" href="${myctx}/prod?upcg=${downcategory.upCg_code}&downcg=${downcategory.downCg_code}"> ${downcategory.downCg_name} </a>
											</div>
										</c:if>
									</c:forEach>

								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
		<div style="height: 60px; padding-top: 6em;"></div>
		<!-- ?????? ???????????? -->
		<div>
			<c:forEach var="upcategory" items="${upcategory}">
				<c:if test='${upcategory.upCg_code eq paging.upcg}'>
					<h4 style="text-align: center;">- ${upcategory.upCg_name} -</h4>
				</c:if>
			</c:forEach>
			<c:forEach var="downcategory" items="${downcategory}">
				<c:if test='${downcategory.upCg_code eq paging.upcg and downcategory.downCg_code eq paging.downcg}'>
					<h2 style="text-align: center; color: #39b559; padding: 0px 0px 80px;">${downcategory.downCg_name}</h2>
				</c:if>
			</c:forEach>
		</div>
		<div id="upCategory" class='container row'>
			<!-- ?????? ?????? ??????-->
			<div style="text-align: center; font-size: 25px;">
				<c:if test="${not empty paging.findKeyword}">
					<b>${paging.findKeyword}</b> ??? ????????? ??????
				</c:if>
			</div>
		</div>
		<br>
		<div style="height: 70px;"></div>
		<!-- ?????? -->
		<div style="margin-bottom: 5px;">
			<c:forEach var="cg_detail" items="${cgdetail}">
				<c:if test='${cg_detail.downCg_code eq paging.downcg}'>
					<div class="tip" style="text-align: right;  font-weight: 500; margin:0 7px 25px; color: #00000082">${cg_detail.comment_name}
						<div class="tiptext tip-bottom" style="margin-right:0;">${cg_detail.comments}</div>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<!-- ?????? ????????????  -->
		<div class="row">
			<form name="sortF" id="sortF" style="margin: -12px;float: right;">
				<input type="hidden" name="findKeyword"> <input type="hidden" name="upcg"> <input type="hidden" name="downcg">
				<select class="form-select" style="width: 124px; margin-left: auto;" name="sort" onchange="changeSort(this.value)">
					<option value="1" <c:if test="${paging.sort eq 1}">selected</c:if>>?????????</option>
					<option value="2" <c:if test="${paging.sort eq 2}">selected</c:if>>?????????</option>
					<option value="3" <c:if test="${paging.sort eq 3}">selected</c:if>>???????????????</option>
					<option value="4" <c:if test="${paging.sort eq 4}">selected</c:if>>???????????????</option>
				</select>
			</form>
		</div>
		<!-- ?????? ?????? -->
		<div class="row ml-3 mb-3 mr-0" style="display: inline;">
			<form name="searchF" id="searchF" class="d-flex" role="search">
				<!-- ?????? ????????? -->
				<span style="margin-left: 2em;">??? <b>${paging.totalCount}</b>?????? ?????? <!-- ????????? -->
				</span> <input name="findKeyword" class="form-control me-2" type="search" placeholder="???????????? ???????????????" aria-label="Search" style="width: 25%; margin-left: auto">
				<button class="btn btn-outline-success" type="submit">??????</button>
			</form>
		</div>
		<!-- ?????? ?????? ?????? -->
		<div class="row">
			<c:if test="${prodArr eq null or empty prodArr}">
				<h1>????????? ????????????</h1>
			</c:if>
			<c:forEach var="prod" items="${prodArr}">
				<div class="col-lg-3 col-sm-6 d-flex flex-column align-items-center justify-content-center product-item my-3">
					<div class="product">
						<a onclick="prodRead('${prod.pidx}')"><img src="${myctx}/resources/product_images/${prod.pimageList[0].pimage}"></a>
						<!-- <ul class="d-flex align-items-center justify-content-center list-unstyled icons">
							<li class="icon"><span class="fas fa-expand-arrows-alt"></span></li>
							<li class="icon mx-3"><span class="far fa-heart"></span></li>
							<li class="icon"><span class="fas fa-shopping-bag"></span></li>
						</ul> -->
					</div>
					<div class="tag ${prod.pspec}">${prod.pspec}</div>
					<div class="title pt-4 pb-1">
						<h5>${prod.pname}</h5>
					</div>
					<!-- <div class="d-flex align-content-center justify-content-center">
						??????
						<span class="fas fa-star"></span> <span class="fas fa-star"></span>
						<span class="fas fa-star"></span> <span class="fas fa-star"></span>
						<span class="fas fa-star"></span>
					</div> -->
					<div class="price text-center" style="margin: 0">
						<c:if test="${prod.price ne prod.psaleprice }">
							<del>
								<small style="color: gray;"><fmt:formatNumber value="${prod.price}" pattern="###,###,### ???" /></small>
							</del>
							<b><fmt:formatNumber value="${prod.psaleprice}" pattern="###,###,### ???" /></b>
							<br>
							<span class="badge bg-danger" style="width:40px; font-weight: 400; font-size: 13px;">${prod.percent}%</span>
						</c:if>
						<c:if test="${prod.price eq prod.psaleprice }">
							<b><fmt:formatNumber value="${prod.psaleprice}" pattern="###,###,### ???" /></b>
						</c:if>
						
						<div style="height:60px;">
						</div>

					</div>
				</div>
			</c:forEach>
		</div>
		<!-- ????????? ??????  -->
		<div id="pageNavi">${pageNavi}</div>
	</div>
</body>
</html>

<%@ include file="/WEB-INF/views/foot.jsp"%>