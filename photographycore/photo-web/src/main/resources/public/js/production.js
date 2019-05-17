
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

                 var pName = result[0].productionName;
                 var pYear = result[0].publishedYear;
                 var fChineseName = result[0].chineseName;
                 var fEnglishName = result[0].englishName;
                 var pContent = result[0].productionContent;

                 $(".pName").html(pName);
                 $(".fChineseName").html(fChineseName);
                 $(".fEnglishName").html(fEnglishName);
                 $(".pYear").html(pYear);
                 $(".pContent").html(pContent);
             }
         }

     });







});




