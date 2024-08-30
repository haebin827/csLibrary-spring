<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object adminLogin = session.getAttribute("adminLogin");
    Object userLogin = session.getAttribute("userLogin");
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
                            <input type='hidden' name='page' value='${1}'>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Title</span>
                                <input type="text" name="b_title" class="form-control" placeholder="Title" required>
                                <div class="invalid-feedback">Title is required.</div>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Author</span>
                                <input type="text" name="b_author" class="form-control" placeholder="Author" required>
                                <div class="invalid-feedback">Author is required.</div>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">ISBN</span>
                                <input type="text" name="b_isbn" class="form-control" placeholder="ISBN" required>
                                <div class="invalid-feedback">ISBN is required.</div>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Released Year</span>
                                <input type="number" name="b_year" class="form-control" placeholder="Year" min="1000" max="9999" required>
                                <div class="invalid-feedback">Released year is required.</div>
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Publisher</span>
                                <input type="text" name="b_publisher" class="form-control" placeholder="Publisher">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Category</span>
                                <input type="text" name="b_category" class="form-control" placeholder="Category" required>
                                <div class="invalid-feedback">Category is required.</div>
                            </div>

                            <div class="my-4">
                                <div class="float-end">
                                    <button type="button" class="btn btn-primary">Submit</button>
                                    <button type="reset" class="btn btn-info">Reset</button>
                                    <button type="button" class="btn btn-secondary">Cancel</button>
                                </div>
                            </div>
                        </form>

                        <script>
                            // Server Validation
                            const serverValidResult = {};
                            <c:forEach items="${errors}" var="error">
                            serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
                            </c:forEach>

                            console.log(serverValidResult);

                            document.querySelector(".btn-primary").addEventListener("click", function(e) {

                                e.preventDefault()
                                e.stopPropagation()

                                const formObj = document.querySelector("form")

                                // Check form validity
                                if (formObj.checkValidity() === false) {
                                    formObj.classList.add('was-validated');
                                } else {
                                    formObj.action = "/book/register";
                                    formObj.method = "post";
                                    formObj.submit();
                                }
                            }, false)

                            document.querySelector(".btn-secondary").addEventListener("click", function(e) {

                                e.preventDefault()
                                e.stopPropagation()

                                self.location = "/book/list?${pageRequestDTO.link}"
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
