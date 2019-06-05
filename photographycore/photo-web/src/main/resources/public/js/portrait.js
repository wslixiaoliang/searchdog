
<!--页面初始化入口-->
$(document).ready(function () {

    <!--初始化调用-->
    portraitInfos();

    <!--点击事件查询调用-->
    $("#search").on("click", function () {
        portraitInfos();
    });


});

    <!--首页条件查询入口-->
    function portraitInfos(){
        var page = 1;
        var limit = 10;
        var total;
        var pageCount = 0;
        var yushu = 0;
        var chineseName = $("#chineseName").val();<!--获取搜索关键词-->
        $.ajax({
            type: "post",
            url: "/famous/portrait/getPortraitInfos",
            data: {
                "chineseName":chineseName,
                "page": page,
                "limit": limit
            },
            cache: false,
            sync:false,
            success: function (result) {
                var firstUrl = "/famous/portrait/downLoad?fileName=";
                var a = 1;
                var b = 10;
                var c = 100;

                var list = result.beans;
                total = result.count;
                yushu = (total % limit);
                pageCount = (total - yushu) / limit;
                if (yushu > 0) {
                    pageCount += 1;
                }

                //分页插件调用
                $(".tcdPageCode").createPage({
                    pageCount: pageCount,
                    current: page,
                    backFn: function () {
                        portraitInfos(page);
                    }
                });

                if (null != list) {
                    for (var i = 0; i < list.length; i++) {
                        var x = "portrait" + (i + a);
                        var y = "porImg" + (i + b);
                        var z = "chname" + (i + c);
                        var portraitName = list[i].portraitName;
                        var finalUrl = firstUrl + portraitName;
                        var famousId = list[i].famousId;
                        var chineseName = list[i].chineseName;

                        <!--头像div设置-->
                        var portrait = document.createElement("div");
                        portrait.setAttribute("class", x);
                        portrait.setAttribute("style", "float:left;width:250px;height:250px;margin:20px 0;margin:0 20px;text-align: center");

                        <!--图片标签设置-->
                        var proImg = document.createElement("img");
                        proImg.setAttribute("class", y);
                        proImg.setAttribute("src", finalUrl);
                        proImg.setAttribute("style", "width:150px;height:150px;border:solid 20px #666666");
                        portrait.appendChild(proImg);

                        <!--a标签设置-->
                        var a = document.createElement("a")
                        a.setAttribute("href", 'production.html?famousId=' + famousId + '&portraitName=' + portraitName);
                        a.innerText = chineseName;

                        <!--p标签设置-->
                        var p = document.createElement("p");
                        p.setAttribute("class", z);
                        p.appendChild(a);<!--将a放入p中-->
                        portrait.appendChild(p); <!--将p放入div中-->

                        <!--添加到父级标签-->
                        var portraits = document.getElementById("portraits");
                        portraits.appendChild(portrait);

                    }

                }

            }

        });




    };






