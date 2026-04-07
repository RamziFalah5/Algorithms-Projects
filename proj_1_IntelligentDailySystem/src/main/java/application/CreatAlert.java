package application;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreatAlert {

    private Main main;

    public CreatAlert(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void AlertError(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(main.getStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        styleAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public void alertEror(Stage stage, String fieldName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Input Required");
        alert.setHeaderText("Field is Empty!");
        alert.setContentText("Please fill in the " + fieldName + " field.");
        styleAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public void alertError(Stage stage, String fieldName, boolean invalid) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid " + fieldName);
        alert.setContentText("The " + fieldName + " you entered is incorrect. Please check and try again.");
        styleAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public void alertInformation(Stage stage, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        styleAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public void alertNormal(Stage stage, String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        styleAlert(alert.getDialogPane());
        alert.show();
    }

    public static boolean alertUpdate(Main main) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(main.getStage());
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you really want to save the changes?");
        styleStaticAlert(alert.getDialogPane());
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void alertNegative(Stage stage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Input Error");
        alert.setHeaderText("Invalid Number");
        alert.setContentText("The entered number is negative. Please enter a positive number.");
        styleStaticAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public static void alertInvslid(Stage stage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Missing Information");
        alert.setHeaderText("Invalid Values");
        alert.setContentText("The entered values are invalid. Please confirm and try again.");
        styleStaticAlert(alert.getDialogPane());
        alert.showAndWait();
    }

    public void alertNotAllowed(Stage stage) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(stage);
        alert.setTitle("Warning");
        alert.setHeaderText("Button Already Used");
        alert.setContentText("You cannot press the button twice.");
        styleAlert(alert.getDialogPane());
        alert.show();
    }

    public static boolean alertClose(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to close?");
        alert.setContentText("Make sure all data is saved before exiting.");
        styleStaticAlert(alert.getDialogPane());
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void styleAlert(DialogPane pane) {

        pane.setStyle(
            "-fx-background-color: #D3D3D3;" +
            "-fx-border-color: #A0A0A0;" +
            "-fx-border-width: 2px;" +
            "-fx-background-radius: 10px;" +
            "-fx-border-radius: 10px;"
        );

        Platform.runLater(() -> {

            Node headerPane = pane.lookup(".header-panel");
            if (headerPane != null) {
                headerPane.setStyle(
                    "-fx-background-color: #C8C8C8;" +
                    "-fx-border-color: #A0A0A0;" +
                    "-fx-border-width: 0 0 2px 0;"
                );
            }

            Label headerLabel = (Label) pane.lookup(".header-panel .label");
            if (headerLabel != null) {
                headerLabel.setStyle(
                    "-fx-text-fill: #333333;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 14px;"
                );
            }

            Label contentLabel = (Label) pane.lookup(".content");
            if (contentLabel != null) {
                contentLabel.setStyle(
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 13px;"
                );
            }

        });
    }

    private static void styleStaticAlert(DialogPane pane) {

        pane.setStyle(
            "-fx-background-color: #D3D3D3;" +
            "-fx-border-color: #A0A0A0;" +
            "-fx-border-width: 2px;" +
            "-fx-background-radius: 10px;" +
            "-fx-border-radius: 10px;"
        );

        Node headerPane = pane.lookup(".header-panel");
        if (headerPane != null) {
            headerPane.setStyle(
                "-fx-background-color: #C8C8C8;" +
                "-fx-border-color: #A0A0A0;" +
                "-fx-border-width: 0 0 2px 0;"
            );
        }

        Label headerLabel = (Label) pane.lookup(".header-panel .label");
        if (headerLabel != null) {
            headerLabel.setStyle(
                "-fx-text-fill: #333333;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;"
            );
        }

        Label contentLabel = (Label) pane.lookup(".content");
        if (contentLabel != null) {
            contentLabel.setStyle(
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 13px;"
            );
        }
    }

}
