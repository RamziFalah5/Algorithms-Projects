package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;

public class ControlSystem {


	private Main main ;
	BorderPane border = new BorderPane();

	public ControlSystem(Main main) {
		this.main = main;
	}

	public BorderPane mainInterFace() {
		VBox v1 = new VBox(30);
		v1.setStyle("-fx-background-color: #0D47A1;");
		v1.setPrefWidth(170);

		ImageView KnapSackImage = new ImageView("KnapSack.png"); 
		
		Button b1 = new Button("Tasks");
		VBox.setMargin(b1, new Insets(70, 10, 0, 10));
		style(b1);		
		ImageView taskIamge = new ImageView("Task.png");
		taskIamge.setFitHeight(30);
		taskIamge.setFitWidth(30);
		b1.setGraphic(taskIamge);
		
		Button b2 = new Button("Opt.Schedule");
		style(b2);	
		ImageView ScheduleImage = new ImageView("Schedule.png");
		ScheduleImage.setFitHeight(30);
		ScheduleImage.setFitWidth(30);
		b2.setGraphic(ScheduleImage);
		
		Button b3 = new Button("Gre.Schedule");
		style(b3);	
		ImageView ScheduleImage2 = new ImageView("Greedy.png");
		ScheduleImage2.setFitHeight(30);
		ScheduleImage2.setFitWidth(30);
		b3.setGraphic(ScheduleImage2);
		
		Button b4 = new Button("Report");
		style(b4);	
		ImageView reportImage = new ImageView("Report.png");
		reportImage.setFitHeight(30);
		reportImage.setFitWidth(30);
		b4.setGraphic(reportImage);
		
		Button b5 = new Button("Setting");
		style(b5);	
		ImageView settingImage = new ImageView("Setting.png");
		settingImage.setFitHeight(30);
		settingImage.setFitWidth(30);
		b5.setGraphic(settingImage);
		
		Button b6 = new Button("Exit");
		style(b6);	
		ImageView exitImage = new ImageView("Exit.png");
		exitImage.setFitHeight(30);
		exitImage.setFitWidth(30);
		b6.setGraphic(exitImage);
		
		v1.getChildren().addAll(KnapSackImage ,b1,b2 , b3 ,b4 ,b5,b6);
		VBox.setMargin(KnapSackImage, new Insets(30, 0, 0, 20));

		b1.setOnAction(x->{
			Dashboard d = new Dashboard();
			border.setCenter(d.ccc(main,border));
		});

		b2.setOnAction(x->{
			OPtimalSchedule o = new OPtimalSchedule();
			border.setCenter(o.CreateOPtimalSchedule(main.getTasks(), Dashboard.t_AvailableTime.getText(), border ,main));
		});
		Dashboard d = new Dashboard();
		border.setCenter(d.ccc(main,border));
		border.setLeft(v1);
		
		b3.setOnAction(x->{
			GreedyScheduleView g = new GreedyScheduleView();
			border.setCenter(g.CreateOPtimalSchedule(main.getTasks(), Dashboard.t_AvailableTime.getText(), border , main));
		});
		
		b4.setOnAction(x->{
			ReportView r =  new ReportView();
			border.setCenter(r.createReport());
		});
		

		return border;
	}
	public void style(Button b ) {
		b.setStyle("-fx-background-color: #0D47A1; -fx-text-fill: white; -fx-font-size: 17px;");
	}
}
