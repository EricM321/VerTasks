package task3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class GenerateXML {

    private static String randomString(int length){
        String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
        String digits = "1234567890";
        String seedChars = asciiUpperCase + asciiLowerCase + digits;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random rand = new Random();

        while(i < length){
            sb.append(seedChars.charAt(rand.nextInt(seedChars.length())));
            i++;
        }
        return sb.toString();
    }

    private static int occurrencesSubString(String subString, StringBuilder fullText){
        int stringLength = subString.length();
        int textLength = fullText.length();
        int count = 0;

        for(int i = 0; i <= textLength - stringLength; i++){
            int j;
            for(j = 0; j < stringLength; j++) {
                if (fullText.charAt(i + j) != subString.charAt(j)){
                    break;
                }
            }

            if(j == stringLength){
                count++;
                j = 0;
            }
        }

        return count;
    }

    public static void generate(int numRecords) {
        Random r = new Random();
        String fileStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><record-table>";
        String fileEnd = "</record-table>";
        int numRecordRow = r.nextInt(31 - 1) + 1;

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

        xmlContents.append("<footer>").append("<record_count>").append(occurrencesSubString("<record>", xmlContents)).append("</record_count>");
        xmlContents.append("<record_row_count>").append(occurrencesSubString("<record_row>", xmlContents)).append("</record_row_count>").append("</footer>");
        xmlContents.append(fileEnd);

        try (FileOutputStream fos = new FileOutputStream("task3Try.xml");
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.append(xmlContents);
        } catch (IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


    }
}
