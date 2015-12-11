<%--
  Created by IntelliJ IDEA.
  User: xtao
  Date: 2015/12/2
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>3+比价，货比三家</title>
</head>
<link rel="stylesheet" href="${ctx}/static/css/mobile-internet.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
  body{margin:0;padding:0;}
  .grid_wrapper{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
  }
  .grid{
    margin-left: 5px;
    margin-top: 5px;
  }
  .grid:after{
    content: ".";
    display: block;
    line-height: 0;
    height: 0;
    clear: both;
    visibility: hidden;
    overflow: hidden;
  }
  .grid a,.grid a:visited{
    float: left;
    display: inline;
    border: 5px solid #ccc;
    width: 32%;
    height: 200px;
    text-align: center;
    line-height: 200px;
    margin-left: 1px;
    margin-top: 1px;
    position: relative;
    z-index: 1;
    color: #1f9c3e;
  }
  .grid a:hover{
    border-color: green;
    z-index: 2;
  }
</style>


<body>
<div class="layout ">
  <div class="main">
    <div class="hero">
      <h1 style="font-size: 100px">3+比价</h1>
      <p style="font-size: 30px">3+提供移动比价，在各大平台中找到最大的优惠。</p>
    </div>

    <div class="grid_wrapper">
      <div class="grid">
        <a class="lia" id="movie-a" href="priceless/movie?">电影票</a>
        <a class="lia" id="local-a" href="priceless/local?">本地生活</a>
        <a class="lia" id="ticket-a" href="priceless/ticket?">交通票务</a>
        <a class="lia" id="car-a" href="priceless/car?">租车代驾</a>
        <a class="lia" id="trip-a" href="priceless/trip?">旅游门票</a>
        <a class="lia" id="home-a" href="priceless/home?">上门服务</a>
      </div>
    </div>

    <input id="lng" name="lng" class="hide" type="text" value="121.481033">
    <input id="lat" name="lat" class="hide" type="text" value="31.238802">




    <div class="footer">
      © CopyRight 2015-2016, 3+ www.3+.com, Inc.All Rights Reserved.
    </div>

  </div>
</div>

</body>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-1.8.3.min.js" ></script>
<script src="${ctx}/static/js/jquery.mobile-1.0.1.min.js"></script>
<script src="${ctx}/static/js/mobile-internet.js" ></script>

<script>
  var $lng = $("#lng");
  var $lat = $("#lat");
  wx.config({
    debug: false,
    appId: '${appid}',
    timestamp: ${timestamp},
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: [
      'checkJsApi',
      'getNetworkType',
      'getLocation'
    ]
  });

  wx.ready(function () {

    wx.getNetworkType({
      success: function (res) {
        networkType = res.networkType;
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });

    wx.getLocation({
      success: function (res) {
        $lng.val(res.longitude);
        $lat.val( res.latitude);
        $("#local-a").attr("href",$("#local-a").attr("href") + "lng=" +res.longitude + "&lat=" + res.latitude);
        $("#ticket-a").attr("href",$("#ticket-a").attr("href") + "lng=" +res.longitude + "&lat=" + res.latitude);
        $("#car-a").attr("href",$("#car-a").attr("href") + "lng=" +res.longitude + "&lat=" + res.latitude);
        $("#movie-a").attr("href",$("#movie-a").attr("href") + "lng=" +res.longitude + "&lat=" + res.latitude);
      },
      cancel: function (res) {
        alert('用户拒绝授权获取地理位置');
      }
    });
  });
  wx.error(function (res) {
    alert(res.errMsg);
  });
</script>


</html>
