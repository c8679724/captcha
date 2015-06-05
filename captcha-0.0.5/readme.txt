mvn deploy:部署项目到maven私服

------------------------------------------------------------------------------------------
简介:



------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
使用:
	
			Captcha captcha = icaptcha.generateCaptcha();
            logger.debug("验证码:{}", captcha.getCaptcha());

            try {
                ImageIO.write(captcha.getCaptchaImage(), "png", out);
            } catch (FileNotFoundException e) {
                logger.warn("", e);
            } catch (IOException e) {
                logger.warn("", e);
            }
            
一、选图片验证码：
	1、在需要验证码的位置添加下面的html代码：
		<!-- name属性随系统而变化，id不能变，captcha.js通过id进行定位 -->
		<div style="position: relative;" id="securityImageDiv">
			<img id="securityImage" src="index.jspl?method=yanCode" width="290" height="200" /> 
			<input type="hidden" name="code" id="securityImageInput" />
		</div>
					
					
	2、在验证码页面链接 captchaImage/chooseImageGenerator文件夹里面的js和css（captchaImage在源代码src/test/resources里面，jar里面没有）
		<link rel="stylesheet" href="js/captchaImage/chooseImageGenerator/captcha_chooseImageGenerator.css" type="text/css" />
		<script type="text/javascript" src="js/captchaImage/chooseImageGenerator/captcha_chooseImageGenerator.js"></script>

	
二、点击图片模糊区域验证码
	1、在需要验证码的位置添加下面的html代码：
		<!-- name属性随系统而变化，id不能变，captcha.js通过id进行定位 -->
		<div style="position: relative;" id="securityImageDiv" class='captcha'>
			<div class='reload-normal up' ></div>
			<img id="securityImage" src="${ctx}/index.jspl?method=yanCode"
				width="290" height="200" /> <input type="hidden" name="code"
				id="securityImageInput" />
		</div>
	2、在验证码页面链接 captchaImage/chooseImageNotch文件夹里面的js和css（captchaImage在源代码src/test/resources里面，jar里面没有）
		<link rel="stylesheet" href="js/captchaImage/chooseImageNotch/captcha_chooseImageNotch.css" type="text/css" />
		<script type="text/javascript" src="js/captchaImage/chooseImageNotch/captcha_chooseImageNotch.js"></script>

----------------------------欢迎使用并向我们反馈建议。------------------------------------