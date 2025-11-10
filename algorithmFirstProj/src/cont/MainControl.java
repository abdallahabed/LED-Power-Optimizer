package cont;

import javafx.stage.Stage;

/*
 * This class creates the main controller that runs the app
 * and shows the first scene.
 */
public class MainControl {

	private Stage stage;
	private OpeningControl openingControl;

	public MainControl(Stage stage) {
		this.stage = stage;
	}

	public void showBrowseScene() {
		setOpeningControl(new OpeningControl(stage));
	}

	/*
	 * Getters and Setters
	 */
	public void setOpeningControl(OpeningControl openingControl) {
		this.openingControl = openingControl;
	}

	public OpeningControl getOpeningControl() {
		return openingControl;
	}
}
