<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>--%>
<script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" />
<html>
  <head>
    <title>坦克大战</title>
  </head>
  <body>
    <input type="button"  name="开始游戏" value="开始游戏" onclick="startGame()" />
  </body>
<script type="application/javascript">
  function startGame() {
    alert(111);
    $.ajax({
      url: "/play",
      data: {name: 'HafonTest'},
      type: "POST",
      dataType: "json",
      success: function (data) {

      }
    });
  }
</script>
</html>
