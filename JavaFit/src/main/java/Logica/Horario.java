package Logica;


public class Horario {
    private String dia;
    private String hora_inicio;
    private String hora_final;

    public Horario(String dia, String hora_inicio, String hora_final) {
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    public String getDia() {
        return dia;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }
    
}
