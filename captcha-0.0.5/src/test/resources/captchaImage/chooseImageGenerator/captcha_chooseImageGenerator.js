var securityImageStutas = new Array();
var index = 0;
var securityImageSelect = "";
var securityImageSrc = null;

$(function() {
	for (var i = 0; i < 8; i++) {
		securityImageStutas[i] = false;
	}
	securityImageSrc = $("#securityImage").attr("src");

	var divHtml = "	<div class=\"yanzheng\">							<div class=\"yanzheng-top\">								<span class=\"shuaxin\" onclick=\"shuaxin();\"></span>							</div>							<div class=\"yanz-list\">								<a href=\"#\" id=\"securityImage0\" onclick=\"selectImage(this);\"									class=\"notSelect\"></a><a href=\"#\" id=\"securityImage1\"									onclick=\"selectImage(this);\" class=\"notSelect\"></a><a href=\"#\"									id=\"securityImage2\" onclick=\"selectImage(this);\"									class=\"notSelect\"></a><a href=\"#\" id=\"securityImage3\"									onclick=\"selectImage(this);\" class=\"notSelect\"></a><a href=\"#\"									id=\"securityImage4\" onclick=\"selectImage(this);\"									class=\"notSelect\"></a><a href=\"#\" id=\"securityImage5\"									onclick=\"selectImage(this);\" class=\"notSelect\"></a><a href=\"#\"									id=\"securityImage6\" onclick=\"selectImage(this);\"									class=\"notSelect\"></a><a href=\"#\" id=\"securityImage7\"									onclick=\"selectImage(this);\" class=\"notSelect\"></a>							</div>						</div>";
	$("#securityImageDiv").append(divHtml);
});

// 点击图片事件
function selectImage(o) {
	index = o.id.replace("securityImage", "");
	if ($(o).hasClass("notSelect")) {
		$(o).removeClass("notSelect");
		$(o).addClass("select");
		securityImageStutas[index] = true;
	} else {
		$(o).removeClass("select");
		$(o).addClass("notSelect");
		securityImageStutas[index] = false;
	}

	securityImageSelect = "";
	for (var i = 0; i < securityImageStutas.length; i++) {
		if (securityImageStutas[i]) {
			securityImageSelect += i + "";
		}
	}
	$("#securityImageInput").val(securityImageSelect);
}

// 刷新按钮
function shuaxin() {
	var tmp = Math.random();
	$("#securityImage").attr("src", securityImageSrc + "&random=" + tmp);
	
	for (var i = 0; i < securityImageStutas.length; i++) {
		if (!$("#securityImage" + i).hasClass("notSelect")) {
			$("#securityImage" + i).removeClass("select");
			$("#securityImage" + i).addClass("notSelect");
		}
	}
}