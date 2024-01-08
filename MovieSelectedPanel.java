package gui;

import api.AddMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MovieSelectedPanel {
    JPanel movieSelectedPanel=new JPanel();
    private final JTextArea displayArea = new JTextArea();
    private final JButton edit=new JButton("Edit movie");
    private final JButton delete=new JButton("Delete movie");
    private final JButton addReview=new JButton("Add review");
    private final JButton addToFavorites= new JButton("Add to favorites");

    public MovieSelectedPanel(AddMovie selectedMovie){
        setMovieSelectedPanel(selectedMovie);
    }
    private void setMovieSelectedPanel(AddMovie selectedMovie){
        movieSelectedPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        movieSelectedPanel.setPreferredSize(new Dimension(360,375));
        movieSelectedPanel.setBackground(Color.white);
        movieSelectedPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        setTextArea(selectedMovie);
        movieSelectedPanel.add(displayArea);
        setButtons();
    }
    private void setTextArea(AddMovie selectedMovie){
        String movieInfo = "Title: " + selectedMovie.getTitle() + "\n" +
                "Description: " + selectedMovie.getDescription() + "\n" +
                "Content Rating: " + selectedMovie.getContentRating() + "\n" +
                "Released Year: " + selectedMovie.getReleaseDate() + "\n" +
                "Length: " + selectedMovie.getLength() + "\n" +
                "Category: " + selectedMovie.getCategory() + "\n" +
                "Actors: " + selectedMovie.getActors() + "\n";
        displayArea.setPreferredSize(new Dimension(358,335));
        displayArea.setBorder(new EmptyBorder(0,5,0,0));
        displayArea.setText(movieInfo);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
    }

    private void setButtons(){
        LoginFrame youAre=new LoginFrame();
        System.out.println(youAre.getAdmin());
        if(youAre.getAdmin()){
            movieSelectedPanel.add(edit);
            movieSelectedPanel.add(delete);
        }else{
            movieSelectedPanel.add(addReview);
            movieSelectedPanel.add(addToFavorites);

        }
    }


}
