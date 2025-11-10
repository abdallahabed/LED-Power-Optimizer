package pres;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class shows the interface to the user for displaying number of LEDs and power sources.
 * It allows the user to show the boards of LEDs and batteries.
 */
public class ProcessScene {
	// Fields
	private Stage stage;
	private GridPane pane;
	private Image img;
	private Button showBoards;
	private Button cancel;
	private Label powerl;

	public ProcessScene(Stage stage) {
		this.stage = stage;

		// Background image
		img = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
		BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);

		// Pane setup
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(15);
		pane.setVgap(15);
		pane.setBackground(bGround);

		// Label for number of power sources
		powerl = new Label();
		powerl.setFont(Font.font(16));
		pane.add(powerl, 1, 3);

		// Button to show boards
		showBoards = new Button("Show Boards");
		showBoards.setPrefSize(150, 30);
		showBoards.setFont(Font.font(16));
		showBoards.setStyle("-fx-background-radius: 18, 7; -fx-background-color:white;");
		pane.add(showBoards, 1, 4);

		// Cancel button
		cancel = new Button("Cancel");
		cancel.setPrefSize(100, 30);
		cancel.setFont(Font.font(16));
		cancel.setStyle("-fx-background-radius: 18, 7; -fx-background-color:white;");
		pane.add(cancel, 2, 4);

		// Scene setup
		Scene scene = new Scene(pane);
		stage.setTitle("Power Source Supplied");
		stage.setScene(scene);
		stage.show();
	}

	/*
	 * Getters and Setters
	 */
	public Stage getStage() {
		return stage;
	}

	public GridPane getPane() {
		return pane;
	}

	public Button getShowBoards() {
		return showBoards;
	}

	public void setShowBoards(Button showBoards) {
		this.showBoards = showBoards;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public Label getPowerl() {
		return powerl;
	}

	public void setPowerl(Label powerl) {
		this.powerl = powerl;
	}
}
