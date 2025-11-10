// ===== PM20Scene.java =====
package pres;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class shows a scene when the power sources are more than 20.
 * It shows only the optimal solution for the maximum LEDs powered.
 */
public class PM20Scene {
	// Fields
	private Stage stage;
	private GridPane pane;
	private Label maxLabel;
	private Label lcss;
	private Button cancelBT;

	public PM20Scene(Stage stage) {
		this.stage = stage;

		// Pane setup
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(15);
		pane.setVgap(15);
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		// Label for max powered LEDs
		maxLabel = new Label();
		maxLabel.setFont(Font.font(16));
		pane.add(maxLabel, 1, 1);

		// Label for exact powered LEDs
		lcss = new Label();
		lcss.setFont(Font.font(14));
		lcss.setTextFill(Color.GREEN);
		pane.add(lcss, 1, 2);

		// Cancel button
		cancelBT = new Button("Cancel");
		cancelBT.setPrefSize(100, 30);
		cancelBT.setStyle("-fx-background-radius: 18, 7; -fx-background-color: lightyellow;");
		cancelBT.setFont(Font.font(16));
		pane.add(cancelBT, 2, 1);

		// Scene settings
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

	public Button getCancel() {
		return cancelBT;
	}

	public void setCancel(Button cancel) {
		this.cancelBT = cancel;
	}
}
