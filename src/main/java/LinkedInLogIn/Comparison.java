package LinkedInLogIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Comparison {
    List<String> googleLinks = new ArrayList<>();
    List<String> linkedInLinks = new ArrayList<>();


    public List<String> readGoogleLinksFromCSV() {
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\Google_Results.csv";  // Path to the CSV file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                googleLinks.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleLinks;
    }

    public List<String> readLinkedInLinksFromCSV() {
        String linkedInFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\LinkedInResults.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(linkedInFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] columns = line.split(",");
                if (columns.length > 1) {
                    linkedInLinks.add(columns[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linkedInLinks;
    }

}
