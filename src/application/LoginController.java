package application;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();
	
	ResultSet rs;
    PreparedStatement ps;
    Connection conn = null;;
    String SQL;
	
	@FXML
	private Label isConnected;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (loginModel.isDBConnected()){
			isConnected.setText("Connected");
		}else {
			isConnected.setText("Not Connected");
		}
	}
	
	
	
	public String usernameHash() {
		String salt = txtPassword.getText();
		String usernameToBeHashed = txtUsername.getText()+salt;
		String generatedUsername = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(usernameToBeHashed.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedUsername = sb.toString();
			return generatedUsername;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedUsername);
		return generatedUsername;
}

	
	
	public String passwordHash() {
		String salt = txtUsername.getText();
		String passwordToBeHashed = txtPassword.getText()+salt;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(passwordToBeHashed.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
			return generatedPassword;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		return generatedPassword;
}

	
	
	public String getUserOriginalName() throws SQLException  {

			String results = null;
			try {
				
				Connection conn = sqliteConnection.dbConnector(); // this is the database connection
			//	data = FXCollections.observableArrayList();
				String SQL = "select name from employee where username = '"+usernameHash()+"' and password = '"+passwordHash()+"' ";
			//	PreparedStatement ps = conn.prepareStatement(SQL);
				ResultSet rs =conn.createStatement().executeQuery(SQL);
				
				while(rs.next()){
					
					results = rs.getString(1);
			//		System.out.println(results);
				}
				rs.close();
			//	conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
			return results;
			
	}
	

	
	
	
	
	
	
	public void RegisterNow(ActionEvent event) {
		try {
		//	JOptionPane.showMessageDialog(null, "Successfully logged out!!!");
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/register.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Registration Form");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}

	
	public void Login (ActionEvent event) throws SQLException {
			
		try {
	//		if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())){
			if (loginModel.isLogin(usernameHash(),passwordHash())){	
				isConnected.setText("username and password is correct");
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/employeeinfo.fxml").openStream());
		
				EmployeeInfoController employeeInfoController = (EmployeeInfoController)loader.getController();
				employeeInfoController.GetLoggedInUser("Welcome back "+getUserOriginalName());
		//		employeeInfoController.GetLoggedInUser("Welcome back " + txtUsername.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Employee Information");
				primaryStage.setResizable(false);
				primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
				primaryStage.setScene(scene);
				primaryStage.show();
			
				
			}else {
				isConnected.setText("username and password is not correct");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
