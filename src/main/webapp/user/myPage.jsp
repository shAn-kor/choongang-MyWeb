<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-06-28
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/header.jsp"%>
<section>
    <div align="center">
        <h3>${sessionScope.user_name}회원(${sessionScope.user_id}) 님의 회원정보를 관리합니다.</h3>

        <a href="modify.user">회원정보 관리</a>
        <a href="delete.user">회원탈퇴</a>
        <a></a>
    </div>
</section>
<%@ include file="../include/footer.jsp"%>
