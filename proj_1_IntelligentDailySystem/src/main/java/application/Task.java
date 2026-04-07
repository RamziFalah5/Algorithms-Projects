package application;

public class Task {

	private String name;
	private double timeRequired; 
	private int productivity;

	public Task(String name, double timeRequired, int productivity) {
		this.name = name;
		this.timeRequired = timeRequired;
		this.productivity = productivity;
	}


	public String getName() {
		return name;
	}

	public double getTimeRequired() {
		return timeRequired;
	}

	public int getProductivity() {
		return productivity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTimeRequired(double timeRequired) {
		this.timeRequired = timeRequired;
	}

	public void setProductivity(int productivity) {
		this.productivity = productivity;
	}

	@Override
	public String toString() {
		return name + timeRequired + productivity;
	}
}
