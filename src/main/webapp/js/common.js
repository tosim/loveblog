
// 返回顶部
$(function(){
    var showHeight = 100;
    $(window).scroll(function(){
        if($(window).scrollTop()>100){
            $("#totop").fadeIn('slow');
        }else{
            $("#totop").fadeOut('slow');
        }
    });
    $("#totop").click(function(){
        $('body,html').animate({scrollTop:0},500);
        return false;
    });
    
    
    $.ajax({
        url:"/loveblog/LoginStateServlet",
        type:"post",
        data:{},
        dataType:"json",
        success:function(data){
            if(data.success==1){
                // $("#login").css("display","none");
                $("#login").text(data.user.u_name);
                $("#login").attr("id","user");
                console.log("你好" + data.user.u_name + "\n\n");
            }
        },
        error:function(){
            console.log("not login");
        }
    });
        // 登录界面的login按钮事件
    $("#loginbtn").click(function(){
        console.log("login clicked!");
        var param = new Object();
        param.account = $("#account").val();
        param.password = $("#password").val();
        console.log(param);
        $.ajax({
            url:"/loveblog/LoginServlet",
            type:"post",
            data:param,
            dataType:"json",
            success:function(data){
                console.log("第二次user:" + data.user.u_name);
                user = data.user;
                if(user.u_email){
                    console.log(user.u_email);
                }else{
                    console.log("user.u_email not defined!");
                }
                $("#login").text(data.user.u_name);
                $("#login").attr("id","user");
                console.log("你好" + data.user.u_name + "\n\n");
                
                $("#overLay").hide();
                $("#loginmodal").hide();
            },
            error:function(){
                console.log("登录失败");
                $("#overLay").hide();
                $(".modal").hide();
            }
        });
    });

    // 登录按钮的点击事件
    $("#login").click(function(){
        // if(typeof(user) != "undefined"){
        //     console.log("user:" + user);
        //     // console.log(user.u_id);
        //     // console.log(user.u_name);
        //     // console.log(user.u_img);
        //     // console.log(user.u_email);
        //     return;
        // }else{
        //     console.log("user not defined");
        // }

        

        $.ajax({
            url:"/loveblog/LoginStateServlet",
            type:"post",
            data:{},
            dataType:"json",
            success:function(data){
                if(data.success==1){
                    // $("#login").css("display","none");
                    $("#login").text(data.user.u_name);
                    $("#login").attr("id","user");
                    console.log("你好" + data.user.u_name + "\n\n");
                    if(data.user.u_name == "姚小城"){
                        window.location.href = "http://localhost:8080/loveblog/newArticle.html";
                    }
                }else{
                    $("#overLay").show();
                    $("#loginmodal").show();
                }
            },
            error:function(){
                console.log("not login");
            }
        });
    });
    $("#toregistbtn").on("click",function(){
        //
        $("#loginmodal").css("display","none");
        $("#registmodal").css("display","block");
    });
    $("#overLay").on("click",function(){
        // $("#loginmodal").css("display","none");
        $("#overLay").css("display","none");
        $(".modal").css("display","none");
        // $("#registmodal").css("display","none");
         
    });

    $("#registbtn").on("click",function(){
        var username = $("#regUsername").val();
        var telephone = $("#regTelephone").val();
        var email = $("#regEmail").val();
        var password = $("#regpassword").val();
        var confirm = $("#confirm").val();
        if(password != confirm){
            var tmp = $("<div class='modal'><h1>Password not equal!</h1></div>");
            tmp.css("display","block");
            $(document).append(tmp);
        }else{
            var param = new Object();
            param.username = username;
            param.telephone = telephone;
            param.email = email;
            param.password = password;
            $.ajax({
                url:"/loveblog/RegistServlet",
                type:"post",
                data:param,
                dataType:"json",
                success:function(data){
                    console.log("第二次user:" + data.user.u_name);
                    user = data.user;
                    if(user.u_email){
                        console.log(user.u_email);
                    }else{
                        console.log("user.u_email not defined!");
                    }
                    $("#overLay").hide();
                    $(".modal").hide();
                },
                error:function(){
                    console.log("登录失败");
                    $("#overLay").hide();
                    $(".modal").hide();
                }
            });
            console.log(param);
        }
    });
    

    $("#overLay").on("click",function(){
        $("#headWrap ul").css("left","-1025px");
    });
    $("#menu").on("click",function(){
        $("#overLay").css("display","block");
        console.log("click!!!!");
        $("#headWrap ul").css("left","-8px");
        console.log($("#headWrap ul").css("left"));
    });
    // $("body").on("click",function(){
    //      $("#headWrap ul").css("left","-343px");
    // });
});

/*
*获取10篇最新的和最热门的文章
*/
function getNewAndHot(){
    $.ajax({
        url:"/loveblog/GetTenNewestServlet",
        type:"post",
        data:{},
        dataType:"json",
        success:function(data){
            //console.log("new:" + JSON.stringify(data));
            var New = $("<div id='new' class='item'></div>");
            New.append($("<div><h3>最新文章</h3></div>"));
            var newUl = $("<ul></ul>");
            console.log("new length = " + data.length);
            for(var i = 0;i < data.length;i++){
                var newLi = $("<li><i>"+(i+1)+"</i>"+"<h4>"+data[i].art_title+"</h4>"+"</i></li>");
                (function(article){
                    newLi.children("h4").click(function(){
                        // showArticleDetail(article);
                        window.location.href = "articleDetail.html?article="+article.art_id;
                    });
                })(data[i]);
                if(i == 0){
                    newLi.children("i").addClass("firstI");
                }
                newUl.append(newLi);
            }
            New.append(newUl);
            $("#otherItems").append(New);
        },
        error:function(){
            console.log("getNew Failed");
        }
    });
    $.ajax({
        url:"/loveblog/GetTenHotestServlet",
        type:"post",
        data:{},
        dataType:"json",
        success:function(data){
            //console.log("hot:" + JSON.stringify(data));
            var Hot = $("<div id='hot' class='item'></div>");
            Hot.append($("<div><h3>热门文章</h3></div>"));
            var HotUl = $("<ul></ul>");
            console.log("hot length = " + data.length);
            for(var i = 0;i < data.length;i++){
                var HotLi = $("<li><i>"+(i+1)+"</i>"+"<h4>"+data[i].art_title+"</h4>"+"</i></li>");
                (function(article){
                    HotLi.children("h4").click(function(){
                        // showArticleDetail(article);
                        window.location.href = "articleDetail.html?article="+article.art_id;
                    });
                })(data[i]);
                
                if(i == 0){
                    HotLi.children("i").addClass("firstI");
                }
                HotUl.append(HotLi);
            }
            Hot.append(HotUl);
            $("#otherItems").append(Hot);
        },
        error:function(){
            console.log("getHot Failed");
        }
    });
}

