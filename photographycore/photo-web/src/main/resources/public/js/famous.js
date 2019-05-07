
$(document).ready(function(){
    $.ajax({
        type:'post',
        url:'/famous/worldFamous/getWorldFamous',
        success:function(result){
            var str = '' ;
            for(var i=0;i<result.length;i++){
                str+='<tr>' +
                    '<td><span>'+result[i].chineseName+'</span></td>' +
                    '<td><span>'+result[i].englishName+'</span></td>' +
                    '<td><span>'+result[i].sex+'</span></td>' +
                    '<td><span>'+result[i].career+'</span></td>' +
                    '<td><span>'+result[i].achievement+'</span></td>' +
                    '<td><span>'+result[i].honor+'</span></td>' +
                    '<td><span>'+result[i].country+'</span></td>' +
                    '<td><span>'+result[i].birthYear+'</span></td>' +
                    '<td><button type="button" name="change">修改</button>' +
                    '<button type="button" name="del">删除</button></td>' +
                    '</tr>'
            }
            $('table tbody').html(str);
        }
    });


});





