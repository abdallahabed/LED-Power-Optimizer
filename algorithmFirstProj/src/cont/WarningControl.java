package cont;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pres.WarningScene;

/*
 * This class is a controller for an alert shown to the user for a specific warning.
 */
public class WarningControl {
	// Fields
	private WarningScene scene;
	private Label message;
	private Button cancel;

	public WarningControl(String warning, String title) {
		this.scene = new WarningScene();
		this.message = scene.getMessage();
		this.cancel = scene.getCancel();

		scene.getStage().setTitle(title);
		message.setText(warning);

		handle_cancel(cancel);
	}

	private void handle_cancel(Button cancelButton) {
		// Handles cancel button click
		cancelButton.setOnAction(e -> {
			if (scene != null && scene.getStage() != null) {
				scene.getStage().close();
			}
		});
	}

	/*
	 * Show the warning window
	 */
	public void show() {
		if (scene != null && scene.getStage() != null) {
			scene.getStage().show();
		}
	}

	/*
	 * Getters and Setters
	 */
	public WarningScene getScene() {
		return scene;
	}

	public void setScene(WarningScene scene) {
		this.scene = scene;
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
