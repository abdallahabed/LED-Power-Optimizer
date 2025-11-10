package cont;

import algorithmFirstProj.LCS;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pres.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Main Controller for Modern JavaFX Application
 * Handles navigation and file processing
 */
public class MainController {
    
    private Stage primaryStage;
    private ModernHomePage homePage;
    private LCS solution;
    
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void showHomePage() {
        homePage = new ModernHomePage(primaryStage);
        
        // Set up file browser
        homePage.getBrowseButton().setOnAction(e -> handleBrowse());
        
        // Set up run button
        homePage.getRunButton().setOnAction(e -> handleRun());
    }
    
    private void handleBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Input File");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file == null) {
            return;
        }
        
        try {
            if (readFile(file)) {
                homePage.setFileName(file.getName());
                homePage.showSuccess("File loaded: " + file.getName() + " - Ready to run!");
            }
        } catch (Exception ex) {
            homePage.showError("Error: " + ex.getMessage());
        }
    }
    
    private boolean readFile(File file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read power source count
            String line1 = reader.readLine();
            if (line1 == null) {
                throw new Exception("Empty file");
            }
            
            int powerCount = Integer.parseInt(line1.trim());
            if (powerCount <= 0) {
                throw new Exception("Invalid power source count");
            }
            
            // Read LED requirements
            String line2 = reader.readLine();
            if (line2 == null) {
                throw new Exception("Missing LED requirements line");
            }
            
            String[] ledValues = line2.trim().split("\\s+");
            if (ledValues.length != powerCount) {
                throw new Exception(
                    "Number mismatch: Expected " + powerCount + 
                    " LED values, got " + ledValues.length
                );
            }
            
            // Create arrays
            int[] powers = new int[powerCount];
            int[] leds = new int[powerCount];
            
            for (int i = 0; i < powerCount; i++) {
                powers[i] = i + 1;
                leds[i] = Integer.parseInt(ledValues[i]);
            }
            
            // Create LCS solution
            solution = new LCS(powers, leds);
            return true;
            
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number format in file");
        }
    }
    
    private void handleRun() {
        if (solution == null) {
            homePage.showError("No data loaded");
            return;
        }
        
        // Open results page in new window
        Stage resultsStage = new Stage();
        new ModernResultsPage(resultsStage, solution);
    }
}
