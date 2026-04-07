package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	public Stage getStage() {
		return stage;
	}

	private Stage stage;
	private BorderPane border = new BorderPane();
	private ArrayList<Task> tasks = new ArrayList<>();

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		MenuBar menu = new MenuBar();
		ControlSystem p = new ControlSystem(this);
		
		border.setTop(menu);
		border.setCenter(p.mainInterFace());
        Scene scene = new Scene(border , 1200 ,730);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void switchPane(Pane pane) {
		border.setCenter(pane);
	}

	public void switchPane(TabPane pane) {
		border.setCenter(pane);
	}
}
