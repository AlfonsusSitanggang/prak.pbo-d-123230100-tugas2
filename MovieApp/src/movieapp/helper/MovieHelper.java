package movieapp.helper;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import movieapp.view.MovieView;

public class MovieHelper {

    public static double parseDoubleSafe(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    public static boolean isValidMovieInput(double... values) {
        for (double v : values) {
            if (v < 0 || v > 5) {
                return false;
            }
        }
        return true;
    }

    public static void showErrorMessage(MovieView parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmAction(MovieView parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Konfirmasi", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}