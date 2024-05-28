package com.cifpcm.inventory.reports;

import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import com.cifpcm.inventory.models.producto.Producto;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class GeneradorInformes {

    public void generarInformePDF(List<Marcaje> fichajes, String nombreArchivo, String tituloInforme) throws MalformedURLException {
        try {
            PdfWriter pdfWriter = new PdfWriter(nombreArchivo);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            // Agregar imagen
            Image image = new Image(ImageDataFactory.create(".//src//logo.jpg"));
            image.setMarginTop(25);
            image.setWidth(20);
            image.setMaxHeight(25);
            image.setAutoScale(false);
            document.add(image);

            // Título del informe
            Paragraph titulo = new Paragraph(tituloInforme);
            titulo.setBold();
            titulo.setFontSize(15);
            titulo.setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            // Crear tabla
            Table table = new Table(6);

            // Encabezados de columna en negrita y centrados
            String[] columnas = {"ID AULA", "ID PRODUCTO", "DESCRIPCIÓN PRODUCTO","ID MARCAJE" , "TIMESTAMP", "TIPO"};
            for (String columna : columnas) {
                Paragraph paragraph = new Paragraph(columna).setBold().setFontSize(10); // Tamaño de la fuente reducido a 10
                Cell cell = new Cell().add(paragraph).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
            }

            // Formatear las fechas a UTC para evitar problemas de zona horaria
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            // Agregar datos de marcaje a la tabla
            for (MarcajeInterface fichaje : fichajes) {
                String tipo = (fichaje.getTipo() == TipoMarcaje.ENTRADA) ? "ENTRADA" : "SALIDA";
                table.addCell(new Cell().add(new Paragraph(String.valueOf(fichaje.getIdAula()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(fichaje.getIdProducto()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(fichaje.getDescripcionProducto())).setTextAlignment(TextAlignment.CENTER)); // Agregar la descripción del producto
                table.addCell(new Cell().add(new Paragraph(String.valueOf(fichaje.getIdMarcaje()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(dateFormat.format(fichaje.getTimeStamp()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(tipo)).setTextAlignment(TextAlignment.CENTER));
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
