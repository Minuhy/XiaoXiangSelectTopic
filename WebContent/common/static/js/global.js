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
        if (h.version) {
            var i, e = h.version.split(".").reverse(), o = layui.v.split(".").reverse();
            if (layui.each(e, function(e, t) {
                parseInt(o[e]) > parseInt(t) ? i = !0 : parseInt(o[e]) < parseInt(t) && (i = !1)
            }),
            !i)
                return;
            l.open({
                type: 1,
                title: "\u66f4\u65b0\u63d0\u793a",
                closeBtn: !1,
                area: "300px;",
                shade: !1,
                offset: "8px",
                id: "LAY_updateNotice",
                btn: ["\u66f4\u65b0\u65e5\u5fd7", "\u6211\u77e5\u9053\u4e86"],
                btnAlign: "c",
                moveType: 1,
                content: ['<div class="layui-text">', 'Layui \u5df2\u53d1\u5e03\u65b0\u7248\u672c\uff1a\uff1a<strong style="padding-right: 10px; color: #fff;">v' + layui.v + "</strong>", "</div>"].join(""),
                skin: "layui-layer-notice",
                yes: function(e) {
                    l.close(e),
                    setTimeout(function() {
                        location.href = ("v2" === p.pathname[0] || "layui.github.io" === location.hostname ? "/v2" : "") + "/docs/base/changelog.html"
                    }, 500)
                },
                end: function() {
                    layui.data("layui", {
                        key: "version",
                        value: layui.v
                    })
                }
            })
        }
        layui.data("layui", {
            key: "version",
            value: layui.v
        })
    }),
    _ = a(['<select lay-search lay-filter="component">', '<option value="">\u641c\u7d22\u7ec4\u4ef6\u6a21\u5757</option>', '<option value="element/layout.html">grid \u6805\u683c\u5e03\u5c40</option>', '<option value="element/layout.html#admin">layout admin \u5e03\u5c40</option>', '<option value="element/color.html">color \u989c\u8272</option>', '<option value="element/icon.html">iconfont \u5b57\u4f53\u56fe\u6807</option>', '<option value="base/element.html#css">font \u5b57\u4f53\u5927\u5c0f \u989c\u8272</option>', '<option value="element/anim.html">animation \u52a8\u753b</option>', '<option value="element/button.html">button \u6309\u94ae</option>', '<option value="element/form.html">form \u8868\u5355\u7ec4</option>', '<option value="element/form.html#input">input \u8f93\u5165\u6846</option>', '<option value="element/form.html#select">select \u4e0b\u62c9\u9009\u62e9\u6846</option>', '<option value="element/form.html#checkbox">checkbox \u590d\u9009\u6846</option>', '<option value="element/form.html#switch">switch \u5f00\u5173</option>', '<option value="element/form.html#radio">radio \u5355\u9009\u6846</option>', '<option value="element/form.html#textarea">textarea \u6587\u672c\u57df</option>', '<option value="element/nav.html">nav \u5bfc\u822a\u83dc\u5355</option>', '<option value="element/menu.html">menu \u57fa\u7840\u901a\u7528\u83dc\u5355</option>', '<option value="element/nav.html#breadcrumb">breadcrumb \u9762\u5305\u5c51</option>', '<option value="element/tab.html">tabs \u9009\u9879\u5361</option>', '<option value="element/progress.html">progress \u8fdb\u5ea6\u6761</option>', '<option value="element/panel.html">panel \u9762\u677f / card</option>', '<option value="element/collapse.html">collapse \u6298\u53e0\u9762\u677f/\u624b\u98ce\u7434</option>', '<option value="element/table.html">table \u8868\u683c\u5143\u7d20</option>', '<option value="element/badge.html">badge \u5fbd\u7ae0</option>', '<option value="element/timeline.html">timeline \u65f6\u95f4\u7ebf</option>', '<option value="element/auxiliar.html#blockquote">blockquote \u5f15\u7528\u5757</option>', '<option value="element/auxiliar.html#fieldset">fieldset \u5b57\u6bb5\u96c6</option>', '<option value="element/auxiliar.html#hr">hr \u5206\u5272\u7ebf</option>', '<option value="modules/layer.html">layer \u5f39\u51fa\u5c42/\u5f39\u7a97\u7efc\u5408</option>', '<option value="modules/laydate.html">laydate \u65e5\u671f\u65f6\u95f4\u9009\u62e9\u5668</option>', '<option value="modules/laypage.html">laypage \u5206\u9875</option>', '<option value="modules/laytpl.html">laytpl \u6a21\u677f\u5f15\u64ce</option>', '<option value="modules/table.html">table \u6570\u636e\u8868\u683c</option>', '<option value="modules/form.html">form \u8868\u5355\u6a21\u5757</option>', '<option value="modules/upload.html">upload \u6587\u4ef6/\u56fe\u7247\u4e0a\u4f20</option>', '<option value="modules/dropdown.html">dropdown \u4e0b\u62c9\u83dc\u5355</option>', '<option value="modules/dropdown.html#contextmenu">contextmenu \u53f3\u952e\u83dc\u5355</option>', '<option value="modules/transfer.html">transfer \u7a7f\u68ad\u6846</option>', '<option value="modules/tree.html">tree \u6811\u5f62\u83dc\u5355</option>', '<option value="modules/colorpicker.html">colorpicker \u989c\u8272\u9009\u62e9\u5668</option>', '<option value="modules/element.html">element \u5e38\u7528\u5143\u7d20\u64cd\u4f5c</option>', '<option value="modules/slider.html">slider \u6ed1\u5757</option>', '<option value="modules/rate.html">rate \u8bc4\u5206</option>', '<option value="modules/carousel.html">carousel \u8f6e\u64ad/\u8dd1\u9a6c\u706f</option>', '<option value="modules/layedit.html">layedit \u5bcc\u6587\u672c\u7f16\u8f91\u5668</option>', '<option value="modules/flow.html">flow \u4fe1\u606f\u6d41/\u56fe\u7247\u61d2\u52a0\u8f7d</option>', '<option value="modules/util.html">util \u5de5\u5177\u96c6</option>', '<option value="modules/code.html">code \u4ee3\u7801\u6587\u672c\u884c\u4fee\u9970</option>', '<option value="https://' + m.devHost + '/themes/layim/">layim</option>', '<option value="https://' + m.devHost + '/themes/layuiAdmin/">layuiadmin</option>', "</select>", '<i class="layui-icon layui-icon-search"></i>'].join("")),
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
    }), y = (a.get("https://" + m.devHost + "/get/cors/spread/", function(e) {
        for (var t in e)
            f[t] && f[t](e[t])
    }, "jsonp"),
    _ = a(".site-menu .layui-menu-body-title"),
    s = a(".site-demo-nav dd"),
    t = p.pathname.join("/"),
    a(".layui-header .layui-nav-item").each(function() {
        var e = a(this)
          , t = p.pathname.concat();
        if ("v2" === t[0] && t.splice(0, 1),
        t[0] && t[0] === e.data("dir"))
            return e.addClass("layui-this"),
            !1
    }),
    _.each(function() {
        var e = a(this);
        if (e.children("a").attr("href") === "/" + t)
            return e.parent().addClass("layui-menu-item-checked2"),
            !1
    }),
    s.each(function() {
        var e = a(this);
        if (e.find(">a").attr("href") === "/" + t)
            return e.addClass("layui-this"),
            !1
    }),
    a(".site-demo-table-nav .layui-nav-item").each(function() {
        var e = a(this);
        if (e.find(">a").attr("href") === "/" + t)
            return e.addClass("layui-this"),
            !1
    }),
    _ = ['<a href="https://beian.miit.gov.cn/" target="_blank" rel="nofollow">\u8d63ICP\u59072021008982\u53f7</a>', '\u672c\u7ad9\u6258\u7ba1\u4e8e<a href="https://gitee.com/" target="_blank">Gitee Pages</a>'],
    s = a("#LAY-footer-info"),
    /layuion\.com/.test(location.hostname) ? s.append(_[0]) : s.append(_[1]),
    a(".site-showv").html(layui.v),
    a("#getStars"));
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
    y[0] && a.get("https://api.github.com/repos/layui/layui", function(e) {
        y.html(e.stargazers_count)
    }, "json"),
    u.fixbar({
        showHeight: 60,
        css: function() {
            if ("demo" === global.pageType)
                return {
                    bottom: 75
                }
        }()
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
