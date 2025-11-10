package algorithmFirstProj;

import cont.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for Modern LED Power Optimizer
 * JavaFX application with React-inspired design
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		MainController controller = new MainController(primaryStage);
		controller.showHomePage();
	}
}
