# 📦 Courier Management System

A console-based Courier Management System built entirely in Java, designed to simulate the functionalities of a real-world courier service. This system handles both administrative and customer-side operations, offering a seamless experience for managing courier requests, tracking, and delivery estimation.

---

## 📌 About

This project aims to mimic a courier service with two types of users: **Admin** and **Customer**. Admins can manage courier requests, while customers can apply for courier service, track their courier, and receive estimated delivery information.

The system was built **without any external libraries** and demonstrates core concepts of **Java programming**, including **OOP**, **data structures**, **threads**, **file handling**, and **custom algorithms**.

---

## 🚀 Unique Features

- 🔐 **Separate Login Portals** for Admin and Customers
- 🧮 **Custom Pricing and Delivery Date Estimation** based on weight and urgency
- 🆔 **Auto-generated Unique 6-character Courier ID** for tracking
- 📤 **Customer Courier Application** via console
- 📋 **Admin Control Panel** to view, edit, and delete pending courier entries
- 🧵 **Multi-threading for Processing Tasks**
- 🛡️ **Robust Error Handling**

---

### 🧑‍💼 User Panel
- Submit new courier requests
- Auto-generate Unique Courier ID
- View estimated delivery time & pricing
- Track courier using unique ID
- Contact support (phone/email)

### 👨‍💻 Admin Panel
- Secure login
- View all pending consignments
- Track consignments by ID

### ⚙️ Technical Highlights
- **Java Concepts**: 
  - OOP principles
  - Interfaces
  - Multithreading (Runnable)
  - Synchronized methods
  - Exception handling
- **Database**:
  - JDBC used for MySQL integration
  - Stores and retrieves all courier data
- **Random ID generation** for each courier

---

## 🛠️ Requirements

- **Java JDK** 8 or above
- **MySQL Server**
- **JDBC Connector** (add to classpath)

---
