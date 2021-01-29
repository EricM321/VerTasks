package task3;

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

    public static StringBuilder generate(int numRecords) {
        Random r = new Random();

        StringBuilder xmlContents = new StringBuilder();

        System.out.println("\nCreating records");
        for(int i = 0; i < numRecords; i++){
            xmlContents.append("<record>");
            xmlContents.append("<record_id>").append((long) i).append("</record_id>");
            xmlContents.append("<record_rows>");

            int numRecordRow = r.nextInt(31 - 1) + 1;

            for(int j = 0; j < numRecordRow; j++){
                xmlContents.append("<record_row>").append(randomString(r.nextInt(200) + 1)).append("</record_row>");
            }
            xmlContents.append("</record_rows>").append("</record>");
        }
        System.out.println("Record creation complete\n");

        return xmlContents;

    }
}
