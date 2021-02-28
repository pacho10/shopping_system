# shopping_system
Spring Boot Application

Register user:
POST http://localhost:8080/registration 
Body: {	
	  "username": "tester",
    "password": "tester",
    "passwordConfirm": "tester",
    "firstName": "Pesho",
    "lastName": "Goshev",
    "roleName": "User"
}

CRUD Products:
GET http://localhost:8080/products - get all products
GET http://localhost:8080/products/{id} - get product by id
POST http://localhost:8080/products - create new product
Body: {	
	  "name": "asus",
    "category": "pc",
    "price": 1500
}

PUT http://localhost:8080/products/{id} - update product 
Body: {	
	  "name": "asus",
    "category": "pc",
    "price": 1700
}

DELETE http://localhost:8080/products/{id} - delete product

POST http://localhost:8080/rate/{id} - rate product
Body: {
    "rate": 4
}

SEARCH FOR PRODUCTS:
GET http://localhost:8080/search?search=name:asus
GET http://localhost:8080/search?search=price>900
GET http://localhost:8080/search?search=price<900
