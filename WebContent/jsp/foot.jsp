<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String currentPath = request.getContextPath();%>

<!-- 页脚 开始 -->
<div class="layui-footer footer footer-demo text-c">
	<p>由 <a class="color-purple" href="https://space.bilibili.com/32778000">敏Ymm</a> 使用 <a class="color-purple" href="https://www.java.com/zh-CN/">Java</a> 制作</p>
</div>
<!-- 页脚 开始 -->

<!-- 返回顶部按钮 -->
<script>
    window.global = {
        pageType: 'demo', // 让其在电脑模式下会上60px以避开页脚
        preview: function () {
            // var preview = document.getElementById('LAY_preview');
            // return preview ? preview.innerHTML : '';
        	return '';
        }()
    };
</script>

<!-- 手机侧边栏打开开关 -->
<div class="site-tree-mobile layui-hide">
    <i class="layui-icon layui-icon-spread-left"></i>
</div>

<!-- 手机侧边栏关闭区域 -->
<div class="site-mobile-shade"></div>


<!-- 引入JavaScript脚本文件 -->
<!-- 放置在文档末尾，以便页面加载更快 -->

<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
    <script src="<%= currentPath %>/common/html5shiv-r29/html5.min.js" type="text/javascript"></script>
    <script src="<%= currentPath %>/common/respond.js-1.4.2/respond.min.js" type="text/javascript"></script>
<![endif]-->

<!-- layui -->
<script src="<%= currentPath %>/common/layui-2.7.6/layui.js" type="text/javascript"></script>

<script>
    // 引入 Layui 模块
    layui.config({
        base: '<%= currentPath %>/common/static/js/'
        , version: '1999'
    }).use('global');
</script>

<!-- 退出登录的代码 -->
<script>
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        //触发事件
        var active = {
            logout: function () {
                layer.open({
                    type: 1
                    , title: "退出登录" //显示标题栏
                    , closeBtn: false
                    , area: '260px;'
                    , shade: 0.8
                    , time: 20000 //20s后自动关闭
                    , id: 'logout_tips' //设定一个id，防止重复弹出
                    , btn: ['确定', '取消']
                    , btnAlign: 'c'
                    , moveType: 0 //拖拽模式，0或者1
                    , content: '<div style="text-align:center;padding:20px;font-size:18px;">确定要退出吗？</div>'
					, btn2: function (index, layero) {
                            $("#logoutLi").removeClass("layui-this");
                            console.log('取消退出');
                            return true;
                    }
                    , btn1: function (index, layero) {
                        window.location.href = "<%= currentPath %>/user/logout";
                        console.log('退出登录');
                        return true;
                    }
                });
            }
        };

        $('#logoutBtn').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
</script>



