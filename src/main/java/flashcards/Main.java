package flashcards;

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

    }


}
