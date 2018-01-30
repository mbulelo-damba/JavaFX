package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Login Form");
			primaryStage.setResizable(false); 
			primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));
			primaryStage.setScene(scene);
			primaryStage.show();	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
	public static void main(String[] args) {
		launch(args);
	
	}

}
