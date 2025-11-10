package pres;

import algorithmFirstProj.LCS;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Modern Table Page showing DP computation
 * Features: Clean grid layout, color-coded cells, legend
 */
public class ModernTablePage {
    
    private Stage stage;
    private int[] leds;
    private int[] powers;
    private LCS solution;
    
    public ModernTablePage(Stage stage, int[] leds, int[] powers) {
        this.stage = stage;
        this.leds = leds;
        this.powers = powers;
        this.solution = new LCS(powers, leds);
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
        backBox.setMaxWidth(900);
        
        Button backButton = new Button("← Back to Results");
        backButton.getStyleClass().add("modern-button-secondary");
        backButton.setOnAction(e -> stage.close());
        backBox.getChildren().add(backButton);
        
        // Card
        VBox card = createTableCard();
        
        mainContainer.getChildren().addAll(backBox, card);
        
        // Scroll pane
        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("scroll-pane");
        
        Scene scene = new Scene(scrollPane, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Dynamic Programming Table");
        stage.show();
    }
    
    private VBox createTableCard() {
        VBox card = new VBox(24);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");
        card.setMaxWidth(900);
        
        // Header
        VBox header = createHeader();
        
        // Table
        GridPane table = createDPTable();
        
        // Legend
        VBox legend = createLegend();
        
        // Info box
        VBox infoBox = createInfoBox();
        
        card.getChildren().addAll(header, table, legend, infoBox);
        
        return card;
    }
    
    private VBox createHeader() {
        VBox header = new VBox(8);
        header.setAlignment(Pos.CENTER);
        
        Label title = new Label("Dynamic Programming Table");
        title.getStyleClass().add("heading-2");
        
        Label subtitle = new Label("Visualization of the LCS computation process");
        subtitle.getStyleClass().add("subtitle");
        
        header.getChildren().addAll(title, subtitle);
        
        return header;
    }
    
    private GridPane createDPTable() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setStyle("-fx-background-color: #E5E7EB;");
        
        int[][] dpTable = solution.getC();
        char[][] directions = solution.getB();
        
        // Top-left corner (empty)
        Label corner = createCell("LEDs/Powers", true, false);
        corner.setStyle(
            "-fx-background-color: #FEF3C7;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #92400E;" +
            "-fx-border-color: #E5E7EB;" +
            "-fx-border-width: 1px;" +
            "-fx-padding: 8px;" +
            "-fx-alignment: center;" +
            "-fx-font-size: 11px;"
        );
        grid.add(corner, 0, 0);
        
        // Column headers (Powers)
        Label zeroCol = createCell("0", true, false);
        grid.add(zeroCol, 1, 0);
        
        for (int i = 0; i < powers.length; i++) {
            Label header = createCell(String.valueOf(powers[i]), true, false);
            grid.add(header, i + 2, 0);
        }
        
        // Row headers (LEDs)  
        Label zeroRow = createCell("0", true, false);
        grid.add(zeroRow, 0, 1);
        
        for (int i = 0; i < leds.length; i++) {
            Label header = createCell(String.valueOf(leds[i]), true, false);
            grid.add(header, 0, i + 2);
        }
        
        // First row (all zeros)
        for (int j = 0; j <= powers.length; j++) {
            Label cell = createCell("0", false, false);
            grid.add(cell, j + 1, 1);
        }
        
        // First column (all zeros)
        for (int i = 0; i <= leds.length; i++) {
            Label cell = createCell("0", false, false);
            grid.add(cell, 1, i + 1);
        }
        
        // Fill table with values
        for (int i = 1; i <= leds.length; i++) {
            for (int j = 1; j <= powers.length; j++) {
                int value = dpTable[i][j];
                char direction = directions[i][j];
                boolean isMatch = direction == '\\';
                
                String cellText = value + "" + direction;
                Label cell = createCell(cellText, false, isMatch);
                grid.add(cell, j + 1, i + 1);
            }
        }
        
        return grid;
    }
    
    private Label createCell(String text, boolean isHeader, boolean isMatch) {
        Label cell = new Label(text);
        cell.setPrefSize(50, 50);
        cell.setAlignment(Pos.CENTER);
        cell.setStyle(
            (isHeader ? 
                "-fx-background-color: #FEF3C7; -fx-font-weight: bold; -fx-text-fill: #92400E;" :
                isMatch ?
                    "-fx-background-color: #D1FAE5; -fx-font-weight: bold; -fx-text-fill: #065F46;" :
                    "-fx-background-color: white; -fx-text-fill: #374151;"
            ) +
            "-fx-border-color: #E5E7EB;" +
            "-fx-border-width: 1px;" +
            "-fx-padding: 4px;" +
            "-fx-font-size: " + (isHeader ? "12px" : "11px") + ";" +
            "-fx-font-family: monospace;"
        );
        return cell;
    }
    
    private VBox createLegend() {
        VBox legendBox = new VBox(8);
        legendBox.setStyle(
            "-fx-background-color: #F9FAFB;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 16px;"
        );
        legendBox.setMaxWidth(Double.MAX_VALUE);
        
        Label title = new Label("Legend:");
        title.setStyle("-fx-font-weight: 600; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        
        HBox items = new HBox(24);
        items.setAlignment(Pos.CENTER);
        
        // Match indicator
        HBox matchItem = new HBox(8);
        matchItem.setAlignment(Pos.CENTER_LEFT);
        
        Region matchColor = new Region();
        matchColor.setPrefSize(24, 24);
        matchColor.setStyle(
            "-fx-background-color: #D1FAE5;" +
            "-fx-border-color: #E5E7EB;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;"
        );
        
        Label matchLabel = new Label("\\ - Match found (diagonal move)");
        matchLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #374151;");
        
        matchItem.getChildren().addAll(matchColor, matchLabel);
        
        // Up indicator
        HBox upItem = new HBox(8);
        upItem.setAlignment(Pos.CENTER_LEFT);
        
        Region upColor = new Region();
        upColor.setPrefSize(24, 24);
        upColor.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E5E7EB;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;"
        );
        
        Label upLabel = new Label("^ - Up move");
        upLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #374151;");
        
        upItem.getChildren().addAll(upColor, upLabel);
        
        // Left indicator
        HBox leftItem = new HBox(8);
        leftItem.setAlignment(Pos.CENTER_LEFT);
        
        Region leftColor = new Region();
        leftColor.setPrefSize(24, 24);
        leftColor.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E5E7EB;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;"
        );
        
        Label leftLabel = new Label("< - Left move");
        leftLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #374151;");
        
        leftItem.getChildren().addAll(leftColor, leftLabel);
        
        items.getChildren().addAll(matchItem, upItem, leftItem);
        legendBox.getChildren().addAll(title, items);
        
        return legendBox;
    }
    
    private VBox createInfoBox() {
        VBox infoBox = new VBox(8);
        infoBox.getStyleClass().add("info-box");
        
        Label title = new Label("How to read:");
        title.setStyle("-fx-font-weight: 600; -fx-font-size: 14px; -fx-text-fill: #3730A3;");
        
        Label description = new Label(
            "Each cell shows the length of the longest common subsequence up to that point. " +
            "Green cells indicate matching values. Follow the backtracking path from the " +
            "bottom-right to reconstruct the optimal solution."
        );
        description.setWrapText(true);
        description.getStyleClass().add("info-text");
        
        infoBox.getChildren().addAll(title, description);
        
        return infoBox;
    }
}
