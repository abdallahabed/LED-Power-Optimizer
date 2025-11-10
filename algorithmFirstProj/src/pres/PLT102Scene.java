package pres;

import java.util.ArrayList;

import algorithmFirstProj.LCS;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Scene for power sources <=10.
 * Displays batteries, LEDs, and connection visualization.
 */
public class PLT102Scene {

    private GridPane grid;
    private Label maxLabel;
    private Button showBT;
    private Button connectBT;
    private Button disconnectBT;
    private Button cancelBT;
    private ArrayList<Button> batts = new ArrayList<>();
    private ArrayList<Button> ledds = new ArrayList<>();
    private ArrayList<Button> labs = new ArrayList<>();

    public PLT102Scene(Stage stage, int[] leds, int[] powers) {

        // Load images from resources
        Image off = new Image(getClass().getResource("/images/off.png").toExternalForm());
        Image on = new Image(getClass().getResource("/images/on.png").toExternalForm());
        Image offLamb = new Image(getClass().getResource("/images/offlamb.jpg").toExternalForm());
        Image onLamb = new Image(getClass().getResource("/images/onlamb.jpg").toExternalForm());

        // BorderPane root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f9f9f9, #e3f2fd);");

        // Header section (top)
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10, 15, 10, 15));
        header.setStyle("-fx-background-color: #1565C0; -fx-background-radius: 10;");

        // LCS computation
        LCS sol = new LCS(powers, leds);

        // Label for maximum LEDs powered
        maxLabel = new Label("Maximum LEDs powered: " +
                sol.getC()[powers.length][leds.length]);
        maxLabel.setFont(Font.font("Segoe UI Semibold", 18));
        maxLabel.setTextFill(Color.WHITE);

        // Buttons row
        showBT = new Button("Show Table");
        connectBT = new Button("Connect");
        disconnectBT = new Button("Disconnect");
        cancelBT = new Button("Cancel");

        Button[] topButtons = { showBT, connectBT, disconnectBT, cancelBT };
        for (Button b : topButtons) {
            b.setFont(Font.font("Segoe UI", 14));
            b.setPrefSize(110, 35);
            b.setStyle("-fx-background-color: white; -fx-text-fill: #1565C0; -fx-background-radius: 8;");
            b.setOnMouseEntered(e -> b.setStyle(
                    "-fx-background-color: #E3F2FD; -fx-text-fill: #0D47A1; -fx-background-radius: 8;"));
            b.setOnMouseExited(e -> b.setStyle(
                    "-fx-background-color: white; -fx-text-fill: #1565C0; -fx-background-radius: 8;"));
        }

        header.getChildren().addAll(maxLabel, showBT, connectBT, disconnectBT, cancelBT);
        root.setTop(header);

        // Grid for LEDs and batteries
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);

        // Responsive icon size
        int iconSize = switch (powers.length) {
            case 6 -> 90;
            case 7 -> 80;
            case 8 -> 70;
            case 9 -> 60;
            case 10 -> 50;
            default -> 100;
        };

        // Create battery buttons
        int row = 1;
        for (int i = 0; i < powers.length; i++) {
            ImageView batteryView = new ImageView(off);
            batteryView.setFitWidth(iconSize);
            batteryView.setFitHeight(iconSize);

            Button battery = new Button();
            battery.setPrefSize(iconSize, iconSize);
            battery.setGraphic(batteryView);
            battery.setStyle(
                    "-fx-border-color: transparent;-fx-background-color: transparent;");
            battery.setId(String.valueOf(powers[i]));
            battery.setOnAction(e -> {
                ImageView onView = new ImageView(on);
                onView.setFitWidth(iconSize);
                onView.setFitHeight(iconSize);
                battery.setGraphic(onView);
            });
            batts.add(battery);
            grid.add(battery, 2, row++);
        }

        // Create LED labels
        row = 1;
        for (int i = 0; i < leds.length; i++) {
            Button label = new Button(String.valueOf(leds[i]));
            label.setPrefSize(iconSize, iconSize);
            label.setFont(Font.font("Segoe UI", 16));
            label.setStyle(
                    "-fx-background-color: transparent; -fx-border-radius: 8;");
            label.setId(String.valueOf(leds[i]));
            label.setOnAction(e -> label.setStyle(
                    "-fx-background-color: lightgray; -fx-border-radius: 8;"));
            labs.add(label);
            grid.add(label, 1, row++);
        }

        // Create LED buttons (with image)
        row = 1;
        for (int i = 0; i < leds.length; i++) {
            ImageView ledView = new ImageView(offLamb);
            ledView.setFitWidth(iconSize);
            ledView.setFitHeight(iconSize);

            Button led = new Button();
            led.setPrefSize(iconSize, iconSize);
            led.setGraphic(ledView);
            led.setStyle(
                    "-fx-border-color: transparent;-fx-background-color: transparent;");
            led.setId(String.valueOf(leds[i]));
            led.setOnAction(e -> {
                ImageView onView = new ImageView(onLamb);
                onView.setFitWidth(iconSize);
                onView.setFitHeight(iconSize);
                led.setGraphic(onView);
            });
            ledds.add(led);
            grid.add(led, 0, row++);
        }

        root.setCenter(grid);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("LED Power Board");
        stage.setScene(scene);
        stage.show();
    }

    // Getters
    public Label getMaxl() {
        return maxLabel;
    }

    public Button getShow() {
        return showBT;
    }

    public Button getConnect() {
        return connectBT;
    }

    public Button getDisconnect() {
        return disconnectBT;
    }

    public Button getCancel() {
        return cancelBT;
    }

    public ArrayList<Button> getBatts() {
        return batts;
    }

    public ArrayList<Button> getLedds() {
        return ledds;
    }

    public ArrayList<Button> getLabs() {
        return labs;
    }
}
