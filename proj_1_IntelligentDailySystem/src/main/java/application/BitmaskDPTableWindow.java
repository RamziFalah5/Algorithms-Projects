package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BitmaskDPTableWindow {

    public static GridPane buildDp1DGrid(int[] dp, int capacityUnits) {

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("dp-grid");

        Label corner = new Label("DP[c]");
        corner.getStyleClass().add("dp-header");
        grid.add(corner, 0, 0);

        for (int c = 0; c <= capacityUnits; c++) {
            Label time = new Label(formatUnitsToTimeText(c));
            time.getStyleClass().add("dp-header");
            grid.add(time, c + 1, 0);
        }

        Label row = new Label("Final");
        row.getStyleClass().add("dp-row-header");
        grid.add(row, 0, 1);

        for (int c = 0; c <= capacityUnits; c++) {
            Label val = new Label(String.valueOf(dp[c]));
            val.getStyleClass().add("dp-cell");
            grid.add(val, c + 1, 1);
        }

        return grid;
    }

    public static GridPane buildMask1DGrid(long[] mask, int capacityUnits, int tasksCount) {

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("dp-grid");

        Label corner = new Label("Mask[c]");
        corner.getStyleClass().add("dp-header");
        grid.add(corner, 0, 0);

        for (int c = 0; c <= capacityUnits; c++) {
            Label time = new Label(formatUnitsToTimeText(c));
            time.getStyleClass().add("dp-header");
            grid.add(time, c + 1, 0);
        }

        Label row = new Label("Final");
        row.getStyleClass().add("dp-row-header");
        grid.add(row, 0, 1);

        for (int c = 0; c <= capacityUnits; c++) {
            String bits = (mask[c] == 0L) ? "0" : toBitString(mask[c], tasksCount);
            Label val = new Label(bits);
            val.getStyleClass().add("dp-cell");
            grid.add(val, c + 1, 1);
        }

        return grid;
    }

    private static String formatUnitsToTimeText(int units) {
        int minutes = units * 30;
        int h = minutes / 60;
        int m = minutes % 60;
        return h + ":" + (m == 0 ? "00" : "30");
    }

    private static String toBitString(long mask, int tasksCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = tasksCount - 1; i >= 0; i--) {
            sb.append((mask & (1L << i)) != 0 ? '1' : '0');
        }
        return sb.toString();
    }
}
