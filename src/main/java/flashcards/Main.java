package flashcards;

import java.io.IOException;
import java.util.List;

/*
* Tests all the classes
*
* */
public class Main {

    public static void main(String[] args){
        VocabularyEntry line = new VocabularyEntry(
                "言葉",
                "ことば",
                "word",
                false
        );

        Flashcard card = new Flashcard(line.getVocabulary(), line.getEnglish());
        System.out.println(card.getFront() + ", " + card.getBack());

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

    }


}
