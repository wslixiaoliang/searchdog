
$(document).ready(function(){

    var page = 1;
    var limit = 10;
    var total;
    var pageCount = 0;
    var yushu = 0;
    getDataList(page)
    function getDataList(page) {
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

                //分页插件调用
                $(".tcdPageCode").createPage({
                    pageCount: pageCount,
                    current: page,
                    backFn: function () {
                        getDataList(page);
                    }
                });

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
            }
        });
    }

});





