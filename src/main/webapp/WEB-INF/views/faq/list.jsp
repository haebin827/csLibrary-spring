<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object adminLogin = session.getAttribute("adminLogin");
    Object userLogin = session.getAttribute("userLogin");
%>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
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
                        <c:choose>
                            <c:when test="${adminLogin != null}">
                                <span class="navbar-text me-3 fs-6" style="display: flex">Hello, <c:out value="${m_name}" />!</span>
                                <a href="#!" class="text-secondary text-decoration-none me-3">My Page</a>
                                <a href="/member/logout" class="text-secondary text-decoration-none">Logout</a>
                            </c:when>
                            <c:when test="${userLogin != null}">
                                <span class="navbar-text me-3 fs-6" style="display: flex">Hello, <c:out value="${m_name}" />!</span>
                                <a href="#!" class="text-secondary text-decoration-none me-3">My Page</a>
                                <a href="/member/logout" class="text-secondary text-decoration-none">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/member/login" class="text-secondary text-decoration-none me-3">Login</a>
                                <a href="/member/register" class="text-secondary text-decoration-none">Register</a>
                            </c:otherwise>
                        </c:choose>
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
                        <h5 class="card-title">FAQ List</h5>
                        <c:choose>
                            <c:when test="${adminLogin != null}">
                                <form>
                                    <input type="hidden" name="size" value="${pageRequestDTO.size}">
                                    <ul class="list-group mb-3">
                                        <c:forEach items="${responseDTO.dtoList}" var="dto">
                                            <li class="list-group-item">
                                                <a href="/faq/read?f_id=${dto.f_id}&${pageRequestDTO.link}" class="text-decoration-none" data-f_id="${dto.f_id}">
                                                    <c:out value="${dto.f_question}"/>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div>
                                        <div class="float-start">
                                            <button type="button" class="btn btn-primary">Register</button>
                                        </div>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form>
                                    <input type="hidden" name="size" value="${pageRequestDTO.size}">
                                    <ul class="list-group mb-3">
                                        <c:forEach items="${responseDTO.dtoList}" var="dto">
                                            <li class="list-group-item">
                                                <a href="#collapse${dto.f_id}" data-bs-toggle="collapse" class="text-decoration-none">
                                                    <c:out value="${dto.f_question}"/>
                                                </a>
                                                <div id="collapse${dto.f_id}" class="collapse mt-2">
                                                    <div class="card card-body">
                                                        <c:out value="${dto.f_answer}"/>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </form>
                            </c:otherwise>
                        </c:choose>

                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start -1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num ? "active" : ""}">
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
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
