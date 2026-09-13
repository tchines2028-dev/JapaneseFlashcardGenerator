package flashcards;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Tests all the classes
*
* */
public class Main {

    public Main() throws IOException {
    }

    public static void main(String[] args){
        VocabularyEntry line = new VocabularyEntry(
                "言葉",
                "ことば",
                "word",
                false
        );

        System.out.println("日本語テスト");


        Flashcard card = new Flashcard(line.getVocabulary(), line.getEnglish());
        System.out.println("\n " + card.getFront() + ", " + card.getBack() + "\n");

        VocabParser parser = new VocabParser();
        try {
            List<VocabularyEntry> entries = parser.parsetoList("input/vocab_test.txt");
            for (int i = 0; i < entries.toArray().length; i++){
                System.out.println((i + 1) + ". " + entries.get(i));
            }

        } catch (IOException e) {
            System.out.println("Couldn't read file");
            e.printStackTrace();
        }


        /*
        * testing FlashcardGenerator
         * */


        //Kanji to English Flashcard Test
        try {
            List<VocabularyEntry> entries = parser.parsetoList("input/vocab_test.txt");
            FlashcardGenerator generator = new FlashcardGenerator();
            List<Flashcard> deck = generator.generate(entries, FlashcardType.KANJI_TO_ENGLISH, true, false);

            System.out.println("\n KANJI_TO_ENGLISH Flashcard Test \n");
            for (Flashcard newCard: deck) {
                System.out.println(newCard);
            }

            AnkiExporter ankiExporter = new AnkiExporter();
            ankiExporter.export(deck, "ankiTest.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //English to Kanji Flashcard Test


        try {
            List<VocabularyEntry> entries = parser.parsetoList("input/vocab_test.txt");
            FlashcardGenerator generator = new FlashcardGenerator();
            List<Flashcard> deck = generator.generate(entries, FlashcardType.ENGLISH_TO_KANJI, false, true);

            System.out.println("\n ENGLISH_TO_KANJI Flashcard Test \n");
            for (Flashcard newCard: deck) {
                System.out.println(newCard);
            }

            AnkiExporter ankiExporter = new AnkiExporter();
            ankiExporter.export(deck, "ankiTest1.txt");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Hiragana to English Flashcard Test

        try {
            List<VocabularyEntry> entries = parser.parsetoList("input/vocab_test.txt");
            FlashcardGenerator generator = new FlashcardGenerator();
            List<Flashcard> deck = generator.generate(entries, FlashcardType.HIRAGANA_TO_KANJI, false, false);

            System.out.println("\n HIRAGANA_TO_KANJI Flashcard Test \n");
            for (Flashcard newCard: deck) {
                System.out.println(newCard);
            }
            AnkiExporter ankiExporter = new AnkiExporter();
            ankiExporter.export(deck, "ankiTest2.txt");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Kanji to Hiragana Flashcard Test


        try {
            List<VocabularyEntry> entries = parser.parsetoList("input/vocab_test.txt");
            FlashcardGenerator generator = new FlashcardGenerator();
            List<Flashcard> deck = generator.generate(entries, FlashcardType.KANJI_TO_HIRAGANA, false, false);

            System.out.println("\n KANJI_TO_HIRAGANA Flashcard Test \n");
            for (Flashcard newCard: deck) {
                System.out.println(newCard);
            }


            System.out.println("Cards #: " + deck.size());

            AnkiExporter ankiExporter = new AnkiExporter();
            ankiExporter.export(deck, "ankiTest3.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }






}
