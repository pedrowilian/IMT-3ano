package wordgraphle.model;

import java.util.Arrays;

public class Feedback {
    // Parâmetros
    public final String guess;
    public final FeedbackColor[] colors;

    // Construtor
    public Feedback(String guess, FeedbackColor[] colors) {
        this.guess = guess.toUpperCase();
        this.colors = colors;
    }

    @Override
    public String toString() {
        return guess + " " + Arrays.toString(colors);
    }
}