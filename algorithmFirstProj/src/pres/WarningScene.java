package pres;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * This class creates a scene to show an alert message to the user.
 */
public class WarningScene {
	// Fields
	private Stage stage;
	private Label message;
	private Button cancel;
	private GridPane pane;

	public WarningScene() {
		// Initialize stage
		stage = new Stage();

		// Initialize layout
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(15);
		pane.setVgap(15);

		// Message label
		message = new Label();
		message.setTextFill(Color.ORANGERED);
		pane.add(message, 0, 1);

		// Cancel button
		cancel = new Button("Okay");
		cancel.setPrefSize(80, 20);
		cancel.setStyle("-fx-background-radius: 18, 7; -fx-background-color:white;");
		GridPane.setHalignment(cancel, HPos.CENTER);
		pane.add(cancel, 0, 2);

		// Create scene and attach to stage
		Scene scene = new Scene(pane);
		stage.setScene(scene);
	}

	/*
	 * Getters and Setters
	 */
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Label getMessage() {
		return message;
	}

	public void setMessage(Label message) {
		this.message = message;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
}
