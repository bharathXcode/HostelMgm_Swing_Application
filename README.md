# 🏨 Hostel Management System (Java Swing)

**Author**: Bharath L  
**Technology**: Java Swing  
**Type**: GUI Desktop Application 

---

## 📘 Introduction

The **Hostel Management System** is a Java GUI application designed to simplify and automate the management of student accommodations in hostels (especially college or university hostels). This system supports **admin login**, **student registration**, **room allocation**, and **student record viewing**, with the ability to extend toward meal plans, payments, and complaints.

It minimizes manual paperwork, reduces data redundancy, and ensures better organization of hostel-related operations.

---

## ✨ Key Features

- ✅ Admin Login System  
- ✅ Student Registration  
- ✅ View Registered Students  
- ✅ Room Allocation Simulation  
- ✅ Dashboard with Navigation  
- ✅ GUI Interface using Java Swing  
- ✅ CardLayout-based screen switching  
- ✅ Easy extension for payments, meals, reports

---

## 🛠️ Steps to Create This System

1. **Define Entities** — Students, Rooms, Admin.  
2. **Design GUI** — Login, Dashboard, Forms.  
3. **Create Classes** — Student, Room with status flags.  
4. **Implement GUI Logic** — Use `CardLayout` to switch views.  
5. **Handle Inputs** — Register students, assign rooms.  
6. **Store Data** — In-memory list or file (currently ArrayList).  
7. **Add Reports** — View students, simulate allocations.  
8. **Test** — Ensure each flow works (login, add, view, logout).  
9. **Optional** — Extend with payment, meal, complaints.  
10. **Document** — Add comments and this README file.

---

## 📂 Code Explanation (10 Key Sections)

---

### 1. **Importing Libraries**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
```
- Required for GUI (`JFrame`, `JButton`), layouts, events, and dynamic student storage.

---

### 2. **Class Declaration**

```java
public class HostelManagementSystem extends JFrame {
```
- Main application window. Extends `JFrame` to use Java Swing.

---

### 3. **Global Variables**

```java
CardLayout cardLayout;
JPanel mainPanel;
JTextField usernameField, nameField, roomField;
JPasswordField passwordField;
JTextArea studentArea;
JLabel welcomeLabel;
ArrayList<String> studentList = new ArrayList<>();
```
- Used for managing screens, input fields, and storing students.

---

### 4. **Constructor**

```java
public HostelManagementSystem() {
    setTitle("Hostel Management System");
    setSize(600, 450);
    ...
    mainPanel.add(loginPanel(), "Login");
    mainPanel.add(dashboardPanel(), "Dashboard");
    ...
    setVisible(true);
}
```
- Sets up the window, adds the login and dashboard panels, and displays the GUI.

---

### 5. **Login Panel**

```java
private JPanel loginPanel() {
    ...
    JButton okBtn = new JButton("OK");
    okBtn.addActionListener(e -> {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        if (user.equals("admin") && pass.equals("1234")) {
            welcomeLabel.setText("Welcome, " + user + "!");
            cardLayout.show(mainPanel, "Dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    });
    ...
}
```
- Admin-only login (hardcoded: `admin/1234`). On success, opens dashboard.

---

### 6. **Dashboard Panel**

```java
private JPanel dashboardPanel() {
    ...
    regBtn.addActionListener(e -> registerStudent());
    viewBtn.addActionListener(e -> viewStudents());
    roomBtn.addActionListener(e -> allocateRoom());
    logoutBtn.addActionListener(e -> {
        usernameField.setText("");
        passwordField.setText("");
        cardLayout.show(mainPanel, "Login");
    });
}
```
- Navigation area with four buttons:
  - Register Student
  - View Students
  - Allocate Room
  - Logout

---

### 7. **Register Student**

```java
private void registerStudent() {
    String name = JOptionPane.showInputDialog("Enter Student Name:");
    String room = JOptionPane.showInputDialog("Enter Room Number:");
    if (!name.isEmpty() && !room.isEmpty()) {
        studentList.add(name + " - Room " + room);
        JOptionPane.showMessageDialog(this, "Student Registered Successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "All fields are required!");
    }
}
```
- Input form to register new student and assign room.

---

### 8. **View Students**

```java
private void viewStudents() {
    studentArea = new JTextArea(15, 40);
    studentArea.setEditable(false);
    if (studentList.isEmpty()) {
        studentArea.setText("No students registered yet.");
    } else {
        for (String s : studentList) {
            studentArea.append(s + "\n");
        }
    }
    JOptionPane.showMessageDialog(this, new JScrollPane(studentArea), "Student List", JOptionPane.INFORMATION_MESSAGE);
}
```
- Shows all registered students in a scrollable dialog box.

---

### 9. **Simulate Room Allocation**

```java
private void allocateRoom() {
    if (studentList.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No students available for room allocation.");
    } else {
        JOptionPane.showMessageDialog(this, "Rooms allocated successfully to all registered students.");
    }
}
```
- Fake room allocation — just a simulation message.

---

### 10. **Main Method**

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(HostelManagementSystem::new);
}
```
- Launches the application on the Event Dispatch Thread.

---

## 📌 How to Run

1. Open your IDE (e.g., IntelliJ, Eclipse, BlueJ).
2. Create a new Java project.
3. Copy the `HostelManagementSystem.java` file into `src/`.
4. Compile and run the project.
5. Login with:
   ```
   Username: admin
   Password: 1234
   ```

---

## 💻 Output Vedio


https://github.com/user-attachments/assets/86f0f2bb-6d1e-4b84-9768-8f0688fe096c



```
---
## 📣 Notes

- All student data is currently stored in memory (RAM).
- Closing the app will erase all data unless saved to a file (can be added).
- You can extend this system to:
  - Add Student Login
  - Save/load from files or DB
  - Add meal tracking, payments, complaints, reports

---

## 📧 Author

Bharath L  
Java Full Stack Developer (Entry-Level)  
Passionate about building real-world applications.

---



