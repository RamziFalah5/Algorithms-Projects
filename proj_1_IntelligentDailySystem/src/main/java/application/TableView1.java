package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableView1 {


	@SuppressWarnings("unchecked")
	public TableView<Task> creatTableView(ArrayList<Task>list) {
		TableView<Task> table = new TableView<>();

		TableColumn<Task, String> name = new TableColumn<>("Name");
		name.setMinWidth(320);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Task, String> time = new TableColumn<>("TimeRequired");
		time.setMinWidth(230);
		time.setCellValueFactory(new PropertyValueFactory<>("timeRequired"));

		TableColumn<Task, String> ivity = new TableColumn<>("Productivity");
		ivity.setMinWidth(200);
		ivity.setCellValueFactory(new PropertyValueFactory<>("productivity"));

		table.getColumns().addAll(name, time, ivity);
			table.setItems(getObservableMovie(list));

		return table;
	}
	
	public ObservableList<Task> getObservableMovie(ArrayList<Task > list) {
		List<Task> allMovies = list ;
		ObservableList<Task> o = FXCollections.observableArrayList(allMovies);

		return o;
	}

}
