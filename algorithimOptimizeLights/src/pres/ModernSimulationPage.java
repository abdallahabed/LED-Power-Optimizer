package pres;

import algorithmFirstProj.LCS;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

/**
 * Modern Simulation Page matching React web app design
 * Features: Animated LED/Battery visualization, modern styling, smooth transitions
 */
public class ModernSimulationPage {
    
    private Stage stage;
    private LCS solution;
    private boolean isConnected = false;
    private Set<Integer> lcsSet;
    
    private VBox[] ledItems;
    private VBox[] batteryItems;
    private Label[] ledStatusLabels;
    private Label[] batteryStatusLabels;
    private Circle[] ledIndicators;
    private Circle[] batteryIndicators;
    
    public ModernSimulationPage(Stage stage, LCS solution) {
        this.stage = stage;
        this.solution = solution;
        
        // Create set for fast lookup
        this.lcsSet = new HashSet<>();
        for (String val : solution.getLcss()) {
            lcsSet.add(Integer.parseInt(val));
        }
        
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
        backBox.setMaxWidth(1000);
        
        Button backButton = new Button("← Back to Results");
        backButton.getStyleClass().add("modern-button-secondary");
        backButton.setOnAction(e -> stage.close());
        backBox.getChildren().add(backButton);
        
        // Card container
        VBox card = createSimulationCard();
        card.setMaxWidth(1000);
        
        mainContainer.getChildren().addAll(backBox, card);
        
        // Scroll pane
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("scroll-pane");
        
        Scene scene = new Scene(scrollPane, 1100, 750);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("LED & Power Simulation");
        stage.show();
    }
    
    private VBox createSimulationCard() {
        VBox card = new VBox(24);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");
        
        // Header
        VBox header = createHeader();
        
        // Control buttons
        HBox controls = createControls();
        
        // LED and Battery grid
        HBox grid = createVisualizationGrid();
        
        // Info box
        VBox infoBox = createInfoBox();
        
        card.getChildren().addAll(header, controls, grid, infoBox);
        
        return card;
    }
    
    private VBox createHeader() {
        VBox header = new VBox(8);
        header.setAlignment(Pos.CENTER);
        
        Label title = new Label("LED & Power Simulation");
        title.getStyleClass().add("heading-2");
        
        int maxLEDs = solution.getC()[solution.getPowers().length][solution.getLeds().length];
        Label subtitle = new Label("Maximum LEDs powered: " + maxLEDs);
        subtitle.getStyleClass().add("subtitle");
        
        header.getChildren().addAll(title, subtitle);
        
        return header;
    }
    
    private HBox createControls() {
        HBox controlBox = new HBox(16);
        controlBox.setAlignment(Pos.CENTER);
        
        Button connectButton = new Button("⚡ Connect");
        connectButton.getStyleClass().add("modern-button-success");
        connectButton.setPrefWidth(150);
        connectButton.setOnAction(e -> handleConnect());
        
        Button disconnectButton = new Button("🔌 Disconnect");
        disconnectButton.getStyleClass().add("modern-button-danger");
        disconnectButton.setPrefWidth(150);
        disconnectButton.setOnAction(e -> handleDisconnect());
        
        controlBox.getChildren().addAll(connectButton, disconnectButton);
        
        return controlBox;
    }
    
    private HBox createVisualizationGrid() {
        HBox gridBox = new HBox(40);
        gridBox.setAlignment(Pos.CENTER);
        gridBox.setMaxWidth(Double.MAX_VALUE);
        
        // LED column
        VBox ledColumn = createLEDColumn();
        
        // Battery column
        VBox batteryColumn = createBatteryColumn();
        
        gridBox.getChildren().addAll(ledColumn, batteryColumn);
        
        return gridBox;
    }
    
    private VBox createLEDColumn() {
        VBox column = new VBox(12);
        column.setAlignment(Pos.TOP_CENTER);
        
        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER);
        
        Label icon = new Label("💡");
        icon.setStyle("-fx-font-size: 20px;");
        
        Label title = new Label("LEDs");
        title.getStyleClass().add("heading-3");
        
        header.getChildren().addAll(icon, title);
        
        VBox itemsBox = new VBox(12);
        itemsBox.setAlignment(Pos.CENTER);
        
        int[] leds = solution.getLeds();
        ledItems = new VBox[leds.length];
        ledStatusLabels = new Label[leds.length];
        ledIndicators = new Circle[leds.length];
        
        for (int i = 0; i < leds.length; i++) {
            VBox item = createLEDItem(i, leds[i]);
            ledItems[i] = item;
            itemsBox.getChildren().add(item);
        }
        
        column.getChildren().addAll(header, itemsBox);
        
        return column;
    }
    
    private VBox createLEDItem(int index, int value) {
        VBox item = new VBox(8);
        item.setAlignment(Pos.CENTER_LEFT);
        item.getStyleClass().add("led-item");
        item.setPrefWidth(200);
        
        HBox contentBox = new HBox(12);
        contentBox.setAlignment(Pos.CENTER_LEFT);
        
        // Indicator circle
        Circle indicator = new Circle(8);
        indicator.setFill(Color.web("#9CA3AF"));
        ledIndicators[index] = indicator;
        
        // Info
        VBox info = new VBox(4);
        
        Label ledLabel = new Label("LED " + (index + 1));
        ledLabel.setStyle("-fx-font-weight: 600; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        
        Label requirement = new Label("Requirement: " + value + "W");
        requirement.setStyle("-fx-font-size: 12px; -fx-text-fill: #6B7280;");
        
        Label status = new Label("○ OFF");
        status.getStyleClass().add("status-off");
        ledStatusLabels[index] = status;
        
        info.getChildren().addAll(ledLabel, requirement, status);
        
        contentBox.getChildren().addAll(indicator, info);
        item.getChildren().add(contentBox);
        
        return item;
    }
    
    private VBox createBatteryColumn() {
        VBox column = new VBox(12);
        column.setAlignment(Pos.TOP_CENTER);
        
        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER);
        
        Label icon = new Label("🔋");
        icon.setStyle("-fx-font-size: 20px;");
        
        Label title = new Label("Power Sources");
        title.getStyleClass().add("heading-3");
        
        header.getChildren().addAll(icon, title);
        
        VBox itemsBox = new VBox(12);
        itemsBox.setAlignment(Pos.CENTER);
        
        int[] powers = solution.getPowers();
        batteryItems = new VBox[powers.length];
        batteryStatusLabels = new Label[powers.length];
        batteryIndicators = new Circle[powers.length];
        
        for (int i = 0; i < powers.length; i++) {
            VBox item = createBatteryItem(i, powers[i]);
            batteryItems[i] = item;
            itemsBox.getChildren().add(item);
        }
        
        column.getChildren().addAll(header, itemsBox);
        
        return column;
    }
    
    private VBox createBatteryItem(int index, int value) {
        VBox item = new VBox(8);
        item.setAlignment(Pos.CENTER_LEFT);
        item.getStyleClass().add("battery-item");
        item.setPrefWidth(200);
        
        HBox contentBox = new HBox(12);
        contentBox.setAlignment(Pos.CENTER_LEFT);
        
        // Indicator circle
        Circle indicator = new Circle(8);
        indicator.setFill(Color.web("#9CA3AF"));
        batteryIndicators[index] = indicator;
        
        // Info
        VBox info = new VBox(4);
        
        Label batteryLabel = new Label("Battery " + (index + 1));
        batteryLabel.setStyle("-fx-font-weight: 600; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        
        Label power = new Label("Power: " + value + "W");
        power.setStyle("-fx-font-size: 12px; -fx-text-fill: #6B7280;");
        
        Label status = new Label("○ INACTIVE");
        status.getStyleClass().add("status-off");
        batteryStatusLabels[index] = status;
        
        info.getChildren().addAll(batteryLabel, power, status);
        
        contentBox.getChildren().addAll(indicator, info);
        item.getChildren().add(contentBox);
        
        return item;
    }
    
    private VBox createInfoBox() {
        VBox infoBox = new VBox(8);
        infoBox.getStyleClass().add("info-box");
        
        Label title = new Label("How it works:");
        title.setStyle("-fx-font-weight: 600; -fx-font-size: 14px; -fx-text-fill: #3730A3;");
        
        Label description = new Label(
            "Click \"Connect\" to power up the optimal LED configuration. Only the LEDs that " +
            "match the LCS solution will light up, showing the maximum number of LEDs that can " +
            "be powered with the available sources."
        );
        description.setWrapText(true);
        description.getStyleClass().add("info-text");
        
        infoBox.getChildren().addAll(title, description);
        
        return infoBox;
    }
    
    private void handleConnect() {
        isConnected = true;
        
        int[] leds = solution.getLeds();
        int[] powers = solution.getPowers();
        
        // Animate each item with delay
        for (int i = 0; i < leds.length; i++) {
            int index = i;
            boolean shouldLight = lcsSet.contains(leds[i]);
            
            PauseTransition pause = new PauseTransition(Duration.millis(i * 100));
            pause.setOnFinished(e -> {
                if (shouldLight) {
                    activateLED(index);
                }
            });
            pause.play();
        }
        
        for (int i = 0; i < powers.length; i++) {
            int index = i;
            boolean shouldActivate = lcsSet.contains(powers[i]);
            
            PauseTransition pause = new PauseTransition(Duration.millis(i * 100));
            pause.setOnFinished(e -> {
                if (shouldActivate) {
                    activateBattery(index);
                }
            });
            pause.play();
        }
    }
    
    private void handleDisconnect() {
        isConnected = false;
        
        for (int i = 0; i < ledItems.length; i++) {
            deactivateLED(i);
        }
        
        for (int i = 0; i < batteryItems.length; i++) {
            deactivateBattery(i);
        }
    }
    
    private void activateLED(int index) {
        ledItems[index].getStyleClass().remove("led-item");
        ledItems[index].getStyleClass().add("led-item-on");
        
        ledIndicators[index].setFill(Color.web("#FBBF24"));
        ledStatusLabels[index].setText("● ON");
        ledStatusLabels[index].getStyleClass().remove("status-off");
        ledStatusLabels[index].getStyleClass().add("status-on");
        
        // Pulse animation
        FadeTransition fade = new FadeTransition(Duration.millis(500), ledIndicators[index]);
        fade.setFromValue(0.5);
        fade.setToValue(1.0);
        fade.setCycleCount(Timeline.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }
    
    private void deactivateLED(int index) {
        ledItems[index].getStyleClass().remove("led-item-on");
        ledItems[index].getStyleClass().add("led-item");
        
        ledIndicators[index].setFill(Color.web("#9CA3AF"));
        ledStatusLabels[index].setText("○ OFF");
        ledStatusLabels[index].getStyleClass().remove("status-on");
        ledStatusLabels[index].getStyleClass().add("status-off");
    }
    
    private void activateBattery(int index) {
        batteryItems[index].getStyleClass().remove("battery-item");
        batteryItems[index].getStyleClass().add("battery-item-on");
        
        batteryIndicators[index].setFill(Color.web("#10B981"));
        batteryStatusLabels[index].setText("● ACTIVE");
        batteryStatusLabels[index].getStyleClass().remove("status-off");
        batteryStatusLabels[index].getStyleClass().add("status-on");
    }
    
    private void deactivateBattery(int index) {
        batteryItems[index].getStyleClass().remove("battery-item-on");
        batteryItems[index].getStyleClass().add("battery-item");
        
        batteryIndicators[index].setFill(Color.web("#9CA3AF"));
        batteryStatusLabels[index].setText("○ INACTIVE");
        batteryStatusLabels[index].getStyleClass().remove("status-on");
        batteryStatusLabels[index].getStyleClass().add("status-off");
    }
}
