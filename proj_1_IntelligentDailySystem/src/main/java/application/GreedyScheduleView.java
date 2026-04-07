package application;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GreedyScheduleView {

	public BorderPane CreateOPtimalSchedule(ArrayList<Task> list, String capacity, BorderPane b, Main main) {
		BorderPane border = new BorderPane();
		CreatAlert alert = new CreatAlert(main);

		GreedyScheduler solver = null;

		try {
			solver = new GreedyScheduler(list, capacity);
			solver.solve();

		} catch (IllegalArgumentException ex) {
			alert.AlertError("Invalid Time", "Wrong Time Format", ex.getMessage());
		}

		if (solver == null) {
			Dashboard d = new Dashboard();
			border.setCenter(d.ccc(main, border));
			return border;
		}

		ArrayList<Task> optimal = solver.getSelectedTasks();

		VBox v = new VBox(30);
		v.setAlignment(Pos.CENTER);
		BorderPane.setMargin(v, new Insets(0, 400, 0, 0));

		HBox hTop = new HBox(30);
		hTop.setAlignment(Pos.CENTER);
		TextField t_search = new TextField();
		t_search.getStyleClass().add("my-epic-textfield");
		t_search.setPrefWidth(350);
		t_search.setPromptText("Search");

		Button searchButton = new Button();
		searchButton.getStyleClass().add("my-epic-button");
		ImageView searchImage = new ImageView(
				new Image(getClass().getResource("/application/Search.png").toExternalForm()));
		searchImage.setFitHeight(22);
		searchImage.setFitWidth(22);
		searchButton.setGraphic(searchImage);

		hTop.getChildren().addAll(t_search, searchButton);

		TableView<Task> table = new TableView1().creatTableView(optimal);
		table.setPrefWidth(760);
		table.setPrefHeight(450);
		table.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		table.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

		HBox h1 = new HBox(20);
		h1.setAlignment(Pos.CENTER);
		Button b1 = new Button("Total Time (hours) of Selected Tasks : " + solver.getTotalTimeHours());
		b1.getStyleClass().add("my-epic-button");
		Button b2 = new Button("Total Productivity of Selected Tasks : " + solver.getTotalProductivity());
		b2.getStyleClass().add("my-epic-button");
		Button b3 = new Button("Total Available Time (Capacity) : " + solver.getTimeS());
		b3.getStyleClass().add("my-epic-button");
		h1.getChildren().addAll(b3, b1);

		HBox h2 = new HBox(20);
		h2.setAlignment(Pos.CENTER);
		Button b4 = new Button("Remaining Unused Time : " + solver.getRemainingTime());
		b4.getStyleClass().add("my-epic-button");
		h2.getChildren().addAll(b4);

		v.getChildren().addAll(hTop, table, h1, h2);

		VBox vR = new VBox(30);
		vR.setAlignment(Pos.CENTER);

		RatioCircle ratioCircle = new RatioCircle(solver.parseTimeToHours(), solver.getTotalTimeHours(), 180);

		vR.getChildren().addAll(ratioCircle, b2);

		HBox hAll = new HBox(160);
		hAll.setAlignment(Pos.CENTER);

		hAll.getChildren().addAll(v, vR);

		border.setCenter(hAll);
		return border;
	}

	public double parseTimeToHours(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Time input cannot be null");
		}

		String s = str.trim();
		if (s.isEmpty()) {
			throw new IllegalArgumentException("Time input cannot be empty");
		}

		if (s.contains(":")) {
			String[] parts = s.split(":");
			if (parts.length != 2) {
				throw new IllegalArgumentException("Invalid time format. Use H:MM, e.g. 5:00 or 5:30");
			}

			int hours = Integer.parseInt(parts[0].trim());
			int minutes = Integer.parseInt(parts[1].trim());

			if (minutes == 0) {
				return hours;
			} else if (minutes == 30) {
				return hours + 0.5;
			} else {
				throw new IllegalArgumentException("Minutes must be 00 or 30 only.");
			}
		}

		return Double.parseDouble(s);
	}
}
