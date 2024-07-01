<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-06-28
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/header.jsp"%>
<section>
    <div align="center">
        <h3>회원정보 관리</h3>
        <hr/>
        <form action="update.user" method="post">
            <table>
                <tr>
                    <td>ID  </td>
                    <td><input type="text" name="id" placeholder="4자이상" readonly="readonly" value="${requestScope.user_id}"></td>
                </tr>
                <tr>
                    <td>PW  </td>
                    <td><input type="password" name="pw" placeholder="4자 이상" required="required" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,50}$"></td>
                </tr>
                <tr>
                    <td>이름  </td>
                    <td><input type="text" name="name" required="required" value="${requestScope.user_name}"></td>
                </tr>
                <tr>
                    <td>이메일  </td>
                    <td><input type="email" name="email" value="${requestScope.user_email}"></td> <%--null 허용, email 형식 검사한다.--%>
                </tr>
                <tr>
                    <td>성별  </td>
                    <td>
                        <input type="radio" name="gender" value="M" ${requestScope.user_gender eq "M" ? 'checked' : null}>남자
                        <input type="radio" name="gender" value="W" ${requestScope.user_gender eq "W" ? 'checked' : null}>여자
                        <% System.out.println(request.getAttribute("user_gender")); %>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="수정">
            <input type="button" value="취소" onclick="location.href='myPage.user'"> <%--location.href : 자바스크립트의 리다이렉트--%>
        </form>
    </div>
</section>
<%@ include file="../include/footer.jsp"%>
