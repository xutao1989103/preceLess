<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title></title>
  <%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
  <form id="mainform" action="${ctx}/priceLess/urlPerms/${action}" method="post">
    <table  class="formTable">
      <tr>
        <td>类型：</td>
        <td>
          <input type="hidden" name="id" value="${id }" />
          <input name="type" value="${filter.type}" />
        </td>
      </tr>
      <tr>
        <td>名称：</td>
        <td>
          <input name="name" value="${filter.name}" />
        </td>
      </tr>
      <tr>
        <td>URL：</td>
        <td><input name="url" type="text" value="${filter.url}" required="required"/></td>
      </tr>
      <tr>
        <td>方法：</td>
        <td> <input name="method" value="${filter.method}" required="required"/></td>
      </tr>
      <tr>
        <td>角色：</td>
        <td><input name="roles" value="${filter.roles}" /></td>
      </tr>
      <tr>
        <td>权限：</td>
        <td><input name="permissions" value="${filter.permissions}" /></td>
      </tr>
      <tr>
        <td>LBS类型：</td>
        <td><input name="lbstype" value="${filter.lbstype}" /></td>
      </tr>
      <tr>
        <td>位置：</td>
        <td><input name="location" value="${filter.location}" /></td>
      </tr>
      <tr>
        <td>距离：</td>
        <td><input name="distance" value="${filter.distance}" /></td>
      </tr>
      <tr>
        <td>区域：</td>
        <td><input name="area" value="${filter.area}" /></td>
      </tr>
    </table>
  </form>
</div>
<script type="text/javascript">
  $(function(){
    $('#mainform').form({
      onSubmit: function(){
        var isValid = $(this).form('validate');
        return isValid;	// 返回false终止表单提交
      },
      success:function(data){
        successTip(data,dg,d);
      }
    });
  });

</script>
</body>
</html>