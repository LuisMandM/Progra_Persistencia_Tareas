package com.ikasgela;

public class Tarea {
    private boolean completada = false;
    private final String titulo;

    public Tarea(String titulo) {
        this.titulo = titulo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada() {
        this.completada = true;
    }

    public String getTitulo() {
        return titulo;
    }

}
