import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HostelManagementSystem extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;
    JTextField usernameField, nameField, roomField;
    JPasswordField passwordField;
    JTextArea studentArea;
    JLabel welcomeLabel;
    JComboBox<String> roleBox;

    ArrayList<String> studentList = new ArrayList<>();
    HashMap<String, String> studentAccounts = new HashMap<>();  // username -> password
    HashMap<String, String> studentRooms = new HashMap<>();     // username -> room

    public HostelManagementSystem() {
        setTitle("Hostel Management System");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(loginPanel(), "Login");
        mainPanel.add(adminDashboardPanel(), "AdminDashboard");
        mainPanel.add(studentDashboardPanel(), "StudentDashboard");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        setVisible(true);
    }

    // Login Panel with Role Selection
    private JPanel loginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 241, 210));

        JLabel title = new JLabel("Hostel Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(180, 30, 240, 30);
        panel.add(title);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(120, 70, 100, 25);
        panel.add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Admin", "Student"});
        roleBox.setBounds(220, 70, 200, 25);
        panel.add(roleBox);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 110, 100, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(220, 110, 200, 25);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 150, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 150, 200, 25);
        panel.add(passwordField);

        JButton okBtn = new JButton("Login");
        okBtn.setBounds(220, 200, 100, 30);
        okBtn.setBackground(new Color(255, 153, 51));
        okBtn.setForeground(Color.WHITE);
        okBtn.addActionListener(e -> handleLogin());
        panel.add(okBtn);

        return panel;
    }

    private void handleLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        if (role.equals("Admin")) {
            if (user.equals("admin") && pass.equals("1234")) {
                welcomeLabel.setText("Welcome, Admin!");
                cardLayout.show(mainPanel, "AdminDashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Admin credentials");
            }
        } else {
            if (studentAccounts.containsKey(user) && studentAccounts.get(user).equals(pass)) {
                welcomeLabel.setText("Welcome, " + user + "!");
                cardLayout.show(mainPanel, "StudentDashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Student credentials");
            }
        }
    }

    // Admin Dashboard
    private JPanel adminDashboardPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 248, 198));

        welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBounds(180, 20, 250, 30);
        panel.add(welcomeLabel);

        JButton regBtn = new JButton("Register Student");
        regBtn.setBounds(200, 80, 180, 30);
        regBtn.addActionListener(e -> registerStudent());
        panel.add(regBtn);

        JButton viewBtn = new JButton("View Students");
        viewBtn.setBounds(200, 130, 180, 30);
        viewBtn.addActionListener(e -> viewStudents());
        panel.add(viewBtn);

        JButton roomBtn = new JButton("Allocate Room");
        roomBtn.setBounds(200, 180, 180, 30);
        roomBtn.addActionListener(e -> allocateRoom());
        panel.add(roomBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(200, 240, 180, 30);
        logoutBtn.setBackground(Color.LIGHT_GRAY);
        logoutBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "Login");
        });
        panel.add(logoutBtn);

        return panel;
    }

    // Student Dashboard
    private JPanel studentDashboardPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(200, 230, 255));

        welcomeLabel = new JLabel("Welcome Student!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBounds(150, 30, 300, 30);
        panel.add(welcomeLabel);

        JButton viewRoomBtn = new JButton("View My Room");
        viewRoomBtn.setBounds(200, 100, 180, 30);
        viewRoomBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String room = studentRooms.getOrDefault(user, "Room not allocated");
            JOptionPane.showMessageDialog(this, "Your Room: " + room);
        });
        panel.add(viewRoomBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(200, 160, 180, 30);
        logoutBtn.setBackground(Color.LIGHT_GRAY);
        logoutBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "Login");
        });
        panel.add(logoutBtn);

        return panel;
    }

    // Register Student (adds account + room)
    private void registerStudent() {
        JPanel regPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField snameField = new JTextField();
        JTextField suserField = new JTextField();
        JPasswordField spassField = new JPasswordField();
        JTextField sroomField = new JTextField();

        regPanel.add(new JLabel("Student Name:"));
        regPanel.add(snameField);
        regPanel.add(new JLabel("Username:"));
        regPanel.add(suserField);
        regPanel.add(new JLabel("Password:"));
        regPanel.add(spassField);
        regPanel.add(new JLabel("Room Number:"));
        regPanel.add(sroomField);

        int result = JOptionPane.showConfirmDialog(this, regPanel, "Register Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = snameField.getText();
            String username = suserField.getText();
            String password = new String(spassField.getPassword());
            String room = sroomField.getText();

            if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty() && !room.isEmpty()) {
                studentList.add(name + " (" + username + ") - Room " + room);
                studentAccounts.put(username, password);
                studentRooms.put(username, room);
                JOptionPane.showMessageDialog(this, "Student Registered Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        }
    }

    // View All Students
    private void viewStudents() {
        studentArea = new JTextArea();
        if (studentList.isEmpty()) {
            studentArea.setText("No students registered yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : studentList) {
                sb.append(s).append("\n");
            }
            studentArea.setText(sb.toString());
        }
        studentArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Registered Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // Simulate Room Allocation
    private void allocateRoom() {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students available for room allocation.");
        } else {
            JOptionPane.showMessageDialog(this, "Rooms already allocated during registration.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HostelManagementSystem::new);
    }
}
