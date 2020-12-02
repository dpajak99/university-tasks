package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.UserDataDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class DocumentComponentImpl implements DocumentComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentComponentImpl.class);

    @Override
    public void createDocument(UserDataDto userDataDto, String fileDestination) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDestination));
            document.open();
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{1, 1, 2});
            table.addCell(createCell("Imię", 2, Element.ALIGN_LEFT));
            table.addCell(createCell("Nazwisko", 2, Element.ALIGN_LEFT));
            table.addCell(createCell("Opis", 2, Element.ALIGN_LEFT));

            table.addCell(createCell(userDataDto.getFirstName(), 1, Element.ALIGN_LEFT));
            table.addCell(createCell(userDataDto.getLastName(), 1, Element.ALIGN_LEFT));
            table.addCell(createCell(userDataDto.getDescription(), 1, Element.ALIGN_LEFT));

            document.add(table);

            setBackgroundAsGradient(document, writer);

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("i can't create document or file not exists");
        }
    }

    private PdfPCell createCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/arial.ttf";

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);

        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(3);
        cell.setPaddingBottom(6);
        cell.setPaddingLeft(3);
        cell.setPaddingRight(3);
        return cell;
    }

    private void setBackgroundAsGradient(Document document, PdfWriter writer) {
        Rectangle pageSize = document.getPageSize();
        PdfShading axial = PdfShading.simpleAxial(writer,
                pageSize.getLeft(pageSize.getWidth()/10), pageSize.getBottom(),
                pageSize.getRight(pageSize.getWidth()/10), pageSize.getBottom(),
                new BaseColor(128, 193, 255),
                new BaseColor(187, 153, 255), true, true);
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.paintShading(axial);
    }
}
