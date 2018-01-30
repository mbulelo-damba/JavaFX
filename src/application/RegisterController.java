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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
	public LoginModel loginModel = new LoginModel();	

	
	@FXML
	private Label isConnected;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private PasswordField txtConfirmPassword;

	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtSurname;
	
	@FXML
	private TextField txtAge;
	
	@FXML
	private TextField txtEID;
	
	  	ResultSet rs;
	    PreparedStatement ps;
	    Connection conn = null;;
	    String SQL;

	
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
		return generatedPassword;

	}

	
	

	
	@FXML
	public void Register(ActionEvent event) throws Exception{
	
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String age = txtAge.getText();
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			String confirmPassword = txtConfirmPassword.getText();
			
			if(name.trim().isEmpty() || surname.trim().isEmpty() || age.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() ){

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText(null);
				alert.setContentText("Fill in all the compulsory fields!");
			//	alert.setResizable(true);
				alert.getDialogPane().setPrefSize(280, 100);
				
				// Get the Stage.
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

				// Add a custom icon.
				stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

				DialogPane dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
				alert.showAndWait();
			} else {
				
				if(password.equals(confirmPassword)){
	//				if(txtPassword.getText().equals(txtConfirmPassword.getText())){
			
								
					try{
						
						((Node)event.getSource()).getScene().getWindow().hide();
							
						conn = sqliteConnection.dbConnector(); // this is the database connection
						String SQL = "insert into employee (name,surname,age,username,password) values ('"+txtName.getText()+ "','"+txtSurname.getText()+ "',"+txtAge.getText()+",'"+usernameHash()+ "','"+passwordHash()+ "') ";

						PreparedStatement ps = conn.prepareStatement(SQL);
				         
				         ps.execute();
				         
				     
				         Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setHeaderText(null);
							alert.setContentText("Successfully registered");
							alert.getDialogPane().setPrefSize(250, 100);
							// Get the Stage.
							Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

							// Add a custom icon.
							stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
							
							DialogPane dialogPane = alert.getDialogPane();
							dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
							alert.showAndWait();
							
				             
				         ps.close();
				         
						
					} catch (Exception e) {
		
						System.out.println("something wrong");

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText(null);
						alert.setContentText("Username/password already exist!");
					//	alert.setResizable(true);
						alert.getDialogPane().setPrefSize(280, 100);
						
						// Get the Stage.
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

						// Add a custom icon.
						stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

						DialogPane dialogPane = alert.getDialogPane();
						dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
						alert.showAndWait();
						
						e.printStackTrace();
					
				
					
					} finally {
					Stage primaryStage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					Pane root = loader.load(getClass().getResource("/application/login.fxml").openStream());
					
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setTitle("Login Form");
					primaryStage.setResizable(false);
					primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
					primaryStage.setScene(scene);
					primaryStage.show();
					
					}
					
					} else {

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText(null);
						alert.setContentText("Passwords don't match. Re-enter your password");
					//	alert.setResizable(true);
						alert.getDialogPane().setPrefSize(280, 100);
						
						// Get the Stage.
						Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

						// Add a custom icon.
						stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

						DialogPane dialogPane = alert.getDialogPane();
						dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
						alert.showAndWait();
					}
				}
}
				
				
		
		
	
	
	@FXML
	public void goBackToLogin(ActionEvent event) throws SQLException, IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/login.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Login Form");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
	

