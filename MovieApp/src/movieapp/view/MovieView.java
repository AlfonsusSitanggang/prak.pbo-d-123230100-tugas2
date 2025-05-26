package movieapp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MovieView extends JFrame {
    // Form input
    public JTextField tfJudul = new JTextField();
    public JTextField tfAlur = new JTextField();
    public JTextField tfPenokohan = new JTextField();
    public JTextField tfAkting = new JTextField();

    // Tombol
    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Hapus");
    public JButton btnClear = new JButton("Bersihkan");

    // Tabel
    public JTable table;
    public DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    // Kolom tabel
    String[] columnNames = {"Judul", "Alur", "Penokohan", "Akting", "Nilai"};

    public MovieView() {
        setTitle("Aplikasi Pengelola Film");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Tabel
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 400, 300);
        add(scrollPane);

        // Form label dan input
        addLabel("Judul", 440, 20);
        addField(tfJudul, 440, 40);

        addLabel("Alur", 440, 80);
        addField(tfAlur, 440, 100);

        addLabel("Penokohan", 440, 140);
        addField(tfPenokohan, 440, 160);

        addLabel("Akting", 440, 200);
        addField(tfAkting, 440, 220);

        // Tombol
        btnTambah.setBounds(440, 260, 100, 25);
        btnUpdate.setBounds(440, 295, 100, 25);
        btnDelete.setBounds(550, 260, 100, 25);
        btnClear.setBounds(550, 295, 100, 25);

        add(btnTambah);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 20);
        add(label);
    }

    private void addField(JTextField field, int x, int y) {
        field.setBounds(x, y, 200, 25);
        add(field);
    }

        public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getJudul() {
        return tfJudul.getText();
    }

    public double getAlur() {
        return Double.parseDouble(tfAlur.getText());
    }

    public double getPenokohan() {
        return Double.parseDouble(tfPenokohan.getText());
    }

    public double getAkting() {
        return Double.parseDouble(tfAkting.getText());
    }

    public void clearForm() {
        tfJudul.setText("");
        tfAlur.setText("");
        tfPenokohan.setText("");
        tfAkting.setText("");
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public String getSelectedJudul() {
        return table.getValueAt(getSelectedRow(), 0).toString();
    }
}