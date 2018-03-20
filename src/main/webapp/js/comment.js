var user;
var commetSize = 10;
var curNum = 1;

$(function(){
    // 发表评论点击事件
    $("#submit").click(function(){
        var param = new Object();
        if(ART_ID){
            param.art_id = ART_ID;
        }
        if(TOOL_ID){
            param.t_id = TOOL_ID;
        }

        param.com_content = $("#editArea textarea").val();
        // param.u_name = user.u_name;

        $.ajax({
            url:"/loveblog/NewCommentServlet",
            type:"post",
            data:param,
            dataType:"json",
            success:function(data){
                console.log(data.user);
                if(data.success == 0){
                    console.log("没有登录");
                    alert("请先登录");
                    return;
                }
                user = data.user;
                var comment = createComment(data.comment);
                $("#showComments").append(comment);
            },
            error:function(evt){
                console.log(evt);
                console.log("发表评论失败");
            }
        });
    });

    // 登录界面的login按钮事件
    $("#loginbtn").click(function(){
        // console.log("login clicked!");
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
        if(user){
            console.log("user:" + user);
            // console.log(user.u_id);
            // console.log(user.u_name);
            // console.log(user.u_img);
            // console.log(user.u_email);
            return;
        }else{
            console.log("user not defined");
        }

        $("#overLay").show();
        $("#loginmodal").show();
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



    $("#moreComments").click(function(){
        getComments(ART_ID);
    });
});
// 创建评论节点
function createComment(comment){
    // console.log("getcommentdata:" +　comment);
    // console.log("user:" + user.u_img);
    var commentDiv = $("<div class='comment'></div>");
    var userInfo = $("<div class='userInfo'></div>");
    userInfo.append($("<div class='userIcon'><img src="+comment.u_img+"></div>"));
    userInfo.append($("<i>"+comment.u_name+"</i>"));
    commentDiv.append(userInfo);
    commentDiv.append($("<div class='time'>"+comment.com_time+"</div>"));
    commentDiv.append($("<div class='cont'>"+comment.com_content+"</div>"));
    commentDiv.append($("<div class='btns'><a>回复</a><a>赞同</a><a>反对</a></div>"));
    return commentDiv;
}

function getComStat(art_id){
    $.ajax({
        url:"/loveblog/GetComStatStatServlet",
        type:"post",
        data:{art_id:art_id},
        dataType:"json",
        success:function(data){
            console.log(data);
            $(".static span:eq(0)").text(data.totalUsers);
            $(".static span:eq(1)").text(data.totalComments);
        },
        error:function(){
            console.log("获取评论数据失败");
        }
    });
}
// 获取此篇文章的size篇评论
function getComments(art_id){
    console.log("getComments art_id = " + art_id);
    var param = new Object();
    param.art_id = art_id;
    param.curNum = curNum;
    param.size = commetSize;
    $.ajax({
        url:"/loveblog/GetCommentsServlet",
        type:"post",
        data:param,
        dataType:"json",
        success:function(data){
            // $("#showComments").empty();
            console.log(data);
            curNum++;
            console.log("curNum = " + curNum);
            if(data.success==0){
                return;
            }
            for(var i = 0;i < data.comments.length;i++){
                var comment = createComment(data.comments[i]);
                $("#showComments").append(comment);
            }
        },
        error:function(){
            console.log("获取评论失败");
        }
    });
}

