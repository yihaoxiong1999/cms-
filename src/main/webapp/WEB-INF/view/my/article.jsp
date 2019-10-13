<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<title>文章详情</title>
<script
	src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
<div style="float: right;">
					<div class="col-md-3" >
							<div class="card" style="width: 18rem;">
								<div class="card-body">
									<h3 class="card-title">点击排行榜</h3>
									<ul>
									<c:forEach items="${articleabc}" var="l">
									<li>
										<h6><a href="/article/selectByUser?id=${l.id }" target="_blank">${l.title }</a></h6>
								  </li>
									</c:forEach>
									</ul>
								</div>
							</div>
					</div>
					<div class="col-md-3" >
							<div class="card" style="width: 18rem;">
								<div class="card-body">
									<h3 class="card-title">评论排行榜</h3>
									<ul>
									<c:forEach items="${articleab }" var="l">
									<li>
										<h6><a href="/article/selectByUser?id=${l.id }" target="_blank">${l.title }</a></h6>
								  </li>
									</c:forEach>
									</ul>
								</div>
							</div>
					</div>
</div>

	<div class="container" style="float: center">
				
				<div>
					<!-- 热门文章 -->
								<c:forEach items="${hotArticles}" var="h">
									<ul class="list-unstyled">
										<li class="media"><img src="/pic/${h.picture }" class="mr-3"
											alt="..." style="height: 124px; width: 190px">
											<div class="media-body">
												<h5 class="mt-0 mb-1"><a href="/article/selectByUser?id=${h.id }" target="_blank">${h.title }</a></h5>
												${h.user.nickname } &nbsp;
												<fmt:formatDate value="${h.updated }"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</div></li>
										<hr />
								</c:forEach>
				</div>
				
	<div>
		<!-- <button class="btn btn-info" onclick="up()">上一篇</button>
		<button class="btn btn-info" onclick="down()">下一篇</button> -->
	</div>
		<dl>
			<dt>
				<h1 align="center">${article.title }</h1>
			</dt>
			<dd>${article.user.nickname}
				&nbsp;
				<fmt:formatDate value="${article.created }"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</dd>
			<dd>
				<hr />
			</dd>
			<dd>
			${article.content }
			
			</dd>

		</dl>
		
		<!-- 从session获取当前登录对象,如果不为空，则说明用户登录 -->
		<c:if test="${sessionScope.user!=null}">
		
	
		<h1>评论</h1>
<hr/>
  <div>
  <form id = "form1">
  	<textarea rows="10" cols="155" name="content"></textarea>
  	<input  type="hidden" name="article.id" value="${article.id}">
  	<input  type="hidden" name="article.comments" value="${article.comments}">
  	
  	<!-- 评论 -->
  	<input type="button" value="评论" onclick="addcomments()"  style="background-color: pink">
  	<button type="reset" class="but btn-warning" >重置</button>
  </form>
  </div>
  		</c:if>
  		
  		<c:if test="${sessionScope.user==null}">
  		
		<h4 style="color: red">请登录后再评论</h4>		
  		</c:if>
  		
  		<hr/>
  <br>
			<div>
				<dl>
					<c:forEach items="${comments}" var="c">
						<dt>${c.user.nickname }&nbsp;
						<fmt:formatDate value="${c.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</dt>
						<dd>${c.content }</dd>
						<hr>
					</c:forEach>
					<c:if test="${comments}!=null">
						${pages}
					</c:if>
				</dl>
			</div>
			${pages }
	</div>
</body>

<script type="text/javascript">
	function addcomments(){
		var id = ${article.id};//获取文章的ID
		
		$.post("/article/comment",$("#form1").serialize(),function(flag){
			if(flag){
				alert("评论成功");
				var comments = ${article.comments};
				comments++;
				location.reload();//重新加载页面
			}else{
				alert("评论失败.请检查是否登录");
			}
		})

	}
	
	$(function(){
		//翻页
		$(".page-link").click(function(){
			//获取点击的url
			var url =$(this).attr("data");
			//在中间区域加载url
			location.href=url+"&id="+'${article.id}';
			
		})
	})
function up(){
		$.get("/article/checkPro",{id:'${article.id}',channelId:'${article.channelId}'},function(obj){
			if(obj){//如果有上一篇.
				location.href="/article/selectPro?id="+'${article.id}'+"&channelId="+'${article.channelId}'
			}else{
				alert("最后一篇!")
			}
		})
	}
function down(){
	$.get("/article/checkNext",{id:'${article.id}',channelId:'${article.channelId}'},function(obj){
		if(obj){//如果有下一篇.
			location.href="/article/selectNext?id="+'${article.id}'+"&channelId="+'${article.channelId}'
	
		}else{
			alert("最后一篇!")
			}
		})
	}
</script>
</html>