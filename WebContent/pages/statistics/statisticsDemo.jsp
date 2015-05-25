<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${applicationScope.staticurl}js/jquery-1.8.0/jquery-1.8.0.js"></script>
<script src="${applicationScope.staticurl}js/My97DatePicker/WdatePicker.js"></script>
<script src="${applicationScope.staticurl}js/raphael-min.js"></script>
<script src="${applicationScope.staticurl}js/morris.min.js"></script>
<script src="${applicationScope.staticurl}js/statistics/getdate.js"></script>
<script>
	var templeteRate;
	var templetePlay;
	var templeteAdmin;

	$(document).ready(function() {
		templeteRate = $("#topRate").clone();
		templetePlay = $("#topPlay").clone();
		templeteAdmin = $("#topAdmin").clone();
		demo(0);

		$("[name='subjectType']").on("change", function(e) {
			getChart();
		});
	});

	function getChart() {
		var subjectType;
		subjectType = $('input[name="subjectType"]:checked').val();
		if (subjectType == null) {
			layer.msg("请先选择想要的图表类型吧！");
			return;
		}
		var createTimebefor;
		var createTimeafter;
		createTimebefor = $("#timeStart").val();
		createTimeafter = $("#timeEnd").val();

		if (createTimebefor == null || createTimebefor =="" || createTimeafter == null || createTimeafter == "") {
			layer.msg("请先筛选时间区间");
		} else {
            $.post("${pageContext.request.contextPath}/statistics/getstatisticschart.do", {
                createTimebefor : getWeekStartDate(),
                createTimeafter : getWeekEndDate()
            }, function(data) {
                console.log(data);
                if (data.resultcode == "000") {
                	Morris.Line({
                        element : 'annual',
                        data : data.statisticsSubjects,
                        xkey : 'disTime',
                        ykeys : [ 'total' ],
                        labels : [ 'subjectName' ]
                    });
                }
            }, "json");
		}
	}

	function demo(e) {
		var lastTimebefor;
		var lastTimeafter;
		var createTimebefor;
		var createTimeafter;
		switch (e) {
		case 0:
			$("#week").addClass("a_xz_s50415");
			$("#month").removeClass();
			$("#quarter").removeClass();
			$("#year").removeClass();
			createTimebefor = getWeekStartDate();
			createTimeafter = getWeekEndDate();
			break;
		case 1:
			$("#week").removeClass();
			$("#month").addClass("a_xz_s50415");
			$("#quarter").removeClass();
			$("#year").removeClass();
			createTimebefor = getMonthStartDate();
			createTimeafter = getMonthEndDate();
			break;
		case 2:
			$("#week").removeClass();
			$("#month").removeClass();
			$("#quarter").addClass("a_xz_s50415");
			$("#year").removeClass();
			createTimebefor = getQuarterStartDate();
			createTimeafter = getQuarterEndDate();
			break;
		case 3:
			$("#week").removeClass();
			$("#month").removeClass();
			$("#quarter").removeClass();
			$("#year").addClass("a_xz_s50415");
			createTimebefor = nowYear;
			createTimeafter = nowYear + 1;
			break;
		default:
			break;
		}
		$.post("${pageContext.request.contextPath}/statistics/getstatisticssubject.do", {
			createTimebefor : createTimebefor,
			createTimeafter : createTimeafter
		}, function(data) {
			if (data.resultcode == "000") {
				if (data.statisticsTopRate.length > 0) {
					$("#distopRate").html("");
					$("#topRate").remove();
					$(data.statisticsTopRate).each(function(i, n) {
						var templateTemp = templeteRate.clone();
						templateTemp.find("#topRatePic").attr("src", "${applicationScope.staticurl} " + n.subjectPicture);
						templateTemp.find("#topRateIndex").html(i + 1);
						templateTemp.find("#topRateName").html(n.subjectName);
						templateTemp.find("#topRateName").attr("onclick", "javascript:window.location.href='" + " " + "'");
						$("#distopRate").append(templateTemp.html());
					});
				} else {
					$("#distopRate").html("没有新评分");
				}

				if (data.statisticsTopSubject.length > 0) {
					$("#distopPlay").html("");
					$("#topPlay").remove();
					$(data.statisticsTopRate).each(function(i, n) {
						var templateTemp = templetePlay.clone();
						templateTemp.find("#topPlayPic").attr("src", "${applicationScope.staticurl} " + n.subjectPicture);
						templateTemp.find("#topPlayIndex").html(i + 1);
						templateTemp.find("#topPlayName").html(n.subjectName);
						templateTemp.find("#topPlayName").attr("onclick", "javascript:window.location.href='" + " " + "'");
						$("#distopPlay").append(templateTemp.html());
					});
				} else {
					$("#topPlay").remove();
					$("#distopPlay").html("没有找到热门课程");
				}

				if (data.statisticsTopAdmin.length > 0) {
					$("#distopAdmin").html("");
					$("#topAdmin").remove();
					$(data.statisticsTopAdmin).each(function(i, n) {
						var templateTemp = templeteAdmin.clone();
						templateTemp.find("#topAdminPic").attr("src", "${applicationScope.staticurl} " + n.adminPicture);
						templateTemp.find("#topAdminIndex").html(i + 1);
						templateTemp.find("#topAdminName").html(n.departmentName + "——" + n.adminName);
						templateTemp.find("#topAdminTotal").html("更新发布" + n.total + "个课程");
						$("#distopAdmin").append(templateTemp.html());
					});
				} else {
					$("#topAdmin").remove();
					$("#distopAdmin").html("没有找到发布人");
				}
			}
			console.log(data.statisticsTopRate);

		}, "json");

	}
</script>
<title>统计分析--各部门课程统计</title>
</head>
<body>


	<!--头部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/top.jsp" />
	<!--头部结束-->

	<!--左侧开始-->
	<jsp:include page="${pageContext.request.contextPath}/statistics/getindexstatistics.do">
		<jsp:param name="leftIndex" value="0" />
	</jsp:include>
	<!--左侧结束-->

	<!--右侧开始-->

	<div class="right_content_s50407 fl">
		<!--各部门课程统计分析-->
		<div class="right_video_s50407">
			<div class="right_vireo_title_s50407">
				<h3>各部门课程统计分析</h3>
			</div>
			<div class="analysis_data_s50415">
				<ul>
					<li class="a_xz_s50415" id="week"><a href="javascript:demo(0);">本周</a></li>
					<li id="month"><a href="javascript:demo(1);">本月</a></li>
					<li id="quarter"><a href="javascript:demo(2);">本季度</a></li>
					<li id="year"><a href="javascript:demo(3);">本年度</a></li>
				</ul>
				<p>
					更新课程数<b>54</b>
				</p>
			</div>

			<div class="score_top_s50415">

				<ul class="score_pr_s50415">
					<li class="score_one_s50415">评分<span>top3</span></li>
					<div id="distopRate"></div>
					<div id="topRate">
						<li><p>
								<b id="topRateIndex">1</b><span id="topRateName"></span>
							</p>
							<p>
								<img src="${applicationScope.staticurl}img/video_zwt_s50408.jpg" id="topRatePic" width="210" height="130" alt="课程图片" />
							</p></li>
					</div>
				</ul>

				<ul class="score_pr_s50415">
					<li class="score_two_s50415">热门<span>top3</span></li>
					<div id="distopPlay"></div>
					<div id="topPlay">
						<li><p>
								<b id="topPlayIndex">1</b><span id="topPlayName"></span>
							</p>
							<p>
								<img src="${applicationScope.staticurl}img/video_zwt_s50408.jpg" id="topPlayPic" width="210" height="130" alt="课程图片" />
							</p></li>
					</div>
				</ul>

				<ul class="score_fb_s50415">
					<li class="score_three_s50415">发布人<span>top3</span></li>
					<div id="distopAdmin"></div>
					<div id="topAdmin">
						<li><p>
								<b id="topAdminIndex">1</b><span id="topAdminName"></span>
							</p> <i id="topAdminTotal">更新发布10</i>
							<p>
								<img src="${applicationScope.staticurl}img/big_head_s50407.png" id="topAdminPic" width="122" height="122" alt="个人头像">
							</p></li>
					</div>
				</ul>
				<div class="cls"></div>

			</div>

			<div class="screening_interval_s50415">
				<p>
					时间区间 <input id="timeStart" type="text" onfocus="WdatePicker({isShowWeek:true})" /> 至 <input id="timeEnd" type="text"
						onfocus="WdatePicker({isShowWeek:true})" />
				</p>

				<p>
					<span><select name="" class="select_167x26_s50415"><option value="">请筛选部门</select></span> <span><input type="radio"
						name="subjectType" value="0" /></span>新开课程 <span><input type="radio" name="subjectType" value="1" /></span>更新课程 <span><input
						type="radio" name="subjectType" value="2" /></span>完结课程 <span><input type="radio" name="subjectType" value="3" /></span>浏览量
				</p>
				<div class="linear_graph_s50415" id="annual"></div>

			</div>

		</div>
		<!--各部门课程统计分析-->

	</div>
	<!--右侧结束-->

	<!--底部开始-->
	<jsp:include page="${pageContext.request.contextPath}/pages/bottom.jsp" />
	<!--底部-->
</body>
</html>