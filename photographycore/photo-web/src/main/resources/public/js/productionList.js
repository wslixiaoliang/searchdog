
//初始化时加载
$(document).ready(function () {
        //发送ajax请求，查询作品详情
        $.ajax({
         type: "post",
         url:"/famous/production/getProductionInfos",
         dataType:"json",
         success:function(result){
             if(null!=result && result.length > 0){

                 for(var i=0;i<result.length;i++){
                     var a = "summary" + i;
                     var s = "pImg"+i;
                     var u = "titleDiv" +i;
                     var v = "pName" + i;
                     var w = "fChineseName" + i;
                     var x = "fEnglishName" + i;
                     var y = "pYear"+i;
                     var z = "pContent" + i ;
                     var firstUrl = "/famous/portrait/downLoad?fileName=";
                     var finalUrl = firstUrl + result[i].portraitName;

                     var proListDiv = document.getElementById("proListDiv");//总div
                     var titleDiv = document.createElement("div");//标题div
                     titleDiv.setAttribute("class",u);
                     titleDiv.setAttribute("style","width: 300px;height: auto; margin-left: 200px;margin-top:40px;")
                     proListDiv.appendChild(titleDiv); //标题div加入总div
                     <!--头像-->
                     var proImg = document.createElement("img");
                     proImg.setAttribute("class",s);
                     proImg.setAttribute("src", finalUrl);
                     proImg.setAttribute("style", "width:55px;height:55px;border-radius:50%;");
                     titleDiv.appendChild(proImg);
                     //作品名
                     var pName = document.createElement("p");
                     pName.setAttribute("class",v);
                     pName.setAttribute("style","font-family: \"黑体\";font-size: 16px;font-weight:bold;");
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
                    fEnglishName.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
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
                     pContent.setAttribute("style","width: 600px;height: auto;font-family: \"微软雅黑\";font-size:14px;margin-left: 200px; margin-bottom: 20px;color: #666;");

                     //摘要链接
                     var summary = document.createElement("a")
                     summary.setAttribute("class",a);
                     summary.setAttribute("src","production.html");
                     summary.setAttribute("style","text-decoration: none;color: #666666;font-size: 14px;font-family: \"微软雅黑\";onmouseover=this.style.color='orangered';");

                     summary.innerText = result[i].summaryInfo;
                     pContent.appendChild(summary);//将摘要链接放入摘要div
                     proListDiv.appendChild(pContent);//内容div加入总div


                 }

             }
         }

     });







});




