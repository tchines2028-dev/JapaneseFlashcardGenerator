package flashcards;
import java.util.*;

public class FlashcardGenerator {

    /*
    * Returns a List of flashcards with the given specifications
    * I:
    * vocabularyEntries: List of VocabularYEntry from a file
    * cardType: FLashcardType The primary type of cards (What is on the front and back) in the deck
    * reversed: a boolean where true = include all the reversed version of the cards in the deck as well
    * shuffle: boolean where true = the cards in the deck will be shuffled; false = the cards in the deck will be in their given order
     */
    public List<Flashcard> generate(List<VocabularyEntry> vocabularyEntries, FlashcardType cardType, boolean reversed, boolean shuffle){

        List<Flashcard> deck = new ArrayList<>();

        for (VocabularyEntry entry: vocabularyEntries){
            Flashcard card;
            Flashcard cardReverse = new Flashcard("", "");

            switch (cardType){
                case ENGLISH_TO_KANJI -> {
                    card = new Flashcard(entry.getEnglish(), entry.getVocabulary());
                    
                    if(reversed){
                        cardReverse = new Flashcard(entry.getVocabulary(), entry.getEnglish());
                    }
                    
                    break;
                }

                case KANJI_TO_ENGLISH -> {
                    card = new Flashcard(entry.getVocabulary(), entry.getEnglish());
                    
                    if(reversed){
                        cardReverse = new Flashcard(entry.getEnglish(), entry.getVocabulary());
                    }
                    
                    break;
                }

                case HIRAGANA_TO_KANJI -> {
                    card = new Flashcard(entry.getReading(), entry.getVocabulary());

                    if(reversed){
                        cardReverse = new Flashcard(entry.getVocabulary(), entry.getReading());
                    }
                    
                    break;
                }

                case KANJI_TO_HIRAGANA -> {
                    card = new Flashcard(entry.getVocabulary(), entry.getReading());

                    if(reversed){
                        cardReverse = new Flashcard(entry.getReading(), entry.getVocabulary());
                    }
                    
                    break;
                }

                case null, default -> {
                    throw new IllegalArgumentException("Unsupported Flashcard type: " + cardType);
                }

            }

            deck.add(card);
            if (reversed) {
                deck.add(cardReverse);
            }
        }

        if(shuffle){
            Collections.shuffle(deck);
        }

        return deck;
    }

    public StringBuilder toString(List<Flashcard> deck){
        StringBuilder deckPrinted = new StringBuilder();

        for(Flashcard flashcard: deck){
            deckPrinted.append(flashcard).append("\n");

        }

        return  deckPrinted;
    }


}
