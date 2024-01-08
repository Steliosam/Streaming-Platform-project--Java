package gui;

import api.AddSeries;
import api.SeasonDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SeriesSelectedPanel {
    JPanel seriesSelectedPanel=new JPanel();
    private final JTextArea displayArea=new JTextArea();
    private final JButton edit=new JButton("Edit movie");
    private final JButton delete=new JButton("Delete movie");
    private final JButton addReview=new JButton("Add review");
    private final JButton addToFavorites= new JButton("Add to favorites");
    public SeriesSelectedPanel(AddSeries selectedSeries){
        setMovieSelectedPanel(selectedSeries);
    }
    private void setMovieSelectedPanel(AddSeries selectedSeries){
        seriesSelectedPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        seriesSelectedPanel.setPreferredSize(new Dimension(360,375));
        seriesSelectedPanel.setBackground(Color.white);
        seriesSelectedPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        setTextArea(selectedSeries);
        seriesSelectedPanel.add(displayArea);
        setButtons();
    }
    private void setTextArea(AddSeries selectedSeries){
        StringBuilder seasonsInfo = new StringBuilder();
        for (SeasonDetails seasons:selectedSeries.getSeasonDetails()) {
            seasonsInfo.append("Season: ").append(seasons.getSeason()).append("\n");
            seasonsInfo.append("Season released year: ").append(seasons.getYear()).append("\n");
            for (int i=0;i<seasons.getLengths().length;i++){
                seasonsInfo.append("\t").append("Episode: ").append(i+1).append("\t").append("Length: ").append(seasons.getLengths()[i]).append("\n");
            }

        }
        String seriesInfo = "Title: " + selectedSeries.getTitle() + "\n" +
                "Description: " + selectedSeries.getDescription() + "\n" +
                "Content Rating: " + selectedSeries.getContentRating() + "\n" +
                "Category: " + selectedSeries.getCategory() + "\n" +
                "Actors: " + selectedSeries.getActors() + "\n"+seasonsInfo;

        displayArea.setPreferredSize(new Dimension(358,335));
        displayArea.setBorder(new EmptyBorder(0,5,0,0));
        displayArea.setText(seriesInfo);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
    }
    private void setButtons(){
        LoginFrame youAre=new LoginFrame();
        if(youAre.getAdmin()){
            seriesSelectedPanel.add(edit);
            seriesSelectedPanel.add(delete);
        }else{
            seriesSelectedPanel.add(addReview);
            seriesSelectedPanel.add(addToFavorites);

        }
    }
}
