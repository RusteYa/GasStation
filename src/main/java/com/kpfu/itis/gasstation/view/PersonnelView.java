package com.kpfu.itis.gasstation.view;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
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
@Component("personnelView")
public class PersonnelView extends AbstractView {
    private final AppUserRepository appUserRepository;

    @Autowired
    public PersonnelView(AppUserRepository appUserRepository) {
        this.appUserRepository= appUserRepository;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=personnel.pdf");

        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/fonts/times.ttf", "cp1251");

        Paragraph title = new Paragraph("Сотрудники и клиенты АЗС");
        title.setFont(font);
        title.setFontSize(24);
        title.setBold();
        pdfDocument.add(title);

        Paragraph date = new Paragraph(new Date().toString());
        title.setFont(font);
        date.setFontSize(16);
        pdfDocument.add(date);

        Table table = new Table(4);
        table.addCell(new Cell().add(new Paragraph("Имя").setFont(font).setFontSize(12).setBold()));
        table.addCell(new Cell().add(new Paragraph("Логин").setFont(font).setFontSize(12).setBold()));
        table.addCell(new Cell().add(new Paragraph("Емайл").setFont(font).setFontSize(12).setBold()));
        table.addCell(new Cell().add(new Paragraph("Роль").setFont(font).setFontSize(12).setBold()));
        for (AppUser appUser : appUserRepository.findAll()) {
            table.addCell(new Cell().add(new Paragraph(appUser.getName()).setFont(font).setFontSize(12)));
            table.addCell(new Cell().add(new Paragraph(appUser.getLogin()).setFont(font).setFontSize(12)));
            table.addCell(new Cell().add(new Paragraph(appUser.getEmail())).setFont(font).setFontSize(12));
            table.addCell(new Cell().add(new Paragraph(appUser.getAppRole().getName())).setFont(font).setFontSize(12));
        }

        pdfDocument.add(table);

        pdfDocument.close();
    }
}
