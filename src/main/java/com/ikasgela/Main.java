package com.ikasgela;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Tarea> tareas = new ArrayList<>();

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("tareasList.json"), StandardCharsets.UTF_8));
            String json = br.lines().collect(Collectors.joining());
            Gson gson = new Gson();
            tareas = gson.fromJson(json, new TypeToken<List<Tarea>>() {
            }.getType());

        } catch (FileNotFoundException e) {
            System.out.println("Sin Datos Cargados aun");
        }

        JFrame frame = new JFrame("Lista Tareas");
        frame.setContentPane(new V_ListTareas().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    public static boolean writeJson(List<Tarea> actualizacion) {

        boolean escritura;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("tareasList.json");
            Writer writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(actualizacion);

            writer.write(json);
            writer.flush();
            writer.close();

            tareas = actualizacion;
            escritura = true;
        } catch (IOException e) {
            escritura = false;
        }
        return escritura;
    }

    public static List<Tarea> getTareas() {
        return tareas;
    }

}


