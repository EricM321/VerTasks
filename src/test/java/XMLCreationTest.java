import org.junit.Assert;
import org.junit.Test;
import task3.GenerateXML;
import task3.SplitXML;
import task3.commonXML;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.junit.Assert.*;

public class XMLCreationTest {

    @Test
    // [the name of the tested feature]_[expected input / tested state]_[expected behavior]
    public void occurrencesSubString_KnownString_CorrectOutput() {
        int actual = commonXML.occurrencesSubString("a", new StringBuilder("banana"));

        assertEquals(3, actual);
    }

    @Test
    public void createFileEnding_KnownString_MatchesExpected() {
        String expected = "<footer><record_count>0</record_count><record_row_count>0</record_row_count></footer></record-table>";

        assertEquals(expected, commonXML.createFileEnding(new StringBuilder("banana")).toString());
    }

    @Test
    public void saveXML_EmptyFileAndLocalPath_FileIsCreated() throws IOException {
        String filePath = System.getProperty("user.dir") +  File.separator + "test.xml";
        System.out.println(filePath);

        commonXML.saveXML(new StringBuilder(""), filePath);
        File file = new File(filePath);

        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void randomString_LengthFive_OutputIsLength() {
        int length = 5;
        String returnedValue = GenerateXML.randomString(length);

        assertEquals(length, returnedValue.length());
    }

    @Test
    public void generate_TwoRecords_OnlyTwoRecords() {
        int numRecords = 2;
        StringBuilder returnedValue = GenerateXML.generate(numRecords);

        assertEquals(numRecords,  commonXML.occurrencesSubString("<record>",returnedValue));
    }

    @Test
    public void splitting_NormalRun_FolderCreatedWithFiles() throws IOException {
        String filePath = System.getProperty("user.dir");
        SplitXML.splitting(2, 3000, filePath);

        File directory = new File(filePath + File.separator + "generatedFiles");
        assertTrue(directory.isDirectory());

        String[] files = directory.list();
        assertNotNull(files);

        assertTrue(files.length > 0);

        for(String s: files){
            File currentFile = new File(directory.getPath(),s);
            currentFile.delete();
        }

        directory.delete();
    }

}
