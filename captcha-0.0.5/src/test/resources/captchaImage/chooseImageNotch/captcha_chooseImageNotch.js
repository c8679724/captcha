/**
 * 选择图片中的模糊区域的一种验证码
 */
$(function() {
	var securityImageSrc = $("#securityImage").attr("src");
	$(".captcha").click(function(event) {
		var p = $(".captcha");
		var offset = p.offset();
		// $(".cls1").html( "left: " + offset.left + ", top: " + offset.top );
		// $(".cls").append('当前鼠标坐标:(' + event.pageX + ', ' + event.pageY +
		// ')');
		// 父节点的位置
		var parentX = offset.left, parentY = offset.top;
		// 鼠标点击的位置
		var mouseX = event.pageX, mouseY = event.pageY;
		// 计算相对父节点的偏移量
		var x = mouseX - parentX - 2, y = mouseY - parentY - 2;

		if (x > 0 && y > 40 && x < 288 && y < 190) {
			var child = $("<div class='mouse'></div>");
			child.css({
				left : x,
				top : y
			});
			p.append(child);
			child.click(function(event) {
				$(this).remove();
				event.stopPropagation();
			});
		}
		getVal();
	});
	$(".reload-normal").mouseup(function() {
		$(this).removeClass("down");
		$(this).addClass("up");
	});

	$(".reload-normal").mousedown(function() {
		$(this).removeClass("up");
		$(this).addClass("down");

		var tmp = Math.random();
		$("#securityImage").attr("src", securityImageSrc + "&random=" + tmp);

		var list = $(".mouse");
		for (var i = 0; i < list.length; i++) {
			list.eq(i).remove();
		}
		getVal();
	});

	function getVal() {
		var list = $(".mouse");
		var length = list.length;
		var array = "", top, left;
		for (var i = 0; i < length; i++) {
			left = list.eq(i).css("left");
			top = list.eq(i).css("top");
			if (left == null || left == undefined || top == null
					|| top == undefined) {
			} else {
				left = left.substr(0, left.length - 2);
				top = top.substr(0, top.length - 2);
				array = array + left + "," + (top - 40) + ";";
			}
		}
		$("#securityImageInput").val(array);
		console.log(array.toString());
	}
})
