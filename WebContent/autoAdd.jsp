<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态添加按钮</title>



<script type="text/javascript">
	function addFile() {
		var div1 = document.getElementById("div1");
		/* 		div1.innerHTML +=  "<div><input type='file' name='photo' /><input type='button' value='删除' onclick='delFile(this)'/><br /></div>";
		 */
		div1.innerHTML += "<div><input type='file' name='phot o' /><input type='button' value='删除' onclick='onDelete(this)'/></div>";
	}

	function onDelete(input) {
		input.parentNode.parentNode.removeChild(input.parentNode);
	}
</script>
</head>


<body>
	<form action="">
		<div id="div1">
			<div>
				<input type="file" name="photo" /> <input type="button" value="添加"
					onclick="addFile()" /> <input type="button" value="删除"
					onclick="onDelete(this)" />
			</div>
		</div>
	</form>
</body>
</html>