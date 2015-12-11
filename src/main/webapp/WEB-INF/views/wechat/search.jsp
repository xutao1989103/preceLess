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
  <div class="main">
    <form class="hero" style="height: 8%;padding-bottom: 30px" action="/priceless/local" method="post">
      <input id="lng" name="lng" class="hide" type="text" value=${lng}>
      <input id="lat" name="lat" class="hide" type="text" value=${lat}>
      <input id="keyword" name="keyword" type="text" value="${keyword}" placeholder="请输入关键字" style="border-radius:10px;width: 80%;height: 70%; margin-top: 20px;margin-bottom: 20px">
      <input id="submit" type="submit" class="button" value="比一比" style="background-color:#1f9c3e;border-radius:10px;width: 10%;height: 70%;margin-top: 20px;margin-bottom: 20px">
    </form>

    <div class="list list-media">
      <ul>
        <c:forEach items="${models}" var="model">
          <li>
            <a href="${model.url}" style="text-decoration: none">
              <div class="row" style="width: 100%">
                <div class="col20">
                  <div class="img" style="width: 100%;overflow:hidden;">
                    <img src="${model.pic}" style="width: 95%"/>
                  </div>
                </div>
                <div class="col80">
                  <div class="text">
                    <h2 style="padding-bottom: 2px"> <span style= "border:1px solid red; color: red;margin-right: 20px">${model.websiteName}</span>${model.shopName}</h2>
                    <p>${model.content}</p>
                  </div>
                </div>
              </div>
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>

  </div>
</div>

</body>


<script src="${ctx}/static/js/jquery-1.8.3.min.js" ></script>
<script src="${ctx}/static/js/jquery.mobile-1.0.1.min.js"></script>
<script src="${ctx}/static/js/mobile-internet.js" ></script>

</html>
