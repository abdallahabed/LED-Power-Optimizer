package cont;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

import algorithmFirstProj.LCS;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pres.OpeningScene;

public class OpeningControl {
private FileChooser fileChooser;
private File file;
private OpeningScene openingScene;
private GridPane pane;
private Label process;
private Button run;
private Button cancel;
private Label hello;
private LCS sol;


public OpeningControl(Stage stage) {
    openingScene = new OpeningScene(stage);
    process = openingScene.getProcess();
    pane = openingScene.getPane();
    run = openingScene.getRun();
    cancel = openingScene.getCancel();
    hello = openingScene.getHello();
    fileChooser = new FileChooser();

    handleBrowse(stage);
    handleCancel(cancel, stage);
}

private void handleRun(Button run, Stage stage, LCS sol) {
    run.setOnAction(e -> {
        if (sol != null) {
            ProcessControl scene = new ProcessControl(stage, sol);
            scene.getPowerl().setText("Power Sources: " + sol.getPower());
        } else {
            new WarningControl("No data loaded yet!", "Missing Input").show();
        }
    });
}

private void handleBrowse(Stage stage) {
    openingScene.getBrowse().setOnAction(d -> {
        file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            new WarningControl("You haven't chosen a file yet!", "No file").show();
            return;
        }

        if (readFile(file) == -1) {
            new WarningControl("Number of LEDs and power sources don't match!", "Number mismatch").show();
        } else {
            // Safely add 'run' button only if it's not already in the pane
            if (!pane.getChildren().contains(run)) {
                pane.add(run, 1, 6);
            }
            handleRun(run, stage, sol);
            hello.setText("Ready to run");
        }
    });
}

private void handleCancel(Button cancel, Stage stage) {
    cancel.setOnAction(e -> stage.close());
}

public int readFile(File input) {
	
	

	
	
	
    int x = 0;
    try (BufferedReader buffer = new BufferedReader(new FileReader(input))) {
    	int power = Integer.parseInt(buffer.readLine());
    	String s[] = buffer.readLine().split(" ");
    	if (s.length != power) {
    	    x = -1;
    	} else {
    	    int[] powerArr = new int[power];
    	    int[] ledArr = new int[power];
    	    for (int i = 0; i < power; i++) {
    	        powerArr[i] = i + 1;
    	        ledArr[i] = Integer.parseInt(s[i]);
    	    }
    	    sol = new LCS(powerArr, ledArr);
    	
        }
    } catch (NumberFormatException t) {
        new WarningControl(t.getMessage(), "Number Format Exception").show();
    } catch (IOException e1) {
        new WarningControl(e1.getMessage(), "IO Exception").show();
    } catch (InputMismatchException e) {
        new WarningControl(e.getMessage(), "Input Mismatch Exception").show();
    }
    return x;
}

// ===== Getters & Setters =====
public Button getCancel() { return cancel; }
public void setCancel(Button cancel) { this.cancel = cancel; }
public Button getRun() { return run; }
public void setRun(Button run) { this.run = run; }
public FileChooser getFileChooser() { return fileChooser; }
public void setFileChooser(FileChooser fileChooser) { this.fileChooser = fileChooser; }
public File getFile() { return file; }
public void setFile(File file) { this.file = file; }
public OpeningScene getOpeningScene() { return openingScene; }
public void setOpeningScene(OpeningScene openingScene) { this.openingScene = openingScene; }
public GridPane getPane() { return pane; }
public void setPane(GridPane pane) { this.pane = pane; }
public Label getProcess() { return process; }
public void setProcess(Label process) { this.process = process; }
public Label getHello() { return hello; }
public void setHello(Label hello) { this.hello = hello; }
public LCS getSol() { return sol; }
public void setSol(LCS sol) { this.sol = sol; }


}
