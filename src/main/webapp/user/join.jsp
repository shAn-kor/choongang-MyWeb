<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-06-28
  Time: 오전 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/header.jsp"%>>
<section>
    <%--
    input 태그에 많이 사용되는 주요 속성

    required : 공백 불가
    readonly : 값 읽기만 가능
    disabled : 태그 사용 안함
    checked : 체크박스 미리 선택
    selected : 셀렉트 태그에서 미리 선택
    --%>
        <div align="center">
            <h3>노름은 파도고, 프로그램은 data flow다</h3>
            <hr/>
            <form action="joinForm.user" method="post">
                <table>
                    <tr>
                        <td>ID  </td>
                        <td><input type="text" name="id" placeholder="4자이상" required="required" pattern="[0-9A-Za-z]{4,}"></td>
                    </tr>
                    <tr>
                        <td>PW  </td>
                        <td><input type="password" name="pw" placeholder="4자 이상" required="required" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,50}$"></td>
                    </tr>
                    <tr>
                        <td>이름  </td>
                        <td><input type="text" name="name" required="required"></td>
                    </tr>
                    <tr>
                        <td>이메일  </td>
                        <td><input type="email" name="email"></td> <%--null 허용, email 형식 검사한다.--%>
                    </tr>
                    <tr>
                        <td>성별  </td>
                        <td>
                            <input type="radio" name="gender" value="M" checked="checked">남자
                            <input type="radio" name="gender" value="W">여자
                        </td>
                    </tr>
                </table>
                ${msg }
                <br>
                <input type="submit" value="가입" onclick="location.href='joinForm.user'">
                <input type="button" value="로그인" onclick="location.href='login.user'"> <%--location.href : 자바스크립트의 리다이렉트--%>
            </form>
        </div>
</section>
<%@ include file="../include/footer.jsp"%>