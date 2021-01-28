package task3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class GenerateXML {

    private static String randomString(int length){
        return "adfasdg";
    }

    private int occurrencesSubString(String subString, String fullText){
        return 1;
    }

    public static void generate() {
        Random r = new Random();
        String fileStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><record-table>";
        String fileEnd = "</record-table>";
        int numRecords = 2; //User input long data type
        long recordID;
        int numRecordRow = 3; //Will be random with max of 30
        String recordRow = "dasgfasg"; //Will be random between 1 and 200 characters
        long recordCount = 2;//Will be based on record elements in this file
        long recordRowCount = 3; //Same as above but for record rows

        StringBuilder xmlContents = new StringBuilder();

        xmlContents.append(fileStart);

        for(int i = 0; i < numRecords; i++){
            xmlContents.append("<record>");
            xmlContents.append("<record_id>").append((long) i).append("</record_id>");
            xmlContents.append("<record_rows>");

            for(int j = 0; j < numRecordRow; j++){
                xmlContents.append("<record_row>").append(randomString(r.nextInt(201 - 1) + 1)).append("</record_row>");
            }
            xmlContents.append("</record_rows>").append("</record>");
        }

        xmlContents.append("<footer>").append("<record_count>").append(numRecords).append("</record_count>");
        xmlContents.append("<record_row_count>").append(numRecords).append("</record_row_count>").append("</footer>");
        xmlContents.append(fileEnd);

        System.out.println("StringBuilder size bytes: " + xmlContents.length());

        try (FileOutputStream fos = new FileOutputStream("task3Try.xml");
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.append(xmlContents);
        } catch (IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


    }
}
