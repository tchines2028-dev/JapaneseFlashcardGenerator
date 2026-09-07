package flashcards;
import java.util.*;
public class FlashcardGenerator {

    public List<Flashcard> generate(List<VocabularyEntry> vocabularyEntries, FlashcardType cardType){

        List<Flashcard> deck = new ArrayList<>();

        for (VocabularyEntry entry: vocabularyEntries){
            Flashcard card;

            switch (cardType){
                case ENGLISH_TO_KANJI -> {
                    card = new Flashcard(entry.getEnglish(), entry.getVocabulary());
                    break;
                }

                case KANJI_TO_ENGLISH -> {
                    card = new Flashcard(entry.getVocabulary(), entry.getEnglish());
                    break;
                }

                case HIRAGANA_TO_KANJI -> {
                    card = new Flashcard(entry.getReading(), entry.getVocabulary());
                    break;
                }

                case KANJI_TO_HIRAGANA -> {
                    card = new Flashcard(entry.getVocabulary(), entry.getReading());
                    break;
                }

                case null, default -> {
                    throw new IllegalArgumentException("Unsupported Flashcard type: " + cardType);
                }

            }

            deck.add(card);
        }

        return deck;
    }
}
