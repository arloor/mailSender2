<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>发送邮件-jsp和js练手</title>

  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="style.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<div class="container">
  <div class="row col-md-4 col-md-offset-4">
    <h2>在此添加内容元素</h2>
  </div>
  <div class="row col-md-4 col-md-offset-4">

    <div class="col-xs-4 col-md-4 ">
      <button class="form-control" onclick="addTitle()" style="padding: 0px 0px;">添加副标题</button>
    </div>
    <div class="col-xs-4 col-md-4 ">
      <button class="form-control" onclick="addP()" style="padding: 0px 0px;">添加段落</button>
    </div>
    <div class=" col-xs-4 col-md-4 ">
      <button class="form-control" onclick="addImg()" style="padding: 0px 0px;">添加图片</button>
    </div>
  </div>
</div>

<div class="header container">
  <div class="row">
    <div class="col-md-offset-4 col-md-4">

    </div>
  </div>
</div>
<div class="mail_set container">
  <div class="row">
    <div class="col-md-offset-4 col-md-4">
      <form class="form-horizontal" method="post" action="/mail/MailProcess">


        <!--js生成内容元素的地方-->
        <div class="form-group" class="row">
          <div class="col-md-12" id="content">
          </div>
        </div>

        <!--是否显示天气-->
        <label>显示天气</label>
        <br>
        <div class="col-md-6 col-xs-6">
          <input type="radio" name="withWeather" value="yes" checked><label>显示天气</label>
        </div>
        <div class="col-md-6  col-xs-6 ">
          <input type="radio" name="withWeather" value="no"><label>不显示天气</label>
        </div>

        <h2>设置邮件信息</h2>
        <label for="to">邮件接收人</label>
        <input id="to" name="to" type="text" class="form-control">

        <label for="title">标题</label>
        <input id="title" name="title" type="text" class="form-control">



        <label class="col-xs-3 col-md-3" style="padding-left: 0px">内容个数</label>
        <input class="col-xs-9 col-md-9" id="numOfCon" name="num" style="padding-left: 0px; padding-right: 0px"
               value="0" readonly>


        <label>选择操作</label><br>
        <div class="form-group" style="width: 70%;margin: auto">
          <input type="radio" name="action" value="save"><label>保存</label>
          <input type="radio" name="action" value="send"><label>发送</label>
          <input type="radio" name="action" value="savesend" checked><label>保存并发送</label><br>
          <input type="radio" name="action" value="viewlast"><label>查看上一版本</label>
          <input type="radio" name="action" value="sendlast"><label>发送上一版本</label>
        </div>
        <br>
        <input type="submit" value="确定" class="form-control">
      </form>

    </div>
  </div>
</div>
<br>
<script>
    var num = 0;
    function addTitle() {
        document.getElementById("content").innerHTML += '<label>副标题</label>' +
            '<input type="text" name="content' + num + '" class="form-control" value="<h>">';
        num++;
        setNum();
    }
    function addP() {
        document.getElementById("content").innerHTML += '<label>段落</label>' +
            '<textarea rows="8" name="content' + num + '" class="form-control"></textarea>';
        num++;
        setNum();
    }
    function addImg() {
        document.getElementById("content").innerHTML += '<label>图片url</label>' +
            '<input type="url"  name="content' + num + '" class="form-control" >';
        num++;
        setNum();
    }

    function setNum() {
        document.getElementById("numOfCon").value = num;
    }

    addTitle();
    addP();
    addImg();
</script>


</body>
</html>
