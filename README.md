# Training Scheduling Management System

## Overview

A Java-based client-server application developed to manage training schedules and related client and trainer information. The system enables trainers to manage clients, training programs, training sessions, fitness profiles, and schools through a graphical user interface.

The application uses a MySQL relational database for persistent data storage and socket-based communication between the client and server.

## Key Features

- **Client Management** – Add, search, update, and delete client records.
- **Training Program Management** – Manage training programs and their associated information.
- **Training Session Management** – Create, search, update, and delete training records.
- **Trainer Management** – Retrieve and manage trainer information and associated schools.
- **Relational Database Integration** – Store and retrieve application data using MySQL.
- **Client-Server Communication** – Exchange requests and responses between the client and server using sockets.
- **Input Validation and Testing** – Test application workflows with valid and invalid inputs.

## System Architecture

The application follows a client-server architecture and is organized into three modules:

- **TreninziKlijent** – Provides the graphical user interface and communicates with the server.
- **TreninziServer** – Handles client connections, processes application requests, and manages database operations.
- **TreninziZajednicki** – Contains shared domain classes and communication components, including `Request`, `Response`, and the `Operation` interface.

The server processes client requests, executes the corresponding operations, interacts with the database, and returns responses to the client.

## Database Design

The application uses a relational MySQL database to store and manage information about trainers, clients, training programs, training sessions, fitness profiles, and schools.

The database schema includes entities and relationships designed to support the application's main data management operations.

## Technologies

- Java
- MySQL
- Socket Programming
- NetBeans IDE

## Testing

The implemented use cases were tested with both valid and invalid inputs to verify application behavior and the handling of unsuccessful operations.

## How to Run the Project

### 1. Download the Project

1. Open the GitHub repository.
2. Click **Code** and select **Download ZIP**.
3. Extract the downloaded archive.

### 2. Start XAMPP

1. Open the XAMPP Control Panel.
2. Start **Apache** and **MySQL**.

### 3. Set Up the Database

1. Open SQLyog and connect to the local MySQL server.
2. Import the SQL database script provided in the repository.
3. Execute the script to create the database and its tables.

### 4. Open the Project

Open the extracted project in NetBeans IDE and ensure that the required Java and MySQL dependencies are configured correctly.

### 5. Run the Application

The application consists of three modules:

- `TreninziZajednicki` – contains shared domain classes and communication components.
- `TreninziServer` – handles client connections, application requests, and database operations.
- `TreninziKlijent` – provides the graphical user interface.

Start `TreninziServer` first. Once the server is running, start `TreninziKlijent`.

### 6. Use the Application

Log in with a valid trainer account to access the application and manage clients, training programs, and training sessions.