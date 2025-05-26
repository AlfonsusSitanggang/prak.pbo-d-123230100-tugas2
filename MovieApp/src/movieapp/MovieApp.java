package movieapp;

import javax.swing.SwingUtilities;
import movieapp.view.MovieView;
import movieapp.controller.MovieController;

public class MovieApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MovieController(new MovieView());
        });
    }
}