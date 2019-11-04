
//初始化时加载
$(document).ready(function () {

    var page = 1;
    var limit = 5;
    var total = 0;
    var pageCount = 0;
    getProductionInfos(page);//初始化调用，page默认为1
    function getProductionInfos(page) //动态page，自动获取点击的page页码
    {
        $.ajax({
            type: "post",
            url: "/famous/production/getProductionInfos",
            dataType: "json",
            data:{
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
                    proListDiv.setAttribute("style", "width: 1000px;height: 1600px;color: #666666;margin：0,auto");

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


                        str += '<div class="proDiv"'+i+' style="width: 1000px;height: 400px;color: #666666;margin:20px,20px">' +
                                        '<div class='+u+' style="width:200px;height:200px;">' +
                                            '<img class='+s+' style="width:55px;height:55px;border-radius:50%" src='+finalUrl+'>' +
                                            '<p class='+v+' style="font-family:\"黑体\";font-size: 16px;font-weight:bold">'+list[i].productionName+'</p>' +
                                            '<p class='+w+' style="font-family:\"微软雅黑\";font-size: 14px">'+list[i].chineseName+'</p>' +
                                            '<p class='+x+' style="font-family:\"Microsoft YaHei\";font-size: 14px">'+list[i].englishName+'</p>' +
                                            '<p class='+y+' style="font-family:1\"楷体\";font-style: oblique;font-size: 14px">'+list[i].publishedYear+'</p>' +
                                        '</div>' +
                                        '<div class='+z+' style="width:600px;height:200px;float:left;color:#666">' +
                                            '<a class='+a+' style="text-decoration:none;color:#666666;font-size:14px;font-family:\"微软雅黑\" href="production.html?productionId='+productionId+'"'+' onmouseover="this.style.color=\'black\'" onmouseout="this.style.color=\'#666666\'" onmousedown="this.style.color=\'#666666\'">'+list[i].summaryInfo+'</a>' +
                                        '</div>' +
                              '</div>';


                        // var proDiv = document.createElement("div");//总div
                        // proDiv.setAttribute("class","proDiv");
                        // proDiv.setAttribute("style", "width: 1200px;height: 350px;color: #666666;margin：0,auto");

                        //标题div
                        // var titleDiv = document.createElement("div");
                        // titleDiv.setAttribute("class", u);
                        // titleDiv.setAttribute("style", "width: 300px;height: auto; margin-left: 200px;margin-top:200px;padding:0,0,0,0");

                        //头像
                        // var proImg = document.createElement("img");
                        // proImg.setAttribute("class", s);
                        // proImg.setAttribute("src", finalUrl);
                        // proImg.setAttribute("style", "width:55px;height:55px;border-radius:50%;");
                        // titleDiv.appendChild(proImg);

                        //作品名
                        // var pName = document.createElement("p");
                        // pName.setAttribute("class", v);
                        // pName.setAttribute("style", "font-family: \"黑体\";font-size: 16px;font-weight:bold;");
                        // pName.innerText = list[i].productionName;
                        // titleDiv.appendChild(pName);

                        //作者中文名
                        // var fChineseName = document.createElement("p");
                        // fChineseName.setAttribute("class", w);
                        // fChineseName.setAttribute("style", "font-family: \"微软雅黑\";font-size: 14px;");
                        // fChineseName.innerText = list[i].chineseName;
                        // titleDiv.appendChild(fChineseName);

                        //作者英文名
                        // var fEnglishName = document.createElement("p");
                        // fEnglishName.setAttribute("class", x);
                        // fEnglishName.setAttribute("style", "font-family: \"Microsoft YaHei\";font-size: 14px;");
                        // fEnglishName.innerText = list[i].englishName;
                        // titleDiv.appendChild(fEnglishName);

                        //发表年份
                        // var pYear = document.createElement("p");
                        // pYear.setAttribute("class", y);
                        // pYear.setAttribute("style", "font-family:\"楷体\";font-style: oblique;font-size: 14px;");
                        // pYear.innerText = list[i].publishedYear;
                        // titleDiv.appendChild(pYear);
                        //
                        // proDiv.appendChild(titleDiv);//标题div加入单个div


                        //摘要div
                        // var pContent = document.createElement("div");
                        // pContent.setAttribute("class", z);
                        // pContent.setAttribute("style", "width: 600px;height: auto;font-family: \"微软雅黑\";font-size:14px;float:left;margin-left: 200px; margin-bottom: 20px;color: #666;");

                        //摘要内容a连接
                        // var summary = document.createElement("a");
                        // summary.setAttribute("class", a);
                        // summary.setAttribute("href", "production.html?productionId=" + productionId);
                        // summary.setAttribute("style", "text-decoration:none;color:#666666;font-size:14px;font-family:\"微软雅黑\";");
                        // summary.setAttribute("onmouseover", "this.style.color='black'");//鼠标移入时相当于 a:hover
                        // summary.setAttribute("onmouseout", "this.style.color='#666666'");//鼠标移出时
                        // summary.setAttribute("onmousedown", "this.style.color='#666666'");//鼠标按下时
                        // summary.innerText = list[i].summaryInfo;
                        // pContent.appendChild(summary);//将摘要a链接放入摘要div
                        // proDiv.appendChild(pContent);//摘要div加入单个div
                        //
                        // proListDiv.appendChild(proDiv);//单个div加入总div
                    }

                    //分页插件div
                    // var tcdPageCode = document.createElement("div");
                    // tcdPageCode.setAttribute("class","tcdPageCode");
                    // proListDiv.appendChild(tcdPageCode);//分页插件div加入总div
                    // $("body").html(proListDiv);//将所有div内容加入body,使用html形式

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


});




