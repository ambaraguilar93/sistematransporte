package com.duoc.sistematransportes.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.duoc.sistematransportes.entity.GuiaDespacho;

@Service
public class ArchivoService {

    @Value("${efs.path}")
    private String carpetaGuias;

    public String generarArchivoTxt(GuiaDespacho guia) {

        try {

            File carpeta = new File(CARPETA_GUIAS);

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String nombreArchivo = guia.getNumeroGuia() + ".txt";

            String rutaCompleta = CARPETA_GUIAS + "/" + nombreArchivo;

            FileWriter writer = new FileWriter(rutaCompleta);

            writer.write("GUIA DE DESPACHO\n");
            writer.write("---------------------------\n");
            writer.write("Numero: " + guia.getNumeroGuia() + "\n");
            writer.write("Transportista: " + guia.getTransportista() + "\n");
            writer.write("Destinatario: " + guia.getDestinatario() + "\n");
            writer.write("Direccion: " + guia.getDireccionEntrega() + "\n");
            writer.write("Fecha: " + guia.getFecha() + "\n");

            writer.close();

            return rutaCompleta;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Error al generar archivo TXT", e);
        }
    }
}
