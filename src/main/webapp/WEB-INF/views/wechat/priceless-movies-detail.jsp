<%--
  Created by IntelliJ IDEA.
  User: xtao
  Date: 2015/12/2
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>3+比价，货比三家</title>
</head>
<link rel="stylesheet" href="${ctx}/static/css/mobile-internet.css">
<body>
<div class="layout ">
  <div class="hero">
    <p style="font-size: 40px">电影详情</p>
  </div>
  <div class="main" style="margin-bottom: 20px">
    <div class="row" style="width: 100%;margin-top: 20px">
      <div class="col20">
        <div class="img" style="width: 100%;overflow:hidden;">
          <img src="${movie.images.large}" style="width: 95%"/>
        </div>
      </div>
      <div class="col80">
        <div class="text" style="font-size: 30px">
          <h2 style="padding-bottom: 2px">
            <span style= "border:1px solid red; color: red;margin-right: 50px">${movie.title}
            </span>
            <a href=${movie.mobile_url}>豆瓣详情</a>
          </h2>
          <p>简介: ${movie.summary}</p>
        </div>
      </div>
    </div>

  </div>


  <div class="hero">
    <p style="font-size: 30px">比价信息</p>
  </div>
  <div class="content" style="margin-bottom: 20px">
    <table width="100%" border="1" style="font-size: 30px">
      <tr>
        <td align="center">网站名</td>
        <td align="center">最低票价</td>
        <td align="center">最高票价</td>
        <td align="center">平均票价</td>
        <td align="center">优惠力度</td>
        <td align="center">说明</td>
      </tr>
      <c:forEach items="${prices}" var="price">
        <tr>
          <td>${price.webSite}</td>
          <td>${price.lowestStr}</td>
          <td>${price.highestStr}</td>
          <td>${price.averageStr}</td>
          <td>${price.discount}</td>
          <td>${price.mark}</td>
        </tr>
      </c:forEach>
    </table>


  </div>

  <div class="hero">
    <p style="font-size: 30px">附近影院</p>
  </div>
  <div class="main">

  <c:forEach items="${cinemas}" var="cinema">
    <div class="row" style="width: 100%">
      <div class="text" style="border:1px solid ;font-size: 20px">
        <h2 style="padding-bottom: 2px">
          <span style= "margin-right: 50px">${cinema.name}</span>
          <a href="http://www.nuomi.com/cinema/${cinema.uid}">影院详情</a>
        </h2>
        <p>评分: ${cinema.score}</p>
        <p>地址: ${cinema.address}</p>
        <p>联系方式: ${cinema.phone}</p>
        <p>评价：${cinema.description}</p>
      </div>
    </div>
  </c:forEach>
</div>

</body>


<script src="${ctx}/static/js/jquery-1.8.3.min.js" ></script>
<script src="${ctx}/static/js/jquery.mobile-1.0.1.min.js"></script>
<script src="${ctx}/static/js/mobile-internet.js" ></script>

</html>
