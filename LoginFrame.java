package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class LoginFrame {
    private final JFrame loginFrame=new JFrame("Authflix");

    private final JPanel mainPanel=new JPanel();
    private final JPanel signInPanel=new JPanel();
    private final JPanel signUpPanel=new JPanel();

    private final JLabel nameLabel=new JLabel("Όνομα: ");
    private final JTextField nameField=new JTextField("Γράψε το όνομα σου");
    private final JLabel lastNameLabel=new JLabel("Επίθετο: ");
    private final JTextField lastNameField=new JTextField("Γράψε το επίθετο σου");
    private final JLabel usernameLabel=new JLabel("Username:");
    private final JTextField usernameField=new JTextField("Enter your username");
    private final JLabel passwordLabel=new JLabel("Password:");
    private final JPasswordField passwordField=new JPasswordField("Enter your password");

    private static boolean admin=false;
    JButton loginButton=new JButton("Login");
    JButton complete=new JButton("Complete");
    JButton cancelButton=new JButton("Cancel");

    public LoginFrame(){

    }
    public LoginFrame(boolean visibility) throws IOException {
        setLoginFrame();
        loginFrame.setVisible(visibility);

    }

    private void setLoginFrame() throws IOException {
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300,200);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        setCompleteButton();
        setLoginButton();
        File adminsData=new File("AdminsData.txt");
        ArrayList<String[]> data = new ArrayList<>();
        if (!adminsData.exists()) {
            data.add(new String[]{"admin1", "password1"});
            data.add(new String[]{"admin2", "password2"});
            try (ObjectOutputStream writeAdminsData = new ObjectOutputStream(new FileOutputStream("AdminsData.txt"))) {
                writeAdminsData.writeObject(data);
            }
        }
        File usersData=new File("UsersData.txt");
        if (!usersData.exists()) {
            data.clear();
            data.add(new String[]{"user1", "password1"});
            data.add(new String[]{"user2", "password2"});
            try (ObjectOutputStream writeUsersData = new ObjectOutputStream(new FileOutputStream("UsersData.txt"))) {
                writeUsersData.writeObject(data);
            }
        }
        setMainPanel();
        loginFrame.add(mainPanel);
    }
    private void setMainPanel(){
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        mainPanel.setPreferredSize(new Dimension(300,200));
        mainPanel.setBackground(Color.decode("#A6AF76"));
        JLabel border=new JLabel("");
        border.setBorder(new EmptyBorder(135,0,0,0));
        JButton signIn=new JButton("Sign in");
        JButton signUp=new JButton("Sign up");
        signIn.addActionListener(e -> {
            setSignInPanel();
            showPanel(signInPanel);
        });
        signUp.addActionListener(e -> {
            setSignUpPanel();
            showPanel(signUpPanel);
        });
        mainPanel.add(border);
        mainPanel.add(signIn);
        mainPanel.add(signUp);
    }
    private void setSignInPanel(){
        signInPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
        signInPanel.setBackground(Color.decode("#8A6154"));
        signInPanel.setPreferredSize(new Dimension(300,200));
        signInPanel.setBorder(new EmptyBorder(20,25,0,25));
        setLabels();
        setTextFields();
        setCancelButton();
        signInPanel.add(usernameLabel);
        signInPanel.add(usernameField);
        signInPanel.add(passwordLabel);
        signInPanel.add(passwordField);
        signInPanel.add(loginButton);
        signInPanel.add(cancelButton);
    }
    private void setSignUpPanel(){
        signUpPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,6));
        signUpPanel.setBackground(Color.decode("#D4AB3E"));
        signUpPanel.setPreferredSize(new Dimension(300,200));
        signUpPanel.setBorder(new EmptyBorder(0,25,0,25));
        nameField.setPreferredSize(new Dimension(130,25));
        lastNameField.setPreferredSize(new Dimension(130,25));
        setLabels();
        setTextFields();
        setCancelButton();
        signUpPanel.add(nameLabel);
        signUpPanel.add(nameField);
        signUpPanel.add(lastNameLabel);
        signUpPanel.add(lastNameField);
        signUpPanel.add(usernameLabel);
        signUpPanel.add(usernameField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);
        signUpPanel.add(complete);
        signUpPanel.add(cancelButton);
    }
    private void setLabels(){
        nameLabel.setForeground(Color.white);
        nameLabel.setPreferredSize(new Dimension(70,25));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameLabel.setForeground(Color.white);
        lastNameLabel.setPreferredSize(new Dimension(70,25));
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setPreferredSize(new Dimension(70,25));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setPreferredSize(new Dimension(70,25));


    }
    private void setTextFields(){
        nameField.setPreferredSize(new Dimension(130,25));
        nameField.setForeground(Color.GRAY);
        nameField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Γράψε το όνομα σου")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("Γράψε το όνομα σου");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });
        lastNameField.setPreferredSize(new Dimension(130,25));
        lastNameField.setForeground(Color.GRAY);
        lastNameField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameField.getText().equals("Γράψε το επίθετο σου")) {
                    lastNameField.setText("");
                    lastNameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameField.getText().isEmpty()) {
                    lastNameField.setText("Γράψε το επίθετο σου");
                    lastNameField.setForeground(Color.GRAY);
                }
            }
        });
        usernameField.setPreferredSize(new Dimension(130,25));
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter your username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter your username");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        passwordField.setPreferredSize(new Dimension(130,25));
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals("Enter your password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Enter your password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
    }
    private void setCompleteButton(){
        complete.setPreferredSize(new Dimension(100,25));

        complete.addActionListener(e -> {
            try(ObjectInputStream setUsersData=new ObjectInputStream(new FileInputStream("UsersData.txt"))) {
                @SuppressWarnings("unchecked")
                ArrayList<String[]> users=(ArrayList<String[]>) setUsersData.readObject();

                boolean exist=false;
                for (String[] usersData:users) {
                    if (usersData[0].equals(usernameField.getText())){
                        JOptionPane.showMessageDialog(loginFrame, "Username already exists.", "Sign up error", JOptionPane.ERROR_MESSAGE);
                        exist=true;
                        break;
                    }
                }
                if (nameField.getText().equals("Γράψε το όνομα σου")||lastNameField.getText().equals("Γράψε το επίθετο σου")||usernameField.getText().equals("Enter your username")||new String(passwordField.getPassword()).equals("Enter your password")) {
                    JOptionPane.showMessageDialog(loginFrame, "Please fill in all the fields!", "Sign up error", JOptionPane.ERROR_MESSAGE);
                    exist=true;

                }
                if (!exist) {
                    users.add(new String[]{usernameField.getText(), new String(passwordField.getPassword())});
                    try (ObjectOutputStream addUsersData = new ObjectOutputStream(new FileOutputStream("UsersData.txt"))) {
                        addUsersData.writeObject(users);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private void setLoginButton(){
        loginButton.setPreferredSize(new Dimension(100,25));
        loginButton.addActionListener(e -> {
            boolean found=false;
            try (ObjectInputStream getAdminsData=new ObjectInputStream(new FileInputStream("AdminsData.txt"))){
                @SuppressWarnings("unchecked")
                ArrayList<String[]> admins=(ArrayList<String[]>) getAdminsData.readObject();
                for (String[] adminsData:admins) {
                    if (Arrays.equals(adminsData, new String[]{usernameField.getText(), new String(passwordField.getPassword())})){
                        admin=true;
                        found=true;
                        loginFrame.dispose();
                        try {
                            new AdminFrame(true);
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try(ObjectInputStream getUsersData=new ObjectInputStream(new FileInputStream("UsersData.txt"))){
                @SuppressWarnings("unchecked")
                ArrayList<String[]> users=(ArrayList<String[]>) getUsersData.readObject();
                for (String[]usersData:users) {
                    if (Arrays.equals(usersData,new String[]{usernameField.getText(), new String(passwordField.getPassword())})){
                        found=true;
                        loginFrame.dispose();
                        try {
                            new UserFrame();
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (!found) {
                JOptionPane.showMessageDialog(loginFrame, "Invalid inputs.", "Sign ip error", JOptionPane.ERROR_MESSAGE);
            }


        });

    }
    public boolean getAdmin(){
        if (admin){
            return true;
        }
        return false;
    }

    private void setCancelButton(){
        cancelButton.setPreferredSize(new Dimension(85,25));
        cancelButton.addActionListener(e -> {
            mainPanel.removeAll();
            setMainPanel();
            mainPanel.revalidate();
            mainPanel.repaint();
        });


    }
    private void showPanel(JPanel displayPanel) {
        mainPanel.removeAll();
        mainPanel.add(displayPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

