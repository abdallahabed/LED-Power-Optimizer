package pres;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * Modern HomePage matching React web app design
 * Features: Gradient background, card layout, modern buttons, file upload area
 */
public class ModernHomePage {
    
    private Stage stage;
    private VBox mainContainer;
    private Label statusLabel;
    private Button browseButton;
    private Button runButton;
    private String fileName = "";
    
    public ModernHomePage(Stage stage) {
        this.stage = stage;
        createScene();
    }
    
    private void createScene() {
        // Main container with gradient background
        mainContainer = new VBox(0);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(40));
        mainContainer.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #EBF4FF 0%, #C7D7FF 100%);"
        );
        
        // Card container
        VBox card = createCard();
        mainContainer.getChildren().add(card);
        
        // Create scene
        Scene scene = new Scene(mainContainer, 900, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("LED Power Optimizer");
        stage.show();
    }
    
    private VBox createCard() {
        VBox card = new VBox(24);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(600);
        card.getStyleClass().add("card");
        
        // Icon
        Region icon = createIcon();
        
        // Title
        Label title = new Label("LED Power Optimizer");
        title.getStyleClass().add("title");
        title.setTextFill(Color.web("#1F2937"));
        
        // Subtitle
        Label subtitle = new Label("Upload your input file to find the optimal LED/Power configuration");
        subtitle.getStyleClass().add("subtitle");
        subtitle.setWrapText(true);
        subtitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        subtitle.setMaxWidth(500);
        
        // Upload area
        VBox uploadArea = createUploadArea();
        
        // Format info
        VBox formatInfo = createFormatInfo();
        
        // Buttons (initially only browse, run appears after file selection)
        HBox buttonBox = new HBox(16);
        buttonBox.setAlignment(Pos.CENTER);
        
        browseButton = new Button("Browse File");
        browseButton.getStyleClass().add("modern-button-primary");
        browseButton.setPrefWidth(150);
        
        runButton = new Button("Run");
        runButton.getStyleClass().add("modern-button-success");
        runButton.setPrefWidth(150);
        runButton.setVisible(false);
        runButton.setManaged(false);
        
        buttonBox.getChildren().addAll(browseButton, runButton);
        
        // Status label
        statusLabel = new Label("");
        statusLabel.getStyleClass().add("body-text");
        statusLabel.setTextFill(Color.web("#EF4444"));
        
        card.getChildren().addAll(
            icon,
            title,
            subtitle,
            uploadArea,
            formatInfo,
            buttonBox,
            statusLabel
        );
        
        return card;
    }
    
    private Region createIcon() {
        // File icon container
        StackPane iconContainer = new StackPane();
        iconContainer.getStyleClass().add("icon-container");
        iconContainer.setPrefSize(64, 64);
        
        // Simple file icon using SVG
        SVGPath fileSVG = new SVGPath();
        fileSVG.setContent("M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8l-6-6z M14 2v6h6");
        fileSVG.setFill(Color.WHITE);
        fileSVG.setStroke(Color.WHITE);
        fileSVG.setScaleX(1.5);
        fileSVG.setScaleY(1.5);
        
        iconContainer.getChildren().add(fileSVG);
        return iconContainer;
    }
    
    private VBox createUploadArea() {
        VBox uploadBox = new VBox(12);
        uploadBox.setAlignment(Pos.CENTER);
        uploadBox.getStyleClass().add("upload-area");
        uploadBox.setPrefHeight(120);
        uploadBox.setMaxWidth(Double.MAX_VALUE);
        
        // Upload icon (simple)
        Label uploadIcon = new Label("📄");
        uploadIcon.setStyle("-fx-font-size: 32px;");
        
        // Upload text
        Label uploadText = new Label("Click to browse file");
        uploadText.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: 600;" +
            "-fx-text-fill: #374151;"
        );
        
        Label uploadHint = new Label("Upload a text file with power sources and LED requirements");
        uploadHint.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #6B7280;"
        );
        
        uploadBox.getChildren().addAll(uploadIcon, uploadText, uploadHint);
        
        // Make clickable
        uploadBox.setOnMouseClicked(e -> browseButton.fire());
        
        return uploadBox;
    }
    
    private VBox createFormatInfo() {
        VBox infoBox = new VBox(12);
        infoBox.getStyleClass().add("info-box");
        infoBox.setMaxWidth(Double.MAX_VALUE);
        
        Label infoTitle = new Label("Input File Format:");
        infoTitle.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: 600;" +
            "-fx-text-fill: #3730A3;"
        );
        
        VBox formatBox = new VBox(4);
        formatBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 12px;" +
            "-fx-font-family: monospace;"
        );
        
        Label line1 = new Label("Line 1: Number of power sources");
        line1.setStyle("-fx-font-size: 12px; -fx-text-fill: #6B7280;");
        
        Label line2 = new Label("Line 2: LED power requirements (space-separated)");
        line2.setStyle("-fx-font-size: 12px; -fx-text-fill: #6B7280;");
        
        VBox exampleBox = new VBox(2);
        exampleBox.setStyle(
            "-fx-background-color: #EEF2FF;" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8px;" +
            "-fx-spacing: 2px;"
        );
        
        Label exampleLabel = new Label("Example:");
        exampleLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #4F46E5; -fx-font-weight: bold;");
        
        Label example1 = new Label("5");
        example1.setStyle("-fx-font-size: 12px; -fx-text-fill: #4F46E5; -fx-font-family: monospace;");
        
        Label example2 = new Label("3 1 2 5 4");
        example2.setStyle("-fx-font-size: 12px; -fx-text-fill: #4F46E5; -fx-font-family: monospace;");
        
        exampleBox.getChildren().addAll(exampleLabel, example1, example2);
        formatBox.getChildren().addAll(line1, line2, exampleBox);
        infoBox.getChildren().addAll(infoTitle, formatBox);
        
        return infoBox;
    }
    
    public void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setTextFill(Color.web("#10B981"));
        runButton.setVisible(true);
        runButton.setManaged(true);
    }
    
    public void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setTextFill(Color.web("#EF4444"));
        runButton.setVisible(false);
        runButton.setManaged(false);
    }
    
    public void setFileName(String name) {
        this.fileName = name;
    }
    
    // Getters
    public Button getBrowseButton() { return browseButton; }
    public Button getRunButton() { return runButton; }
    public Stage getStage() { return stage; }
}
