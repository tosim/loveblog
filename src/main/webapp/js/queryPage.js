// console.log("pp" + pageClass);
var curPage = 1;//当前页数
var pageSize = 5;//每页显示的文章数量

var showNum= 5;//最多显示多少切换页面的按钮
var isFirstAjax = 1;//是否第一次加载页面
var totalPage;//总的页面数量
var totalNum;//总的文章数量
var curPagePos;//当前指向的页标
var totalPagePos;//总的页标 parseInt((totalPage - 1) / showNum)
var isMultiple;//是否有多个页标
   
// 拼装ArticleBref节点
function createArticleBref(data){
    var articleBref = $("<div class='articleBref'></div>");
    articleBref.append($("<h2>"+data.art_title+"</h2>"));
    articleBref.append($("<img src="+data.art_img+">"));
    articleBref.append($("<p>"+data.art_abstract+"</p>"));
    var button = $("<button class='showDetail'>阅读全文</button>");
    if(pageClass == "tool"){
        button.text("查看详情");
    }
    button.click(function(){
        // alert("aaa");
        // console.log(data);
        // showArticleDetail(data);
        window.location.href = "articleDetail.html?article="+data.art_id;
    });
    articleBref.append(button);
    var articleBrefDiv = $('<div></div>');
    articleBrefDiv.append($('<span><img src="img/time.png"><i>'+data.art_time+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/author.png"><i>'+data.art_author+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/tag.png"><i>'+data.art_tag+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/class.png"><i>'+data.art_class+'</i></span>'));
    articleBref.append(articleBrefDiv);
    return articleBref;
}

function createToolBref(data){
    var articleBref = $("<div class='articleBref'></div>");
    articleBref.append($("<h2>"+data.t_name+"</h2>"));
    articleBref.append($("<img src='img/default.jpg'>"));
    articleBref.append($("<p>"+data.t_abstract+"</p>"));
    var button = $("<button class='showDetail'>阅读全文</button>");
    if(pageClass == "工具下载"){
        button.text("查看详情");
    }
    button.click(function(){
        // alert("aaa");
        // console.log(data.t_id);
        // showArticleDetail(data);
        window.location.href = "toolDetail.html?tool="+data.t_id;
    });
    articleBref.append(button);
    var articleBrefDiv = $('<div></div>');
    articleBrefDiv.append($('<span><img src="img/time.png"><i>'+data.t_time+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/author.png"><i>'+data.t_owner+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/download_cnt.png"><i>'+data.t_download_cnt+'</i></span>'));
    articleBrefDiv.append($('<span><img src="img/class.png"><i>'+"自用"+'</i></span>'));
    articleBref.append(articleBrefDiv);
    return articleBref;
}

/**
*第一次加载pages导航
*创建导航元素，添加各个事件响应方法
*/
function loadPageNav(){
    var pages = $("<div id='pages'></div>");
    pages.append($("<label id='total'>共<span>22</span>篇</label>"));
    $(".brefsWrap").append(pages);
    var toFirst = $("<div class='toFirst'><<</div>");
    toFirst.on("click",function(){
        if(curPage != 1){
            getQueryPage(1);
        }
    });
    pages.append(toFirst);
    //------------------注册到第一页--------------------------
    var pre = $("<div class='pre'>&nbsp<&nbsp</div>");
    pre.on("click",function(){
        if(curPage-1 >= 1){
            getQueryPage(curPage-1);
        }
    });
    pages.append(pre);
    if(totalPage > showNum){
        isMultiple = 1;
        var premore = $("<div class='premore'>...</div>");
        // premore.css("display","none");
        premore.on("click",function(){
            if(curPagePos == 0){
                return;
            }
            pages.children("div[pagenum]").css("display","none");
            curPagePos--;
            for(var i = 1;i <= showNum;i++){
                pages.children("div[pagenum="+(curPagePos*showNum+i)+"]").css("display","inline-block");
            } 
            if(curPagePos == 0){
                // this.style.display = "none";
            }
            pages.children("div.nextmore").css("display","inline-block");
        });
        pages.append(premore);
    }else{
        isMultiple = 0;
    }
    //-------------------注册到前一页--------------------------------
    for(var i = 1;i <= totalPage;i++){
        var ipage = $("<div pagenum="+i+">"+i+"</div>");
        // ipage.pagenum = i;
        //注册到第i页
        (function(pagenum){
            ipage.on("click",function(){
                // console.log("this.pagenum = " + pagenum);
                getQueryPage(pagenum);
            });
        })(i);

        if(i > showNum){
            ipage.css("display","none");
        }
        pages.append(ipage);
        // pages.append($("<div pageNum="+i+">"+i+"</div>"));
    }
    //--------------------------------------------------------------
    if(isMultiple == 1){
        var nextmore = $("<div class='nextmore'>...</div>");
        nextmore.css("display","none");
        nextmore.on("click",function(){
            if(curPagePos == totalPagePos){
                return;
            }
            pages.children("div[pagenum]").css("display","none");
            curPagePos++;
            for(var i = 1;i <= showNum;i++){
                pages.children("div[pagenum="+(curPagePos*showNum+i)+"]").css("display","inline-block");
            }
            if(curPagePos == totalPagePos){
                // this.style.display = "none";
            }
            pages.children("div.premore").css("display","inline-block");
        });
        pages.append(nextmore);
    }
    //--------------------注册到下一页-------------------------------
    var next = $("<div class='next'>&nbsp>&nbsp</div>");
    next.on("click",function(){
        if(curPage+1 <= totalPage){
            getQueryPage(curPage+1);
        }
    });
    pages.append(next);
    //---------------------注册到最后一页-------------------------------
    var toLast = $("<div class='toLast'>>></div>");
    toLast.on("click",function(){
        if(curPage != totalPage){
            getQueryPage(totalPage);
        }
    });
    pages.append(toLast);
    //----------------------设置样式------------------------------
    var clientWidth = document.documentElement.clientWidth;
    // alert("像素比:" + window.devicePixelRatio);
    clientWidth = window.devicePixelRatio * clientWidth;
    console.log("clientWidth = " + clientWidth);
    // console.log(clientWidth);
    // alert(clientWidth);
    if(clientWidth >= 755){
        pages.children("div").css("margin-right","5px");
        console.log("setted");
    }else{
        pages.children("div").css("margin-right","2px");
    }
    pages.children("#total").children("span").text(totalNum);
    pages.children("div").addClass("active");
    totalPagePos = parseInt((totalPage-1) / showNum);
    //--------------------------------------------------------------
}
/**
*每次成功获取一张页面的时候就更新页面导航显示效果
*/
function updatePageNav(){
    var pages = $("#pages");
    // pages.children("div[pagenum]").css("font-size","50px");
    //当前页面超过了最多显示的页面,或者是第一页
    if((curPage % showNum) == 1){
        pages.children("div[pagenum]").css("display","none");
        for(var i = curPage;i < curPage + showNum;i++){
            pages.children("div:eq("+(i+1+isMultiple)+")").css("display","inline-block");
        }
        if(isMultiple == 1){
            if(curPage == 1){//如果是第一页，显示下n页
            pages.children("div.nextmore").css("display","inline-block");
            }else{//如果是跳到下一页了，显示前n页
                pages.children("div.premore").css("display","inline-block");
                if(curPage+showNum-1 >= totalPage){//如果下面没有更多的了
                    // pages.children("div.nextmore").css("display","none");
                }
            }
        }
    }
    if((curPage % showNum) == 0){//跳到前一页去了
        pages.children("div[pagenum]").css("display","none");
        for(var i = curPage - showNum + 1;i < curPage + 1;i++){
            pages.children("div:eq("+(i+1+isMultiple)+")").css("display","inline-block");
            // console.log("i = " + i);
        }
        if(isMultiple == 1){
            pages.children("div.nextmore").css("display","inline-block");
            if(curPage - showNum + 1 == 1){//如果下面没有更多的了
                // pages.children("div.premore").css("display","none");
            }
        }
    }
    //判断前一页，最前页，后一页，最后页是否能用
    if(curPage == 1){
        // console.log("curPage == 1!");
        pages.children(".toFirst").removeClass("active");
        pages.children(".pre").removeClass("active");
    }else{
        pages.children(".toFirst").addClass("active");
        pages.children(".pre").addClass("active");
    }
    if(curPage == totalPage){
        console.log("curPage == totalPage!");
        pages.children(".toLast").removeClass("active");
        pages.children(".next").removeClass("active");
    }else{
        pages.children(".toLast").addClass("active");
        pages.children(".next").addClass("active");
    }
    pages.children("div.actived").removeClass("actived");
    pages.children("div[pagenum="+curPage+"]").addClass("actived");//将当前页标记
    curPagePos = parseInt((curPage-1) / showNum);
    console.log(pages);
    // console.log("curPagePos = " + curPagePos);
}
//获取请求的页面
function getQueryPage(queryPage,isPopState){
    var param = new Object();
    param.queryPage = queryPage;
    param.pageSize = pageSize;
    param.pageClass = pageClass;
    var url = "/loveblog/QueryPageServlet";
    if(pageClass == "工具下载"){
        url = "/loveblog/QueryToolPageServlet";
    }
    $.ajax({
        url:url,
        type:"post",
        data:param,
        dataType:"json",
        success:function(data){
            // console.log(data);
            $("#nav ul li[attr='nop']").remove();
            $("#nav ul li[attr='art']").remove();
            $(".brefs").empty();
            $("#nav ul li:eq(2)").addClass("active");
            curPage = queryPage;
            if(isFirstAjax == 1){
                // $("#pages div").remove();
                totalPage = data.totalPage;
                totalNum = data.totalNum;
                loadPageNav();
                isFirstAjax = 0;
            }
            $("#pages").css("display","block");
            // $(".brefs div.articleBref").remove();//移除旧页的所有文章
            // $("hr").remove();//移除旧页的所有hr
            // $(".brefs div.articleDetail").remove();
            // $("#pages").css("display","block");
            
            for(var i = 0;i < data.list.length;i++){
                var bref;
                if(pageClass != "工具下载"){
                    bref = createArticleBref(data.list[i]);
                }else{
                    bref = createToolBref(data.list[i]);
                }
                $(".brefs").append(bref);
                $(".brefs").append($("<hr>"));
            }
            
            updatePageNav();//每次请求一张页面就更新页面导航
            //console.log("ajax+"+location.href);
            // history.pushState(null,"",location.href);
            if(!isPopState){//如果是用户点击的，就添加这样一条历史记录
                // history.replaceState({dd:1},"",location.href.split("?")[0]+"?curPage="+curPage);
                history.pushState(null,"",location.href.split("?")[0]+"?curPage="+curPage);
            }
        },
        error:function(){
            console.log("error!");
        }
    });
}


//为ajax添加回退刷新功能，在ajax请求成功后向histor.pushState添加带有查询的url，监听用户返回按钮，点击返回时根据查询条件自动执行这个查询条件的ajax
$(function(){
    document.title = pageClass;
    $("#nav li:eq(2)").children("a").text(pageClass);
    getNewAndHot();

    changeContent();
    //getQueryPage(curPage);
    //添加链接的处理事件
    //加载默认的章节
    //添加popstate事件
    $(window).on("popstate",function(e){
        console.log("onpopstate");
        changeContent();
    });
});

// 每次后退执行ajax回退刷新
function changeContent(){
    var query = location.href.split("?")[1];
    if (!query) {
        // 如果没有查询条件，则显示默认第1个章节
        history.replaceState(null, "", location.href + "?curPage=" + curPage);    
        changeContent();
    }else {     
        var param = query.substring(8,query.length);
        getQueryPage(parseInt(param),true);
    }    
}


 