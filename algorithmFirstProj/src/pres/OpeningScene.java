package pres;

import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class creates the scene for browsing input files.
 * It allows the user to browse the input file and shows alerts if needed.
 */
public class OpeningScene {

	// Fields
	private GridPane pane;
	private Image img;
	private Label hello;
	private Label process;
	private Button run;
	private Button browse;
	private Button cancel;

	public OpeningScene(Stage stage) {
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);

		// Try loading background safely
		try {
			// Load from resources (adjust path if needed)
			img = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER, new BackgroundSize(600, 600, false, false, false, false));
			pane.setBackground(new Background(bImg));
		} catch (Exception e) {
			System.out.println("Warning: Background image not found.");
		}

		// Greeting label
		hello = new Label("Hello user, select your LEDs and Power file...");
		hello.setFont(Font.font(16));
		hello.setTextFill(Color.BLACK);
		pane.add(hello, 0, 5, 3, 1);

		// Process label
		process = new Label("Process");
		process.setFont(Font.font(12));
		process.setTextFill(Color.RED);
		pane.add(process, 0, 6, 3, 1);

		// Run button
		run = new Button("Run");
		run.setFont(Font.font(14));
		run.setPrefSize(100, 30);
		run.setStyle("-fx-background-radius: 18, 7; -fx-background-color: white; -fx-text-fill: black;");
		pane.add(run, 0, 7);

		// Browse button
		browse = new Button("Browse");
		browse.setFont(Font.font(14));
		browse.setPrefSize(100, 30);
		browse.setStyle("-fx-background-radius: 18, 7; -fx-background-color: white; -fx-text-fill: black;");
		pane.add(browse, 1, 7);

		// Cancel button
		cancel = new Button("Cancel");
		cancel.setFont(Font.font(14));
		cancel.setPrefSize(100, 30);
		cancel.setStyle("-fx-background-radius: 18, 7; -fx-background-color: white; -fx-text-fill: black;");
		cancel.setOnAction(e -> Platform.exit());
		pane.add(cancel, 2, 7);

		// Scene setup
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Max LED Lighting");
		stage.show();
	}

	/*
	 * Getters and Setters
	 */
	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Label getHello() {
		return hello;
	}

	public void setHello(Label hello) {
		this.hello = hello;
	}

	public Label getProcess() {
		return process;
	}

	public void setProcess(Label process) {
		this.process = process;
	}

	public Button getRun() {
		return run;
	}

	public void setRun(Button run) {
		this.run = run;
	}

	public Button getBrowse() {
		return browse;
	}

	public void setBrowse(Button browse) {
		this.browse = browse;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
}
