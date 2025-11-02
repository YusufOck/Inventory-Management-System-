# Inventory Management and Sales System

A comprehensive, Java-based desktop application designed to manage product inventory, track sales, and perform financial calculations using a graphical user interface built with **Java Swing**.

> **Note:** This project was developed as a university module project. It serves as a practical demonstration of object-oriented programming (OOP) principles and GUI development in Java to simulate a real-world business environment.

## ✨ Key Features

* **🖥️ Graphical User Interface (GUI):**
    * Provides a user-friendly desktop interface built with **Java Swing** for all management tasks.
    * Features include forms for adding products, processing sales, and viewing reports.

* **📦 Comprehensive Inventory Control:**
    * Add, edit, remove, and track products in the inventory.
    * Maintain accurate stock levels, automatically updated after every sale.

* **🛒 Multi-Channel Sales Processing:**
    * **In-Store Sales:** Process sales directly through the GUI.
    * **Online Sales:** Handle e-commerce orders with additional logic for logistics.

* **💸 Financial Calculations:**
    * **Profit Analysis:** Automatically calculates the profit margin for each sale.
    * **Tax Calculation:** Applies relevant sales tax (e.g., VAT) to transactions.
    * **Shipping & Logistics:** Manages and calculates shipping costs for online orders.

## 🛠️ Technologies Used

* **Core Language:** **Java**
* **User Interface (GUI):** **Java Swing**
* **Data Management:** In-Memory (using Java Collections)

---

## 🚀 Getting Started

Follow these steps to get a local copy up and running on your machine.

### Prerequisites

* **Java Development Kit (JDK):** Version 11 or newer.
* **Git:** To clone the repository.

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/YusufOck/Inventory-Management-System-.git
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd Inventory-Management-System-
    ```

---

## 🏃 Running the Application

There are two primary ways to run this project:

### Option 1: Using an IDE (Recommended)

Using an Integrated Development Environment (IDE) is the simplest method.

1.  Open your preferred Java IDE (e.g., **IntelliJ IDEA**, **Eclipse**, or **VS Code** with Java extensions).
2.  Select `File > Open` (or `Import Project`) and choose the `Inventory-Management-System-` directory you just cloned.
3.  The IDE will automatically detect the project structure.
4.  Locate the main class: `InventoryManagementSystem.java` (inside the `INVENTORY MANAGEMENT SYSTEM` folder).
5.  Right-click on the `InventoryManagementSystem.java` file and select **"Run"**. The Swing GUI should appear.

### Option 2: Using the Terminal (Command Line)

You can compile and run the project manually from your terminal.

1.  **Open your terminal** and ensure you are in the root project directory (`Inventory-Management-System-`).

2.  Navigate into the source code directory. (The quotes are important because the folder name has spaces).
    ```sh
    cd "INVENTORY MANAGEMENT SYSTEM"
    ```

3.  Compile all the Java source files. This will create `.class` files for each `.java` file.
    ```sh
    javac *.java
    ```

4.  After successful compilation, run the application by specifying the main class. This will launch the Swing application window.
    ```sh
    java InventoryManagementSystem
    ```

## 🧑‍💻 Author

**Yusuf Ocak**
* GitHub: [@YusufOck](https://github.com/YusufOck)
