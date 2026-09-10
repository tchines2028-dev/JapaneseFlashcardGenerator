package flashcards;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;

import java.awt.*;

public class FlashcardGUI extends Application {

    Button button;
    public static void main(String[] args) {
        launch(args);
    }
    //launch args

    @Override
    public void start(Stage primaryStage) {


        //Top Section
        Label titleLabel = new Label("Japanese Flashcards");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(new Font("Courier New", 50));


        //Left Section <-


        Label menuTitle = new Label("Create Deck");
        menuTitle.setFont(new Font("Courier New", 30));

        TextField deckNameEntry = new TextField();
        deckNameEntry.setPromptText("Deck Name");

        Label card_type_label = new Label("Card Type");
        ComboBox<String> card_type = new ComboBox<>();
        card_type.setPromptText("-- to --");
        card_type.getItems().addAll(
          "Kanji to English",
            "Kanji to Hiragana",
                "English to Kanji",
                "Kanji to Hiragana"
        );

        VBox checkBackground = new VBox(); //Contains the components of the checkbox
        checkBackground.setSpacing(5);
        String[] deckFormat = {"Shuffle", "Reversed"};

        for (String word: deckFormat) {
            CheckBox c = new CheckBox(word);
            checkBackground.getChildren().add(c);
        }


        ColorPicker setColor = new ColorPicker();

        Button generate = new Button("Generate");
        Button export = new Button("Export");

        //Generate button styling
        generate.setPadding(new Insets(10));
        generate.setBackground(Background.fill(Color.DARKSLATEBLUE));
        generate.setFont(new Font(20));
        generate.setTextFill(Color.WHITE);

        //Export button styling

        VBox menuContainer = new VBox(card_type_label, card_type, checkBackground, deckNameEntry, generate, export);
        menuContainer.setSpacing(20);

        ToolBar menu = new ToolBar(menuTitle, new Separator(), menuContainer);
        menu.setOrientation(Orientation.VERTICAL);
        menu.setBackground(Background.fill(Color.SLATEBLUE));
        menu.setPadding(new Insets(15));


        //Right Section ->
        Label previewText = new Label("Preview");
        Rectangle card = new Rectangle(500, 350);
        card.setFill(Color.WHITESMOKE);
        previewText.setAlignment(Pos.CENTER);
        VBox preview = new VBox(previewText, card);
        //Bottom Section
        button = new Button();


        //Main Border Pane
        BorderPane mainLayout = new BorderPane();
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(preview, Pos.CENTER);
        mainLayout.setBackground(Background.fill(Color.ALICEBLUE));
        mainLayout.setTop(titleLabel);
        mainLayout.setCenter(preview);
        mainLayout.setLeft(menu);

        //Main stage
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
