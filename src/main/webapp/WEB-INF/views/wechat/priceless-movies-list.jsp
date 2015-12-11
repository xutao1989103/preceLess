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
    <div class="hero">
      <h1 style="font-size: 50px">电影票比价</h1>
      <p style="font-size: 30px">正在热映的电影</p>
    </div>

    <div class="list list-media">
      <ul>
        <c:forEach items="${movies}" var="model">
          <li>
            <a href="movie/${model.id}?lng=${lng}&lat=${lat}" style="text-decoration: none">
              <div class="row" style="width: 100%">
                <div class="col20">
                  <div class="img" style="width: 100%;overflow:hidden;">
                    <img src="${model.images.large}" style="width: 95%"/>
                  </div>
                </div>
                <div class="col80">
                  <div class="text">
                    <h2 style="padding-bottom: 2px"> <span style= "border:1px solid red; color: red;margin-right: 20px">${model.title}</span></h2>
                    <p>原名：${model.original_title}</p>
                    <P>类型：${model.genresStr} </P>
                    <p>演员：${model.castsStr} </p>
                    <p>导演：${model.directorsStr} </p>

                  </div>
                </div>
              </div>
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>

    <input id="lng" name="lng" class="hide" type="text" value=${lng}>
    <input id="lat" name="lat" class="hide" type="text" value=${lat}>

  </div>
</div>

</body>


<script src="${ctx}/static/js/jquery-1.8.3.min.js" ></script>
<script src="${ctx}/static/js/jquery.mobile-1.0.1.min.js"></script>
<script src="${ctx}/static/js/mobile-internet.js" ></script>

</html>
