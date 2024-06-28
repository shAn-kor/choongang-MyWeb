<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-06-28
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/header.jsp"%>
<section>
    <div align="center">
        <h3>로그인</h3>
        <hr>
        <form action="loginForm.user" method="post">
            <input type="text" name="id" placeholder="아이디">
            <input type="password" name="pw" placeholder="비밀번호">

            <br>
            ${msg}
            <br>

            <input type="submit" value="로그인">
            <input type="button" value="회원가입" onclick="location.href='join.user'">
        </form>
    </div>
</section>
<%@ include file="../include/footer.jsp"%>
