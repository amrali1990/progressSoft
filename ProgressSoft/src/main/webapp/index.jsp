<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload CSV File</title>
</head>
<body>
	<div align="center">

		<form action="upload" name="upload" method="post" enctype="multipart/form-data">
			<table border="5px" width="300" align="center" bgcolor="#FEF">
				<tr>
					<td colspan=2 style="font-size: 12pt;" align="center">
						<h3>Choose File to Upload</h3>
					</td>
				</tr>
				<tr>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type="submit"
						name="submit" value="Upload"></td>
				</tr>
			</table>
		</form>
		<br /> <br />
		<form method="post" name="search" action="search">
			<table border="5px" width="300" align="center" bgcolor="#FEF">
				<tr>
					<td colspan=2 style="font-size: 12pt;" align="center">
						<h3>Search File</h3>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="filename" id="filename" style="width: 290px;"></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type="submit" name="submit" value="Search"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
