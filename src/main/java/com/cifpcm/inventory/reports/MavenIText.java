package com.mycompany.maven.itext;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MavenIText {

    public static void main(String[] args) throws MalformedURLException {
        System.out.println("Hello World!");
         String fileName = ".//src//InformePRUEBASImagen.pdf";
         String paratextH = "NOMBRE  APELLIDOS  TELEFONO  EMAIL  FNACIMIENTO  Campo7  Campo8  Campo9  Campo10  Campo11  Campo12  Campo13  Campo14";
         String paratextLine = "____________________________________________________________________________________________________________________";
         String paratextC = "PEPO  Apellido  922221  dasd@mail.com  12/12/2022   89123456789123456789123456789123456789123456789123456789123456789";
         
         
         
        try {
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();     
            Document document = new  Document(pdfDocument);
            
            String imFile = ".//src//logo2.png";
            ImageData data = ImageDataFactory.create(imFile);
            Image image = new Image(data);
            //image.setPadding(20);
            image.setMarginTop(25);
            image.setWidth(20);
            image.setMaxHeight(25);
            image.setAutoScale(false);
            document.add(image);
            
            /*
            Encabezado de informe con su nombre y imagen
            */
            Paragraph paragraph0 = new Paragraph("NOMBRE DEL INFORME.");
                      
            paragraph0.setBold();
            paragraph0.setFontSize(15);
            //paragraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14));
            paragraph0.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph0);
            /*
            Cabecera
            */
            Paragraph paragraph1 = new Paragraph(paratextLine);
            paragraph1.setBold();
            paragraph1.setFontSize(8);
            document.add(paragraph1);
            
            
            Paragraph paragraph2 = new Paragraph(paratextH);
            paragraph2.setBold();
            paragraph2.setFontSize(8);
            document.add(paragraph2);
            
            Paragraph paragraph3 = new Paragraph(paratextLine);
            paragraph3.setBold();
            paragraph3.setFontSize(8);
            document.add(paragraph3);
            
            
            // cuerpo del informe  
            
            for (int i = 0; i < 30; i++) {
                Paragraph paragraph4 = new Paragraph(paratextC);
                paragraph4.setFontSize(8);
                document.add(paragraph4);
            }
            
            // pie de página
            
            //document.add(new Paragraph("\n"));
            //document.add(paragraph1);
            
            Paragraph paragraph4 = new Paragraph("Página 1/1  \t\t" + LocalDateTime.now().getDayOfMonth() + "/"+ LocalDateTime.now().getMonthValue()+ "/"+ LocalDateTime.now().getYear());
            paragraph4.setFontSize(8);
            paragraph4.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph4);
            //document.add(paragraph1);
             
            
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MavenIText.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}
