
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Book</title>
    </head>
    <c:if test="${!user.authenticated}">
        <script type="text/javascript">
            window.location = "/HenryBooksJPA";
        </script>    
    </c:if>
    <c:if test="${user.adminLevel != 'Admn'}">
        <script type="text/javascript">
            window.location = "/HenryBooksJPA";
        </script>    
    </c:if>
    <body>
        <h1>Add New Book</h1>
        <h3>
            User: ${user.userID} - ${user.userName}, 
        </h3>
        <form name="addbook" id="addbook" action="AddBook" method="post" > 
            <table>
                <tr>
                    <td>Book ID:</td>
                    <td><input type="text" name="bookid" id="bookid"
                         value="" />
                    </td>
                </tr>
                <tr>
                    <td>Title:</td>
                    <td><input type="text" name="title" id="title"
                         value="" />
                    </td>
                </tr>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" id="author"
                         value="" />
                    </td>
                </tr>
                <tr>
                    <td>Publisher:</td>
                    <td><select id="pubcode" name="pubcode" >
                            <c:forEach var="pub" items="${publishers}">
                                <option value="${pub.pubcd}">${pub.pubname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Book Type:</td>
                    <td><input type="text" name="booktype" id="booktype"
                         value="" />
                    </td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="price" id="price"
                         value="" />
                    </td>
                </tr>
            </table>
            <br/>
            <input type="submit" value="Add Book">
            <br/>            
        </form>
        <br>
        <a href="StoreSelection.jsp">Cancel Add</a>
        ${msg}
        <br>
    </body>
</html>
