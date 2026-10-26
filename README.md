<p align="center">
  <img src="frontend/public/favicon.svg" alt="Taskly Logo" width="100" height="100">
</p>

<h1 align="center">💜 Taskly — Full-Stack Task Manager</h1>

<p align="center">
  <strong>A little progress every day.</strong>
</p>

<p align="center">
  A full-stack task management application built with React, Spring Boot, and MySQL.
</p>

<p align="center">
  <a href="https://springboot-task-manager.vercel.app">🚀 Live Demo</a> ·
  <a href="https://github.com/mohitkpani/springboot-task-manager">GitHub Repository</a>
</p>
---

## 🚀 Live Demo

- **Frontend:** https://springboot-task-manager.vercel.app
- **Backend API:** https://springboot-task-manager-1-8hsm.onrender.com

> Note: The Render free instance may sleep after inactivity. The database is currently hosted on an Aiven trial.

## ✨ Features

- ➕ Create tasks with a title and description
- 📋 View tasks stored in MySQL
- ✏️ Edit task titles and descriptions
- ✅ Mark tasks as completed or active
- 🗑️ Delete tasks
- 💾 Persistent database storage
- 🎨 Modern purple UI with custom favicon
- ☁️ Cloud deployment with Vercel, Render, and Aiven
- 📱 Responsive interface for desktop and mobile

## 🛠️ Tech Stack

**Frontend**

- React
- JavaScript
- HTML5 and CSS3
- Vite
- Vercel

**Backend**

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate ORM
- Maven
- Render

**Database and Tools**

- MySQL
- Postman
- Git and GitHub
- Visual Studio Code
- Spring Tool Suite (STS)
- Aiven

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
│       │   ├── security/
│       │   └── service/
│       └── resources/
│           └── application.properties
├── frontend/
│   ├── public/
│   ├── src/
│   ├── package.json
│   └── index.html
├── Dockerfile
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

- Java 21
- Maven or the included Maven Wrapper
- Node.js and npm
- MySQL-compatible database
- Git

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/mohitkpani/springboot-task-manager.git
cd springboot-task-manager
```

### 2. Configure the database

For local development, create a MySQL database:

```sql
CREATE DATABASE task_manager_db;
```

Configure the database connection in `src/main/resources/application.properties`.

The application supports these environment variables:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USERNAME`
- `DB_PASSWORD`

Use your own database credentials locally. Never commit passwords or other secrets to GitHub.

### 3. Start the Spring Boot backend

On Windows PowerShell, run from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, run the project through Spring Tool Suite.

Local backend URL: http://localhost:8080

### 4. Start the React frontend

Open a separate terminal:

```bash
cd frontend
npm install
npm run dev
```

Open the URL printed by Vite, usually http://localhost:5173.

For local development, configure `VITE_API_URL` if needed to point to the local backend.

## 🔌 REST API Endpoints

**Local base URL:** `http://localhost:8080/api/tasks`

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

## ☁️ Deployment

- **Frontend:** Vercel
- **Backend:** Render
- **Database:** Aiven MySQL

The frontend uses the `VITE_API_URL` environment variable to communicate with the deployed backend.

The database currently uses an Aiven trial. Long-term availability depends on the database hosting plan and its limits.

## 🔮 Future Improvements

- Task filters: All, Active, and Completed
- Search tasks by title
- User authentication
- Task priorities and due dates

## 👨‍💻 Author

**Mohit Kumar Pani**

GitHub: [mohitkpani](https://github.com/mohitkpani)

## 📄 License

This project is intended for learning and portfolio purposes. Add a license file if you plan to distribute it under a specific open-source license.
