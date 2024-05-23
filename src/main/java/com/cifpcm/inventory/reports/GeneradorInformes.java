package com.mycompany.maven.itext;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class GeneradorInformes {

public static void main(String[] args) {
        
    String[] datosp = new String[30] ;
    for (int i = 0; i < datosp.length; i++) {
        datosp[i]= "PEPO  Apellido  922221  dasd@mail.com  12/12/2022   89123456789123456789123456789123456789123456789123456789123456789"; 
    }
    
    
        new GeneradorInformes(".//src//InformeProCaja.pdf",
                "INFORME DE LAS AULAS DE PRUEBAS con cajas.",
                "Nombre  APELLIDOS  Teléfono  EMAIL  Fecha  Otro Campo  Campo8  Campo9  Campo10  Campo11  Campo12  Campo13  Campo14",
                datosp,
                "Página 1 de 1 páginas.",
                true
        
        ); 
         
        new GeneradorInformes(".//src//InformeProSinCaja.pdf",
                "INFORME DE LAS AULAS DE PRUEBAS sin cajas.",
                "Nombre  APELLIDOS  Teléfono  EMAIL  Fecha  Otro Campo  Campo8  Campo9  Campo10  Campo11  Campo12  Campo13  Campo14",
                datosp,
                "Página 1 de 1 páginas.",
                false
        
        ); 
    }

/**
 * Generador de informes en PDF.
 * @param fichero  : Nombre fichero de salida, debe tener extensión .pdf 
 * @param titulo   : Título de informe se mostrará al comienzo de cada página. tamaño máximo 25 caracteres.    
 * @param cabecera : Cabecera con los campos que mostrará el informe  tamaño máximo 120 caracteres.    
 * @param datos    : Datos de linea de informe  tamaño máximo 120 caracteres.
 * @param paginado : Número de páginas  ejempo . "1"  |  "1 de 23"  | "Primera " "1 de 23 páginas"
 * @param caja     : Encierra las zonas en cajas de texto Ej menús
 */

    
    public  GeneradorInformes(String fichero, String titulo, String cabecera, String datos[], String paginado, boolean caja){
         
        System.out.println("Comienza genaración de "+ fichero + "   "+ LocalDateTime.now().toString());
         
        // Limpieza  programación defensiva  
        // importante para trabajar en equipo, junto con la documentación 
        String paratextLine = "____________________________________________________________________________________________________________________";
        String fileName = fichero;
        String tituloinfome = titulo; 
        String cabeceras = cabecera;
        String pagina2 = paginado;
        
        if (tituloinfome.length() > 25) tituloinfome= tituloinfome.substring(0, 24);
        if (cabeceras.length() > 120) cabeceras= cabeceras.substring(0, 119);
        // se propaga por ser  referencia. 
        for (int i = 0; i < datos.length; i++) {
              if (datos[i].length() > 120) datos[i]= datos[i].substring(0, 119);
        }
        if (pagina2.length() > 25) pagina2= pagina2.substring(0, 24);
        // fin Limpieza  programación defensiva   
        
        
        try {
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            /*
            Encabezado de informe con su nombre y imagen
             */
            // try que libera recursos al producir error
            try (Document document = new  Document(pdfDocument)) {
                /*
                Encabezado de informe con su nombre y imagen
                */
                Paragraph paragraph0 = new Paragraph(tituloinfome);
                
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
                
                
                Paragraph paragraph2 = new Paragraph(cabeceras);
                paragraph2.setBold();
                paragraph2.setFontSize(8);
                document.add(paragraph2);
               
                if (caja){
                    Paragraph paragraph3 = new Paragraph(paratextLine);
                    paragraph3.setBold();
                    paragraph3.setFontSize(8);
                    document.add(paragraph3);
                }
                
                // cuerpo del informe
                
                // Ejemplo para añadir una linea suelta
                //
                //String paratextC = "PEPO  Apellido  922221  dasd@mail.com  12/12/2022   89123456789123456789123456789123456789123456789123456789123456789";
                //Paragraph paragraph4 = new Paragraph(datos[i]);
                //paragraph4.setFontSize(8);
                //document.add(paragraph4);
                
                for (int i = 0; i < datos.length; i++) {
                    Paragraph paragraph4 = new Paragraph(datos[i]);
                    paragraph4.setFontSize(8);
                    document.add(paragraph4);
                }
                
                // pie de página
                
                //document.add(new Paragraph("\n"));
                document.add(paragraph1);
                
                Paragraph paragraph4 = new Paragraph(pagina2+  "  \t\t" + LocalDateTime.now().getDayOfMonth() + "/"+ LocalDateTime.now().getMonthValue()+ "/"+ LocalDateTime.now().getYear());
                paragraph4.setFontSize(8);
                paragraph4.setTextAlignment(TextAlignment.CENTER);
                document.add(paragraph4);
                
                // añade linea final 
                if (caja){
                document.add(paragraph1);
                 }
            }
            
            System.out.println("Finaliza genaración de "+ fichero + "   "+ LocalDateTime.now().toString());
        } catch (FileNotFoundException ex) {
           System.out.println("Error en contructor con parámetros " + ex.toString());
        }
    
    }
    /**
     *   Constructor desestructurado 
     */
    public  GeneradorInformes(){
        System.out.println("Hello World!");
         String fileName = ".//src//InformePro.pdf";
         String paratextH = "NOMBRE  APELLIDOS  TELEFONO  EMAIL  FNACIMIENTO  Campo7  Campo8  Campo9  Campo10  Campo11  Campo12  Campo13  Campo14";
         String paratextLine = "____________________________________________________________________________________________________________________";
         String paratextC = "PEPO  Apellido  922221  dasd@mail.com  12/12/2022   89123456789123456789123456789123456789123456789123456789123456789";
         
         
         
        try {
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document document = new  Document(pdfDocument);
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
            document.add(paragraph1);
            
            Paragraph paragraph4 = new Paragraph("Página 1/1  \t\t" + LocalDateTime.now().getDayOfMonth() + "/"+ LocalDateTime.now().getMonthValue()+ "/"+ LocalDateTime.now().getYear());
            paragraph4.setFontSize(8);
            paragraph4.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph4);
            document.add(paragraph1);
             
            
            document.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error en contructor sin parámetros " + ex.toString());
        }
        
        
    
    }
    
    
        
}
