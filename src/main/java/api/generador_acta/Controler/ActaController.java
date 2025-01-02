/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.generador_acta.Controler;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ActaController {

    @GetMapping("/")
    public String mostrarFormulario() {
        return "formulario"; // Muestra el formulario
    }

    @PostMapping("/generar-acta")
    public ResponseEntity<byte[]> generarActa(
            @RequestParam("nombreEquipo") String nombreEquipo,
            @RequestParam("numeroSerie") String numeroSerie,
            @RequestParam("cargo") String cargo,
            @RequestParam("jefeInmediato") String jefeInmediato,
            @RequestParam("marca") String marca,
            @RequestParam("modelo") String modelo,
            @RequestParam("area") String area,
            @RequestParam("responsable") String responsable,
            @RequestParam("observaciones") String observaciones,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("dia") String dia,
            @RequestParam("mes") String mes,
            @RequestParam("anio") String anio
    ) {
        try {
            // Ruta al archivo de plantilla (puedes modificar esta ruta)
            FileInputStream fis = new FileInputStream("src/main/resources/templates/acta_template.docx");

            // Cargar la plantilla de Word
            XWPFDocument documento = new XWPFDocument(fis);
            
            // Reemplazar los campos (placeholders) por los datos proporcionados
            for (XWPFParagraph parrafo : documento.getParagraphs()) {
                for (var run : parrafo.getRuns()) {
                    String texto = run.getText(0);
                    if (texto != null) {
                        texto = texto.replace("{{NombreEquipo}}", nombreEquipo)
                                     .replace("{{NumeroSerie}}", numeroSerie)
                                     .replace("{{Cargo}}", cargo)
                                     .replace("{{JefeInmediato}}", jefeInmediato)
                                     .replace("{{Marca}}", marca)
                                     .replace("{{Modelo}}", modelo)
                                     .replace("{{Area}}", area)
                                     .replace("{{Responsable}}", responsable)
                                     .replace("{{Observaciones}}", observaciones)
                                     .replace("{{Ciudad}}", ciudad)
                                     .replace("{{Dia}}", dia)
                                     .replace("{{Mes}}", mes)
                                     .replace("{{Anio}}", anio);
                        run.setText(texto, 0);
                    }
                }
            }

            // Convertir el documento a bytes para ser descargado
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            documento.write(baos);
            documento.close();

            // Configurar la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "acta_entrega.docx");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

