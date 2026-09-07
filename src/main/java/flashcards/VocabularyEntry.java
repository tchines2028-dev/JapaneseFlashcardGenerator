package flashcards;

public class VocabularyEntry {

    private String vocabulary;
    private String reading;
    private String english;
    private boolean priority;

    public VocabularyEntry(String vocabulary, String reading, String english, boolean priority){
        this.vocabulary = vocabulary;
        this.reading = reading;
        this.english = english;
        this.priority = priority;
    }

    public String getVocabulary(){
        return vocabulary;
    }

    public String getReading(){
        return reading;
    }
    public String getEnglish(){
        return english;
    }

    public boolean getPriority(){
        return priority;
    }

    @Override
    public String toString(){
        String marker = "*";

        if (getPriority()){
            marker = "";
        }

        return vocabulary + marker + " " + reading +  " : " + english;
    }

}
