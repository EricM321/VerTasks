package task3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitXML {

    public static void splitting(int numRecords, long filesMaxSize, String filePath) throws IOException {
        if(!new File(filePath).exists()){
            throw new IOException("This path does not exist: \"" + filePath + "\"");
        }

        StringBuilder fileStart = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><record-table>");
        StringBuilder currentFullFile = new StringBuilder();

        StringBuilder fullXmlContents = GenerateXML.generate(numRecords);

        StringBuilder fileEnding = commonXML.createFileEnding(fullXmlContents);
        int minMaxFileSize = fileEnding.length();
        currentFullFile.append(fileStart).append(fullXmlContents).append(fileEnding);

        System.out.println("All records file size: " + fullXmlContents.length());
        System.out.println("Min max file size: " + (minMaxFileSize + fileStart.length()));
        System.out.println("Full file size: " + currentFullFile.length());

        File theDir = new File(filePath + File.separator + "generatedFiles");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        System.out.println("\nSplitting files");

        if(currentFullFile.length() <= filesMaxSize){
            System.out.println("No split required. Saving to: " + theDir + File.separator + "generatedXML.xml\n");
            commonXML.saveXML(currentFullFile, theDir + File.separator + "generatedXML.xml");
        } else{
            Matcher m = Pattern.compile("(?=(<record>))").matcher(fullXmlContents);

            List<Integer> recordPositions = new ArrayList<>();
            while (m.find())
            {
                recordPositions.add(m.start());
            }
            System.out.println("Start positions of all <record>: " + recordPositions + "\n");

            int index = 0;
            int i = 0;

            while(index != recordPositions.size()) {
                StringBuilder xmlContents = new StringBuilder(fileStart);

                while (xmlContents.length() <= filesMaxSize - minMaxFileSize && index != recordPositions.size()) {

                    StringBuilder record = new StringBuilder(fullXmlContents.substring(recordPositions.get(index), (index + 1 < recordPositions.size()) ? recordPositions.get(index + 1) : fullXmlContents.length()));

                    if(record.length() + xmlContents.length() > filesMaxSize){
                        if(xmlContents.length() <= fileStart.length()){
                            System.out.println("File will be over size");
                        }else {
                            System.out.println("\nAdding record to next file to try and maintain size constraint.\n");
                            break;
                        }
                    }

                    xmlContents.append(record);
                    System.out.println("Record size: " + record.length());
                    System.out.println(record);
                    index++;
                }

                i++;
                xmlContents.append(commonXML.createFileEnding(xmlContents));

                System.out.println("Saving split file to: " + theDir + File.separator + "generatedFile" + i + ".xml");
                commonXML.saveXML(xmlContents, theDir + File.separator + "generatedFile" + i + ".xml");
            }

            System.out.println("\nShowing file sizes:");
            for(int j = 1; j <= i; j++) {
                String path = theDir + File.separator + "generatedFile" + j + ".xml";
                File file = new File(path);
                System.out.println("File size bytes of " + "generatedFile" + j + ".xml" + ": " + file.length());
            }
        }
    }

}
