
//初始化时加载
$(document).ready(function () {

     var url = document.URL;
     var famousId = "";
     var portraitName = "";
     var paraString = [];
     var paraString1 =[];
     var paraString2 = [];
     var subStart = 0;
     var subEnd = 0;
     var params = "";
     var productionId = "";
     var searchkeyword = "";
     var param = {};


    //获取名人ID函数
    if( url.indexOf("famousId") > 0)
    {
        //进入多个作品详情页
        function getFamousId(url){
            subStart = url.indexOf("?") + 1;
            subEnd = url.length;
            params = url.substring(subStart,subEnd);
            paraString = params.split("&");
            var sfamousId = paraString[0];
            var sportraitName = paraString[1];
            paraString1 = sfamousId.split("=");
            paraString2= sportraitName.split("=");
            famousId = paraString1[1];
            portraitName = paraString2[1];
        }
        getFamousId(url);//函数调用
        param = {
            "famousId":famousId
        };
        console.log("===param参数：====================="+param);

    }else{
        //进入单个作品详情页
        function getFunctionId(url){
            subStart = url.indexOf("?") + 1;
            subEnd = url.length;
            params = url.substring(subStart,subEnd);
            paraString = params.split("&");

            paraString1 = paraString[0].split("=");
            paraString2= paraString[1].split("=");
            productionId = paraString1[1];
            searchkeyword = decodeURI(paraString2[1]);
        }
        getFunctionId(url);
        param =  {
            "productionId":productionId,
            "searchkeyword":searchkeyword
        };
        console.log("===param参数：====================="+param);
    }


        //发送ajax请求，查询作品详情
        $.ajax({
         type: "post",
         url:"/famous/production/getFamousProductionById",
         data:param,
         dataType:"json",
         success:function(result) {

         for (var i = 0; i < result.length; i++) {

             var s = "pImg"+i;
             var u = "titleDiv"+i;
             var v = "pName"+i;
             var w = "fChineseName"+i;
             var x = "fEnglishName"+i;
             var y = "pYear"+i;
             var z = "pContent"+i;
             var firstUrl = "/famous/portrait/downLoad?fileName=";
             var finalUrl = "";
             if (portraitName != "") {
                 finalUrl = firstUrl + portraitName;
             } else {
                 finalUrl = firstUrl + result[i].portraitName;
             }

             var proDiv = document.getElementById("proDiv");//总div
             var titleDiv = document.createElement("div");//标题div
             titleDiv.setAttribute("class", u);
             titleDiv.setAttribute("style", "width: 300px;height: auto; margin: auto;margin-top:40px;text-align: center;")
             proDiv.appendChild(titleDiv); //标题div加入总div
             <!--头像-->
             var proImg = document.createElement("img");
             proImg.setAttribute("class", s);
             proImg.setAttribute("src", finalUrl);
             proImg.setAttribute("style", "width:55px;height:55px;border-radius:50%;");
             titleDiv.appendChild(proImg);
             //作品名
             var pName = document.createElement("p");
             pName.setAttribute("class", v);
             pName.setAttribute("style", "font-family: \"黑体\";font-size: 16px;font-weight:bold;");
             pName.innerHTML = result[i].productionName;
             titleDiv.appendChild(pName);
             //作者中文名
             var fChineseName = document.createElement("p");
             fChineseName.setAttribute("class", w);
             fChineseName.setAttribute("style", "font-family: \"微软雅黑\";font-size: 14px;");
             fChineseName.innerHTML = result[i].chineseName;
             titleDiv.appendChild(fChineseName);
             //作者英文名
             var fEnglishName = document.createElement("p");
             fEnglishName.setAttribute("class", x);
             fEnglishName.setAttribute("style", "font-family: \"Microsoft YaHei\";font-size: 14px;");
             fEnglishName.innerText = result[i].englishName;
             titleDiv.appendChild(fEnglishName);
             //发表年份
             var pYear = document.createElement("p");
             pYear.setAttribute("class", y);
             pYear.setAttribute("style", "font-family:\"楷体\";font-style: oblique;font-size: 14px;");
             pYear.innerText = result[i].publishedYear;
             titleDiv.appendChild(pYear);
             //文章内容
             var pContent = document.createElement("div");
             pContent.setAttribute("class", z);
             pContent.setAttribute("style", "width: 800px;height: auto;margin: auto;font-family: \"微软雅黑\";font-size:14px;line-height:180%;");
             pContent.innerHTML = result[i].productionContent;
             proDiv.appendChild(pContent);//内容div加入总div
            }
         }
     });
});




