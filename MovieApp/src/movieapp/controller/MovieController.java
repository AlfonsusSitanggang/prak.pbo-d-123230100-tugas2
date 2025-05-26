package movieapp.controller;

import movieapp.model.*;
import movieapp.view.MovieView;
import movieapp.helper.MovieHelper;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MovieController {
    MovieView view;
    MovieDAO dao;
    String judulDipilih = "";

    public MovieController(MovieView view) {
        this.view = view;
        this.dao = new MovieDAO();
        loadTable();

        view.btnTambah.addActionListener(e -> {
            String judul = view.getJudul();
            double alur = MovieHelper.parseDoubleSafe(view.tfAlur.getText(), -1);
            double penokohan = MovieHelper.parseDoubleSafe(view.tfPenokohan.getText(), -1);
            double akting = MovieHelper.parseDoubleSafe(view.tfAkting.getText(), -1);

            if (!MovieHelper.isValidTitle(judul) || !MovieHelper.isValidMovieInput(alur, penokohan, akting)) {
                view.showMessage("Input tidak valid! Judul tidak boleh kosong dan nilai antara 0-5.");
                return;
            }

            try {
                Movie movie = new Movie(judul, alur, penokohan, akting);
                dao.insert(movie);
                view.showMessage("Data berhasil ditambahkan.");
                view.clearForm();
                loadTable();
            } catch (Exception ex) {
                MovieHelper.showErrorMessage(view, ex.getMessage());
            }
        });

        view.btnUpdate.addActionListener(e -> {
            if (judulDipilih.isEmpty()) {
                view.showMessage("Pilih data terlebih dahulu!");
                return;
            }

            String judul = view.getJudul();
            double alur = MovieHelper.parseDoubleSafe(view.tfAlur.getText(), -1);
            double penokohan = MovieHelper.parseDoubleSafe(view.tfPenokohan.getText(), -1);
            double akting = MovieHelper.parseDoubleSafe(view.tfAkting.getText(), -1);

            if (!MovieHelper.isValidTitle(judul) || !MovieHelper.isValidMovieInput(alur, penokohan, akting)) {
                view.showMessage("Update gagal! Input tidak valid.");
                return;
            }

            try {
                Movie movie = new Movie(judul, alur, penokohan, akting);
                dao.update(movie, judulDipilih);
                view.showMessage("Data berhasil diupdate.");
                view.clearForm();
                loadTable();
                judulDipilih = "";
            } catch (Exception ex) {
                MovieHelper.showErrorMessage(view, ex.getMessage());
            }
        });

        view.btnDelete.addActionListener(e -> {
            if (judulDipilih.isEmpty()) {
                view.showMessage("Pilih data terlebih dahulu!");
                return;
            }

            if (MovieHelper.confirmAction(view, "Yakin ingin menghapus data ini?")) {
                try {
                    dao.delete(judulDipilih);
                    view.showMessage("Data berhasil dihapus.");
                    view.clearForm();
                    loadTable();
                    judulDipilih = "";
                } catch (Exception ex) {
                    MovieHelper.showErrorMessage(view, "Gagal menghapus data: " + ex.getMessage());
                }
            }
        });

        view.btnClear.addActionListener(e -> {
            view.clearForm();
            judulDipilih = "";
        });

        view.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getSelectedRow();
                if (row >= 0) {
                    judulDipilih = view.getSelectedJudul();
                    view.tfJudul.setText(judulDipilih);
                    view.tfAlur.setText(view.table.getValueAt(row, 1).toString());
                    view.tfPenokohan.setText(view.table.getValueAt(row, 2).toString());
                    view.tfAkting.setText(view.table.getValueAt(row, 3).toString());
                }
            }
        });
    }

    public void loadTable() {
        List<Movie> list = dao.getAll();
        DefaultTableModel model = (DefaultTableModel) view.table.getModel();
        model.setRowCount(0);
        for (Movie m : list) {
            Object[] row = {
                m.getJudul(),
                m.getAlur(),
                m.getPenokohan(),
                m.getAkting(),
                m.getNilai()
            };
            model.addRow(row);
        }
    }
}