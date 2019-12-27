
            var page = 1;
            var limit = 5;
            var total = 0;
            var pageCount = 0;
            var searchKeywords ="";
            var autoComplete="";
            var suggest = [];


            // 初始化时加载
            $(document).ready(function() {
                getSuggestionWords();
                // pressSpaceSearch();//查询搜索提示词
                getSuggestion(suggest);//展示搜索提示词
                getProductionInfos(page);//初始化页面，page默认为1
                searchProductionInfos();//点击按钮搜索
                pressEnterSearch();//按下回车键：搜索
            });


            /**
             * 点击按钮搜索
             */
            function searchProductionInfos() {
                $("#search").click(function () {
                    getProductionInfos(page);
                });
            }


            /**
             * 按下回车键：搜索（与点击按钮搜索功能相同）
             */
            function pressEnterSearch(){
                $("#searchKeyword").keypress(function(event) {
                    if (event.keyCode == 13) {
                        getProductionInfos(page);
                    }
                });
            }


            /**
             * 查询搜索提示词
             */
            function getSuggestionWords() {
                searchKeywords = $("#searchKeyword").val();
                if (searchKeywords) {
                $.ajax({
                    type: "post",
                    url: "/famous/suggest/getSuggestions",
                    dataType: "json",
                    data: {
                        "suggestionName": searchKeywords
                    },
                    success: function (result) {
                        if (result.suggestions) {
                            suggest = result.suggestions;
                        }
                    }
                });
            }
            getSuggestion(suggest);
     }

    /**
     * 搜索主入口
     * @param page
     */
    function getProductionInfos(page) //动态page，自动获取点击的page页码
    {
        searchKeywords =$("#searchKeyword").val();<!--获取搜索关键词-->
        $.ajax({
            type: "post",
            url: "/famous/production/getProductionInfos",
            dataType: "json",
            data:{
                "searchKeyword":searchKeywords,
                "page":page,
                "limit":limit
            },
            success: function (result) {

                var list = result.beans;
                total = result.count;
                var yushu = (total % limit);
                pageCount = (total-yushu)/limit;
                if(yushu> 0){
                    pageCount +=1;
                }
                if (null != result && list.length > 0) {

                    var str = "";

                    var proListDiv = document.createElement("div");//总div
                    proListDiv.setAttribute("class","proListDiv");
                    proListDiv.setAttribute("style", "width: 1000px;height: 1600px;color: #666666");

                    for (var i = 0; i < list.length; i++) {

                        var a = "summary" + i;
                        var s = "pImg" + i;
                        var u = "titleDiv" + i;
                        var v = "pName" + i;
                        var w = "fChineseName" + i;
                        var x = "fEnglishName" + i;
                        var y = "pYear" + i;
                        var z = "pContent" + i;
                        var firstUrl = "/famous/portrait/downLoad?fileName=";
                        var finalUrl = firstUrl + list[i].portraitName;
                        var productionId = list[i].productionId;

                        str += '<div class="proDiv"'+i+' style="width:1000px;height:170px;color: #666666;margin-left:230px">' +
                                        '<div class='+u+' style="width:150px;height:155px;float:left;margin-right:10px">' +
                                            '<img class='+s+' style=" width:55px; height:55px; border-radius:50%" src='+finalUrl+'>' +
                                            '<p class='+w+' style=" font-family:\'微软雅黑\'; font-size:14px">'+list[i].chineseName+'</p>' +
                                            '<p class='+x+' style=" font-family:\'Microsoft YaHei\'; font-size:14px ">'+list[i].englishName+'</p>' +
                                            '<p class='+y+' style=" font-family:\'楷体\'; font-style:oblique; font-size:14px">'+list[i].publishedYear+'</p>' +
                                            '<p class='+v+' style=" font-family:\'微软雅黑\'; font-size:14px">'+list[i].productionName+'</p>' +
                                        '</div>' +
                                        '<div class='+z+' style="width:600px;height:150px;float:left;color:#666;margin-top:20px">' +
                                            '<a class='+a+' href="production.html?productionId='+productionId+'&searchKeyword='+searchKeywords+'" style="text-decoration:none;color:#666666;font-size:16px;font-family:\'微软雅黑\'" onmouseover="this.style.color=\'black\'" onmouseout="this.style.color=\'#666666\'" onmousedown="this.style.color=\'#666666\'">'+list[i].summaryInfo+'</a>' +
                                        '</div>' +
                              '</div>';
                        }

                    var listDiv = str+'<div class="tcdPageCode"></div>';
                    $("#productListDiv").html(listDiv);//将所有div内容加入body,使用html形式
                }

                //分页插件调用
                $(".tcdPageCode").createPage({
                    pageCount: pageCount,
                    current: page,
                    backFn:getProductionInfos
                });
            }

        });
    }

    /**
     * 搜索提示词，下拉列表展示
     * @author wslixiaoliang
     */
    function getSuggestion(suggest)
    {
        var $ = function (id) {
            return "string" == typeof id ? document.getElementById(id) : id;
        };

        var Bind = function (object, fun) {
            return function () {
                return fun.apply(object, arguments);
            }
        };

        function AutoComplete(obj, autoObj, arr) {
            this.obj = $(obj);        //输入框
            this.autoObj = $(autoObj);//DIV的根节点
            this.value_arr = arr;        //不要包含重复值
            this.index = -1;          //当前选中的DIV的索引
            this.search_value = "";   //保存当前搜索的字符
        }

        AutoComplete.prototype = {
            //初始化div的位置
            init: function () {
                this.autoObj.style.left = this.obj.offsetLeft + "px";
                this.autoObj.style.top = this.obj.offsetTop + this.obj.offsetHeight + "px";
                this.autoObj.style.width = this.obj.offsetWidth - 2 + "px";//减去边框的长度2px
            },

            //删除自动完成需要的所有div
            deleteDIV: function () {
                while (this.autoObj.hasChildNodes()) {
                    this.autoObj.removeChild(this.autoObj.firstChild);
                }
                this.autoObj.className = "auto_hidden";
            },

            //设置值
            setValue: function (_this) {
                return function () {
                    _this.obj.value = this.seq;
                    _this.autoObj.className = "auto_hidden";
                }
            },

            //模拟鼠标移动至DIV时，DIV高亮
            autoOnmouseover: function (_this, _div_index) {
                return function () {
                    _this.index = _div_index;
                    var length = _this.autoObj.children.length;
                    for (var j = 0; j < length; j++) {

                        if (j != _this.index) {
                            _this.autoObj.childNodes[j].className = 'auto_onmouseout';
                        } else {
                            _this.autoObj.childNodes[j].className = 'auto_onmouseover';
                        }
                    }
                }
            },

            //更改classname
            changeClassname: function (length) {
                for (var i = 0; i < length; i++) {
                    if (i != this.index) {
                        this.autoObj.childNodes[i].className = 'auto_onmouseout';
                    } else {
                        this.autoObj.childNodes[i].className = 'auto_onmouseover';
                        this.obj.value = this.autoObj.childNodes[i].seq;
                    }
                }
            },

            //响应键盘
            pressKey: function (event) {
                var length = this.autoObj.children.length;

                //光标键"↓"
                if (event.keyCode == 40) {
                    ++this.index;
                    if (this.index > length) {
                        this.index = 0;
                    } else if (this.index == length) {
                        this.obj.value = this.search_value;
                    }
                    this.changeClassname(length);
                }

                //光标键"↑"
                else if (event.keyCode == 38) {
                    this.index--;
                    if (this.index < -1) {
                        this.index = length - 1;
                    } else if (this.index == -1) {
                        this.obj.value = this.search_value;
                    }
                    this.changeClassname(length);
                }

                //回车键
                else if (event.keyCode == 13) {
                    this.autoObj.className = "auto_hidden";
                    this.index = -1;
                } else {
                    this.index = -1;
                }
            },

            //程序入口
            start: function (event) {
                if (event.keyCode != 13 && event.keyCode != 38 && event.keyCode != 40) {
                    this.init();
                    this.deleteDIV();
                    this.search_value = this.obj.value;
                    var valueArr = this.value_arr;
                    valueArr.sort();
                    if (this.obj.value.replace(/(^\s*)|(\s*$)/g, '') == "") {
                        return;
                    }//值为空，退出
                    try {
                        var reg = new RegExp("(" + this.obj.value + ")", "i");
                    } catch (e) {
                        return;
                    }

                    var div_index = 0;//记录创建的DIV的索引
                    for (var i = 0; i < valueArr.length; i++) {
                        if (reg.test(valueArr[i])) {
                            var div = document.createElement("div");
                            div.className = "auto_onmouseout";
                            div.seq = valueArr[i];
                            div.onclick = this.setValue(this);
                            div.onmouseover = this.autoOnmouseover(this, div_index);
                            div.innerHTML = valueArr[i].replace(reg, "<strong>$1</strong>");//搜索到的字符粗体显示
                            this.autoObj.appendChild(div);
                            this.autoObj.className = "auto_show";
                            div_index++;
                        }
                    }
                }
                this.pressKey(event);
                window.onresize = Bind(this, function () {
                    this.init();
                });
            }
        };
        autoComplete = new AutoComplete('searchKeyword', 'auto', suggest);
    }










