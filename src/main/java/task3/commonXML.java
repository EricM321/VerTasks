package task3;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class commonXML {

    public static int occurrencesSubString(String subString, StringBuilder fullText){
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
    public static StringBuilder createFileEnding(StringBuilder xmlContents){
        StringBuilder fileEnding = new StringBuilder();
        fileEnding.append("<footer>").append("<record_count>").append(occurrencesSubString("<record>", xmlContents)).append("</record_count>");
        fileEnding.append("<record_row_count>").append(occurrencesSubString("<record_row>", xmlContents)).append("</record_row_count>").append("</footer>");
        fileEnding.append("</record-table>");

        return fileEnding;
    }

    public static void saveXML(StringBuilder xmlContents, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath); //"task3Try.xml"
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.append(xmlContents);
        } catch (IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new IOException("Bad file path: ", e);
        }
    }
}
