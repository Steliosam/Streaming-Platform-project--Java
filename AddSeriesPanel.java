package gui;

import api.AddSeries;
import api.SeasonDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;

public class AddSeriesPanel extends AddMoviePanel {
    protected JPanel addSeriesPanel=new JPanel();
    private final JPanel seasonsPanel=new JPanel();
    private final JTextField seasonField=new JTextField();
    private final JTextField yearField=new JTextField();
    private final JTextField lengthField=new JTextField();
    JButton addSeason=new JButton("Add");
    private final JButton complete=new JButton("Complete");
    boolean flag=false;
    boolean seriesExists=false;

    protected AddSeriesPanel() throws IOException, ClassNotFoundException {
        setAddSeriesPanel();
    }

    private void setAddSeriesPanel(){
        addSeriesPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        addSeriesPanel.setPreferredSize(new Dimension(500,460));
        addSeriesPanel.setBackground(Color.yellow);
        setSeasonPanel();
        setCompleteButton();
        addSeriesPanel.add(titlePanel);
        addSeriesPanel.add(descriptionPanel);
        addSeriesPanel.add(contentRatingPanel);
        addSeriesPanel.add(categoryPanel);
        addSeriesPanel.add(actorsPanel);
        addSeriesPanel.add(seasonsPanel);
        addSeriesPanel.add(complete);

    }
    private void setSeasonPanel(){
        seasonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,20));
        seasonsPanel.setPreferredSize(new Dimension(500,55));
        seasonsPanel.setBackground(Color.yellow);
        JLabel season=new JLabel("Season: ");
        setSeasonField();
        JLabel year=new JLabel("Year: ");
        setYearField();
        JLabel lengths=new JLabel("Lengths of episodes: ");
        setLengthField();
        addSeason.setPreferredSize(new Dimension(65,25));
        addSeason.addActionListener(e -> {
            if (validSeasonInputs()&& (Integer.parseInt(yearField.getText()) <= Year.now().getValue())) {
                try {
                    SeasonDetails seasonData = new SeasonDetails();
                    seasonData.addSeason(Integer.parseInt(seasonField.getText()), Integer.parseInt(yearField.getText()), lengthField.getText());
                    flag=true;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{
                if (!validSeasonInputs()) {
                    JOptionPane.showMessageDialog(addSeriesPanel, "Please fill in all the fields!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(addSeriesPanel, "Release Year cannot be over " + Year.now().getValue()+".", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        seasonsPanel.add(season);
        seasonsPanel.add(seasonField);
        seasonsPanel.add(year);
        seasonsPanel.add(yearField);
        seasonsPanel.add(lengths);
        seasonsPanel.add(lengthField);
        seasonsPanel.add(addSeason);
    }
    private void setCompleteButton(){
        complete.addActionListener(e -> {
            File firstMovie=new File("MOVIES.txt");
            if (firstMovie.exists()) {
                try (ObjectInputStream movieData = new ObjectInputStream(new FileInputStream("SERIES.TXT"))) {
                    @SuppressWarnings("unchecked")
                    ArrayList<AddSeries> series = (ArrayList<AddSeries>) movieData.readObject();
                    for (AddSeries seriesOne : series) {
                        if (seriesOne.getTitle().equals(titleField.getText())) {
                            seriesExists = true;
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (validInputs()&&flag&&!seriesExists) {
                try {
                    AddSeries series=new AddSeries();
                    series.addSeriesToFile(titleField.getText(),descriptionArea.getText(),adult.isSelected(),Objects.requireNonNull(categories.getSelectedItem()).toString(),actorsArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                if (!validInputs()) {
                    JOptionPane.showMessageDialog(addSeriesPanel, "Please fill in all the fields!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                }
                else if(!flag) {
                    JOptionPane.showMessageDialog(addSeriesPanel, "You need to add at least one season!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(addSeriesPanel, "This series already exist!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                    seriesExists=false;
                }
            }
            resetFields();
        });
    }
    private void resetFields(){
        setTitleField();
        setDescriptionArea();
        setContentRatingFields();
        setCategoryOptions();
        setSeasonField();
        setYearField();
        setLengthField();

    }
    private void setSeasonField(){
        seasonField.setText("e.g 2");
        seasonField.setForeground(Color.GRAY);
        seasonField.setPreferredSize(new Dimension(35, 25));
        seasonField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (seasonField.getText().equals("e.g 2")) {
                    seasonField.setText("");
                    seasonField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (seasonField.getText().isEmpty()) {
                    seasonField.setText("e.g 2");
                    seasonField.setForeground(Color.GRAY);
                }
            }
        });
        seasonField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    private void setYearField(){
        yearField.setText("e.g 2008");
        yearField.setForeground(Color.GRAY);
        yearField.setPreferredSize(new Dimension(55, 25));
        yearField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (yearField.getText().equals("e.g 2008")) {
                    yearField.setText("");
                    yearField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (yearField.getText().isEmpty()) {
                    yearField.setText("e.g 2008");
                    yearField.setForeground(Color.GRAY);
                }
            }
        });
        yearField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    private void setLengthField(){
        lengthField.setText("e.g 40,20,55");
        lengthField.setForeground(Color.GRAY);
        lengthField.setPreferredSize(new Dimension(75,25));
        lengthField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (lengthField.getText().equals("e.g 40,20,55")) {
                    lengthField.setText("");
                    lengthField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (lengthField.getText().isEmpty()) {
                    lengthField.setText("e.g 40,20,55");
                    lengthField.setForeground(Color.GRAY);
                }
            }
        });
        lengthField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c!=','){
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    private boolean validInputs() {
        return !titleField.getText().trim().equalsIgnoreCase("e.g Prisoners") && !descriptionArea.getText().trim().equalsIgnoreCase("Write a description")
                && (adult.isSelected() || nonAdult.isSelected()) && !Objects.requireNonNull(categories.getSelectedItem()).toString().trim().equalsIgnoreCase("-")
                &&!actorsArea.getText().trim().equalsIgnoreCase("e.g Tom Hardy, Bruce Willis");

    }
    private boolean validSeasonInputs(){
        return !seasonField.getText().trim().equalsIgnoreCase("e.g 2") &&!yearField.getText().trim().equalsIgnoreCase("e.g 2008")
                &&!lengthField.getText().trim().equalsIgnoreCase("e.g 40,20,55" );
    }
}
