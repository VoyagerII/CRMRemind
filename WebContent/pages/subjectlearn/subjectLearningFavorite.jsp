<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<link href="${applicationScope.staticurl}css/public_paging.css"
	rel="stylesheet" type="text/css" />
<script>
    var subjectlitemplate = null;
	var page = 1;
	$(document).ready(function() {
		subjectlitemplate = $("#subjectul").clone();
		$("#subjectlitemplate").remove();
		getdata();
	});
	
	function getdata(){
		
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/subjectlearningaction/getallsubjectfavorite.do",
			data : {
				pageno : page
			},
			dataType : "json",
			success : function(data) {
				if(data.resultcode == "000"){
					$.ajax({
						type : "post",
						url : "${applicationScope.webhost}pages/common/pageno_javascript.jsp",
						dataType : "html",
						data : {
							pageno : page,
							totalpage : data.totalPages,
							jsfunc : "relists",
							tagid : "pages1",
							jsparam : "{}"
						},
						success : function(data, status) {
							$("#jspageshtml").html(data);
						}
					});
					insertInfo(data);
				} else {
					alert(data.resultmsg);
				}
			}
		});
	}
	function relists(p, param) {
		page = p;
		getdata();
	}

	function insertInfo(data) {
		$("#subjectlitemplate").remove();
		$("#learningSubjectNum").html(data.SubjectLearningListBean.length);
		var num = 0;
		$(data.SubjectLearningListBean)
				.each(
						function(i, val) {

							var template = subjectlitemplate.clone();
							template.find("#isFavoriteChange").attr(
									"href",
									"javaScript:isFavoriteChange("
											+ val.subjectId + ","
											+ val.isFavorite + ")");
							template.find("#subjectPicture").attr("src",
									val.subjectPicture);
							if (0 == val.isFavorite) {
								template
										.find("#isFavorite")
										.attr("src",
												"${applicationScope.staticurl}img/heart_gray_s50408.png");
								template.find("#isFavorite").attr("id",
										"isFavorite" + val.subjectId);
							} else {
								template
										.find("#isFavorite")
										.attr("src",
												"${applicationScope.staticurl}img/heart_red_s50408.png");
							}
							template.find("#subjectName").html(
									"课程名称：" + val.subjectName);
							template.find("#videoName").html(
									"更新情况：" + val.videoName);

							template.find("#adminName").html(
									"发布人：" + val.adminName);

							template.find("#creatTime").html(
									"更新时间：" + val.lastTime);

							if (val.subjectName.length < 5) {
								template.find("#subjectName2").html(
										"课程名称：" + val.subjectName);
							} else {
								template.find("#subjectName2").html(
										"课程名称："
												+ val.subjectName.substring(0,
														5) + "...");
							}

							if (val.videoNameLearning.length < 5) {
								template.find("#videoNameLearning").html(
										"最近学习进度：" + val.videoNameLearning);
							} else {
								template.find("#videoNameLearning").html(
										"最近学习进度："
												+ val.videoNameLearning
														.substring(0, 5)
												+ "...");
							}

							template.find("#creatTimeLearning").html(
									"最近学习时间：" + val.lastTimeLearning);

							template.find("#subjectName").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#videoName").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#adminName").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#creatTime").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#subjectName2").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#videoNameLearning").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);
							template.find("#creatTimeLearning").attr("href",
									"videoLearning.jsp?subjectId="+val.subjectId);

							var score = val.subjectScore;
							var starsnum = (val.subjectScore / 2).toFixed(1);
							var starsString = "";
							for (var i = 0; i < 5; i++) {
								if (starsnum > 1) {
									starsString += '<span><img src="${applicationScope.staticurl}img/stars_big_s50410.png" width="12" height="12" alt="点亮的星星"/></span>';
								} else if (starsnum > 0 && starsnum < 1) {
									starsString += '<span><img src="${applicationScope.staticurl}img/stars_ban_s50408.png" width="12" height="12" alt="半亮的星星"/></span>';
								} else {
									starsString += '<span><img src="${applicationScope.staticurl}img/stars_big_h_s50410.png" width="12" height="12" alt="不亮的星星 "/></span>';
								}
								starsnum = starsnum - 1;
							}
							template.find("#starsnum").html(starsString);

							template.find("#playnum").html(
									val.subjectPlay + "次");
							$("#subjectul").append(template.html());

						});
		$(".video_content_s50408").hover(function() {
			$(this).find(".video_dask_s50408").stop().delay(50).animate({
				"top" : 0
			}, 300)
		}, function() {
			$(this).find(".video_dask_s50408").stop().animate({
				"top" : 300
			}, 300)
		}

		)
	}
	function isFavoriteChange(subjectId, isFavorite) {
		$
				.ajax({
					type : "post",
					url : "/subjectlearningaction/changesubjectfavorite.do",
					dataType : "json",
					data : {
						subjectId : subjectId,
						isFavorite : (isFavorite + 1) % 2
					},
					success : function(data) {
						if (data.resultcode == 000) {
							if (0 == isFavorite) {
								$("#isFavorite" + subjectId)
										.attr("src",
												"${applicationScope.staticurl}img/heart_gray_s50408.png");
							} else {
								$("#isFavorite" + subjectId)
										.attr("src",
												"${applicationScope.staticurl}img/heart_red_s50408.png");
							}
						} else {
							alert(data.resultmsg);
						}
					}
				});
	}
</script>
<title>正在学习的课程</title>
</head>
<body>
	<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp">
		<jsp:param name="demo" value="0" />
	</jsp:include>
	<!--头部结束-->

	<!--左侧开始-->
	<jsp:include
		page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="1" />
	</jsp:include>
	<!--左侧结束-->

	<!--最近学习-->
	<div class="right_video_s50407" style="border-color: red">
		<div class="right_vireo_title_s50407">
			<h3>
				正在学习的课程<b id="learningSubjectNum"></b>
			</h3>
		</div>
		<div class="video_course_s50408">
			<ul id="subjectul">
				<li class="ml43" id="subjectlitemplate">
					<div class="video_content_s50408">
						<img id="subjectPicture"
							src="${applicationScope.staticurl}img/video_zwt_s50408.jpg"
							width="208" height="130" alt="视频占位图" />
						<ul class="video_dask_s50408">
							<li class="video_img_s50408"><a
								href="javaScript:isFavoriteChange()" id="isFavoriteChange"><img
									id="isFavorite"
									src="${applicationScope.staticurl}img/heart_red_s50408.png"
									width="20" height="17" alt="收藏--红心" /></a></li>
							<li><a href="#" id="subjectName">课程名称：</a></li>
							<li><a href="#" id="videoName">更新情况：</a></li>
							<li><a href="#" id="adminName">发布人：</a></li>
							<li><a href="#" id="creatTime">更新时间：</a></li>
						</ul>
					</div>

					<ul class="video_introduce_s50408">
						<li><a href="#" id="subjectName2">课程名称：</span></a></li>
						<li><a href="#" id="videoNameLearning">最近学习进度：<span
								id="subjectName"></span></a></li>
						<li><a href="#" id="creatTimeLearning">最近学习时间：2015.3.31</a></li>
						<li>
							<div class="stars_play_s50408">
								<ul>
									<li id="starsnum"><span><img
											src="${applicationScope.staticurl}img/stars_h_s50408.png"
											width="12" height="12" alt="点亮的星星" /></span> <span><img
											src="${applicationScope.staticurl}img/stars_h_s50408.png"
											width="12" height="12" alt="点亮的星星" /></span> <span><img
											src="${applicationScope.staticurl}img/stars_gary_s50408.png"
											width="12" height="12" alt="灰色的星星" /></span> <span><img
											src="${applicationScope.staticurl}img/stars_gary_s50408.png"
											width="12" height="12" alt="灰色的星星" /></span></li>
									<li class="stars_playli_s50408"><img
										src="${applicationScope.staticurl}img/play_s50408.png"
										width="13" height="9" alt="播放" /><b id="playnum">400次</b></li>
								</ul>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div><br/>
<div class="cls"></div>
	<div id="jspageshtml" style="margin-bottom: 10px " align="center"></div>
	<!--分页开始-->
		
		<!--分页结束-->
	<!--最近学习结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
<script>
	$(function() {
		$(".video_content_s50408").hover(function() {
			$(this).find(".video_dask_s50408").stop().delay(50).animate({
				"top" : 0
			}, 300)
		}, function() {
			$(this).find(".video_dask_s50408").stop().animate({
				"top" : 300
			}, 300)
		}

		)
	})
</script>
</html>