<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object adminLogin = session.getAttribute("adminLogin");
    if (adminLogin == null) {
        response.sendRedirect("/faq/list");
        return;
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<div class="container-fluid">
    <div class="row">
        <!--<h1>Header</h1>-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Library</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-link active" aria-current="page" href="/book/list">Books</a>
                        <a class="nav-link" href="/notif/list">Notifications</a>
                        <a class="nav-link" href="/faq/list">FAQs</a>
                        <c:if test="${adminLogin != null}">
                            <a class="nav-link" href="/member/list">Members</a>
                        </c:if>
                        <a class="nav-link disabled">Disabled</a>
                    </div>
                    <div class="d-flex ms-auto">
                        <!-- Validation -->
                        <span class="navbar-text me-3 fs-6" style="display: flex">Hello, <c:out value="${m_name}" />!</span>
                        <a href="#!" class="text-secondary text-decoration-none me-3">My Page</a>
                        <a href="/member/logout" class="text-secondary text-decoration-none">Logout</a>
                    </div>
                </div>
            </div>
        </nav>

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="mb-3">
                                <label for="question" class="form-label">Q:</label>
                                <input type="text" name="f_question" id="question" class="form-control "
                                       value='<c:out value="${dto.f_question}"></c:out>' readonly>
                            </div>

                            <div class="mb-3">
                                <label for="answer" class="form-label">A:</label>
                                <input type="text" name="f_answer" id="answer" class="form-control"
                                       value='<c:out value="${dto.f_answer}"></c:out>' readonly>
                            </div>

                            <div class="my-4">
                                <div class="float-end">
                                    <button type="button" class="btn btn-danger">Remove</button>
                                    <button type="button" class="btn btn-primary">Modify</button>
                                    <button type="button" class="btn btn-secondary">List</button>
                                </div>
                            </div>
                        </form>

                        <script>

                            const formObj = document.querySelector("form")
                            document.querySelector(".btn-danger").addEventListener("click", function(e) {

                                formObj.action = "/faq/remove?f_id=" + ${dto.f_id};
                                formObj.method = "post"

                                formObj.submit()
                            }, false);

                            document.querySelector(".btn-primary").addEventListener("click", function(e) {
                                self.location = `/faq/modify?f_id=${dto.f_id}&${pageRequestDTO.link}`;
                            },false)

                            document.querySelector(".btn-secondary").addEventListener("click", function(e) {
                                self.location = `/faq/list?${pageRequestDTO.link}`;
                            }, false)

                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row content">
        <!--<h1>Content</h1>-->
    </div>
    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

</body>
</html>
