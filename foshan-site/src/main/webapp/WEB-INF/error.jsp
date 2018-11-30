<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath()+""; 
request.setAttribute("path",rc);
%>
<html>
<head>
	<title>错误界面</title>
	<link href="${path}/css/errorStyle.css" rel="stylesheet" type="text/css" />
	<link href='//fonts.googleapis.com/css?family=Josefin+Sans:400,100,100italic,300,300italic,400italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script> 
	<!-- For-Mobile-Apps-and-Meta-Tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Flat Error Page Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- //For-Mobile-Apps-and-Meta-Tags -->
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script> 
</head>
<body>
	<div class="main">
		<div class="agile">
			<div class="agileits_main_grid">
				<!-- <div class="agileits_main_grid_left">
					<h1>光纤传输网</h1>
				</div> -->
				<!-- <div class="agileits_main_grid_right">
					<div class="menu">
						<span class="menu-icon"><a href="#"><img src="images/menu-icon.png" alt=""></a></span>	
							<ul class="nav1">
								<li><a href="#">Home</a></li>
								<li><a href="#">About</a></li>
								<li><a href="#">Services</a></li>
								<li><a href="#">Gallery</a></li>
								<li><a href="#">Contact</a></li>
							</ul> 	
							script-for-menu
							 <script>
							   $( "span.menu-icon" ).click(function() {
								 $( "ul.nav1" ).slideToggle( 300, function() {
								 // Animation complete.
								  });
								 });
							</script>
							/script-for-menu
					</div>
				</div> -->
				<div class="clear"> </div>
			</div>
			<div class="w3l">
				<div class="text">
					<h1>页面错误</h1>	
					
					<!-- <p>You have been tricked into click on a link that can not be found. Please check the url or go to <a href="#">main page</a> and see if you can locate what you are looking for</p> -->
				</div>
				<div class="image">
					<img src="${path}/images/smile.png">
				</div>
				<div class="clear"></div>
			</div>
				<!-- <div class="footer">
					<p>&copy; 2016 Flat Error Page. All Rights Reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
				</div>
				<div class="wthree">
					<div class="back">
						<a href="#">Back to home</a>
					</div>
					<div class="social-icons w3agile">
						<ul>
							<li>Follow us on :</li>
							<li><a href="#" class="facebook"><img src="images/fb.png" title="facebook" alt="facebook"></a></li>
							<li><a href="#" class="twitter"><img src="images/tw.png" title="twitter" alt="twitter"></a></li>
							<li><a href="#" class="googleplus"><img src="images/gp.png" title="googleplus" alt="googleplus"></a></li>
							<div class="clear"></div>
						</ul>	
					</div>
					<div class="clear"></div>
				</div> -->
		</div>
	</div>
</body>
</html>