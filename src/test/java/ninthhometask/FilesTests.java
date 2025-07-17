package ninthhometask;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ninthhometask.model.DNDCharacterModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FilesTests {
    private final ClassLoader classLoader = FilesTests.class.getClassLoader();
    private final String zipFilePath = "src/test/resources/QAGuru.zip";

    @Test
    @DisplayName("Checking the names of the archived files")
    void zipFileContentsNamesTest() throws IOException {

        List<String> expectedFileNamesInArchive = List.of("CSVFile.csv", "ExcelTable.xlsx", "PDFFile.pdf");

        try (ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("QAGuru.zip")))) {
            ZipEntry zipEntry;
            int fileIndexInArchive = 0;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Assertions.assertEquals(expectedFileNamesInArchive.get(fileIndexInArchive), zipEntry.getName());
                fileIndexInArchive++;
            }
        }
    }

    @Test
    @DisplayName("Checking the contents of the archived CSV file")
    void zipFileCSVContentsTest() throws IOException, CsvException {
        String csvFileNameInZip = "CSVFile.csv";

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));


        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry entry = zipFile.getEntry(csvFileNameInZip);
            Assertions.assertNotNull(entry, "Could not find the CSV file in the archive");
            while ((zipInputStream.getNextEntry()) != null) {
                try (InputStream inputStream = zipFile.getInputStream(entry);
                     CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
                    List<String[]> actualCsvRow = csvReader.readAll();
                    Assertions.assertEquals(2, actualCsvRow.size());
                    Assertions.assertArrayEquals(
                            new String[]{"Username", "Identifier", "First name", "Last name"},
                            actualCsvRow.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"booker12", "9012", "Rachel", "Booker"},
                            actualCsvRow.get(1)
                    );
                }
            }
        }
    }

    @Test
    @DisplayName("Checking the contents of the archived PDF file")
    void zipFilePDFContentsTest() throws IOException {
        String pdfFileNameInZip = "PDFFile.pdf";

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry;

        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry entry = zipFile.getEntry(pdfFileNameInZip);
            Assertions.assertNotNull(entry, "Could not find the PDF in the archive");

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().equals(pdfFileNameInZip)) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        Assertions.assertEquals(3, pdf.numberOfPages);
                        Assertions.assertEquals("Adobe InDesign CS6 (Macintosh)", pdf.creator);
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Checking the contents of the archived Excel file")
    void zipFileXLSXContentsTest() throws IOException {
        String xlsxFileNameInZip = "ExcelTable.xlsx";

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry;

        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry entry = zipFile.getEntry(xlsxFileNameInZip);
            Assertions.assertNotNull(entry, "Could not find the Excel Table in the archive");

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().equals(xlsxFileNameInZip)) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        String actualValueA1 = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                        Double actualValueB2 = xls.excel.getSheetAt(0).getRow(1).getCell(1).getNumericCellValue();
                        Double actualValueB3 = xls.excel.getSheetAt(0).getRow(1).getCell(2).getNumericCellValue();


                        Assertions.assertTrue(actualValueA1.contains("Ana Amari"));
                        Assertions.assertEquals(250, actualValueB2.doubleValue());
                        Assertions.assertEquals(37, actualValueB3.doubleValue());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Checking the contents of the JSON file")
    void jsonFileParsingTest() throws IOException {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(classLoader.getResourceAsStream("JSONExample.json"))
        )) {
            ObjectMapper objectMapper = new ObjectMapper();
            DNDCharacterModel actual = objectMapper.readValue(new File("src/test/resources/JSONExample.json"), DNDCharacterModel.class);
            List<String> expectedResultLanguages = List.of("Draconic", "Druidic", "Common");

            Assertions.assertEquals("DRAGONBORN", actual.getRace());
            Assertions.assertEquals("1", actual.getLevel());
            Assertions.assertEquals(expectedResultLanguages, actual.getLanguages());
        }
    }
}




