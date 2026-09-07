package flashcards;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
public class VocabParser {
    private static final Pattern pattern = Pattern.compile("^\\d+\\.\\s*(?<vocab>.+?)(?<priority>\\*)?\\s*(?<reading> ([^)]+))\\s*:\\s*(?<english>.+)$");

    /*
    * Converts a line of text into the VocabEntry format
    * */
    private VocabularyEntry parseLine(String line){
        Matcher matcher = pattern.matcher(line);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Invalid format: " + line);
        }

        String vocabulary = matcher.group("vocab");
        String priorityMark = matcher.group("priority");
        String reading = matcher.group("reading");
        String english = matcher.group("english");
        boolean priority = priorityMark != null;

        return new VocabularyEntry(vocabulary, reading, english, priority);

    }

    /*
    *  Takes in a filepath for a txt file and converts its data into a list of VocabularyEntry objects
    * throws an exception for an invalid file
    * */
    public List<VocabularyEntry> parsetoList(String filePath) throws IOException {

        List<VocabularyEntry> vocabList = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            //create the pattern that breaks every line in the file into the sections of VocabEntry


            while ((line = br.readLine()) != null) {

                //skip blank lines
                if(line.isBlank()){
                    continue;
                }

                VocabularyEntry entry = parseLine(line);
                vocabList.add(entry);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Trouble reading in file");
            throw new RuntimeException(e);
        }


        return vocabList;
    }


}
