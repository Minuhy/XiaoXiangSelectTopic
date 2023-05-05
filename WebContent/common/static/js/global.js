layui.define(["code", "element", "table", "util", "carousel", "laytpl"], function(e) {
    function n(e, o) {
        var t, i, n = layui.data("layui");
        e = e || {},
        d.mobile || (n = n[t = "notice_topnav_" + e.key] && (new Date).getTime() - n[t] < (e.tipsInterval || 18e5),
        e.tips || l.close(l.tipsIndex),
        !n && e.tips && (i = l.tipsIndex = l.tips(['<a href="' + e.url + '" target="_blank" style="display: block; line-height: 30px; padding: 10px; text-align: center; font-size: 14px; background-image: linear-gradient(to right,#8510FF,#D025C2,#FF8B2D,#FF0036); color: #fff; ' + (e.tipsCss || "") + '">', e.desc || "", "</a>"].join(""), o, {
            tips: e.tipsStyle ? new Function("return " + e.tipsStyle)() : [3, "#9F17E9"],
            skin: "layui-hide-xs",
            maxWidth: 320,
            time: 0,
            anim: 5,
            tipeMore: !0,
            success: function(e, t) {
                e.find(".layui-layer-content").css({
                    padding: 0
                }),
                e.find("a").on("click", function() {
                    o.trigger("click")
                });
                var i = e.find(".layui-layer-TipsG");
                "5px" !== i.css("left") && i.hide(),
                "none" === o.parent().css("display") && (e.css({
                    left: "50%",
                    top: "80px",
                    "margin-left": -e.width() / 2
                }),
                i.hide())
            }
        }),
        o.on("click", function() {
            layui.data("layui", {
                key: t,
                value: (new Date).getTime()
            }),
            l.close(i)
        })))
    }
    var t, i, a = layui.jquery, o = layui.element, l = layui.layer, s = layui.form, u = layui.util, r = layui.carousel, c = layui.laytpl, d = layui.device(), p = layui.url(), m = (a(window),
    a("body"),
    self !== parent && (location.href = "//www.baidu.com/"),
    d.ie && d.ie < 8 && l.alert("Layui \u6700\u4f4e\u652f\u6301 IE8\uff0c\u800c\u60a8\u5f53\u524d\u4f7f\u7528\u7684\u662f\u53e4\u8001\u7684 IE" + d.ie + "\uff0c\u4f53\u9a8c\u5c06\u4f1a\u4e0d\u4f73\uff01"),
    {
        getBrowser: function() {
            function e(e, t) {
                var i, o = navigator.mimeTypes;
                for (i in o)
                    if (o[i][e] && -1 !== o[i][e].indexOf(t))
                        return 1
            }
            var t = navigator.userAgent.toLocaleLowerCase();
            return t.match(/chrome/) ? e("type", "360") || e("type", "sogou") ? void 0 : t.match(/edg\//) ? "edge" : "chrome" : t.match(/firefox/) ? "firefox" : void 0
        },
        openWin: function(e) {
            var t = (e = e || {}).window || window.open(e.url || "", e.target, e.specs);
            e.url || (t.document.open("text/html", "replace"),
            t.document.write(e.content || ""),
            t.document.close())
        },
        devHost: "127.0.0.1"
    }), h = (a("#LAY_home"),
    layui.data("layui")), v = m.getBrowser(), f = (l.ready(function() {
        layui.data("layui", {
            key: "version",
            value: layui.v
        })
    }),
    _ = a("<div></div>"),
    a(".component").append(_),
    s.render("select", "LAY-site-header-component"),
    s.on("select(component)", function(e) {
        var t = e.value;
        /^(\/|http)/.test(t) ? (m.openWin({
            url: t,
            target: "_blank"
        }),
        e.elem.value = "") : location.href = ("v2" === p.pathname[0] ? "/v2" : "") + "/docs/" + t
    }),
    {
        headerNotice: function(i) {
            var e, o = a(".site-notice");
            !d.mobile && o[0] && v && (i = i || [],
            i = layui.sort(i, "sort", !0),
            e = ["{{# if(d.length > 0){ }}", '<div class="layui-carousel" id="layui-spm-event-parter" lay-filter="site-top-carousel">', "<div carousel-item>", "{{# layui.each(d, function(index, v){ ", 'var tg = v.ad ? "tg" : "";', 'var style = v.tipsCss || "";', "}}", "<div>", '<a href="{{ v.url }}" target="_blank" class="{{ tg }} tg-{{ v.key }}" data-tips="{{ v.tips }}">', '<cite style="{{ style }}">{{ v.title }} {{# if(v.hot){ }}<span class="layui-badge-dot" style="margin-top: -5px;"></span>{{# } }}</cite>', "</a>", "<style>", "{{# if(v.ad){ }} .site-notice a.tg-{{ v.key }} cite{padding-right:25px;} {{# } }}", '{{# if(v.ad){ }}.site-notice a.tg-{{ v.key }}:after{content:"{{ v.ad }}"} {{# } }}', "</style>", "</div>", "{{# }); }}", "</div>", "</div>", "{{# } }}"].join(""),
            c(e).render(i, function(e) {
                var t = ".site-notice .layui-carousel";
                o.html(e),
                r.render({
                    elem: t,
                    width: "100%",
                    height: "100%",
                    arrow: "none",
                    indicator: "none",
                    anim: "fade",
                    interval: 5e3
                }),
                n(i[0], a(t).children("div").children("div").eq(0).find("a")),
                r.on("change(site-top-carousel)", function(e) {
                    n(i[e.index], e.item.find("a"))
                })
            }))
        },
        headerNav: function(e) {
            var t = a("#LAY_NAV_TOP");
            "chrome" !== v && "firefox" !== v || t[0] && (e = (e = (e = e || [])[0] || {}).content) && (t.append(e),
            t.find(".layui-nav-bar").remove(),
            t.find(".layui-nav-item").off("mouseenter").off("mouseleave"),
            o.render("nav"))
        },
        popupNotive: function(e) {
            var t = (e = (e = e || [])[0] || {}).content;
            !t || h.popup_notice && (new Date).getTime() - h.popup_notice < (e.tipsInterval || 2592e5) || setTimeout(function() {
                l.open({
                    type: 1,
                    title: e.title || "\u516c\u544a",
                    area: d.mobile ? ["90%", "90%"] : ["800px", "520px"],
                    shade: !1,
                    id: "LAY_Notice",
                    skin: "site-popup-notice",
                    resize: !1,
                    content: t,
                    success: function(e, t) {
                        e.find("a").on("click", function() {
                            l.close(t)
                        })
                    },
                    end: function() {
                        layui.data("layui", {
                            key: "popup_notice",
                            value: (new Date).getTime()
                        })
                    }
                })
            }, 500)
        }
    });
    function g() {
        var e = a(window).scrollTop();
        a(window).width() <= 992 || (a(".footer").offset().top,
        a(window).height(),
        60 < e ? i.hasClass("site-fix") || i.addClass("site-fix").css({
            width: i.parent().width()
        }) : i.hasClass("site-fix") && i.removeClass("site-fix").css({
            width: "auto"
        }))
    }
    u.fixbar({
        showHeight: 60,
        css: function() {
            if ("demo" === global.pageType)
                return {
                    bottom: 75
                }
        }(),
        click: function(){
            console.log('返回顶部')
        }
    }),
    i = a(".site-menu"),
    g(),
    a(window).on("scroll", g),
    a(window).on("scroll", function() {
        var e = a(".layui-table-tips");
        e[0] && e.remove(),
        a(".layui-layer")[0] && l.closeAll("tips")
    }),
    layui.code({
        elem: "pre"
    });
    /* 向返回顶部按钮添加类 开始 */
    var backTops = a('li[lay-type$="top"]').parent('ul');
    console.log(backTops);
    for(var i=0;i<backTops.length;i++){
        a(backTops[i]).addClass("back-top-btn"); 
    }
    /* 向返回顶部按钮添加类 结束 */
    function b() {
        var e;
        k[0] && (e = x.val(),
        k[0].srcdoc = e)
    }
    var w = a(".site-dir")
      , x = (w[0] && 750 < a(window).width() && (l.ready(function() {
        l.open({
            type: 1,
            content: w,
            skin: "layui-layer-dir",
            area: "auto",
            maxHeight: a(window).height() - 300,
            title: "\u76ee\u5f55",
            closeBtn: !1,
            offset: "r",
            shade: !1,
            success: function(e, t) {
                l.style(t, {
                    marginLeft: -15
                })
            }
        })
    }),
    w.find("li").on("click", function() {
        var e = a(this);
        e.find("a").addClass("layui-this"),
        e.siblings().find("a").removeClass("layui-this")
    })),
    a("body").on("keydown", "#LAY_editor, .site-demo-text", function(e) {
        9 === e.keyCode && window.getSelection && (e.preventDefault(),
        function(e) {
            var t = this.selectionStart
              , i = this.selectionEnd
              , o = t + e.length;
            this.value = this.value.substring(0, t) + e + this.value.substring(i),
            this.setSelectionRange(o, o)
        }
        .call(this, "  "))
    }),
    a("#LAY_editor"))
      , k = a("#LAY_demo")
      , s = (a("#LAY_demoCodes")[0],
    a("#LAY_demo_run").on("click", b),
    b(),
    u.toVisibleArea({
        scrollElem: a(".layui-side-scroll").eq(0),
        thisElem: a(".site-demo-nav").find("dd.layui-this")
    }),
    u.toVisibleArea({
        scrollElem: a(".layui-side-scroll").eq(1),
        thisElem: a(".site-demo-table-nav").find("li.layui-this")
    }),
    a(function() {
        var e = a(".site-demo-body>.layui-tab-item").eq(1)
          , t = a('<textarea class="layui-border-box site-demo-text site-demo-code" id="LAY_democode" spellcheck="false" readonly></textarea>');
        t.val(["<!DOCTYPE html>", "<html>", "<head>", '  <meta charset="utf-8">', "  <title>\u793a\u4f8b\u6f14\u793a</title>", '  <meta name="renderer" content="webkit">', '  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">', '  <meta name="viewport" content="width=device-width, initial-scale=1">', "  \x3c!-- \u6ce8\u610f\uff1a\u9879\u76ee\u6b63\u5f0f\u73af\u5883\u8bf7\u52ff\u5f15\u7528\u8be5\u5730\u5740 --\x3e", '  <link href="//unpkg.com/layui@' + layui.v + '/dist/css/layui.css" rel="stylesheet">', "</head>", "<body>", global.preview, "\x3c!-- \u6ce8\u610f\uff1a\u9879\u76ee\u6b63\u5f0f\u73af\u5883\u8bf7\u52ff\u5f15\u7528\u8be5\u5730\u5740 --\x3e", '<script src="//unpkg.com/layui@' + layui.v + '/dist/layui.js"><\/script>', a("#LAY_democodejs").html(), "</body>\n</html>"].join("\n")),
        e.html(t)
    }),
    o.on("tab(demoTitle)", function(e) {
        1 === e.index && d.ie && d.ie < 9 && l.alert("\u5f3a\u70c8\u4e0d\u63a8\u8350\u4f60\u901a\u8fc7ie8/9 \u67e5\u770b\u4ee3\u7801\uff01\u56e0\u4e3a\uff0c\u6240\u6709\u7684\u6807\u7b7e\u90fd\u4f1a\u88ab\u683c\u5f0f\u6210\u5927\u5199\uff0c\u4e14\u6ca1\u6709\u6362\u884c\u7b26\uff0c\u5f71\u54cd\u9605\u8bfb")
    }),
    a(".site-tree-mobile"))
      , _ = a(".site-mobile-shade");
    s.on("click", function() {
        a("body").addClass("site-mobile")
    }),
    _.on("click", function() {
        a("body").removeClass("site-mobile")
    }),
    m.getIconData = function() {
        var t = layui.$
          , i = [];
        t(".site-doc-icon li").each(function() {
            var e = t(this);
            i.push({
                title: t.trim(e.find(".doc-icon-name").text()),
                fontclass: t.trim(e.find(".doc-icon-fontclass").text()),
                unicode: t.trim(e.find(".doc-icon-code").html())
            })
        }),
        t(".site-h1").html('<textarea style="width: 100%; height: 600px;">' + JSON.stringify(i) + "</textarea>")
    }
    ,
    e("global", m)
});
