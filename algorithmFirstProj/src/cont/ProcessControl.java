package cont;

import algorithmFirstProj.LCS;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pres.ProcessScene;

/*
 * This class creates the controller for the scene that shows how many LEDs
 * the user has and lets them show the boards of LEDs and batteries.
 */
public class ProcessControl {
	// Fields
	private ProcessScene procScene;
	private GridPane pane;
	private Button showBoards;
	private Label powerl;
	private Button btCancel;

	public ProcessControl(Stage stage, LCS sol) {
		this.procScene = new ProcessScene(stage);
		this.pane = procScene.getPane();
		this.powerl = procScene.getPowerl();
		this.btCancel = procScene.getCancel();
		this.showBoards = procScene.getShowBoards();

		handle_cancel(btCancel, stage);
		handle_boards(showBoards, stage, sol);

		// Optional: Show quick summary
		powerl.setText("Power Sources: " + sol.getPower());
	}

	private void handle_boards(Button show, Stage stage, LCS sol) {
		show.setOnAction(e -> {
			int powerCount = sol.getPower();

			if (powerCount <= 10) {
				new PowerLT10Control(stage, sol);
			} else if (powerCount <= 20) {
				new PLT20Control(stage, sol);
			} else {
				new PM20Control(stage, sol);
			}
		});
	}

	private void handle_cancel(Button cancel, Stage stage) {
		cancel.setOnAction(e -> stage.close());
	}

	/*
	 * Getters and Setters
	 */
	public GridPane getPane() {
		return pane;
	}

	public Button getShowBoards() {
		return showBoards;
	}

	public void setShowBoards(Button showBoards) {
		this.showBoards = showBoards;
	}

	public Label getPowerl() {
		return powerl;
	}

	public void setPowerl(Label powerl) {
		this.powerl = powerl;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}
}
