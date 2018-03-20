var ART_ID;
var TOOL_ID;
// 显示文章细节
function showArticleDetail(art_id,isPopState){
    $.ajax({
        url:"/loveblog/GetOneByIdServlet",
            type:"post",
            data:{"art_id":art_id},
            dataType:"json",
            success:function(articles){
                console.log(articles);
                var article = articles.article;
                $(".brefs").empty();
                $('body,html').animate({scrollTop:0},500);
                // $(".brefs div.articleDetail").remove();
                // $(".brefs div.articleBref").remove();
                // $("hr").remove();//移除旧页的所有hr
                // // <li class="active"><a href="studyNotes.html">学习笔记</a></li>
                $("#nav ul li:eq(2)").children("a").text(article.art_class);
                $("#nav ul li[class='active']").removeClass("active");
                $("#nav ul li[attr='nop']").remove();
                $("#nav ul li[attr='art']").remove();
                $("#nav ul").append($("<li attr='nop' style='font-size: 10px;''> >> </li>")).append($("<li attr='art' class='active art'><a href='#'>"+article.art_title+"</a></li>"));

                var articleDetail = $("<div class='articleDetail'></div>");
                articleDetail.append($("<h2>"+article.art_title+"</h2>"));
                var detailInfo = $("<div class='detailInfo'></div>");
                detailInfo.append($("<span><img src='img/author.png'><i>"+article.art_author+"</i></span>"));
                detailInfo.append($("<span><img src='img/time.png'><i>"+article.art_time+"</i></span>"));
                detailInfo.append($("<span><img src='img/view.png'><i>"+article.art_view+"</i></span>"));
                detailInfo.append($("<span><img src='img/like1.png'><i>"+article.art_like+"</i></span>"));
                articleDetail.append(detailInfo);
                var articleContent = $("<div class='articleContent'></div>");
                if(article.art_music){
                    console.log(article.art_music);
                    var music = $("<div class='music'><audio controls='controls'></audio></div>").children("audio").attr("src",article.art_music);
                    articleContent.append(music);
                }
                if(article.art_music && article.art_video){
                    articleContent.append($("<hr style='height:1px;border:none;border-top:1px dashed #ccc;margin-bottom:30px;' />"));
                }
                if(article.art_video){
                    articleContent.append($("<div class='video'><video src="+article.art_video+" controls='controls'></video></div>"));
                }
                articleContent.append($("<div class='content' id='content'>"+article.art_content+"</div>"));
                articleDetail.append(articleContent);
                // <div class="preAndNext">
                //     <div class="pre"><label>[上一篇]</label><a href="#">上一篇</a></div>
                //     <div class="next"><label>[下一篇]</label><a href="#">下一篇</a></div>
                // </div>
                //---------------------------------------------------------------------------------------
                // <div class="mark">
                //     <i>♡</i>
                    // <span class="like">喜欢(<span>10</span>)</span>
                // </div>
                var markWrap = $("<div class='markWrap'></div>");
                var mark = $("<div class='mark'></div>");
                markWrap.append(mark);
                mark.append($("<i>♡</i>"));  
                mark.append($("<span class='like'>喜欢(<span>"+article.art_like+"</span>)</span>"));
                mark.click(function(){
                    console.log("点击的art_id = "+article.art_id);
                    $.ajax({
                        url:"/loveblog/AddLikeByIdServlet",
                        type:"post",
                        data:{"art_id":article.art_id},
                        dataType:"json",
                        success:function(data){
                            console.log(data);
                            var oldLike = $(".mark .like span").text();
                            oldLike=parseInt(oldLike);
                            $(".mark .like span").text(oldLike+1);
                            $(".detailInfo i:eq(3)").text(oldLike+1);
                        },
                        error:function(){
                            console.log("addLike error!");
                        }
                    });
                });
                articleDetail.append(markWrap);              
                //---------------------------------------------------------------------------------------
                var preAndNext = $("<div class='preAndNext'></div>");
                preAndNext.append($("<div class='pre'><label>[上一篇]</label><a href='#'>没有上一篇啦！</a></div>"));
                preAndNext.append($("<div class='next'><label>[上一篇]</label><a href='#'>没有下一篇啦！</a></div>"));
                
                if(articles.pre){
                    // console.log("pre : " + articles.pre.art_title);
                    // console.log(preAndNext.children(".pre a"));
                    preAndNext.children("div.pre").children("a").attr("href","articleDetail.html?article="+articles.pre.art_id).text(articles.pre.art_title);
                }
                if(articles.next){
                    // console.log("next : " + articles.next.art_title);
                    preAndNext.children("div.next").children("a").attr("href","articleDetail.html?article="+articles.next.art_id).text(articles.next.art_title);
                }
                articleDetail.append(preAndNext);
                //------------------------------------------------------------------------------------------
                $(".brefs").append(articleDetail);
                
                if(!isPopState){//如果是用户点击的，就添加这样一条历史记录
                    console.log("button:" + location.href);
                    history.pushState(null,"",location.href.split("?")[0]+"?article="+article.art_id);
                    // history.replaceState({article:article},"",location.href.split("?")[0]+"?curPage="+curPage);
                }
                editormd.markdownToHTML("content",{
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true
                });
            },
            error:function(){
                console.log("getArticle Failed");
            }
    });
    getComments(art_id);
    getComStat(art_id);
}


function showToolDetail(t_id,isPopState){
    $.ajax({
        url:"/loveblog/GetToolByIdServlet",
            type:"post",
            data:{"t_id":t_id},
            dataType:"json",
            success:function(tool){
                $(".brefs").empty();
                $('body,html').animate({scrollTop:0},500);
                $("#nav ul li:eq(2)").children("a").text("工具下载");
                $("#nav ul li[class='active']").removeClass("active");
                $("#nav ul li[attr='nop']").remove();
                $("#nav ul li[attr='art']").remove();
                $("#nav ul").append($("<li attr='nop' style='font-size: 10px;''> >> </li>")).append($("<li attr='art' class='active art'><a href='#'>"+tool.t_name+"</a></li>"));
                

                // <div class="toolDetail">
                //     <h2 class="toolname">Ollydebug <span class="version">1.0.0</span></h2>
                //     <p class="abstract">这是简介哦这是简介这是简介哦这是简介这是简介哦这是简介这是简介哦这是简介这是简介哦这是简介这是简介哦这是简介</p>
                //     <ul class="tags">
                //         <li>标签1</li>
                //         <li>标签2</li>
                //         <li>标签3</li>
                //     </ul>
                //     <div class="description">
                //         <h4>详细介绍</h4>
                //         <p>
                //             这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍这是详细介绍
                //         </p>
                //     </div>
                //     <div class="markWrap">
                //         <div class="mark">
                //             <i>♡</i>
                //             <span class="like">喜欢(<span>10</span>)</span>
                //         </div>
                //         <div class="download">下载</div>
                //     </div>
                // </div>
                var toolDetail = $("<div class='toolDetail'></div>");
                toolDetail.append($("<h2 class='toolname'>"+tool.t_name+" <span class='version'>"+tool.t_version+"</span></h2>"));
                toolDetail.append($("<p class='abstract'>"+tool.t_abstract+"</p>"));
                var tags = $("<ul class='tags'></ul>");
                for(let i = 0;i < 2;i++){
                    if(i == 1){
                        tags.append($("<li>安全</li>"));
                    }else{
                        tags.append($("<li>自用</li>"));
                    }
                }
                toolDetail.append(tags);
                var desc = $("<div class='description'></div>");
                desc.append($("<h4>详细介绍</h4>"));
                desc.append($("<p>"+tool.t_description+"</p>"));
                toolDetail.append(desc);

                var markWrap = $("<div class='markWrap'></div>");
                markWrap.append($("<div class='mark'><i>♡</i><span class='like'>喜欢(<span>"+tool.t_like+"</span>)</span></div>"));
                markWrap.append($("<div class='download'>下载</div>"));
                // http://localhost:8080/loveblog/DownloadServlet?filepath=
                markWrap.children(".download").on("click",function(){
                    window.location.href = "http://localhost:8080/loveblog/DownLoadServlet?filepath=" + tool.t_path;
                });
                toolDetail.append(markWrap);
                $(".brefs").append(toolDetail);
        },
            error:function(){
                console.log("getArticle Failed");
            }
    });
    getComments(t_id);
    getComStat(t_id);
}