package edu.proyectodual.crearpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class PdfCreator {

    public void createPdf(String fileName, String text) throws IOException, DocumentException, URISyntaxException {
        Document doc = new Document();
        PdfWriter pdfWriter =
                PdfWriter.getInstance(doc, new FileOutputStream(fileName + ".pdf"));

       // pdfWriter.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);

        doc.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.RED);
        Chunk chunk = new Chunk(text, font);

        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);


        doc.add(paragraph);

        doc.close();

        PdfReader pdfReader = new PdfReader(fileName + ".pdf");
    }


}
