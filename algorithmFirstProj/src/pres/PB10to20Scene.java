package pres;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * Scene for power sources >10 and <=20.
 * Shows the optimal result summary in a modern styled layout.
 */
public class PB10to20Scene {
    // Fields
    private Label powerLabel;
    private Label maxLabel;
    private Label lcss;
    private Button cancelBT;
    private Button showBT;

    private GridPane content;
    private BorderPane root;

    public PB10to20Scene(Stage stage) {
        // Root layout with padding
        root = new BorderPane();
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f9f9f9, #dbe9f4);");

        // Header label
        Label header = new Label("Power Source Summary");
        header.setFont(Font.font("Segoe UI Semibold", 22));
        header.setTextFill(Color.DARKSLATEBLUE);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));
        root.setTop(header);
        BorderPane.setAlignment(header, Pos.CENTER);

        // Main content grid
        content = new GridPane();
        content.setAlignment(Pos.CENTER);
        content.setHgap(30);
        content.setVgap(20);

        DropShadow shadow = new DropShadow(8, Color.gray(0.4, 0.4));

        // Power label
        powerLabel = new Label("Power sources: ");
        powerLabel.setFont(Font.font("Segoe UI", 16));
        powerLabel.setTextFill(Color.DARKSLATEGRAY);
        content.add(powerLabel, 0, 0);

        // Max LEDs label
        maxLabel = new Label("Maximum LEDs powered: ");
        maxLabel.setFont(Font.font("Segoe UI", 16));
        maxLabel.setTextFill(Color.DARKSLATEGRAY);
        content.add(maxLabel, 0, 1);

        // LCS sequence label
        lcss = new Label("Optimal LED sequence: ");
        lcss.setFont(Font.font("Consolas", 15));
        lcss.setTextFill(Color.FORESTGREEN);
        content.add(lcss, 0, 2, 2, 1);

        // Buttons layout
        showBT = new Button("Show Table");
        showBT.setFont(Font.font("Segoe UI", 14));
        showBT.setPrefSize(150, 38);
        showBT.setStyle(
            "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");
        showBT.setEffect(shadow);
        showBT.setOnMouseEntered(e -> showBT.setStyle(
            "-fx-background-color: #45A049; -fx-text-fill: white; -fx-background-radius: 8;"));
        showBT.setOnMouseExited(e -> showBT.setStyle(
            "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;"));

        cancelBT = new Button("Cancel");
        cancelBT.setFont(Font.font("Segoe UI", 14));
        cancelBT.setPrefSize(120, 38);
        cancelBT.setStyle(
            "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 8;");
        cancelBT.setEffect(shadow);
        cancelBT.setOnMouseEntered(e -> cancelBT.setStyle(
            "-fx-background-color: #c0392b; -fx-text-fill: white; -fx-background-radius: 8;"));
        cancelBT.setOnMouseExited(e -> cancelBT.setStyle(
            "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 8;"));

        HBox buttonBox = new HBox(20, showBT, cancelBT);
        buttonBox.setAlignment(Pos.CENTER);
        content.add(buttonBox, 0, 3, 2, 1);

        root.setCenter(content);

        Scene scene = new Scene(root, 550, 400);
        stage.setTitle("Power Source Summary");
        stage.setScene(scene);
        stage.show();
    }

    // Getters and setters
    public Label getPowerl() {
        return powerLabel;
    }

    public void setPowerl(Label powerl) {
        this.powerLabel = powerl;
    }

    public Label getMaxl() {
        return maxLabel;
    }

    public void setMaxl(Label maxl) {
        this.maxLabel = maxl;
    }

    public Label getLcss() {
        return lcss;
    }

    public void setLcss(Label lcss) {
        this.lcss = lcss;
    }

    public Button getBtCancel() {
        return cancelBT;
    }

    public void setBtCancel(Button btCancel) {
        this.cancelBT = btCancel;
    }

    public Button getShow() {
        return showBT;
    }

    public void setShow(Button show) {
        this.showBT = show;
    }
}
