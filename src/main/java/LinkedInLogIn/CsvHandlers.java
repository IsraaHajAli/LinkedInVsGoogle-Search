package LinkedInLogIn;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvHandlers {

    public static void writeDataLineByLine(String filePath, List<String> [] data)
    {
        // below I'm creating a file object to take rhe path
        File file = new File(filePath);
        try {
            // create file writer object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object and th output file writer object is a parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // below is a nested for lop which iterates the data array (columns then rows)
            for (int col = 0; col < data[0].size(); col++) {

                String [] record = new String[data.length];

                for (int row = 0; row < data.length; row++) {

                    record[row] = data[row].get(col); // here I'm forming the record

                }
                writer.writeNext(record);
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object[][] readDataLineByLine(String path){
        List<List<String>> records = new ArrayList<>(); // this is for storing the records of the csv file, such that each List<Sting> represents a record
        int columns = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // here I'm extracting the values or "cells" from each record and sore them in an array of strings "values"
                records.add(Arrays.asList(values)); /** here I'm inserting a new record to the records list, the record is (values)
                 which is from the type array of strings, so I'm converting is to List of strings to insert it as a record **/
                columns = values.length;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // below I'm converting the records list to object
        Object[][] data = new Object[records.size()][columns];

        // below I'm filling the Object array
        for (int i = 0; i < data.length; i++) {
            for (int j = 0 ; j < data[i].length ; j ++){
                data[i][j] = records.get(i).get(j); // searchData

            }

        }

        return data;
    }

}
