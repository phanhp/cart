<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
				<html>
				<style>
					<%@include file="/resources/css/main.css" %>
				</style>

				<head>
					<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
					<title>JSP Page</title>
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
					<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
					<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
				</head>

				<body>
                    <button class=""><a href="/Shopping/">Back To Home Page</a></button>
					<div class="container">
						<form:form action="/buy" method="post">
							<h2>List Products</h2>
							<div class="rows">
								<div class="col-sm-9">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>Id</th>
												<th>Name</th>
												<th>Price</th>
												<th>Quantity</th>
												<th>Amount</th>
												<th></th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="orderDetails" items="${cartList}">
												<form action="/cart/update" method="post">
													<input type="hidden" name="productId"
														value="${orderDetails.product.productId}" />
													<tr>
														<td>${orderDetails.product.productId }</td>
														<td>${orderDetails.product.productName}</td>
														<td>${orderDetails.product.productPrice}</td>
														<td><input name="quantity" value="${orderDetails.quantity}"
																onblur="this.form.submit()" style="width: 50px;"></td>
														<td>${orderDetails.product.productPrice * orderDetails.quantity}
														</td>
														<td><a class="btn btn-primary btn-sm"
																href="/Shopping/remove/${orderDetails.product.productId }">Remove</a>
														</td>
													</tr>
												</form>
											</c:forEach>


										</tbody>
									</table>
									<p>Tong Tien:${totalCost}</p>
									<hr />
									<a class="btn btn-primary btn-sm" href="/Shopping/emptyCart">Clear
										Cart</a> <a class="btn btn-primary btn-sm" href="/Shopping/">Add
										more</a>
								</div>
							</div>
							<button type="submit" class="btn btn-primary col-md-2" onclick="location.href='/Shopping/buy">Buy</button>
						</form:form>
					</div>
				</body>

				</html>