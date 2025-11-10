package algorithmFirstProj;

import cont.MainControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		MainControl mainCtrl = new MainControl(stage);
		mainCtrl.showBrowseScene();
	}
}
