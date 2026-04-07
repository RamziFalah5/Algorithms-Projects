package application;

import java.util.ArrayList;
import java.util.List;

public class BitmaskDPScheduler {

	private List<Task> tasks;
	private String timeS;
	private int capacityUnits;
	private int[] dp;
	private long[] mask;
	
	private ArrayList<Task> optimalTasks;
	private double totalTimeHours;
	private int totalProductivity;

	public BitmaskDPScheduler(List<Task> tasks, String capacityText) {
		this.tasks = tasks;
		this.timeS = capacityText;
		this.capacityUnits = parseCapacityToUnits(capacityText);
		this.optimalTasks = new ArrayList<>();
	}

	public void solve() {
		int n = tasks.size();
		dp = new int[capacityUnits + 1];
		mask = new long[capacityUnits + 1];


		for (int i = 0; i < n; i++) {
			Task t = tasks.get(i);
			double timeHours = t.getTimeRequired();
			int timeUnite = (int) Math.round(timeHours * 2);
			int v = t.getProductivity();

			for (int c = capacityUnits; c >= timeUnite; c--) {
				int takeValue = v + dp[c - timeUnite];
				if (takeValue > dp[c]) {
					dp[c] = takeValue;
					mask[c] = mask[c - timeUnite] | (1L << i);
				}
			}

		}
		reconstructSolutionFromMask();
	}

	private void reconstructSolutionFromMask() {
		optimalTasks.clear();
		totalTimeHours = 0.0;
		totalProductivity = 0;

		long bestMask = mask[capacityUnits];
		int n = tasks.size();

		for (int i = 0; i < n; i++) {
			if ((bestMask & (1L << i)) != 0) {
				Task t = tasks.get(i);
				optimalTasks.add(t);
				totalTimeHours += t.getTimeRequired();
				totalProductivity += t.getProductivity();
			}
		}
	}

	public ArrayList<Task> getOptimalTasks() {
		return optimalTasks;
	}

	public double getTotalTimeHours() {
		return totalTimeHours;
	}

	public int getTotalProductivity() {
		return totalProductivity;
	}

	public int[] getDpArray() {
		return dp;
	}

	public long[] getMaskArray() {
		return mask;
	}

	public double getCapacityHours() {
		return capacityUnits / 2.0;
	}

	public int getCapacityUnits() {
		return capacityUnits;
	}

	public String getTimeS() {
		return timeS;
	}

	private int parseCapacityToUnits(String input) {
		double hours = parseTimeStringToHours(input);
		double unitsD = hours * 2.0;
		long units = Math.round(unitsD);

		if (Math.abs(unitsD - units) > 1e-9) {
			throw new IllegalArgumentException(
					"Time must be a multiple of 0.5 hours (e.g., 5, 5.5, 3, 2.5, 8:00, 8:30).");
		}
		return (int) units;
	}

	private double parseTimeStringToHours(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Time input cannot be null.");
		}

		String s = input.trim();
		if (s.isEmpty()) {
			throw new IllegalArgumentException("Time input cannot be empty.");
		}

		if (s.contains(":")) {
			if (s.length() > 5) {
				throw new IllegalArgumentException("Time must be in format H:MM or HH:MM (e.g., 8:00, 8:30).");
			}

			if (!s.matches("\\d{1,2}:\\d{2}")) {
				throw new IllegalArgumentException("Time must be in format H:MM (e.g., 8:00 or 8:30).");
			}

			String[] parts = s.split(":");
			int hours;
			int minutes;
			try {
				hours = Integer.parseInt(parts[0].trim());
				minutes = Integer.parseInt(parts[1].trim());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Hours and minutes must be numeric.");
			}

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
			throw new IllegalArgumentException("Time must be a multiple of 0.5 hours (e.g., 5, 5.5, 3, 2.5).");
		}

		return hours;
	}

	public double parseTimeToHours() {
		return parseTimeStringToHours(timeS);
	}

	public double getRemainingTime() {
		double remaining = parseTimeToHours() - getTotalTimeHours();
		return remaining < 0 ? 0.0 : remaining;
	}
}
