var captchaPicRefresh = 0;
var captchaPicSrc = '';


layui.use('layer', function () { //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

    $('#btnHelp').on('click', function () {
        layer.open({
            title: notHaveAccountTitle
            , content: notHaveAccountContent
            , shade: 0.7
            , icon: 6
        });
    });



	$('#captchaImg').click(function(){
		var captchaPic = $('#captchaImg')
	    // 动作触发后执行的代码!!
	    console.log('更新验证码~');
	    if (captchaPicRefresh === 0) {
	        captchaPicSrc = captchaPic.attr('src');
	        console.log('图片地址：' + captchaPicSrc);
	    }
	    captchaPicRefresh += 1;
	    captchaPic.attr('src', captchaPicSrc + '?t=' + captchaPicRefresh);
	});

});


