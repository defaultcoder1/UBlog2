<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UBlog - Login</title>
<script type="text/javascript" src="/UBlog/assets/JS/jquery-1.9.1.min.js"></script>

<style type="text/css">
	body {margin:0px; background-color:#FFF; background-image:url(/UBlog/assets/IMG/bg.JPG);
		background-repeat:no-repeat; background-size:1366px 668px;}
	#header {width:100%; background-image:-webkit-linear-gradient(top, #222, #000); padding-top:10px; 
		padding-bottom:10px;}
	#logo {display:block; height:30px; margin:0 auto;}
	#login_container {position:absolute; top:80px; right:80px; width:300px;
		background-image:url(/UBlog/assets/IMG/trans_black.png); padding:20px; border-radius:2px;
		box-shadow:0px 0px 20px #FFF;}
	#login_container_t {width:100%;}
	#login_container input {padding:5px; border:3px solid #FFF; border-radius:2px;
		font-family:Arial,sans-serif; font-size:12px; padding-left:30px; outline-color:#507EAD; color:#424242;}
	#login_email {width:85%; background-image:url(/UBlog/assets/IMG/user.png); background-repeat:no-repeat;
		background-position:5px 5px;}
	#login_password {width:80%; background-image:url(/UBlog/assets/IMG/lock.png); background-repeat:no-repeat;
		background-position:5px 5px;}
	#login_submit {position:relative; margin-left:-3px; width:100%; border:none; border-radius:2px; padding:8px;
		background-image:-webkit-linear-gradient(top, #6493C4, #507EAD); color:#FFF; font-family:Arial,sans-serif;
		font-size:12px; cursor:pointer;}
	#login_submit:active {background-image:-webkit-linear-gradient(top, #507EAD, #6493C4);}
	
	#register_container {position:absolute; top:210px; right:80px; width:300px;
		background-image:url(/UBlog/assets/IMG/trans_black.png); padding:20px; border-radius:2px;
		box-shadow:0px 0px 20px #FFF;}
	#register_container_t {width:100%;}
	#register_container input {width:90%; padding:5px; border:3px solid #FFF; border-radius:2px;
		font-family:Arial,sans-serif; font-size:12px; padding-left:10px; outline-color:#507EAD; color:#424242;}
	#register_submit {width:100px; border:none; border-radius:2px; padding:10px;
		background-image:-webkit-linear-gradient(top, #6493C4, #507EAD); color:#FFF; font-family:Arial,sans-serif;
		font-size:12px; cursor:pointer; margin-top:20px;}
	#register_submit:active {background-image:-webkit-linear-gradient(top, #507EAD, #6493C4);}
</style>
</head>

<body>
	<div id="header"><img id="logo" src="/UBlog/assets/IMG/logos/logo_white.png" /></div>
	<div id="login_container">
	
		<form action="Login" method="post">
		<table id="login_container_t" cellspacing="2" border="0">
			<tr><td align="center" colspan="2"><input id="login_email" type="text" name="email" spellcheck="false" placeholder="Email" /></td></tr>
			<tr>
				<td width="80%" align="center"><input id="login_password" type="password" name="password" spellcheck="false" placeholder="Password" /></td>
				<td width="20%"><button type="submit" id="login_submit">Log in</button></td>	
			</tr>
		</table>
		</form>
	</div>
	
	<div id="register_container">
		<form action="Register" method="post">
		<table id="register_container_t" cellspacing="2" border="0">
			<tr><td align="center"><input type="text" spellcheck="false" placeholder="First Name" name = "rfname"/></td></tr>
			<tr><td align="center"><input type="text" spellcheck="false" placeholder="Last Name" name = "rlname"/></td></tr>
			<tr><td align="center"><input type="text" spellcheck="false" placeholder="Name of Blog" name = "rbname"/></td></tr>
			<tr><td align="center"><input type="text" spellcheck="false" placeholder="Email" name = "remail"/></td></tr>
			<tr><td align="center"><input type="password" spellcheck="false" placeholder="Password" name = "rpassword"/></td></tr>
			<tr><td align="center"><input type="password" spellcheck="false" placeholder="Repeat Password" name = "rrpassword"/></td></tr>
			<tr><td align="center"><button type="submit" id="register_submit">Sign up</button></td></tr>
		</table>
		</form>
	</div>
</body>
</html>
