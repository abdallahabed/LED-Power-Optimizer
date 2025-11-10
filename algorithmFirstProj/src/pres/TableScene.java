package pres;

import algorithmFirstProj.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class shows a scene of the dynamic programming table for the user.
 */
public class TableScene {
	// Fields
	private GridPane root;
	private Button btCancel;
	private Stage stage;

	public TableScene(int[] leds, int[] powers) {
		LCS sol = new LCS(leds, powers);

		int length = powers.length;
		int width = leds.length;

		root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(1);
		root.setVgap(1);

		// Column headers (LEDs)
		for (int y = 1; y <= length; y++) {
			Label num = new Label(leds[y - 1] + "");
			num.setPrefSize(50, 50);
			num.setAlignment(Pos.CENTER);
			num.setFont(Font.font(14));
			num.setStyle("-fx-background-color:lightyellow; -fx-border-color:lightgrey;");
			GridPane.setRowIndex(num, y);
			GridPane.setColumnIndex(num, 0);
			root.getChildren().add(num);
		}

		// Row headers (Powers)
		for (int x = 1; x <= width; x++) {
			Label num = new Label(powers[x - 1] + "");
			num.setPrefSize(50, 50);
			num.setAlignment(Pos.CENTER);
			num.setFont(Font.font(14));
			num.setStyle("-fx-background-color:lightyellow; -fx-border-color:lightgrey;");
			GridPane.setRowIndex(num, 0);
			GridPane.setColumnIndex(num, x);
			root.getChildren().add(num);
		}

		// Table cells
		for (int y = 1; y <= length; y++) {
			for (int x = 1; x <= width; x++) {
				Label num = new Label(sol.getC()[y - 1][x - 1] + "" + sol.getB()[y - 1][x - 1]);
				num.setPrefSize(50, 50);
				num.setAlignment(Pos.CENTER);
				num.setFont(Font.font(14));
				num.setStyle("-fx-background-color:white; -fx-border-color:lightgrey;");
				GridPane.setRowIndex(num, y);
				GridPane.setColumnIndex(num, x);
				root.getChildren().add(num);
			}
		}

		// Cancel button
		btCancel = new Button("Ok");
		btCancel.setFont(Font.font(14));
		btCancel.setTextFill(Color.WHITE);
		btCancel.setStyle("-fx-background-radius: 7, 7; -fx-background-color:grey;");
		root.add(btCancel, 0, 0);
		GridPane.setColumnSpan(btCancel, width + 1);

		// Stage setup
		stage = new Stage();
		Scene scene = new Scene(root, 800, 800);
		stage.setTitle("Dynamic Table");
		stage.setScene(scene);
		stage.show();
	}

	/*
	 * Getters and Setters
	 */
	public GridPane getRoot() {
		return root;
	}

	public void setRoot(GridPane root) {
		this.root = root;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
