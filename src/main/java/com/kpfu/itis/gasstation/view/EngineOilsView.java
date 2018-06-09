package com.kpfu.itis.gasstation.view;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.kpfu.itis.gasstation.entities.EngineOil;
import com.kpfu.itis.gasstation.repositories.EngineOilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by Rustem.
 */
@Component("engineOilsView")
public class EngineOilsView extends AbstractView {
    private final EngineOilRepository engineOilRepository;

    @Autowired
    public EngineOilsView(EngineOilRepository engineOilRepository) {
        this.engineOilRepository = engineOilRepository;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=engine_oils.pdf");

        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/fonts/times.ttf", "cp1251");

        Paragraph title = new Paragraph("Моторные масла");
        title.setFont(font);
        title.setFontSize(24);
        title.setBold();
        pdfDocument.add(title);

        Paragraph date = new Paragraph(new Date().toString());
        title.setFont(font);
        date.setFontSize(16);
        pdfDocument.add(date);

        Table table = new Table(3);
        table.addCell(new Cell().add(new Paragraph("Название").setFont(font).setFontSize(12).setBold()));
        table.addCell(new Cell().add(new Paragraph("Производитель").setFont(font).setFontSize(12).setBold()));
        table.addCell(new Cell().add(new Paragraph("Цена").setFont(font).setFontSize(12).setBold()));
        for (EngineOil engineOil : engineOilRepository.findAll()) {
            table.addCell(new Cell().add(new Paragraph(engineOil.getName()).setFont(font).setFontSize(12)));
            table.addCell(new Cell().add(new Paragraph(engineOil.getManafacturer()).setFont(font).setFontSize(12)));
            table.addCell(new Cell().add(new Paragraph(engineOil.getPrice() + "")).setFont(font).setFontSize(12));
        }

        pdfDocument.add(table);

        pdfDocument.close();
    }
}
