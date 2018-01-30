package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.Statement;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EmployeeInfoController implements Initializable {

	// public LoginModel loginModel = new LoginModel();

	@FXML
	private ObservableList<UserModel> data;

	@FXML
	private TableView<UserModel> tableview;

	@FXML
	private TableColumn<UserModel, String> columnName;

	@FXML
	private TableColumn<UserModel, String> columnSurname;

	@FXML
	private TableColumn<UserModel, String> columnEID;

	@FXML
	private TableColumn<UserModel, String> columnAge;

	@FXML
	private Label isConnected;

	@FXML
	private Label userLbl;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtSurname;

	@FXML
	private TextField txtAge;

	@FXML
	private TextField txtEID;

	@FXML
	private TextField txtSearch;

	@FXML
	private ComboBox<String> combobox;

	@FXML
	private PieChart piechart;

	@FXML
	private Label lblwebviewStatus;

	@FXML
	private Sphere earth;
	
	@FXML
	private MediaView mv;
	private MediaPlayer mp;
	private Media me;
	
	@FXML
	private Slider volumeSlider;

	@FXML
	private WebView webview;

	private WebEngine engine;

	ObservableList<String> columnTitles = FXCollections.observableArrayList(
			"EID", "Name", "Surname", "Age");

	ResultSet rs;
	PreparedStatement ps;
	Connection conn = null;;
	String SQL;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		String path = new File("src/media/vid2.mp4").getAbsolutePath();
		String path = new File("C:/Users/Mbulelo Damba/Desktop/vid2.mp4").getAbsolutePath();
		
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		mv.setMediaPlayer(mp);
	//	mp.setAutoPlay(true);
		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
		volumeSlider.setValue(mp.getVolume() * 100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
				mp.setVolume(volumeSlider.getValue() / 100);
				
			}
		});

		combobox.setItems(columnTitles);
		// combobox.getSelectionModel().selectFirst();

		engine = webview.getEngine();
		
		setCellValueFromTableToTextField();
		

		// SETTING IMAGE TO THE SPHERE

		Image earthImage = new Image("/images/Mercator.jpg");
		PhongMaterial earthPhong = new PhongMaterial();
		earthPhong.setDiffuseMap(earthImage);
		earth.setMaterial(earthPhong);

		// STEP NINE: ANIMATE THE OBJECTS

		RotateTransition rt4 = new RotateTransition();
		rt4.setNode(earth);
		rt4.setDuration(Duration.millis(9000));
		rt4.setAxis(Rotate.Y_AXIS);
		rt4.setByAngle(-360);
		rt4.setCycleCount(Animation.INDEFINITE);
		rt4.setInterpolator(Interpolator.LINEAR);
		rt4.play();

	}

	public void play(ActionEvent event) {
		mp.play();
		mp.setRate(1);
	}

	public void pause(ActionEvent event) {
		mp.pause();
	}

	public void fast(ActionEvent event) {
		mp.setRate(2);
		mp.play();
	}

	public void slow(ActionEvent event) {
		mp.setRate(0.5);
		mp.play();
	}

	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.setRate(1);
		mp.play();
	}

	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	}

	public void last(ActionEvent event) {
		mp.seek(mp.getStopTime());
		mp.stop();
	}

	
	public void stopMediaPlayer(){
		mp.stop();
	}
	
	public void loadWebsites(ActionEvent event) {
		engine.loadContent("<html><head><title>Mbulelo</title></head><body><h1>Welcome!!!</h1></body></html>");
	}

	public int ageFrom20To30Employees() {
		int rowCount = 0;
		try {
			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee where age >=20 and age<=30");

			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);
			System.out.println(rowCount);

			rs.close();
			st.close();
			conn.close();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rowCount;

	}

	public int ageFrom31To40Employees() {
		int rowCount = 0;
		try {
			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee where age >=31 and age<=40");

			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);
			System.out.println(rowCount);

			rs.close();
			st.close();
			conn.close();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rowCount;

	}

	public int ageFrom41To50Employees() {
		int rowCount = 0;
		try {
			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee where age >=41 and age<=50");

			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);
			System.out.println(rowCount);

			rs.close();
			st.close();
			conn.close();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rowCount;

	}

	public int ageFrom51To60Employees() {
		int rowCount = 0;
		try {
			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee where age >=51 and age<=60");

			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);
			System.out.println(rowCount);

			rs.close();
			st.close();
			conn.close();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rowCount;

	}

	public double recordsNumberPercentage() {
		double rowCount = 0.0;
		try {
			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee ");

			// get the number of rows from the result set
			rs.next();
			rowCount = rs.getInt(1);

			rs.close();
			st.close();
			conn.close();
			return (rowCount / 100);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return (rowCount / 100);

	}

	public void populatePieChart(ActionEvent event) {
		ObservableList<Data> list = FXCollections.observableArrayList(
				new PieChart.Data("20 - 30", ageFrom20To30Employees()),
				new PieChart.Data("31 - 40", ageFrom31To40Employees()),
				new PieChart.Data("41 - 50", ageFrom41To50Employees()),
				new PieChart.Data("51 - 60", ageFrom51To60Employees()));
		piechart.setData(list);

		for (final PieChart.Data data : piechart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							lblwebviewStatus.setText(String.valueOf(data
									.getPieValue() / recordsNumberPercentage())
									+ "%");
							// lblwebviewStatus.setText(String.valueOf(data.getPieValue()));
						}

					});
		}
	}

	public String usernameHash() {
		// String salt = txtUsername.getText();
		String salt = txtPassword.getText();
		String usernameToBeHashed = txtUsername.getText() + salt;
		String generatedUsername = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(usernameToBeHashed.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			// Get complete hashed password in hex format
			generatedUsername = sb.toString();
			return generatedUsername;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedUsername);
		return generatedUsername;

	}

	public String passwordHash() {
		// String salt = txtPassword.getText();
		String salt = txtUsername.getText();
		String passwordToBeHashed = txtPassword.getText() + salt;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(passwordToBeHashed.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
			return generatedPassword;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		return generatedPassword;

	}

	public void GetLoggedInUser(String user) {
		userLbl.setText(user);

	}

	private void setCellValueFromTableToTextField() {
		tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UserModel user = tableview.getItems().get(
						tableview.getSelectionModel().getSelectedIndex());
				txtEID.setText(user.getEID());
				txtName.setText(user.getName());
				txtSurname.setText(user.getSurname());
				txtAge.setText(user.getAge());
			}

		});
	}

	@FXML
	public void searchByFiltering() {
		txtSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				try {

					Connection conn = sqliteConnection.dbConnector(); // this is
																		// the
																		// database
																		// connection
					data = FXCollections.observableArrayList();
					String selection = (String) combobox.getSelectionModel()
							.getSelectedItem();
					String SQL = "select eid, name, surname, age from employee where "
							+ selection + " = ?  ";

					PreparedStatement ps = conn.prepareStatement(SQL);

					ps.setString(1, txtSearch.getText());

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						data.add(new UserModel(rs.getString(1),
								rs.getString(2), rs.getString(3), rs
										.getString(4)));
						tableview.setItems(data);
					}

					ps.close();
					rs.close();
					// conn.close();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error on Building Data");
				}

			}

		});
	}

	public void buildData() {

		data = FXCollections.observableArrayList();

		try {
			String SQL = "select eid,name,surname,age from employee";

			Connection conn = sqliteConnection.dbConnector(); // this is the
																// database
																// connection
			// PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = conn.createStatement().executeQuery(SQL);

			while (rs.next()) {
				data.add(new UserModel(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		// set cell value factory to tableview
		// NB: Property Value Factory must be the same with the one set in model
		// class
		columnEID.setCellValueFactory(new PropertyValueFactory<>("eid"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnSurname
				.setCellValueFactory(new PropertyValueFactory<>("surname"));
		columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));

		tableview.setItems(null);
		tableview.setItems(data);

	}

	@FXML
	public void saveData(ActionEvent event) {

		try {

			conn = sqliteConnection.dbConnector(); // this is the database
													// connection

			String SQL = "insert into employee (eid,name,surname,age) values ("
					+ txtEID.getText() + ",'" + txtName.getText() + "','"
					+ txtSurname.getText() + "'," + txtAge.getText() + ") ";

			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.execute();

			// JOptionPane.showMessageDialog(null, "Data Saved");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Data Saved");
			alert.getDialogPane().setPrefSize(200, 100);
			// Get the Stage.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

			// Add a custom icon.
			stage.getIcons()
					.add(new Image(this.getClass()
							.getResource("/images/system_login.png").toString()));

			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
					getClass().getResource("myDialogs.css").toExternalForm());
			alert.showAndWait();

			ps.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Enter valid data");
			System.out.println("something wrong");
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Information");
			alert2.setHeaderText(null);
			alert2.setContentText("Record already exists!");
			alert2.getDialogPane().setPrefSize(210, 100);
			// Get the Stage.
			Stage stage2 = (Stage) alert2.getDialogPane().getScene()
					.getWindow();

			// Add a custom icon.
			stage2.getIcons()
					.add(new Image(this.getClass()
							.getResource("/images/system_login.png").toString()));

			DialogPane dialogPane2 = alert2.getDialogPane();
			dialogPane2.getStylesheets().add(
					getClass().getResource("myDialogs.css").toExternalForm());
			alert2.showAndWait();
			e.printStackTrace();

		} finally {
			buildData();
		}
	}

	@FXML
	public void updateData(ActionEvent event) {

		try {

			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			String SQL = "update employee set eid = " + txtEID.getText()
					+ ", name = '" + txtName.getText() + "', surname = '"
					+ txtSurname.getText() + "', age = " + txtAge.getText()
					+ " where eid = " + txtEID.getText() + " ";

			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.execute();

			// JOptionPane.showMessageDialog(null, "Data Updated");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Data Updated");
			alert.getDialogPane().setPrefSize(200, 100);
			// Get the Stage.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

			// Add a custom icon.
			stage.getIcons()
					.add(new Image(this.getClass()
							.getResource("/images/system_login.png").toString()));

			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
					getClass().getResource("myDialogs.css").toExternalForm());
			alert.showAndWait();

			ps.close();

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Enter valid data");
			System.out.println("something wrong");
			e.printStackTrace();

		} finally {
			buildData();
		}

	}

	@FXML
	public void deleteData(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(null);
		alert.setContentText("Do you really want to delete?");
		alert.getDialogPane().setPrefSize(210, 100);
		// Get the Stage.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(
				new Image(this.getClass()
						.getResource("/images/system_login.png").toString()));

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(
				getClass().getResource("myDialogs.css").toExternalForm());

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		ButtonType buttonCancel = new ButtonType("Cancel",
				ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes) {
			try {

				conn = sqliteConnection.dbConnector(); // this is the database
														// connection
				String SQL = "delete from employee where eid = "
						+ txtEID.getText() + " ";

				PreparedStatement ps = conn.prepareStatement(SQL);

				ps.execute();

				// JOptionPane.showMessageDialog(null, "Data Deleted");
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information");
				alert2.setHeaderText(null);
				alert2.setContentText("Data Deleted");
				alert2.getDialogPane().setPrefSize(200, 100);
				// Get the Stage.
				Stage stage2 = (Stage) alert.getDialogPane().getScene()
						.getWindow();

				// Add a custom icon.
				stage2.getIcons().add(
						new Image(this.getClass()
								.getResource("/images/system_login.png")
								.toString()));

				DialogPane dialogPane2 = alert2.getDialogPane();
				dialogPane2.getStylesheets().add(
						getClass().getResource("myDialogs.css")
								.toExternalForm());
				alert2.showAndWait();

				ps.close();

			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "Enter valid data");
				System.out.println("something wrong");
				e.printStackTrace();

			} finally {
				buildData();
			}
		} else if (result.get() == buttonNo) {
			// do nothing and the pop-up window disappears
		}

	}

	@FXML
	public void clearData(ActionEvent event) {

		try {
			txtEID.setText(null);
			txtName.setText(null);
			txtSurname.setText(null);
			txtAge.setText(null);
			txtSearch.setText(null);

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Enter valid data");
			System.out.println("something wrong");
			e.printStackTrace();
		}
	}

	@FXML
	public void LoadData(ActionEvent event) {
		buildData();
	}

	@FXML
	public void displayTotalNumberOfRows(ActionEvent event) throws SQLException {

		try {

			conn = sqliteConnection.dbConnector(); // this is the database
													// connection
			Statement st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM employee");

			// get the number of rows from the result set
			rs.next();
			int rowCount = rs.getInt(1);
			System.out.println(rowCount);

			// JOptionPane.showMessageDialog(null,
			// "The total number of rows in this table is : "+rowCount);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("The total number of rows in this table is : "
					+ rowCount);
			alert.getDialogPane().setPrefSize(330, 70);
			// Get the Stage.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

			// Add a custom icon.

			stage.getIcons()
					.add(new Image(this.getClass()
							.getResource("/images/system_login.png").toString()));
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
					getClass().getResource("myDialogs.css").toExternalForm());
			alert.showAndWait();

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void Logout(ActionEvent event) throws SQLException, IOException {

		try {
				stopMediaPlayer();		// stops the media player
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource(
					"/application/login.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Employee Information");
			primaryStage.setResizable(false);
			primaryStage
					.getIcons()
					.add(new Image(this.getClass()
							.getResource("/images/system_login.png").toString()));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
