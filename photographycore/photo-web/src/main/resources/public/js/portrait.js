/**
 * 首页-名人展示
 */
$(document).ready(function (){// innitSearch();//初始化搜索框

    <!--页面初始化-->
    portraitInfos();
    <!--点击搜索-->
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
                var str ='';

                for (var i = 0; i < list.length; i++){
                    var x = "portrait" + (i + a);
                    var y = "porImg" + (i + b);
                    var z = "chname" + (i + c);
                    var portraitName = list[i].portraitName;
                    var finalUrl = firstUrl + portraitName;
                    var famousId = list[i].famousId;
                    var chineseName = list[i].chineseName;

                   str += '<div class='+x+' style=" width:250px; height:250px; margin:20px 0; margin:0 20px; text-align:center; float:left">'+
                           '<img class='+y+' src='+finalUrl+' style="width:150px;height:150px;border:solid 20px #666666">'+
                                '<p class='+z+'>'+
                                    '<a href="production.html?famousId=' + famousId + '&portraitName=' + portraitName+'">'+chineseName+'</a>'+
                                '</p>'+
                          '</div>';
                }
                $("#famous-portraits").html(str);
            }
        });

    }



