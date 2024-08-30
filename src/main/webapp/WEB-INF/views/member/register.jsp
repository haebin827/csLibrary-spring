<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <a href="/member/login" class="text-secondary text-decoration-none me-3">Login</a>
            <a href="/member/register" class="text-secondary text-decoration-none">Register</a>
          </div>
        </div>
      </div>
    </nav>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-12">
          <div class="card">
            <div class="card-header">Register</div>
            <div class="card-body">

              <form style="width:30%;margin:50px auto;">
                <input type="hidden" name="m_name" id="m_name" />

                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div data-mdb-input-init class="form-outline">
                      <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First name" required/>
                      <div class="invalid-feedback">First name is required.</div>
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div data-mdb-input-init class="form-outline">
                      <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last name" required/>
                      <div class="invalid-feedback">Last name is required.</div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col mb-4">
                      <input type="number" class="form-control" name="m_phone" id="phone" placeholder="Phone number" required/>
                    <div class="invalid-feedback">Phone number is required.</div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col mb-4">
                      <input type="text" class="form-control" name="m_id" id="id" placeholder="Student ID" required/>
                    <div class="invalid-feedback">Student ID is required.</div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col mb-4">
                    <div class="input-group">
                      <input type="password" class="form-control" name="m_pw" id="password" placeholder="Enter your Password" required/>
                      <div class="invalid-feedback">Password is required.</div>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col mb-4">
                    <div class="input-group">
                      <input type="password" class="form-control" name="confirm" id="confirm" placeholder="Confirm your Password" required/>
                      <div class="invalid-feedback">Please check the password.</div>
                    </div>
                  </div>
                </div>
                <div class="form-group float-end">
                  <button type="button" class="btn btn-primary">Register</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  // Form Control
  document.querySelector(".btn-primary").addEventListener("click", function (e) {

    e.preventDefault()
    e.stopPropagation()

    const formObj = document.querySelector("form");
    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');

    // Set m_name value
    document.getElementById('m_name').value = `${firstName} ${lastName}`;

    // Check form validity
    if (formObj.checkValidity() === false) {
      formObj.classList.add('was-validated');
    } else {

      formObj.action = "/member/register";
      formObj.method = "post";
      formObj.submit();
    }
  }, false);
</script>

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