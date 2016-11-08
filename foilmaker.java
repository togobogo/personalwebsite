import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Foilmaker extends JFrame {
    //the master frame: called frame
    JFrame frame = new JFrame("FoilMaker");
    JPanel masterPanel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel textBoxPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    
    GridLayout layout = new GridLayout(6, 3, 10, 10);
    
    //Los Buttons
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");
    
    //Los Labels
    JLabel usernameLabel = new JLabel("Username: ", JLabel.LEFT);
    JLabel passwordLabel = new JLabel("Password: ", JLabel.LEFT);
    
    //Los TextBoxes
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();
    
    public Foilmaker() {
        //change button size
        login.setBounds(400, 200, 111, 111);
        
        //Add buttons and Labels to the login screen
        masterPanel.add(usernameLabel);
        masterPanel.add(passwordLabel);
        masterPanel.add(userField);
        masterPanel.add(passField);
        masterPanel.add(login);
        masterPanel.add(register);        
        
        //set color
        masterPanel.setBackground(Color.PINK);
        
        //set frame layout        
        masterPanel.setLayout(layout);
                
        /* Add action listener to login button */
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //open new window if credentials exist
                String username = userField.getText();
                String password = passField.getText();
                
                if (username.equals("")){
                    JOptionPane.showMessageDialog(null, "Username cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password.equals("")){
                    JOptionPane.showMessageDialog(null, "Password cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //start the server
                String serverIP = "localhost";
                int serverPort = 1452;
                
                try {
                    // Connect to server
                    Socket socket = new Socket(serverIP, serverPort);
                    // Create data writer
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    // Create data reader
                    InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                    BufferedReader in = new BufferedReader(isr);
                    // Send message to server
                    out.println("LOGIN--" + username + "--" + password);
                    // Read server response
                    String serverMessage = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
        });
            
        /* Add action listener to button 2 on panel 2 */
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //open new window if username is already not in the database
                String username = userField.getText();
                String password = passField.getText();
                
                if (username.equals("")){
                    JOptionPane.showMessageDialog(null, "Username cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password.equals("")){
                    JOptionPane.showMessageDialog(null, "Password cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //start the server
                String serverIP = "localhost";
                int serverPort = 1452;
                
                try {
                    // Connect to server
                    Socket socket = new Socket(serverIP, serverPort);
                    // Create data writer
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    // Create data reader
                    InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                    BufferedReader in = new BufferedReader(isr);
                    // Send message to server
                    out.println("CREATENEWUSER--" + username + "--" + password);
                    // Read server response
                    String serverMessage = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        });
        
        /* Set dimensions */
        frame.setLocation(700, 400);
        frame.setSize(400, 600);
        
        /* Dispose frame when close button is pressed */
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        /* Show panel 1 initially */
//        frame.pack();
        frame.add(masterPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Foilmaker foilmaker = new Foilmaker(); /* Show GUI */
    }
}
