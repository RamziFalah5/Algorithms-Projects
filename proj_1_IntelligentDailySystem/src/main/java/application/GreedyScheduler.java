package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyScheduler {

	private List<Task> tasks;
	private String timeS;
	private int capacityUnits;

	private ArrayList<Task> selectedTasks;
	private double totalTimeHours;
	private int totalProductivity;

	public GreedyScheduler(List<Task> tasks, String capacityText) {
		this.tasks = tasks;
		this.timeS = capacityText;
		this.capacityUnits = parseCapacityToUnits(capacityText);

		this.selectedTasks = new ArrayList<>();
		this.totalTimeHours = 0.0;
		this.totalProductivity = 0;
	}

	public void solve() {
		selectedTasks.clear();
		totalTimeHours = 0.0;
		totalProductivity = 0;

		ArrayList<Task> sorted = new ArrayList<>(tasks);

		sorted.sort(Comparator.comparingDouble(this::ratio).reversed());

		int remainingUnits = capacityUnits;

		for (Task t : sorted) {
			int timeUnits = (int) Math.round(t.getTimeRequired() * 2);
			if (timeUnits <= remainingUnits) {
				selectedTasks.add(t);
				remainingUnits -= timeUnits;

				totalTimeHours += t.getTimeRequired();
				totalProductivity += t.getProductivity();
			}
		}
	}

	private double ratio(Task t) {
		double time = t.getTimeRequired();
		if (time <= 0)
			return 0;
		return t.getProductivity() / time;
	}

	public ArrayList<Task> getSelectedTasks() {
		return selectedTasks;
	}

	public double getTotalTimeHours() {
		return totalTimeHours;
	}

	public int getTotalProductivity() {
		return totalProductivity;
	}

	public String getTimeS() {
		return timeS;
	}

	public double parseTimeToHours() {
		return parseTimeStringToHours(timeS);
	}

	public double getRemainingTime() {
		double remaining = parseTimeToHours() - totalTimeHours;
		return remaining < 0 ? 0.0 : remaining;
	}

	public int getCapacityUnits() {
		return capacityUnits;
	}

	public double getCapacityHours() {
		return capacityUnits / 2.0;
	}

	private int parseCapacityToUnits(String input) {
		double hours = parseTimeStringToHours(input);
		double unitsD = hours * 2.0;
		long units = Math.round(unitsD);

		if (Math.abs(unitsD - units) > 1e-9) {
			throw new IllegalArgumentException("Time must be a multiple of 0.5 hours (e.g., 5, 5.5, 8:00, 8:30).");
		}
		return (int) units;
	}

	private double parseTimeStringToHours(String input) {
		if (input == null)
			throw new IllegalArgumentException("Time input cannot be null.");
		String s = input.trim();
		if (s.isEmpty())
			throw new IllegalArgumentException("Time input cannot be empty.");

		if (s.contains(":")) {
			if (s.length() > 5) {
				throw new IllegalArgumentException("Time must be in format H:MM or HH:MM (e.g., 8:00, 8:30).");
			}
			if (!s.matches("\\d{1,2}:\\d{2}")) {
				throw new IllegalArgumentException("Time must be in format H:MM (e.g., 8:00 or 8:30).");
			}

			String[] parts = s.split(":");
			int hours = Integer.parseInt(parts[0].trim());
			int minutes = Integer.parseInt(parts[1].trim());

			if (hours < 0 || hours > 24) {
				throw new IllegalArgumentException("Hours must be between 0 and 24.");
			}
			if (minutes != 0 && minutes != 30) {
				throw new IllegalArgumentException("Minutes must be 00 or 30 only.");
			}

			return hours + (minutes / 60.0);
		}

		double hours;
		try {
			hours = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Time must be a number (e.g., 5 or 5.5 or 8:00).");
		}

		double units = hours * 2.0;
		long rounded = Math.round(units);
		if (Math.abs(units - rounded) > 1e-9) {
			throw new IllegalArgumentException("Time must be a multiple of 0.5 hours (e.g., 5, 5.5).");
		}

		return hours;
	}
}
