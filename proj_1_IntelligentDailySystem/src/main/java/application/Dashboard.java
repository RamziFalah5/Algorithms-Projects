package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class Dashboard {

	public BorderPane ccc(Main main, BorderPane border) {
		CreatAlert alert = new CreatAlert(main);

		BorderPane border2 = new BorderPane();

		HBox hTop = new HBox(30);
		hTop.setAlignment(Pos.CENTER);
		TextField t_search = new TextField();
		t_search.getStyleClass().add("my-epic-textfield");
		t_search.setPrefWidth(350);
		t_search.setPromptText("Name Task");

		Button searchButton = new Button();
		searchButton.getStyleClass().add("my-epic-button");
		ImageView searchImage = new ImageView(
				new Image(getClass().getResource("/application/Search.png").toExternalForm()));
		searchImage.setFitHeight(22);
		searchImage.setFitWidth(22);
		searchButton.setGraphic(searchImage);

		BorderPane.setMargin(hTop, new Insets(120, 370, 0, 0));

		hTop.getChildren().addAll(t_search, searchButton);
		HBox h1 = new HBox(30);
		h1.setAlignment(Pos.CENTER);

		TableView<Task> table = new TableView1().creatTableView(main.getTasks());
		table.setPrefWidth(800);
		table.setPrefHeight(450);
		table.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		table.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

		VBox v1 = new VBox(20);
		v1.setAlignment(Pos.TOP_CENTER);

		HBox hbox_time_capacity = new HBox(10);
		hbox_time_capacity.setAlignment(Pos.CENTER);

		Button b_NumberOFTasks = new Button("Tasks " + main.getTasks().size());
		b_NumberOFTasks.getStyleClass().add("my-epic-button");

		Label time_capacity = new Label("Time Capacity (Hpurs)");
		time_capacity.getStyleClass().add("my-epic-label");

		t_AvailableTime.getStyleClass().add("my-epic-textfield");
		t_AvailableTime.setPrefWidth(90);
		t_AvailableTime.setPrefHeight(20);

		hbox_time_capacity.getChildren().addAll(time_capacity, t_AvailableTime);

		Label add_task = new Label("Add New Task");
		add_task.getStyleClass().add("my-epic-label");

		HBox hbox_tasks = new HBox(10);
		TextField TextFieldName = new TextField();
		TextFieldName.getStyleClass().add("my-epic-textfield");
		TextFieldName.setPromptText("Task Name");

		TextField textFieldTime = new TextField();
		textFieldTime.getStyleClass().add("my-epic-textfield");
		textFieldTime.setPromptText("Time (hour)");

		TextField textFieldProductivity = new TextField();
		textFieldProductivity.getStyleClass().add("my-epic-textfield");
		textFieldProductivity.setPromptText("Productivity");

		hbox_tasks.getChildren().addAll(TextFieldName, textFieldTime, textFieldProductivity);

		Button b1 = new Button("Add Task");
		b1.getStyleClass().add("my-epic-button");
		ImageView addImage = new ImageView(
				new Image(getClass().getResource("/application/Add.png").toExternalForm()));
		addImage.setFitHeight(27);
		addImage.setFitWidth(27);
		b1.setGraphic(addImage);

		Button readFileButton = new Button("Reading from file");
		readFileButton.getStyleClass().add("my-epic-button");
		ImageView readFile = new ImageView(
				new Image(getClass().getResource("/application/ReadFile.png").toExternalForm()));
		readFile.setFitHeight(27);
		readFile.setFitWidth(27);
		readFileButton.setGraphic(readFile);

		Button showOptinalScheduleButton = new Button("Show Optimal Schedule");
		showOptinalScheduleButton.getStyleClass().add("my-epic-button");
		ImageView ShowPotinalScheduleIameg = new ImageView(
				new Image(getClass().getResource("/application/Arrow.png").toExternalForm()));
		ShowPotinalScheduleIameg.setFitHeight(27);
		ShowPotinalScheduleIameg.setFitWidth(27);
		showOptinalScheduleButton.setGraphic(ShowPotinalScheduleIameg);

		Button showGreedyScheduleButton = new Button("Show Greedy Schedule");
		showGreedyScheduleButton.getStyleClass().add("my-epic-button");

		v1.getChildren().addAll(b_NumberOFTasks, hbox_time_capacity, add_task, hbox_tasks, b1, readFileButton,
				showOptinalScheduleButton, showGreedyScheduleButton);
		HBox.setMargin(v1, new Insets(120, 0, 0, 0));

		h1.getChildren().addAll(table, v1);

		HBox h2 = new HBox(40);
		h2.setAlignment(Pos.BASELINE_CENTER);

		Button saveFileButton = new Button("Save to File");
		saveFileButton.getStyleClass().add("my-epic-button");
		ImageView saveFileImage = new ImageView(
				new Image(getClass().getResource("/application/SaveFile.png").toExternalForm()));
		saveFileImage.setFitHeight(27);
		saveFileImage.setFitWidth(27);
		saveFileButton.setGraphic(saveFileImage);

		Button deletTaskButton = new Button("Delet Task");
		deletTaskButton.getStyleClass().add("my-epic-button");
		deletTaskButton.setDisable(true);
		ImageView deletTaskIamge = new ImageView(
				new Image(getClass().getResource("/application/Delete.png").toExternalForm()));
		deletTaskIamge.setFitHeight(27);
		deletTaskIamge.setFitWidth(27);
		deletTaskButton.setGraphic(deletTaskIamge);

		h2.getChildren().addAll(saveFileButton, deletTaskButton);
		BorderPane.setMargin(h2, new Insets(0, 1000, 80, 0));

		b1.setOnAction(x -> {

			String name = TextFieldName.getText().trim();
			String timeText = textFieldTime.getText().trim();
			String prodText = textFieldProductivity.getText().trim();

			if (name.isEmpty()) {
				alert.AlertError("Invalid Name", "Name is Empty", "Please enter a task name.");
				return;
			}

			if (timeText.isEmpty()) {
				alert.AlertError("Invalid Time", "Time is Empty",
						"Please enter time in hours.\nAllowed: 1, 2, 2.5, 3.5\nNot allowed: 2.2, 4.1, 6.3");
				return;
			}

			if (prodText.isEmpty()) {
				alert.AlertError("Invalid Productivity", "Productivity is Empty", "Please enter productivity value.");
				return;
			}

			double timeRequired;
			try {
				timeRequired = Double.parseDouble(timeText);
			} catch (NumberFormatException e) {
				alert.AlertError("Invalid Time", "Wrong Format", "Time must be a number.\nAllowed: 5 or 5.5");
				return;
			}

			if (timeRequired <= 0) {
				alert.AlertError("Invalid Time", "Non-positive Time", "Time must be greater than 0.");
				return;
			}

			double units = timeRequired * 2.0;
			long rounded = Math.round(units);
			if (Math.abs(units - rounded) > 1e-9) {
				alert.AlertError("Invalid Time", "Only .5 is Allowed", "Time must be integer or end with .5 only.");
				return;
			}

			int productivity;
			try {
				productivity = Integer.parseInt(prodText);
			} catch (NumberFormatException e) {
				alert.AlertError("Invalid Productivity", "Wrong Format", "Productivity must be an integer.");
				return;
			}

			if (productivity <= 0) {
				alert.AlertError("Invalid Productivity", "Non-positive Value", "Productivity must be greater than 0.");
				return;
			}

			Task task = new Task(name, timeRequired, productivity);
			main.getTasks().add(task);

			TextFieldName.clear();
			textFieldTime.clear();
			textFieldProductivity.clear();

			b_NumberOFTasks.setText("Tasks " + main.getTasks().size());
			Dashboard d = new Dashboard();
			border.setCenter(d.ccc(main, border));
		});

		readFileButton.setOnAction(e -> {

			ArrayList<Task> tasks = main.getTasks();
			tasks.clear();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Tasks File");

			FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(txtFilter);

			File initialDir = new File("C:/Users/MSI/OneDrive/Algoritms");
			if (initialDir.exists()) {
				fileChooser.setInitialDirectory(initialDir);
			}

			File file = fileChooser.showOpenDialog(main.getStage());
			if (file == null) {
				return;
			}

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				String line;

				line = br.readLine();
				if (line == null) {
					alert.AlertError("Invalid File", "File is empty", "");
					return;
				}

				line = line.trim();
				String[] headerParts = line.split(",");
				if (headerParts.length != 2) {
					alert.AlertError("Invalid Header", "First line must be: numberOfTasks,capacityTime",
							"Example: 30,17:30");
					return;
				}

				int numberOfTasks = Integer.parseInt(headerParts[0].trim());
				tasks.ensureCapacity(numberOfTasks);
				String capacityText = headerParts[1].trim();
				t_AvailableTime.setText(capacityText);

				while ((line = br.readLine()) != null) {

					line = line.trim();
					if (line.isEmpty())
						continue;

					String[] parts = line.split(",");
					if (parts.length != 3) {
						alert.AlertError("Invalid Line", "Wrong Format",
								"Line must be: name,time,productivity\nLine: " + line);
						continue;
					}

					String name = parts[0].trim();

					double time = Double.parseDouble(parts[1].trim());

					int prod = Integer.parseInt(parts[2].trim());

					Task task = new Task(name, time, prod);
					tasks.add(task);
				}

				alert.alertNormal(main.getStage(), "Done", "Tasks Loaded Successfully",
						"Tasks and capacity were loaded.\nCapacity = " + capacityText);

			} catch (Exception ex) {
				alert.AlertError("Error Reading File", "An error occurred while reading the file.", ex.getMessage());
				ex.printStackTrace();
			}

			b_NumberOFTasks.setText("Tasks " + main.getTasks().size());
			Dashboard d = new Dashboard();
			border.setCenter(d.ccc(main, border));

		});

		table.setOnMouseClicked(x -> {
			deletTaskButton.setDisable(false);
			deletTaskButton.setOnAction(y -> {
				Task t = table.getSelectionModel().getSelectedItem();
				main.getTasks().remove(t);
				Dashboard d = new Dashboard();
				border.setCenter(d.ccc(main, border));
			});

		});

		showOptinalScheduleButton.setOnAction(x -> {
			OPtimalSchedule o = new OPtimalSchedule();
			border.setCenter(o.CreateOPtimalSchedule(main.getTasks(), t_AvailableTime.getText(), border, main));

		});

		showGreedyScheduleButton.setOnAction(x -> {
			GreedyScheduleView g = new GreedyScheduleView();
			border.setCenter(g.CreateOPtimalSchedule(main.getTasks(), t_AvailableTime.getText(), border, main));

		});

		border2.setTop(hTop);
		border2.setBottom(h2);
		border2.setCenter(h1);
		return border2;
	}

	public static TextField t_AvailableTime = new TextField("0:00");

}
