package gui;

import api.AddMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

public class AddMoviePanel  {
    protected JPanel addMoviePanel=new JPanel();

    protected JPanel titlePanel=new JPanel();
    protected JTextField titleField=new JTextField();

    protected JPanel descriptionPanel=new JPanel();
    protected JTextArea descriptionArea=new JTextArea(3,25);

    protected JPanel contentRatingPanel=new JPanel();
    protected JCheckBox adult=new JCheckBox(">18");
    protected JCheckBox nonAdult=new JCheckBox("<18");

    private final JPanel releaseDatePanel=new JPanel();
    private final JTextField releaseDateField=new JTextField();

    private final JPanel movieLengthPanel=new JPanel();
    private final JTextField movieLengthField=new JTextField();

    protected JPanel categoryPanel=new JPanel();

    protected JComboBox <String> categories= new JComboBox<>();

    protected JPanel actorsPanel=new JPanel();
    protected JTextArea actorsArea=new JTextArea(2,20);

    private final JButton complete=new JButton("Complete");
    boolean movieExists=false;

    protected AddMoviePanel() throws IOException, ClassNotFoundException {
        setAddMoviePanel();
    }

    private void setAddMoviePanel(){
        addMoviePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        addMoviePanel.setPreferredSize(new Dimension(500,460));
        addMoviePanel.setBackground(Color.yellow);
        setTitlePanel();
        setDescriptionPanel();
        setContentRatingPanel();
        setReleaseDatePanel();
        setMovieLengthPanel();
        setCategoryPanel();
        setActorsPanel();
        complete.addActionListener(e -> {
            File firstMovie=new File("MOVIES.txt");
            if (firstMovie.exists()) {
                try (ObjectInputStream movieData = new ObjectInputStream(new FileInputStream("MOVIES.TXT"))) {
                    @SuppressWarnings("unchecked")
                    ArrayList<AddMovie> movies = (ArrayList<AddMovie>) movieData.readObject();
                    for (AddMovie movie : movies) {
                        if (movie.getTitle().equals(titleField.getText())) {
                            movieExists = true;
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (validInputs() && (Integer.parseInt(releaseDateField.getText()) <= Year.now().getValue())&& !movieExists) {
                try {
                    AddMovie movie=new AddMovie();
                    movie.addMovieToFile(titleField.getText(),descriptionArea.getText(),adult.isSelected(),Integer.parseInt(releaseDateField.getText()),Integer.parseInt(movieLengthField.getText()), Objects.requireNonNull(categories.getSelectedItem()).toString(),actorsArea.getText());
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                if (!validInputs()) {
                    JOptionPane.showMessageDialog(addMoviePanel, "Please fill in all the fields!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                } else if(Integer.parseInt(releaseDateField.getText()) > Year.now().getValue()) {
                    JOptionPane.showMessageDialog(addMoviePanel, "Release Year cannot be over " + Year.now().getValue()+"!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(addMoviePanel, "This movie already exists!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                    movieExists=false;
                }
            }
        setAllFields();
        });
        addMoviePanel.add(titlePanel);
        addMoviePanel.add(descriptionPanel);
        addMoviePanel.add(contentRatingPanel);
        addMoviePanel.add(releaseDatePanel);
        addMoviePanel.add(movieLengthPanel);
        addMoviePanel.add(categoryPanel);
        addMoviePanel.add(actorsPanel);
        addMoviePanel.add(complete);

    }
    private void setAllFields(){
        setTitleField();
        setDescriptionArea();
        setContentRatingFields();
        setReleaseDateField();
        setMovieLengthField();
        setCategoryOptions();
        setActorsArea();
    }
    private void setTitlePanel(){
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        titlePanel.setPreferredSize(new Dimension(500,50));
        titlePanel.setBackground(Color.yellow);
        JLabel title=new JLabel("Title:");
        title.setBorder(new EmptyBorder(0,150,0,150));
        setTitleField();
        titlePanel.add(title);
        titlePanel.add(titleField);
    }
    protected void setTitleField(){
        titleField.setText("e.g Prisoners");
        titleField.setForeground(Color.GRAY);
        titleField.setPreferredSize(new Dimension(220, 25));
        titleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleField.getText().equals("e.g Prisoners")) {
                    titleField.setText("");
                    titleField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (titleField.getText().isEmpty()) {
                    titleField.setText("e.g Prisoners");
                    titleField.setForeground(Color.GRAY);
                }
            }
        });
    }
    private void setDescriptionPanel(){
        descriptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        descriptionPanel.setPreferredSize(new Dimension(500,85));
        descriptionPanel.setBackground(Color.yellow);
        JLabel description=new JLabel("Description:");
        description.setBorder(new EmptyBorder(0,150,0,150));
        setDescriptionArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionPanel.add(description);
        descriptionPanel.add(descriptionScrollPane);
    }
    protected void setDescriptionArea(){
        descriptionArea.setText("Write a description");
        descriptionArea.setForeground(Color.GRAY);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (descriptionArea.getText().equals("Write a description")) {
                    descriptionArea.setText("");
                    descriptionArea.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (descriptionArea.getText().isEmpty()) {
                    descriptionArea.setText("Write a description");
                    descriptionArea.setForeground(Color.GRAY);
                }
            }
        });
    }
    private void setContentRatingPanel(){
        contentRatingPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        contentRatingPanel.setPreferredSize(new Dimension(500,45));
        contentRatingPanel.setBackground(Color.yellow);
        JLabel contentRating= new JLabel("Content Rating:");
        setContentRatingFields();
        contentRatingPanel.add(contentRating);
        contentRatingPanel.add(adult);
        contentRatingPanel.add(nonAdult);
    }
    protected void setContentRatingFields(){
        adult.setSelected(false);
        nonAdult.setSelected(false);
        adult.setBackground(Color.yellow);
        nonAdult.setBackground(Color.yellow);
        adult.addActionListener(e -> nonAdult.setSelected(false));
        nonAdult.addActionListener(e -> adult.setSelected(false));
    }
    private void setReleaseDatePanel(){
        releaseDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        releaseDatePanel.setPreferredSize(new Dimension(500,45));
        releaseDatePanel.setBackground(Color.yellow);
        JLabel yearReleased=new JLabel("Year Released: ");
        setReleaseDateField();
        releaseDatePanel.add(yearReleased);
        releaseDatePanel.add(releaseDateField);
    }
    private void setReleaseDateField(){
        releaseDateField.setText("e.g 2024");
        releaseDateField.setForeground(Color.GRAY);
        releaseDateField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (releaseDateField.getText().equals("e.g 2024")) {
                    releaseDateField.setText("");
                    releaseDateField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (releaseDateField.getText().isEmpty()) {
                    releaseDateField.setText("e.g 2024");
                    releaseDateField.setForeground(Color.GRAY);
                }
            }
        });
        releaseDateField.addKeyListener(new KeyListener() {
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
        releaseDateField.setPreferredSize(new Dimension(55, 20));
    }
    private void setMovieLengthPanel(){
        movieLengthPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        movieLengthPanel.setPreferredSize(new Dimension(500,45));
        movieLengthPanel.setBackground(Color.yellow);
        JLabel movieLength=new JLabel("Movie Length: ");
        setMovieLengthField();
        movieLengthPanel.add(movieLength);
        movieLengthPanel.add(movieLengthField);
    }
    private void setMovieLengthField(){
        movieLengthField.setText("e.g 210");
        movieLengthField.setForeground(Color.GRAY);
        movieLengthField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (movieLengthField.getText().equals("e.g 210")) {
                    movieLengthField.setText("");
                    movieLengthField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (movieLengthField.getText().isEmpty()) {
                    movieLengthField.setText("e.g 210");
                    movieLengthField.setForeground(Color.GRAY);
                }
            }
        });
        movieLengthField.addKeyListener(new KeyListener() {
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
        movieLengthField.setPreferredSize(new Dimension(50, 20));
    }
    private void setCategoryPanel(){
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        categoryPanel.setPreferredSize(new Dimension(500,35));
        categoryPanel.setBackground(Color.yellow);
        JLabel category=new JLabel("Movie Category: ");
        setCategoryOptions();
        categoryPanel.add(category);
        categoryPanel.add(categories);
    }
    protected void setCategoryOptions(){
        categories.setPreferredSize(new Dimension(100,25));
        categories.removeAllItems();
        categories.addItem("-");
        categories.addItem("HORROR");
        categories.addItem("DRAMA");
        categories.addItem("SCIENCE FICTION");
        categories.addItem("COMEDY");
        categories.addItem("ACTION");
    }
    private void setActorsPanel(){
        actorsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        actorsPanel.setPreferredSize(new Dimension(500,75));
        actorsPanel.setBackground(Color.yellow);
        JLabel actors=new JLabel("Actors:");
        actors.setBorder(new EmptyBorder(0,150,0,150));
        setActorsArea();
        JScrollPane actorsScrollPane = new JScrollPane(actorsArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        actorsPanel.add(actors);
        actorsPanel.add(actorsScrollPane);
    }
    protected void setActorsArea(){
        actorsArea.setText("e.g Tom Hardy, Bruce Willis");
        actorsArea.setForeground(Color.GRAY);
        actorsArea.setLineWrap(true);
        actorsArea.setWrapStyleWord(true);
        actorsArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (actorsArea.getText().equals("e.g Tom Hardy, Bruce Willis")) {
                    actorsArea.setText("");
                    actorsArea.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (actorsArea.getText().isEmpty()) {
                    actorsArea.setText("e.g Tom Hardy, Bruce Willis");
                    actorsArea.setForeground(Color.GRAY);
                }
            }
        });
        actorsArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    private boolean validInputs() {
        return !titleField.getText().trim().equalsIgnoreCase("e.g Prisoners") && !descriptionArea.getText().trim().equalsIgnoreCase("Write a description")
                && (adult.isSelected() || nonAdult.isSelected()) && !releaseDateField.getText().trim().equalsIgnoreCase("e.g 2024")
                &&!movieLengthField.getText().trim().equalsIgnoreCase("e.g 210") && !Objects.requireNonNull(categories.getSelectedItem()).toString().trim().equalsIgnoreCase("-")
                &&!actorsArea.getText().trim().equalsIgnoreCase("e.g Tom Hardy, Bruce Willis");
    }
}
