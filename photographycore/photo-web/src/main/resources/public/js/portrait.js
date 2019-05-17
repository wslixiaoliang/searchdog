
<!--页面初始化入口-->
$(document).ready(function () {

    $.ajax({
        type:"post",
        url:"/famous/portrait/getPortraitInfos",
        success:function(result){
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
                    var famousId = result[i].famousId;
                    var chineseName = result[i].chineseName;

                    <!--div标签设置-->
                    var portrait = document.createElement("div");
                    portrait.setAttribute("class",x);
                    portrait.setAttribute("style","float:left;width:250px;height:250px;margin:20px 0;margin:0 20px;text-align: center");

                    <!--图片标签设置-->
                    var proImg = document.createElement("img");
                    proImg.setAttribute("class",y);
                    proImg.setAttribute("src",finalUrl);
                    proImg.setAttribute("style","width:150px;height:150px;border:solid 20px #666666");

                    <!--添加到父级标签-->
                    portrait.appendChild(proImg);
                    var portraits = document.getElementById("portraits");
                    portraits.appendChild(portrait);

                    <!--a标签设置-->
                    var a = document.createElement("a")
                    a.setAttribute("href","production.html?famousId="+famousId);
                    a.innerText = chineseName;

                    <!--p标签设置-->
                    var p = document.createElement("p");
                    p.setAttribute("class",z);
                    p.appendChild(a); <!--将a放入p中-->
                    portrait.appendChild(p);<!--将p放入div中-->

                }
            }

        }

    });







});