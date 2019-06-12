

$(document).ready(function (){
    innitSearch();//初始化搜索框
    <!--页面初始化-->
    portraitInfos();
    <!--点击搜索调用-->
    $("#search").click(function(){
        portraitInfos();
    });

});

    <!--首页条件查询入口-->
    function portraitInfos(){
        var chineseName = $("#chineseName").val();<!--获取搜索关键词-->
        $.ajax({
            type: "post",
            url: "/famous/portrait/getPortraitInfos",
            data: {
                "chineseName":chineseName
            },
            cache: false,
            sync:false,
            success: function (result) {
                var firstUrl = "/famous/portrait/downLoad?fileName=";
                var a = 1;
                var b = 10;
                var c = 100;
                var list = result.beans;

                for (var i = 0; i < list.length; i++){
                    var x = "portrait" + (i + a);
                    var y = "porImg" + (i + b);
                    var z = "chname" + (i + c);
                    var portraitName = list[i].portraitName;
                    var finalUrl = firstUrl + portraitName;
                    var famousId = list[i].famousId;
                    var chineseName = list[i].chineseName;

                    //肖像div
                    var portrait = document.createElement("div");
                    portrait.setAttribute("class", x);
                    portrait.setAttribute("style", "float:left;width:250px;height:250px;margin:20px 0;margin:0 20px;text-align: center");

                    //图片标签
                    var proImg = document.createElement("img");
                    proImg.setAttribute("class", y);
                    proImg.setAttribute("src", finalUrl);
                    proImg.setAttribute("style", "width:150px;height:150px;border:solid 20px #666666");
                    portrait.appendChild(proImg);

                    //a标签(中文名)
                    var a4 = document.createElement("a");
                    a4.setAttribute("href", 'production.html?famousId=' + famousId + '&portraitName=' + portraitName);
                    a4.innerText = chineseName;

                    //p标签
                    var p = document.createElement("p");
                    p.setAttribute("class", z);
                    p.appendChild(a4);<!--将a放入p中-->
                    portrait.appendChild(p); <!--将p放入div中-->

                    <!--添加到父级标签-->
                    portraits.appendChild(portrait);
                }
            $("body").html(portraits);
        }

        });

    };


    //初始化搜索框函数
    function innitSearch(){

        /*初始化导航栏及搜索框*/
        var portraits = document.createElement("div");
        portraits.setAttribute("id","portraits");
        portraits.setAttribute("style","width: 1170px;height: auto;margin: 0 auto;");

        //导航div
        var navigator  = document.createElement("div");
        navigator.setAttribute("id","navigator");
        navigator.setAttribute("style","width:500px;height: 20px;margin-left: 60px;margin-top:100px ;");
        portraits.appendChild(navigator);

        var span1 = document.createElement("span");
        var a1 = document.createElement("a");
        a1.setAttribute("href","portrait.html");
        a1.innerText="首页　　　　";
        span1.appendChild(a1);
        navigator.appendChild(span1);

        var span2 = document.createElement("span");
        var a2 = document.createElement("a");
        a2.setAttribute("href","productionList.html");
        a2.innerText="作品展　　　";
        span2.appendChild(a2);
        navigator.appendChild(span2);

        var span3 = document.createElement("span");
        var a3 = document.createElement("a");
        a3.setAttribute("href","famous.html");
        a3.innerText="名人管理　　　";
        span3.appendChild(a3);
        navigator.appendChild(span3);

        //logo
        var logo = document.createElement("p");
        logo.setAttribute("id","logo");
        logo.innerText="world famous exhibition center";
        logo.setAttribute("style","font-family: 'Microsoft YaHei';color: #666666;font-size: 35px;font-style: oblique;text-align: center ;");
        portraits.appendChild(logo);

        //search Box
        var box = document.createElement("div");
        box.setAttribute("id","box");
        box.setAttribute("style","width: 420px;height: 100px;margin: auto;font-family: 'Microsoft YaHei';color: #666666;font-size: 14px;padding-top: 30px;");

        var chineseName = document.createElement("input");
        chineseName.setAttribute("id","chineseName");
        chineseName.setAttribute("type","text");
        chineseName.setAttribute("name","search");
        chineseName.setAttribute("placeholder","请输入关键字");
        chineseName.setAttribute("style","width: 300px;border: 1px solid #e2e2e2;height: 40px;float: left;background-repeat: no-repeat;background-size: 25px;background-position:5px center;padding:0 0 0 40px;");
        box.appendChild(chineseName);

        var search = document.createElement("div");
        search.setAttribute("id","search");
        search.setAttribute("conclick",portraitInfos);
        search.innerText="搜索";
        search.setAttribute("style","width: 78px;height: 42px;float: right;background: #666666;color: white;text-align: center;line-height: 42px;cursor: pointer;");
        box.appendChild(search);
        portraits.appendChild(box);
        $("body").html(portraits);

    };



