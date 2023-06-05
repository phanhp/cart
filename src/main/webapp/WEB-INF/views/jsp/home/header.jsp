<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <head>
    <link rel="stylesheet" type="text/css"
      href='${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/css/bootstrap.min.css' />
    <script type="text/javascript"
      src="${pageContext.request.getContextPath()}/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/Shopping/">Home<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Shopping/cart">View Cart</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>