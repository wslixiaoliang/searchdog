/**
 * 首页-名人搜素主页面
 */
$(document).ready(function (){

    <!--页面初始化搜索-->
    portraitInfos();

    <!--按下回车键搜索-->
    pressEnterSearch();

    <!--点击按钮搜索-->
    buttionSearch();
});

/**
 * 按下回车键：搜索（与点击按钮搜索功能相同）
 */
function pressEnterSearch(){
    $("#chineseName").keypress(function(event) {
        if (event.keyCode == 13) {
            portraitInfos();
        }
    });
}

/**
 * 点击按钮：搜索
 */
function buttionSearch() {

    $("#search").click(function(){
        portraitInfos();
    });
}


    <!--首页搜索入口-->
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
                          '<a href="famousDetail.html?famousId='+ famousId + '&portraitName=' + portraitName+'">'+
                            '<img class='+y+' src='+finalUrl+' style="width:150px;height:150px;border:solid 20px #666666">' +
                          '</a>'+
                                '<p class='+z+'>'+
                                    '<a href="production.html?famousId=' + famousId + '&portraitName=' + portraitName+'">'+chineseName+'</a>'+
                                '</p>'+
                          '</div>';
                }
                $("#famous-portraits").html(str);
            }
        });

    }



