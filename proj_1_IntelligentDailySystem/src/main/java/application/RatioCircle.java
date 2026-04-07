package application;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RatioCircle extends StackPane {

	private double value1;
	private double value2;
	private double size; 

	public RatioCircle(double value1, double value2, double size) {
		this.value1 = value1;
		this.value2 = value2;
		this.size = size;
		draw();
	}

	private void draw() {
		getChildren().clear();

		double max = Math.max(value1, value2);
		double min = Math.min(value1, value2);

		double ratio = 0;
		if (max > 0) {
			ratio = min / max; 
		}

		double angle = 360 * ratio; 

		double radius = size / 2.0;

		Circle background = new Circle(radius);
		background.getStyleClass().add("ratio-circle-bg");

		Arc arc = new Arc();
		arc.setRadiusX(radius);
		arc.setRadiusY(radius);

		arc.setStartAngle(90); 
		arc.setLength(-angle);
		arc.setType(ArcType.ROUND);
		arc.getStyleClass().add("ratio-circle-arc");

		int percentInt = (int) Math.round(ratio * 100);
		Text percentText = new Text(percentInt + "%");
		percentText.getStyleClass().add("ratio-circle-text");
		percentText.setFont(Font.font(20));

		setMinSize(size, size);
		setPrefSize(size, size);
		setMaxSize(size, size);

		this.getStyleClass().add("ratio-circle-container");

		getChildren().addAll(background, arc, percentText);
	}

	public void setValues(double value1, double value2) {
		this.value1 = value1;
		this.value2 = value2;
		draw();
	}
}
