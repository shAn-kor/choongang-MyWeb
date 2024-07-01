<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-07-01
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/header.jsp"%>
<section>
    <div align="center">
        <h3>회원탈퇴</h3>
        <p>${msg}</p>

        <form action="deleteForm.user" method="post">
            비밀번호 : <input type="password" name="pw"/>
            <input type="submit" value="회원탈퇴"/>
        </form>
    </div>
</section>
<%@ include file="../include/footer.jsp"%>