
//初始化时加载
$(document).ready(function () {

     var url = document.URL;
     var famousId = "";
     var paraString =[];
     var subStart = 0;
     var subEnd = 0;
     var param = "";

         //获取名人ID函数
         function getFamousId(url){
             subStart = url.indexOf("?") + 1;
             subEnd = url.length;
             param = url.substring(subStart,subEnd);
             paraString = param.split("=");
             famousId= paraString[1];
            if (typeof(famousId) == "undefined") {
                return "";
            } else {
                return famousId;
            }
        }
        var famousId = getFamousId(url);
        var param = 'famousId='+famousId;
        console.log("===param参数：====================="+param),

        //发送ajax请求，查询作品详情
        $.ajax({
         type: "post",
         url:"/famous/production/getProductionInfos",
         data:param,
         dataType:"json",
         success:function(result){
             if(null!=result && result.length > 0){

                 for(var i=0;i<result.length;i++){

                     var u = "titleDiv" +i;
                     var v = "pName" + i;
                     var w = "fChineseName" + i;
                     var x = "fEnglishName" + i;
                     var y = "pYear"+i;
                     var z = "pContent" + i ;

                     //总div
                     var proDiv = document.getElementById("proDiv")
                     //标题div
                     var titleDiv = document.createElement("div");
                     titleDiv.setAttribute("class",u);
                     titleDiv.setAttribute("style","width: 300px;height: 150px; margin: auto;margin-top:40px;text-align: center;")
                     proDiv.appendChild(titleDiv); //标题div加入总div
                     //作品名
                     var pName = document.createElement("p");
                     pName.setAttribute("class",v);
                     pName.setAttribute("style","font-family: \"黑体\";font-size: 16px;");
                     pName.innerText = result[i].productionName;
                     titleDiv.appendChild(pName);
                     //作者中文名
                    var fChineseName = document.createElement("p");
                    fChineseName.setAttribute("class",w);
                    fChineseName.setAttribute("style","font-family: \"微软雅黑\";font-size: 14px;");
                    fChineseName.innerText = result[i].chineseName;
                    titleDiv.appendChild(fChineseName);
                    //作者英文名
                    var fEnglishName = document.createElement("p");
                    fEnglishName.setAttribute("class",x);
                    fEnglishName.setAttribute("style","font-family: \"微软雅黑\";font-size: 14px;");
                    fEnglishName.innerText = result[i].englishName;
                    titleDiv.appendChild(fEnglishName);
                    //发表年份
                    var pYear = document.createElement("p");
                    pYear.setAttribute("class",y);
                    pYear.setAttribute("style","font-family:\"楷体\";font-style: oblique;font-size: 14px;");
                    pYear.innerText = result[i].publishedYear;
                    titleDiv.appendChild(pYear);

                    var pContent = document.createElement("div");
                     pContent.setAttribute("class",z);
                     pContent.setAttribute("style","width: 800px;height: auto;margin: auto;font-family: \"微软雅黑\";font-size:14px;");
                     pContent.innerText = result[i].productionContent;
                     proDiv.appendChild(pContent);//内容div加入总div


                 }

             }
         }

     });







});




