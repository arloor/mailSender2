<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>发送邮件-jsp练手</title>

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

<div class="header container">
  <div class="row">
    <div class="col-md-offset-4 col-md-4">
      <h1>发送邮件</h1>
    </div>
  </div>
</div>
<div class="mail_set container">
  <div class="row">
    <div class="col-md-offset-4 col-md-4">
      <form class="form-group" method="post" action="/mail/MailProcess">
        <label for="to">邮件接收人</label>
        <input id="to" name="to" type="text" class="form-control">

        <label for="title">标题</label>
        <input id="title" name="title" type="text" class="form-control">

        <label for="p-content">内容</label>
        <textarea id="p-content" name="content" class="form-control" rows="18"></textarea>

        <br>
        <input type="submit" value="发送" class="form-control">
      </form>

    </div>
  </div>
</div>


</body>
</html>
