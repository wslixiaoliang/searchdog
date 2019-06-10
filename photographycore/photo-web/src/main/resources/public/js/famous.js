
$(document).ready(function(){

    var page = 1;
    var limit = 10;
    var total;
    var pageCount = 0;
    var yushu = 0;
    getDataList(page);//初始化page为1
    function getDataList(page)//切记此处为动态的page,自动获取点击的page页码
    {
        $.ajax({
            type: 'post',
            url: '/famous/worldFamous/getWorldFamous',
            data: {
                "page": page,
                "limit": limit
            },
            success: function (result) {
                var str = '';
                var list = result.beans;
                total = result.count;
                yushu = (total % limit);
                pageCount = (total - yushu) / limit;
                if (yushu > 0) {
                    pageCount += 1;
                }

                for (var i = 0; i < list.length; i++) {
                    var portraitName = list[i].portraitName;
                    var firstUrl = "/famous/portrait/downLoad?fileName=";
                    var url = firstUrl + portraitName;
                    str += '<tr>' +
                        '<td><span>' + list[i].famousId + '</span></td>' +
                        '<td><span><img id=' + i + ' src=' + url + '/></span></td>' +
                        '<td><span>' + list[i].chineseName + '</span></td>' +
                        '<td><span>' + list[i].englishName + '</span></td>' +
                        '<td><span>' + list[i].sex + '</span></td>' +
                        '<td><span>' + list[i].career + '</span></td>' +
                        '<td><span>' + list[i].achievement + '</span></td>' +
                        '<td><span>' + list[i].honor + '</span></td>' +
                        '<td><span>' + list[i].country + '</span></td>' +
                        '<td><span>' + list[i].birthYear + '</span></td>' +
                        '<td><button id="update" type="button" name="change">修改</button>' +
                        '<button id="delete" type="button" name="del">删除</button></td>' +
                        '</tr>'
                }
                $('table tbody').html(str);

                $(".tcdPageCode").createPage({
                    pageCount: pageCount,
                    current: page
                });
            }
        });

    }
    //回调函数
    $(".tcdPageCode").createPage({
        backFn: getDataList
    });

});





