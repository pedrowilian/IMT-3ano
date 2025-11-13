package wordgraphle.model;

// enum(enumeration) - tipo que representa um conjunto fixo de valores nomeados
public enum FeedbackColor {
    GREEN, YELLOW, GRAY; // Conjunto fixo de valores nomeados

    // 1. awt(Abstract Window Toolkit) - a base gráfica do Java usada pelo Swing
    // 2. toawt - recebe um FeedbackColor e devolve um java.awt.Color (classe de cor do AWT/Swing)
    public static java.awt.Color toAwt(FeedbackColor c) {
        return switch (c) {
            case GREEN  -> new java.awt.Color(83, 141, 78);     // Verde - Certo
            case YELLOW -> new java.awt.Color(181, 159, 59);    // Amarelo - Presente
            case GRAY   -> new java.awt.Color(58, 58, 60);      // Cinza - Ausente
        };
    }
}