package com.test;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SwitchButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Showcase app demonstrating SwitchButton with various themes and styles.
 */
public class SwitchButtonShowcase extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.getStyleClass().add("showcase-root");

        VBox header = createHeader();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("showcase-scroll");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox content = new VBox();
        content.getStyleClass().add("showcase-content");

        content.getChildren().addAll(
                createJavaFXThemesSection(),
                createPlatformStylesSection(),
                createCreativeStylesSection(),
                createInteractiveSection()
        );

        scrollPane.setContent(content);
        root.getChildren().addAll(header, scrollPane);

        Scene scene = new Scene(root, 885, 1020);
        scene.getStylesheets().add(getClass().getResource("showcase.css").toExternalForm());

        primaryStage.setTitle("JavaFX SwitchButton Showcase");
        primaryStage.setScene(scene);
        // Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.show();
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.getStyleClass().add("showcase-header");

        Label title = new Label("SwitchButton Showcase");
        title.getStyleClass().add("showcase-title");

        Label subtitle = new Label("Live JavaFX demo — built-in themes and custom CSS styles");
        subtitle.getStyleClass().add("showcase-subtitle");

        header.getChildren().addAll(title, subtitle);
        return header;
    }

    // -------------------------------------------------------------------------
    // JavaFX Themes - Modena & Caspian with text/graphic variations
    // -------------------------------------------------------------------------

    private VBox createJavaFXThemesSection() {
        VBox section = new VBox();
        section.getStyleClass().add("showcase-section");

        Label titleLabel = new Label("JavaFX Themes");
        titleLabel.getStyleClass().add("section-title");

        Label descLabel = new Label("Built-in JavaFX theme styles with text and graphic examples");
        descLabel.getStyleClass().add("section-description");

        FlowPane cards = new FlowPane();
        cards.getStyleClass().add("card-flow");
        cards.getChildren().addAll(
                createThemeCard("Modena", null, "Default since JavaFX 8"),
                createThemeCard("Caspian", "caspian-style", "Classic gradient style")
        );

        section.getChildren().addAll(titleLabel, descLabel, cards);
        return section;
    }

    private VBox createThemeCard(String name, String styleClass, String description) {
        VBox card = new VBox();
        card.getStyleClass().addAll("style-card", "theme-card");

        // Header: name + description
        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("card-title");

        Label descLabel = new Label(description);
        descLabel.getStyleClass().add("card-description");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox head = new HBox(nameLabel, spacer, descLabel);
        head.setAlignment(Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        grid.getStyleClass().add("theme-grid");

        // Column constraints for alignment
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHalignment(HPos.LEFT);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);

        grid.getColumnConstraints().addAll(col0, col1, col2);

        // Column headers
        grid.add(new Label(""), 0, 0);
        Label offHeader = new Label("OFF");
        offHeader.getStyleClass().add("column-header");
        grid.add(offHeader, 1, 0);
        Label onHeader = new Label("ON");
        onHeader.getStyleClass().add("column-header");
        grid.add(onHeader, 2, 0);

        // Basic / With Text / Text & Graphic
        addThemeRow(grid, 1, "Basic", styleClass, false, false);
        addThemeRow(grid, 2, "With Text", styleClass, true, false);
        addThemeRow(grid, 3, "Text & Graphic", styleClass, true, true);

        card.getChildren().addAll(head, grid);
        return card;
    }

    private void addThemeRow(GridPane grid, int row, String labelText,
                             String styleClass, boolean withText, boolean withGraphic) {
        Label rowLabel = new Label(labelText);
        rowLabel.getStyleClass().add("row-label");

        // OFF state
        SwitchButton offSwitch = new SwitchButton();
        offSwitch.setSelected(false);
        if (styleClass != null) {
            offSwitch.getStyleClass().add(styleClass);
        }
        if (withGraphic) {
            offSwitch.setGraphic(createWifiIcon());
            offSwitch.getStyleClass().add("wifi-switch");
        }
        if (withText) {
            if (withGraphic) {
                offSwitch.setText("Wi-Fi");
            } else {
                offSwitch.textProperty().bind(offSwitch.selectedProperty().map(selected -> selected ? "On" : "Off"));
            }
        }

        // ON state
        SwitchButton onSwitch = new SwitchButton();
        onSwitch.setSelected(true);
        if (styleClass != null) {
            onSwitch.getStyleClass().add(styleClass);
        }
        if (withGraphic) {
            onSwitch.setGraphic(createWifiIcon());
            onSwitch.getStyleClass().add("wifi-switch");
        }
        if (withText) {
            if (withGraphic) {
                onSwitch.setText("Wi-Fi");
            } else {
                onSwitch.textProperty().bind(onSwitch.selectedProperty().map(selected -> selected ? "On" : "Off"));
            }
        }

        grid.add(rowLabel, 0, row);
        grid.add(offSwitch, 1, row);
        grid.add(onSwitch, 2, row);
    }

    /**
     * WiFi icon - shape controlled by CSS pseudo-class on parent SwitchButton.
     */
    private Node createWifiIcon() {
        Region icon = new Region();
        icon.getStyleClass().add("wifi-icon");
        icon.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        icon.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return icon;
    }

    // -------------------------------------------------------------------------
    // Platform Styles - iOS, Material, Fluent, macOS
    // -------------------------------------------------------------------------

    private VBox createPlatformStylesSection() {
        VBox section = new VBox();
        section.getStyleClass().add("showcase-section");

        Label titleLabel = new Label("Platform Styles");
        titleLabel.getStyleClass().add("section-title");

        Label descLabel = new Label("Native platform-inspired designs");
        descLabel.getStyleClass().add("section-description");

        FlowPane cards = new FlowPane();
        cards.getStyleClass().add("card-flow");
        cards.getChildren().addAll(
                createStyleCard("iOS", "ios", "Apple"),
                createStyleCard("Material Design", "material", "Google / Android"),
                createStyleCard("Fluent", "fluent", "Microsoft / Windows"),
                createStyleCard("macOS", "macos", "Apple Desktop")
        );

        section.getChildren().addAll(titleLabel, descLabel, cards);
        return section;
    }

    // -------------------------------------------------------------------------
    // Creative Styles - Theme toggle, Neon, Minimal, Square
    // -------------------------------------------------------------------------

    private VBox createCreativeStylesSection() {
        VBox section = new VBox();
        section.getStyleClass().add("showcase-section");

        Label titleLabel = new Label("Creative Styles");
        titleLabel.getStyleClass().add("section-title");

        Label descLabel = new Label("Custom themed switches for unique designs");
        descLabel.getStyleClass().add("section-description");

        FlowPane cards = new FlowPane();
        cards.getStyleClass().add("card-flow");
        cards.getChildren().addAll(
                createStyleCard("Theme Toggle", "theme-switch", "Sun/Moon icons"),
                createStyleCard("Neon Glow", "neon", "Cyberpunk style"),
                createStyleCard("Minimal", "minimal", "Ultra-clean"),
                createStyleCard("Square", "square", "Sharp corners")
        );

        section.getChildren().addAll(titleLabel, descLabel, cards);
        return section;
    }

    /**
     * Simple card with OFF/ON switches side by side.
     */
    private VBox createStyleCard(String name, String styleClass, String description) {
        VBox card = new VBox();
        card.getStyleClass().add("style-card");
        card.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("card-title");

        Label descLabel = new Label(description);
        descLabel.getStyleClass().add("card-description");

        HBox switches = new HBox();
        switches.getStyleClass().add("switch-pair");
        switches.setAlignment(Pos.CENTER);

        SwitchButton switchOff = new SwitchButton();
        switchOff.setSelected(false);
        switchOff.getStyleClass().add(styleClass);

        SwitchButton switchOn = new SwitchButton();
        switchOn.setSelected(true);
        switchOn.getStyleClass().add(styleClass);

        switches.getChildren().addAll(switchOff, switchOn);
        card.getChildren().addAll(nameLabel, descLabel, switches);
        return card;
    }

    // -------------------------------------------------------------------------
    // Interactive Demo
    // -------------------------------------------------------------------------

    private VBox createInteractiveSection() {
        VBox section = new VBox();
        section.getStyleClass().add("showcase-section");

        Label titleLabel = new Label("Interactive Demo");
        titleLabel.getStyleClass().add("section-title");

        Label descLabel = new Label("Try clicking the switches to see them in action");
        descLabel.getStyleClass().add("section-description");

        VBox card = new VBox();
        card.getStyleClass().add("interactive-card");

        card.getChildren().addAll(
                createSettingRow("Enable Notifications", "Receive push notifications", "ios"),
                new Separator(),
                createSettingRow("Dark Mode", "Switch to dark theme", "theme-switch")
        );

        section.getChildren().addAll(titleLabel, descLabel, card);
        return section;
    }

    private HBox createSettingRow(String title, String subtitle, String styleClass) {
        HBox row = new HBox();
        row.getStyleClass().add("setting-row");

        VBox textBox = new VBox();
        textBox.getStyleClass().add("setting-text");
        HBox.setHgrow(textBox, Priority.ALWAYS);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("setting-title");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.getStyleClass().add("setting-subtitle");

        textBox.getChildren().addAll(titleLabel, subtitleLabel);

        SwitchButton toggle = new SwitchButton();
        toggle.getStyleClass().add(styleClass);

        row.getChildren().addAll(textBox, toggle);
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}