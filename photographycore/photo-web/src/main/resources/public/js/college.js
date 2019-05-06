<script src="jquery/jquery-3.3.1.js" type="text/javascript"></script>
<script>
    //加载table-modal.json
    $.ajax({
        type:'get',
        url:'college/getCollegeInfos',
        success:function(res){
            // console.log(res)   数据成功加载
            var str = '' ;
            var len = res.length;
            for(var i=0;i<len;i++){
                str+='<tr><td><span>'+res[i].nickName+'</span></td><td><span>'+res[i].sex+'</span></td><td><span>'+res[i].phoneNumber+'</span></td><td><span>'+res[i].mailBox+'</span></td><td><span>'+res[i].address+'</span></td><td><button type="button" name="change">修改</button> <button type="button" name="check">查看</button> <button type="button" name="del">删除</button></td></tr>'
            }
            $('table tbody').html(str)
        }
    })

</script>
