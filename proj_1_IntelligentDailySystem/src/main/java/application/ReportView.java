package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ReportView {

    public BorderPane createReport() {

        BorderPane border = new BorderPane();

        Label title = new Label("DP + Bitmask Recurrence Relation");
        title.getStyleClass().add("report-title");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("report-textarea");

        textArea.setText(
            "DP 1D + BITMASK (MASK 1D)\n\n" +

            "Initial Values:\n" +
            "dp[c]   = 0   for all c (0 <= c <= Capacity)\n" +
            "mask[c] = 0   for all c (no tasks selected initially)\n\n" +

            "Definitions:\n" +
            "dp[c]   = maximum productivity achievable with capacity c\n" +
            "mask[c] = bitmask representing selected tasks for dp[c]\n\n" +

            "DP Recurrence (for each task i):\n" +
            "dp[c] = max( dp[c], productivity_i + dp[c - time_i] )\n" +
            "for c from Capacity down to time_i\n\n" +

            "Mask Recurrence:\n" +
            "mask[c] = mask[c - time_i] | (1 << i)\n\n" +

            "Update Condition:\n" +
            "if (productivity_i + dp[c - time_i] > dp[c]) then\n" +
            "    dp[c]   = productivity_i + dp[c - time_i]\n" +
            "    mask[c] = mask[c - time_i] | (1 << i)\n\n" +

            "Get Selected Tasks:\n" +
            "finalMask = mask[Capacity]\n" +
            "bit = 1 -> task is selected\n" +
            "bit = 0 -> task is not selected"
        );

        textArea.setPrefHeight(500);
        textArea.setPrefWidth(500);

        VBox v = new VBox(30);
        v.setAlignment(Pos.TOP_CENTER);
        v.setMaxWidth(Region.USE_PREF_SIZE);

        VBox.setMargin(title, new Insets(120, 0, 0, 0));
        v.getChildren().addAll(title, textArea);

        BorderPane.setMargin(v, new Insets(0, 0, 0, 50));
        border.setCenter(v);

        return border;
    }
}
