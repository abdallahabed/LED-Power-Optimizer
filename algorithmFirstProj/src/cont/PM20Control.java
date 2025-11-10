package cont;

import algorithmFirstProj.LCS;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pres.PM20Scene;

/*
 * This class creates the controller for when the power sources
 * are more than 20 batteries.
 * It only shows the maximum number of LEDs that can be powered.
 */
public class PM20Control {

	// fields
	private PM20Scene powerMT20Scene;
	private Label maxl;
	private GridPane pane;
	private Button btCancel;
	private Label lcss;

	public PM20Control(Stage stage, LCS sol) {

		setPowerMT20Scene(new PM20Scene(stage));
		setMaxl(powerMT20Scene.getMaxl());
		setPane(powerMT20Scene.getPane());
		setBtCancel(powerMT20Scene.getCancel());
		setLcss(powerMT20Scene.getLcss());

		// Display the maximum number of powered LEDs
		getMaxl().setText("Maximum LEDs powered: " + sol.getC()[sol.getLeds().length - 1][sol.getPowers().length - 1]);

		// Build the list of LEDs
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < sol.getLcss().length; i++) {
			text.append(sol.getLcss()[i]);
			if (i < sol.getLcss().length - 1) {
				text.append(", ");
			}
		}
		getLcss().setText(text.toString());

		// Handle cancel button
		handleCancel(btCancel, stage);
	}

	private void handleCancel(Button cancButton, Stage stage) {
		cancButton.setOnAction(e -> stage.close());
	}

	/*
	 * Getters and Setters
	 */
	public PM20Scene getPowerMT20Scene() {
		return powerMT20Scene;
	}

	public void setPowerMT20Scene(PM20Scene powerMT20Scene) {
		this.powerMT20Scene = powerMT20Scene;
	}

	public Label getMaxl() {
		return maxl;
	}

	public void setMaxl(Label maxl) {
		this.maxl = maxl;
	}

	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}

	public Label getLcss() {
		return lcss;
	}

	public void setLcss(Label lcss) {
		this.lcss = lcss;
	}
}
