# E-Commerce-Application

- The E-Commerce Application is built using Java and Spring Boot, with security, scalability, and ease of maintenance. The backend uses Spring Data JPA to interact with a MySQL database, making it easy to manage and store important entities such as users, products, categories, orders, and more. User authentication is handled by Auth0, providing a secure and reliable means of authenticating users.

- The APIs are well-documented and easily accessible through Swagger UI, making it simple for developers to test and understand the various endpoints. Overall, this project provides secure Rest APIs to create a scalable platform for businesses to sell their products to customers.

# Features
## Admin:-
- Registration & Login
- Creating & managing categories
- Adding & managing products
- Managing discount & price of products
- Fetching & managing Orders
## User:-
- Registration & Login
- Fetching categories and also products based on category
- Adding & deleting products to cart
- Managing address and products quantity
- Ordering products and fetching order status

# Security
- The API is secured using JSON Web Tokens (JWT) handled by Auth0. To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorize option available in the Swagger-ui.

  ### Example:
  - Authorization: <your_jwt>

# Technologies:
- Java
- Spring Boot 3.0
- Maven
- MySQL
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)
- Auth0
- Swagger UI

# Running the app
1. Clone the repository: git clone https://github.com/Sirajuddin135/E-Commerce-Application.git
2. Import the project into STS:
  - Click File > Import...
  - Select Maven > Existing Maven Projects and click Next
  - Browse to the project directory and click Finish
3. Update the values in application.properties with your MySQL database connection details.
4. Run the app: Right-click the project in the Package Explorer and click Run As > Spring Boot App.
5. The app will be running at http://localhost:8080.

# API documentation
- API documentation is available via Swagger UI at http://localhost:8080/swagger-ui.html.

<!-- # E-Commerce Application Image
<img width="188" alt="Event-Scheduler-Image" src="https://user-images.githubusercontent.com/101395494/211131507-fb25a63c-c620-4d2f-8446-c97aa6316ce9.png"> -->

# ER-Diagram
<img width="605" alt="ER-Diagram" src="https://user-images.githubusercontent.com/101395494/216134703-e7cefef6-187f-44df-9fd4-52aedc66d24b.png">

# Swagger-ui
<!-- <img width="948" alt="Swagger-ui" src="https://user-images.githubusercontent.com/101395494/210170660-4f6bd3b0-ec1b-411f-82c1-c059ad14d567.png"> -->

<!-- # API Controllers
<img width="939" alt="Event App Controllers" src="https://user-images.githubusercontent.com/101395494/210179001-2b271f7a-6f13-4060-979b-e88e5a803a93.png"> -->

# Thank You
