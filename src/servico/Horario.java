package servico;

public final class Horario {

    private Horario() {
    }

    public static int paraMinutos(String horario) {
        if (horario == null || !horario.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Horario invalido. Use o formato HH:MM (ex: 09:30).");
        }
        int horas = Integer.parseInt(horario.substring(0, 2));
        int minutos = Integer.parseInt(horario.substring(3, 5));
        if (horas > 23 || minutos > 59) {
            throw new IllegalArgumentException("Horario invalido. Horas de 00 a 23 e minutos de 00 a 59.");
        }
        return horas * 60 + minutos;
    }
}
