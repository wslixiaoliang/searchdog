
    var page = 1;
    var limit = 5;
    var total = 0;
    var pageCount = 0;
    var productionContent ="";

    //初始化时加载
    $(document).ready(function () {

        getProductionInfos(page);//初始化页面，page默认为1

        searchProductionInfos();//点击按钮搜索

        pressEnterSearch();//按下回车键：搜索

    });



    /**
     * 点击按钮：搜索
     */
    function searchProductionInfos() {

        $("#search").click(function () {
            getProductionInfos(page);
        });
    }


    /**
     * 按下回车键：搜索（与点击按钮搜索功能相同）
     */
    function pressEnterSearch(){
        $("#productionContent").keypress(function(event) {
            if (event.keyCode == 13) {
                getProductionInfos(page);
            }
        });
    }

    function getProductionInfos(page) //动态page，自动获取点击的page页码
    {
        productionContent = $("#productionContent").val();<!--获取搜索关键词-->
        $.ajax({
            type: "post",
            url: "/famous/production/getProductionInfos",
            dataType: "json",
            data:{
                "productionContent":productionContent,
                "page":page,
                "limit":limit
            },
            success: function (result) {

                var list = result.beans;
                total = result.count;
                var yushu = (total % limit);
                pageCount = (total-yushu)/limit;
                if(yushu> 0){
                    pageCount +=1;
                }
                if (null != result && list.length > 0) {

                    var str = "";

                    var proListDiv = document.createElement("div");//总div
                    proListDiv.setAttribute("class","proListDiv");
                    proListDiv.setAttribute("style", "width: 1000px;height: 1600px;color: #666666");

                    for (var i = 0; i < list.length; i++) {

                        var a = "summary" + i;
                        var s = "pImg" + i;
                        var u = "titleDiv" + i;
                        var v = "pName" + i;
                        var w = "fChineseName" + i;
                        var x = "fEnglishName" + i;
                        var y = "pYear" + i;
                        var z = "pContent" + i;
                        var firstUrl = "/famous/portrait/downLoad?fileName=";
                        var finalUrl = firstUrl + list[i].portraitName;
                        var productionId = list[i].productionId;

                        str += '<div class="proDiv"'+i+' style="width:1000px;height:180px;color: #666666;margin-left:220px">' +
                                        '<div class='+u+' style="width:160px;height:150px;float:left;margin-right:20px">' +
                                            '<img class='+s+' style=" width:55px; height:55px; border-radius:50%" src='+finalUrl+'>' +
                                            '<p class='+w+' style=" font-family:\'微软雅黑\'; font-size:14px">'+list[i].chineseName+'</p>' +
                                            '<p class='+x+' style=" font-family:\'Microsoft YaHei\'; font-size:14px ">'+list[i].englishName+'</p>' +
                                            '<p class='+y+' style=" font-family:\'楷体\'; font-style:oblique; font-size:14px">'+list[i].publishedYear+'</p>' +
                                            '<p class='+v+' style=" font-family:\'微软雅黑\'; font-size:14px">'+list[i].productionName+'</p>' +
                                        '</div>' +
                                        '<div class='+z+' style="width:600px;height:150px;float:left;color:#666;margin-top:30px">' +
                                            '<a class='+a+' href="production.html?productionId='+productionId+'&productionContent='+productionContent+'" style="text-decoration:none;color:#666666;font-size:16px;font-family:\'微软雅黑\'" onmouseover="this.style.color=\'black\'" onmouseout="this.style.color=\'#666666\'" onmousedown="this.style.color=\'#666666\'">'+list[i].summaryInfo+'</a>' +
                                        '</div>' +
                              '</div>';
                        }

                    var listDiv = str+'<div class="tcdPageCode"></div>';
                    $("#productListDiv").html(listDiv);//将所有div内容加入body,使用html形式
                }

                //分页插件调用
                $(".tcdPageCode").createPage({
                    pageCount: pageCount,
                    current: page,
                    backFn:getProductionInfos
                });
            }

        });
    }







