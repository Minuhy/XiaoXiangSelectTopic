var imgId;
var loadAvatar;
var chooseAvatar;

layui.use('flow', function () {
    var flow = layui.flow;
    var $ = layui.jquery;

    function t0(i) {
        if (i < 10) {
            return '00' + i;
        } else if (i < 100) {
            return '0' + i;
        } else {
            return i;
        }
    }

    loadAvatar = function loadAvatar() {
        flow.load({
            elem: '#avatarImg' //流加载容器
            , scrollElem: '#avatarImg' //滚动条所在元素，一般不用填，此处只是演示需要。
            , done: function (page, next) { //执行下一页的回调

                //模拟数据插入
                setTimeout(function () {
                    var lis = [];
                    var imgIds = []; // 记录下ID
                    for (var i = 0; i < 10; i++) {

                        // 最多70个头像，从0开始到69
                        imgId = imgId % 70;

                        lis.push('<img id="h' + t0(imgId) + '" width="70" height="70" src="' + currentPath + 'common/lectopic/img/h' + t0(imgId) + '.gif" alt="预选头像' + t0(imgId) + '">');
                        imgIds.push('#h' + t0(imgId));


                        imgId++;
                    }

                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(lis.join(''), page < 7); //假设总页数为 10

                    // 添加监听
                    for (var i = 0; i < imgIds.length; i++) {

                        $(imgIds[i]).on('click', function () {
                            var imgs = $(this).parent().find('img'); //获取所有img
                            if (imgs.hasClass('img-choose')) { //判断这些img 有没有Class ‘on'’
                                imgs.removeClass('img-choose');//把class on 移除 
                            }
                            $(this).addClass('img-choose');//点击的tr 添加 on class 用于改变样式
                            window.chooseAvatar = $(this).attr('id');

                            console.log('选择的头像：' + window.chooseAvatar);
                        });
                    }

                }, 500);
            }
        });
    }
});

layui.use('layer', function () { //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

    //触发事件
    var active = {

        chooseAvatar: function () {
            //示范一个公告层
            layer.open({
                type: 1
                , title: "\u8bf7\u9009\u62e9\u4f60\u7684\u5934\u50cf" // 请选择你的头像
                , closeBtn: false
                , area: '61%;'
                , shade: 0.6
                , id: 'ca_layer' //设定一个id，防止重复弹出
                , btn: ['\u786e\u5b9a', '\u53d6\u6d88']
                , btnAlign: 'r'
                , moveType: 1 //拖拽模式，0或者1
                , content: '<div id="avatarImg" class="avatar-img"> </div>'
                , success: function () {
                    // 开启懒加载
                    loadAvatar();
                    var max = 645456;
                    var min = 2;
                    imgId = Math.floor(Math.random() * (max - min + 1)) + min;
                }
                , yes: function () {
                    console.log('保存的头像：' + window.chooseAvatar);
                    $("#avatarInput").attr("value", window.chooseAvatar+".gif");
                    $("#userAvatarImg").attr("src", currentPath + "common/lectopic/img/" + window.chooseAvatar + ".gif");
                    layer.closeAll();
                }
                , btn2: function () {
                    layer.closeAll();
                }
            });
        }
    };

    $('#chooseAvatar').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });


});
