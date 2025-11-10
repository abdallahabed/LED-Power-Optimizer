package pres;

import algorithmFirstProj.LCS;
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
 * Modern Results Page matching React web app design
 * Features: Metric cards, badge display, modern buttons
 */
public class ModernResultsPage {
    
    private Stage stage;
    private LCS solution;
    private int powerCount;
    
    public ModernResultsPage(Stage stage, LCS solution) {
        this.stage = stage;
        this.solution = solution;
        this.powerCount = solution.getPower();
        createScene();
    }
    
    private void createScene() {
        // Main container
        VBox mainContainer = new VBox(24);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(40));
        mainContainer.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #EBF4FF 0%, #C7D7FF 100%);"
        );
        
        // Back button
        HBox backBox = new HBox();
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.setMaxWidth(800);
        
        Button backButton = new Button("← Back to Home");
        backButton.getStyleClass().add("modern-button-secondary");
        backButton.setOnAction(e -> stage.close());
        backBox.getChildren().add(backButton);
        
        // Card container
        VBox card = createResultsCard();
        card.setMaxWidth(800);
        
        mainContainer.getChildren().addAll(backBox, card);
        
        // Scroll pane
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setStyle("-fx-background-color: transparent;");
        
        Scene scene = new Scene(scrollPane, 900, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Optimization Results");
        stage.show();
    }
    
    private VBox createResultsCard() {
        VBox card = new VBox(32);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");
        
        // Header with icon
        VBox header = createHeader();
        
        // Metric card
        VBox metricCard = createMetricCard();
        
        // Solution display
        VBox solutionCard = createSolutionCard();
        
        // Action buttons
        HBox buttons = createActionButtons();
        
        card.getChildren().addAll(header, metricCard, solutionCard, buttons);
        
        return card;
    }
    
    private VBox createHeader() {
        VBox header = new VBox(16);
        header.setAlignment(Pos.CENTER);
        
        // Success icon
        StackPane iconContainer = new StackPane();
        iconContainer.getStyleClass().add("icon-container-success");
        iconContainer.setPrefSize(64, 64);
        
        SVGPath checkSVG = new SVGPath();
        checkSVG.setContent("M20 6L9 17l-5-5");
        checkSVG.setStroke(Color.WHITE);
        checkSVG.setStrokeWidth(3);
        checkSVG.setFill(Color.TRANSPARENT);
        checkSVG.setScaleX(1.8);
        checkSVG.setScaleY(1.8);
        
        iconContainer.getChildren().add(checkSVG);
        
        Label title = new Label("Optimization Results");
        title.getStyleClass().add("heading-2");
        
        Label subtitle = new Label("Power sources: " + powerCount);
        subtitle.getStyleClass().add("subtitle");
        
        header.getChildren().addAll(iconContainer, title, subtitle);
        
        return header;
    }
    
    private VBox createMetricCard() {
        VBox metricBox = new VBox(8);
        metricBox.getStyleClass().add("metric-card");
        metricBox.setAlignment(Pos.CENTER);
        metricBox.setMaxWidth(Double.MAX_VALUE);
        
        Label label = new Label("Maximum LEDs Powered");
        label.getStyleClass().add("metric-label");
        
        int maxLEDs = solution.getC()[solution.getPowers().length][solution.getLeds().length];
        Label value = new Label(String.valueOf(maxLEDs));
        value.getStyleClass().add("metric-value");
        
        metricBox.getChildren().addAll(label, value);
        
        return metricBox;
    }
    
    private VBox createSolutionCard() {
        VBox solutionBox = new VBox(12);
        solutionBox.setStyle(
            "-fx-background-color: #F9FAFB;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 24px;"
        );
        solutionBox.setMaxWidth(Double.MAX_VALUE);
        
        Label title = new Label("Optimal Configuration");
        title.getStyleClass().add("heading-3");
        
        // Badge container
        FlowPane badgeContainer = new FlowPane(8, 8);
        badgeContainer.setAlignment(Pos.CENTER);
        
        String[] lcss = solution.getLcss();
        for (String value : lcss) {
            Label badge = new Label(value);
            badge.getStyleClass().add("badge");
            badgeContainer.getChildren().add(badge);
        }
        
        solutionBox.getChildren().addAll(title, badgeContainer);
        
        return solutionBox;
    }
    
    private HBox createActionButtons() {
        HBox buttonBox = new HBox(16);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Conditionally show buttons based on power count
        if (powerCount <= 20) {
            Button tableButton = new Button("📊 View DP Table");
            tableButton.getStyleClass().add("modern-button-primary");
            tableButton.setPrefWidth(180);
            tableButton.setOnAction(e -> {
                new ModernTablePage(new Stage(), solution.getLeds(), solution.getPowers());
            });
            buttonBox.getChildren().add(tableButton);
        }
        
        if (powerCount <= 10) {
            Button simulationButton = new Button("⚡ Visual Simulation");
            simulationButton.getStyleClass().add("modern-button-success");
            simulationButton.setPrefWidth(180);
            simulationButton.setOnAction(e -> {
                new ModernSimulationPage(new Stage(), solution);
            });
            buttonBox.getChildren().add(simulationButton);
        }
        
        return buttonBox;
    }
}
