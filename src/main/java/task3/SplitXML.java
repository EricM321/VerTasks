package task3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitXML {

    public static void splitting(int numRecords, int filesMaxSize, String filePath){
        StringBuilder fileStart = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><record-table>");
        StringBuilder currentFullFile = new StringBuilder();

        StringBuilder fullXmlContents = GenerateXML.generate(numRecords);

        StringBuilder fileEnding = commonXML.createFileEnding(fullXmlContents);
        int minMaxFileSize = fileEnding.length();
        currentFullFile.append(fileStart).append(fullXmlContents).append(fileEnding);

        System.out.println("record file size: " + fullXmlContents.length());
        System.out.println("minMaxFileSize: " + (minMaxFileSize + fileStart.length()));
        System.out.println("currentFullFile size: " + currentFullFile.length());

        File theDir = new File(filePath+ File.separator + "generatedFiles");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        if(currentFullFile.length() <= filesMaxSize){
            commonXML.saveXML(currentFullFile, theDir + File.separator + "generatedXML.xml");
        } else{
            Matcher m = Pattern.compile("(?=(<record>))").matcher(fullXmlContents);

            List<Integer> pos = new ArrayList<>();
            while (m.find())
            {
                pos.add(m.start());
            }
            System.out.println("start positions of <record>: " + pos);

            int index = 0;
            int i = 0;

            while(index != pos.size()) {
                StringBuilder xmlContents = new StringBuilder(fileStart);

                while (xmlContents.length() <= filesMaxSize - minMaxFileSize && index != pos.size()) {
                    System.out.println("index: " + index);
                    StringBuilder record = new StringBuilder(fullXmlContents.substring(pos.get(index), (index + 1 < pos.size()) ? pos.get(index + 1) : fullXmlContents.length()));

                    if(record.length() + xmlContents.length() > filesMaxSize){
                        if(xmlContents.length() <= fileStart.length()){
                            System.out.println("File will be over size");
                        }else {
                            System.out.println("____breaking____");
                            break;
                        }
                    }

                    xmlContents.append(record);
                    System.out.println("Record size: " + record.length());
                    System.out.println(record);
                    index++;
                }

                i++;
                System.out.println(i);
                xmlContents.append(commonXML.createFileEnding(xmlContents));
                commonXML.saveXML(xmlContents, theDir + File.separator + "generatedFile" + i + ".xml");
            }

            for(int j = 1; j <= i; j++) {
                String path = theDir + File.separator + "generatedFile" + j + ".xml";
                File file = new File(path);
                System.out.println("File size bytes of " + "generatedFile" + j + ".xml" + ": " + file.length());
            }
        }
    }

}
