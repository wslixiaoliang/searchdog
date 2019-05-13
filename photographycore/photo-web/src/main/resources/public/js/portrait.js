
<!--页面初始化入口-->
$(document).ready(function () {

    $.ajax({
        type:"post",
        url:"/famous/portrait/getPortraitInfos",
        success:function(result){
            var portrait = "";
            var portraitName = "";
            var path ="";
            if(null!=result){
                for(var i=0; i<result.length; i++){
                    path = result[i].finalPath;
                    portrait = '<tr><td><span><img id="porImg" src= "?" height="100px" width="100px" /></span></td></tr>';
                    // document.getElementById('porImg').src = path;<!--给肖像路径赋值-->
                    $("#porImg").attr("src",path);

                    portraitName = '<tr><td><span>'+result[i].chineseName+'</span></td></tr>';

                    $('portrait pict').html(portrait);
                    $('portrait porName').html(portraitName);

                }


            }






        }

    });







});