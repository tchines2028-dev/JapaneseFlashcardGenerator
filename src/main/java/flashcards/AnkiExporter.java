package flashcards;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import  java.nio.file.*;

public class AnkiExporter {


    public void export(List<Flashcard> deck, String filePath) throws IOException{

        try{
            //Creating directory for file
            Path exportFiles = Paths.get("Anki Exports");
            Files.createDirectories(exportFiles);
            Path exportPath = exportFiles.resolve(filePath);

            //Writing to new file
            BufferedWriter bw = new BufferedWriter(new FileWriter(exportPath.toFile()));

            for (Flashcard flashcard: deck) {
                bw.write(flashcard.getFront() + "\t" + flashcard.getBack());
                bw.flush();
                bw.newLine();
            }

            bw.close();

        } catch (IOException e){
            System.out.println("Error writing file");
        }
    }
}
