package flashcards;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.awt.Color.WHITE;
import static java.awt.SystemColor.window;

public class FlashcardGUI extends Application {

    Button button;
    public static void main(String[] args) {
        launch(args);
    }
    //launch args

    @Override
    public void start(Stage primaryStage) {


        /*
        * Home Page
        * Simply contains the list of previously created flashcard decks
        * Allows the user to create new deck(s)
        * */

        //Header
        Label homeTitle = new Label("Japanese Flashcards");
        homeTitle.setFont(new Font("Comic Sans MS", 50));
        homeTitle.setTextFill(Color.WHITE);
        homeTitle.setPadding(new Insets(10));

       // FileInputStream createDeckImageFile = new FileInputStream("")
       // Image homeCreateDeckImage = new Image();

        Button homeCreateNewDeck = new Button("New Deck");
        //homeCreateNewDeck.setAlignment(Pos.CENTER);

        HBox homePageHeader = new HBox();
        homePageHeader.getChildren().addAll(
                homeTitle,
                homeCreateNewDeck
        );
        homePageHeader.setSpacing(100);
       homePageHeader.setBackground(Background.fill(Color.rgb(255, 183, 197)));
       homePageHeader.setAlignment(Pos.CENTER);

        //Deck Display Area
        TilePane homeDeckDisplay = new TilePane();

        homeDeckDisplay.setPadding(new Insets(15));
        homeDeckDisplay.setHgap(10);
        homeDeckDisplay.setVgap(15);

        //Example Decks
        Rectangle exampleCard = new Rectangle(300, 200);
        exampleCard.setFill(Color.WHITE);
        exampleCard.setStroke(Color.BLACK);
        exampleCard.setStrokeWidth(2);

        Rectangle exampleCard2 = new Rectangle(300, 200);
        exampleCard2.setFill(Color.WHITE);
        exampleCard2.setStroke(Color.BLACK);
        exampleCard2.setStrokeWidth(2);

        Rectangle exampleCard3 = new Rectangle(300, 200);
        exampleCard3.setFill(Color.WHITE);
        exampleCard3.setStroke(Color.BLACK);
        exampleCard3.setStrokeWidth(2);

        Rectangle exampleCard4 = new Rectangle(300, 200);
        exampleCard4.setFill(Color.WHITE);
        exampleCard4.setStroke(Color.BLACK);
        exampleCard4.setStrokeWidth(2);

        Rectangle exampleCard5 = new Rectangle(300, 200);
        exampleCard5.setFill(Color.WHITE);
        exampleCard5.setStroke(Color.BLACK);
        exampleCard5.setStrokeWidth(2);


        homeDeckDisplay.getChildren().addAll(
                exampleCard,
                exampleCard2,
                exampleCard3,
                exampleCard4,
                exampleCard5);


        //Main Container
        VBox homePageMainContainer = new VBox();
        homePageMainContainer.getChildren().addAll(
            homePageHeader,
            homeDeckDisplay
        );

        homePageMainContainer.setBackground(Background.fill(Color.WHITE));



        /*
        * Generate Deck Screen
        *
        * */

        //Top Section
       /* Label titleLabel = new Label("Japanese Flashcards");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setPadding(new Insets(30));
        titleLabel.setAlignment(Pos.CENTER_LEFT);
        titleLabel.setFont(new Font("Comic Sans MS", 50));
        HBox topBar = new HBox(titleLabel);
        topBar.setStyle("-fx-background-color: #ffb7c5"); //
       */

        //Left Section <-


        Label menuTitle = new Label("Create Deck");
      //  menuTitle.setTextFill(Color.WHITE);
        menuTitle.setFont(new Font("Comic Sans MS", 30));

        Label deckNameEntryLabel = new Label("Deck Name:");
      //  deckNameEntryLabel.setTextFill(Color.WHITE);
        deckNameEntryLabel.setFont(new Font("Comic Sans MS", 20));

        TextField deckNameEntry = new TextField();
        deckNameEntry.setPromptText("Name");

        VBox deckNameEntryBox = new VBox();
        deckNameEntryBox.getChildren().addAll(
          deckNameEntryLabel,
          deckNameEntry
        );



        //Deck Card Types
        Label card_type_label = new Label("Card Type");
        card_type_label.setFont(new Font("Comic Sans MS", 15));
        //card_type_label.setTextFill(Color.WHITE);
        ComboBox<FlashcardType> card_type = new ComboBox<>();
        card_type.setPromptText("-- Choose Card Type --");
        card_type.getItems().addAll(FlashcardType.values());
        VBox card_type_group = new VBox();
        card_type_group.getChildren().addAll(
                card_type_label,
                card_type
        );

        //Deck Checkbox
        VBox checkBackground = new VBox(); //Contains the components of the checkbox

        checkBackground.setSpacing(5);
        String[] deckFormat = {"Shuffle", "Reversed"};

        for (String word: deckFormat) {
            CheckBox c = new CheckBox(word);
            checkBackground.getChildren().add(c);
        }

        CheckBox shuffle = (javafx.scene.control.CheckBox) checkBackground.getChildren().get(0);
        CheckBox reverse = (javafx.scene.control.CheckBox)checkBackground.getChildren().get(1);
        //shuffle.setTextFill(Color.WHITE);
        //reverse.setTextFill(Color.WHITE);

        //Deck color picker
        ColorPicker setColor = new ColorPicker();

        //Buttons

        Button generate = new Button("Generate");
        Button export = new Button("Export");

        //Generate button

        //style
        generate.setPadding(new Insets(10));
        generate.setBackground(Background.fill(Color.rgb(242,199,199)));
        generate.setFont(new Font(20));
        //generate.setTextFill(Color.WHITE);

        //event
        generate.setOnAction(event-> {

            //Data collected from user input
            FlashcardType selectedType = card_type.getValue();
            boolean shuffleChecked = shuffle.isSelected();
            boolean reverseChecked = reverse.isSelected();
            Color chosenColor = setColor.getValue();
            String inputtedDeckName = deckNameEntry.getText();

            if(selectedType == null ){
                System.out.println("Please Select a Type:");
                return;
            }
            //User data -> created deck
            VocabParser parser = new VocabParser();
            List<VocabularyEntry> entries = null;
            try {
                entries = parser.parsetoList("input/vocab_test.txt"); //eventually will be the imported file
                System.out.println("Test:" + entries.getFirst().getVocabulary());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FlashcardGenerator generator = new FlashcardGenerator();
            List<Flashcard> deck = generator.generate(entries, selectedType, reverseChecked, shuffleChecked);


            //test prints
            System.out.println(inputtedDeckName);
            System.out.println(selectedType);
            System.out.println( shuffleChecked);
            System.out.println( reverseChecked);
            System.out.println(chosenColor);
            System.out.println("Generated cards:" + deck.toString());
        });

        //generate.setOnMouseExited( event -> {
          //  generate.setBackground(Background.fill(Color.ALICEBLUE));
        //});

        //Export button styling

        VBox menuContainer = new VBox(deckNameEntryBox, card_type_group, checkBackground,setColor, generate, export);
        menuContainer.setSpacing(20);


        ToolBar menu = new ToolBar(menuTitle, new Separator(), menuContainer);
        menu.scaleYProperty();
        menu.setOrientation(Orientation.VERTICAL);
        menu.setBackground(Background.fill(Color.rgb(213,243,216)));
        menu.setPadding(new Insets(15));

        //event

        //Center Section ->
        Label previewText = new Label("Preview");




        previewText.setFont(new Font("Comic Sans MS", 50));
        previewText.setTextAlignment(TextAlignment.CENTER);

        //Card
        Rectangle card = new Rectangle(650, 420);
        card.setFill(Color.WHITE);
        card.setStroke(Color.rgb(242,199,199));
        card.setStrokeWidth(10);
        VBox preview = new VBox(previewText, card);

        //Card effects
        DropShadow cardShadow = new DropShadow();
        //cardShadow.setBlurType();
        cardShadow.setRadius(10);
        cardShadow.setOffsetX(10);
        cardShadow.setOffsetY(10);
        card.setEffect(cardShadow);

        preview.setPadding(new Insets(50, 50, 50, 50));
        preview.setAlignment(Pos.CENTER);



        //Bottom Section
        button = new Button();


        //Main Border Pane
        BorderPane mainLayout = new BorderPane();
       // BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(preview, Pos.TOP_CENTER);
        mainLayout.setBackground(Background.fill(Color.WHITE));
        //mainLayout.setTop(topBar);
        mainLayout.setCenter(preview);
        mainLayout.setLeft(menu);


        /*
         *
         *
         * */

        //Main stage
        Scene generateDeckScreen = new Scene(mainLayout, 1000, 600);
        Scene homeScreen = new Scene(homePageMainContainer, 1000, 600);
        Scene deckScreen = new Scene(new BorderPane());
        primaryStage.setTitle("Flashcards");

        //Scene switching button actions
        export.setOnAction(event -> primaryStage.setScene(homeScreen));
        homeCreateNewDeck.setOnAction(event -> primaryStage.setScene(generateDeckScreen));
        //generate.setOnAction(event -> primaryStage.setScene(deckScreen));

        //Stage setting
        primaryStage.setScene(homeScreen);
        primaryStage.show();




    }
}
