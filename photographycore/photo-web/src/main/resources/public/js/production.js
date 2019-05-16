
 //初始化js
 $(document).ready(function () {

     var preUrl = document.referrer;
     var portraitId
     var portraitValue ;

     //获取肖像ID函数
     portraitValue = function(preUrl) {

        var paraString = preUrl.substring(preUrl.indexOf("?") + 1, preUrl.length).split("=")
         portraitId= paraString[1];
        if (typeof(portraitId) == "undefined") {
            return "";
        } else {
            return portraitId;
        }

    }

    //发送ajax请求，查询详情
     $.ajax({
         type: "post",
         url:"/famous/production/getProductionInfos",
         data:portraitId=portraitValue,
         dataType:"json",
         success:function(result){
             if(null!=result && result.length > 0){
                 var chineseName = '<p id="chname">'+result.chineseName+'</p>';
                 var detail = '<div id="produdiv"><textarea>'+result.portraitSummary+'</textarea></div>';
                 $("#porDetail").html(detail)
                 $("#chname").html(chineseName)
             }



     }










     });




 });