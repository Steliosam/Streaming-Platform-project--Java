package gui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddMovieAndSeriesPanel {
    protected JPanel addPanel= new JPanel();


    protected AddMovieAndSeriesPanel() throws IOException {
        setAddPanel();
    }

    private void setAddPanel() {
        addPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        addPanel.setPreferredSize(new Dimension(500,460));
        addPanel.setBackground(Color.cyan);
        JLabel movieOrSeries=new JLabel("What do you want to add?");
        movieOrSeries.setBorder(new EmptyBorder(150,250,10,250));
        JButton movie=new JButton("MOVIE");
        JButton series=new JButton("SERIES");
        movie.addActionListener(e -> {
            AddMoviePanel addMovie;
            try {
                addMovie = new AddMoviePanel();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            showPanel(addMovie.addMoviePanel);
        });
        series.addActionListener(e -> {
            AddSeriesPanel addSeries;
            try {
                addSeries = new AddSeriesPanel();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            showPanel(addSeries.addSeriesPanel);
        });
        addPanel.add(movieOrSeries);
        addPanel.add(movie);
        addPanel.add(series);


    }

    private void showPanel(JPanel displayPanel) {
        addPanel.removeAll();
        addPanel.add(displayPanel);
        addPanel.revalidate();
        addPanel.repaint();
    }
}
