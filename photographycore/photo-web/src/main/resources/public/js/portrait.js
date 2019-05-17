
<!--页面初始化入口-->
$(document).ready(function () {

    $.ajax({
        type:"post",
        url:"/famous/portrait/getPortraitInfos",
        success:function(result){
            var portrait = "";
            var portraitName = "";
            var path ="";
            var firstUrl= "/famous/portrait/downLoad?fileName=";
            var finalUrl = "";
            var a = 1;
            var b = 10;
            var c = 100;
            if(null!=result){
                for(var i=0; i<result.length; i++){
                    var x = "portrait" +(i+a);
                    var y = "porImg"+(i+b);
                    var z = "chname"+(i+c);
                    path = result[i].portraitName;
                    finalUrl = firstUrl + path;
                    portrait += '<div class='+x+'><img class='+y+  ' src='+ finalUrl+ ' height="150px" width="150px" border="20px" /></div>'     ;
                    portraitName += '<p class='+z+'><a href='+'"production.html?famousId='+result[i].famousId+'">'+result[i].chineseName+'</a></p>';

                    $(".portraits").html(portrait);
                    $('.'+x).html(portraitName);
                }
            }









        }

    });







});