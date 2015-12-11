<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title></title>
  <%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
  <form id="mainform" action="${ctx}/priceLess/topics/${action}" method="post">
    <table  class="formTable">
      <tr>
        <td>主题名：</td>
        <td>
          <input type="hidden" name="id" value="${id }" />
          <input type="hidden" name="pid" value="${topic.pid }" />
          <input type="hidden" name="img" value="${topic.img }" />
          <input name="name" type="text" value="${topic.name }" class="easyui-validatebox" required="required"/>
        </td>
      </tr>
      <tr>
        <td>描述：</td>
        <td><textarea rows="2" cols="41" name="description" style="font-size: 12px;font-family: '微软雅黑'">${topic.description}</textarea></td>
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