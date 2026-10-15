# 💜 Taskly — Full-Stack Task Manager

Taskly is a full-stack task management web application built with **React, Spring Boot, and MySQL**. It provides a clean, modern interface to create, view, edit, complete, and delete tasks.

## ✨ Features

- ➕ Create tasks with a title and description
- 📋 View tasks stored in MySQL
- ✏️ Edit task titles and descriptions
- ✅ Mark tasks as completed or active
- 🗑️ Delete tasks
- 💾 Persistent database storage
- 🎨 Modern purple UI with custom favicon
- 📱 Access from other devices on the same Wi-Fi during development

## 🛠️ Tech Stack

**Frontend**

- React
- JavaScript
- HTML5 and CSS3
- Vite

**Backend**

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate ORM
- Maven

**Database and Tools**

- MySQL
- Postman
- Git and GitHub
- Visual Studio Code
- Spring Tool Suite (STS)

## 📁 Project Structure

```text
task-manager/
├── src/
│   └── main/
│       ├── java/com/task_manager/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
├── frontend/
│   ├── public/
│   ├── src/
│   ├── package.json
│   └── index.html
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

- Java 21
- Maven or the included Maven Wrapper
- Node.js and npm
- MySQL Server
- Git

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/mohitkpani/springboot-task-manager.git
cd springboot-task-manager
```

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE task_manager_db;
```

Configure your database connection in `src/main/resources/application.properties`.

Use your local database credentials and avoid committing passwords or other secrets to GitHub.

### 3. Start the Spring Boot backend

On Windows PowerShell, run from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, run the project through Spring Tool Suite.

Backend URL: `http://localhost:8080`

### 4. Start the React frontend

Open a separate terminal:

```bash
cd frontend
npm install
npm run dev
```

Open the URL printed by Vite, usually `http://localhost:5173`.

Keep the frontend and backend running while using the application.

## 🔌 REST API Endpoints

Base URL: `http://localhost:8080/api/tasks`

| Method | Endpoint          | Description             |
| ------ | ----------------- | ----------------------- |
| POST   | `/api/tasks`      | Create a task           |
| GET    | `/api/tasks`      | Get all tasks           |
| GET    | `/api/tasks/{id}` | Get a task by ID        |
| PATCH  | `/api/tasks/{id}` | Partially update a task |
| PUT    | `/api/tasks/{id}` | Fully update a task     |
| DELETE | `/api/tasks/{id}` | Delete a task           |

The API can be tested using Postman.

## 🔄 How It Works

1. React provides the user interface.
2. React sends HTTP requests to the Spring Boot REST API.
3. The controller forwards requests to the service layer.
4. Spring Data JPA interacts with MySQL.
5. The API returns JSON responses, and React updates the UI.

## 📱 Testing on a Phone

During development, you can access Taskly from another device on the same Wi-Fi by starting Vite with:

```bash
npm run dev -- --host 0.0.0.0
```

Open the Network URL shown in the terminal on your phone. The computer must remain on, with both servers running.

This setup is for local testing, not public deployment.

## 🔮 Future Improvements

- Task filters: All, Active, and Completed
- Search tasks by title
- User authentication
- Task priorities and due dates
- Cloud deployment

## 👨‍💻 Author

**Mohit Kumar Pani**

GitHub: [mohitkpani](https://github.com/mohitkpani)

## 📄 License

This project is intended for learning and portfolio purposes. Add a license file if you plan to distribute it under a specific open-source license.
