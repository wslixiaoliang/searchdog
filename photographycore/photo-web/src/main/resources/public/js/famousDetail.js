/**
 * 页面初始化
 */

var url = document.URL;
var famousId = "";
var portraitName = "";

$(document).ready(function(){

    getFamousId(url);//获取famousId
    getFamousDetail(famousId)//ajax 查询名人信心详情
});




/**
 * 获取famousId
 * @param url
 */
function getFamousId(url){
    var subStart = url.indexOf("?") + 1;
    var subEnd = url.length;
    var params = url.substring(subStart,subEnd);
    var paraString = params.split("&");
    var sfamousId = paraString[0];
    var sportraitName = paraString[1];
    var paraString1 = sfamousId.split("=");
    var paraString2= sportraitName.split("=");
    famousId = paraString1[1];
    portraitName = paraString2[1];
}

/**
 *  查询个人详情
 * @param famousId
 */
function getFamousDetail(famousId) {

    $.ajax({
        type: "post",
        url: "/famous/portrait/getPortraitInfos",
        data: {
            "chineseName":"",
            "famousId": famousId
        },
        dataType: "json",
        success: function (result) {

            var firstUrl = "/famous/portrait/downLoad?fileName=";
            var finalUrl = firstUrl+portraitName;
            if (result && result.beans){
                var list = result.beans;

                for (var i=0;i<list.length ;i++){

                    //总div
                    var detailDiv = document.getElementById("detail");
                    detailDiv.setAttribute("class","detail");
                    detailDiv.setAttribute("style", "width: 700px;height: 1000px; margin: 0 auto;margin-top:40px;text-align: center;color: #666");

                    //头像
                    var proImg = document.createElement("img");
                    proImg.setAttribute("class", "portrait");
                    proImg.setAttribute("src", finalUrl);
                    proImg.setAttribute("style", "width:100px;height:100px;border-radius:50%;");
                    detailDiv.appendChild(proImg);

                    //中文名
                    var chineseName = document.createElement("p");
                    chineseName.setAttribute("class", "chineseName");
                    chineseName.setAttribute("style", "font-family: \"黑体\";font-size: 14px;");
                    chineseName.innerText = list[i].chineseName;
                    detailDiv.appendChild(chineseName);

                    //英文名
                    var englishName = document.createElement("p");
                    englishName.setAttribute("class", "englishName");
                    englishName.setAttribute("style", "font-family: \"微软雅黑\";font-size: 14px;");
                    englishName.innerText = list[i].englishName;
                    detailDiv.appendChild(englishName);

                    //生卒年月
                    var birthYear = document.createElement("p");
                    birthYear.setAttribute("class", "birthYear");
                    birthYear.setAttribute("style", "font-family: \"楷体\";font-style: oblique;font-size: 14px;");
                    birthYear.innerText = list[i].birthYear;
                    detailDiv.appendChild(birthYear);

                    // //性别
                    // var sex = document.createElement("p");
                    // sex.setAttribute("class","sex");
                    // sex.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
                    // sex.innerText = list[i].sex;
                    // detailDiv.appendChild(sex);

                    // //国家
                    // var country = document.createElement("p");
                    // country.setAttribute("class","country");
                    // country.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
                    // country.innerText = list[i].country;
                    // detailDiv.appendChild(country);

                    // //职业
                    // var career = document.createElement("p");
                    // career.setAttribute("class","career");
                    // career.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
                    // career.innerText = list[i].career;
                    // detailDiv.appendChild(career);

                    // //成就
                    // var achievement = document.createElement("p");
                    // achievement.setAttribute("class","achievement");
                    // achievement.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
                    // achievement.innerText = list[i].summaryInfo;
                    // detailDiv.appendChild(achievement);

                    //作者简介
                    var summaryInfo = document.createElement("div");
                    summaryInfo.setAttribute("class", "summaryInfo");
                    summaryInfo.setAttribute("style", "width: 700px;height: auto;margin: 0 auto;font-family: \"微软雅黑\";font-size:14px;line-height:40px;");
                    summaryInfo.innerHTML = list[i].summaryInfo;
                    detailDiv.appendChild(summaryInfo);//作者简介

                    // //荣誉
                    // var honor = document.createElement("p");
                    // honor.setAttribute("class","honor");
                    // honor.setAttribute("style","font-family: \"Microsoft YaHei\";font-size: 14px;");
                    // honor.innerText = list[i].honor;
                    // detailDiv.appendChild(honor);
                }

            }
        }

    })
}

