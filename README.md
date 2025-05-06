# 🏏 **IPL Team Management System**

A full-stack **IPL Team Management Web Application** built using **Spring Boot**, **Java**, **Thymeleaf**, and **MySQL**. The platform allows users to seamlessly manage IPL teams and players — including full **CRUD** functionality, **image uploads**, detailed **player stats**, and more — all through a responsive, visually engaging interface.

---

## 🚀 **Features**

### 👥 **Team Management**
- ✅ Create, read, update, and delete IPL teams  
- 🖼️ Upload and view team logos  
- 📋 Team details: Coach, Owner, Total Matches Won  
- ✨ Interactive team cards with modern UI  

### 🏏 **Player Management**
- ➕ Add/edit/delete players under specific teams  
- 🖼️ Upload player profile photos  
- 🔍 Filter and search players by name or role  
- 📊 View player stats: Net Worth, Matches, Runs, Biography  

### 🌐 **Frontend & UI/UX**
- ✅ Fully responsive design using **Bootstrap 5**  
- ♻️ Modular Thymeleaf fragments for header, navbar, and footer  
- ✨ Engaging hover effects, gradients, and UI cards  

---

## 🧰 **Tech Stack**

| Layer          | Technology                                   |
|----------------|----------------------------------------------|
| **Backend**    | Java, Spring Boot, Spring MVC, Spring Data JPA |
| **Frontend**   | Thymeleaf, HTML5, CSS3, Bootstrap 5           |
| **Database**   | MySQL                                        |
| **File Upload**| MultipartFile (Spring Boot)                  |
| **Tools**      | Postman, Spring Tool Suite 4 (STS), Git      |

---

## 📷 **Project Snapshots**

<p float="left">
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(654).png" width="32%" />
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(657).png" width="32%" />
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(658).png" width="32%" />
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(660).png" width="32%" />
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(656).png" width="32%" />
  <img src="https://github.com/chirag31045/FinalProjectIPLTeamManagement/blob/2630b4084ae72ede8841358f5f0f17f17ab1217e/Screenshot%20(655).png" width="32%" />
</p>

---

🧩 Project Modules

This application is organized into functional modules for better separation of concerns and maintainability:


---

1. 🧑‍🤝‍🧑 Team Module (com.yourapp.team)

Responsibilities:

Manage IPL team entities (Create, Read, Update, Delete)

Upload and display team logos

View team details: Coach, Owner, Matches Won


Components:

Team.java (Entity)

TeamRepository.java

TeamService.java / TeamServiceImpl.java

TeamController.java

team.html (Thymeleaf View)



---

2. 🏏 Player Module (com.yourapp.player)

Responsibilities:

CRUD operations for players under teams

Upload player profile images

Display player statistics: Net Worth, Runs, Matches, Biography


Components:

Player.java (Entity)

PlayerRepository.java

PlayerService.java / PlayerServiceImpl.java

PlayerController.java

player.html (Thymeleaf View)



---

3. 📁 File Upload Module (com.yourapp.upload)

Responsibilities:

Upload images for players and teams

Serve uploaded files from static directories


Components:

File upload logic within TeamController and PlayerController

upload-dir configured in application.properties

Static resource mapping in WebMvcConfigurer (if used)



---

4. 🏠 Home/Dashboard Module (com.yourapp.home)

Responsibilities:

Manage landing page and dashboard view

Provide navigation to teams and players


Components:

HomeController.java

index.html, dashboard.html



---

5. 🧰 Shared/Utility Module (com.yourapp.common or com.yourapp.util)

Responsibilities:

Common utilities and reusable helper methods

Global exception handling and logging


Components:

Utility classes (e.g., image validator, formatter)

GlobalExceptionHandler.java (if implemented)


## 🧠 **System Design & Architecture**

This project follows a **Layered MVC Architecture** for clear separation of concerns and scalability:

### 🎨 **Presentation Layer (View)**
- Uses **Thymeleaf templates** to render dynamic HTML pages  
- Integrated with **Bootstrap 5** for responsive UI  

### 🧭 **Controller Layer**
- Uses `@Controller` to handle requests and routes  
- Manages form submission, validation, and model-view mapping  

### ⚙️ **Service Layer**
- Contains core business logic  
- Bridges the Controller and Repository layers  

### 🗃️ **Persistence Layer (Repository)**
- Utilizes **Spring Data JPA** for database interaction  
- Simplifies CRUD operations with interfaces  

### 🛢️ **Database**
- MySQL database storing Teams and Players  
- Entity relationships (OneToMany, ManyToOne)  

### 🖼️ **File Upload System**
- Uses `MultipartFile` to upload and serve profile images  

---

## ⚙️ **How to Run the Project**

1. ⬇️ Clone the repository:  
   ```bash
   git clone https://github.com/chirag31045/FinalProjectIPLTeamManagement.git
2. 📦 Import into Spring Tool Suite (STS) or IntelliJ IDEA


3. 🛠️ Configure your MySQL database and update credentials in application.properties


4. ▶️ Run the application:

mvn spring-boot:run


5. 🌐 Open in browser:
http://localhost:8282




---

💡 Future Enhancements

🔐 Role-based access (Admin/User)

🔗 REST API support

🧮 Pagination and Sorting

☁️ Upload images to AWS S3 or cloud storage



---
