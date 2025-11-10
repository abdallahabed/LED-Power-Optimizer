// ===== TableControl.java =====
package cont;

import javafx.scene.control.Button;
import pres.TableScene;

/*
 * This class creates the controller of the Dynamic Programming table scene
 * to be shown to the user.
 */
public class TableControl {
	// Fields
	private Button cancel;
	private TableScene scene;

	public TableControl(int[] leds, int[] powers) {
		setScene(new TableScene(leds, powers));
		setCancel(getScene().getBtCancel());
		handle_cancel(getCancel());
	}

	private void handle_cancel(Button cancelButton) {
		cancelButton.setOnAction(e -> {
			// Defensive check: make sure stage isn't null
			if (getScene() != null && getScene().getStage() != null) {
				getScene().getStage().close();
			}
		});
	}

	/*
	 * Getters and Setters
	 */
	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public TableScene getScene() {
		return scene;
	}

	public void setScene(TableScene scene) {
		this.scene = scene;
	}
}
