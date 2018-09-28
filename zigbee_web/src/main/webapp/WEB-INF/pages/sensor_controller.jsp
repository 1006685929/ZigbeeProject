<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>


<div class="text-center panel-footer panel-body bg-light">
    <a href="/sensor/send?request=08">
        <button name="on_light" type="button" id="light_btn" style="width: 80px;height:25px">灯</button>
    </a>
</div>

<div class="text-center panel-footer panel-body bg-light">
    <a href="/sensor/send?request=01">
        <button name="on_light" type="button" id="fan_btn" style="width: 80px;height:25px">风扇</button>
    </a>
</div>
<div class="text-center panel-footer panel-body bg-light">
    <a href="/sensor/send?request=09">
        <button name="on_light" type="button" id="on_btn" style="width: 80px;height:25px">全部打开</button>
    </a>
</div>
<div class="text-center panel-footer panel-body bg-light">
    <a href="/sensor/send?request=00">
        <button name="on_light" type="button" id="off_btn" style="width: 80px;height:25px">全部关闭</button>
    </a>
</div>


<jsp:include page="bottom.jsp"/>