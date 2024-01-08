package api;

import java.io.*;
import java.util.ArrayList;

public class ShowDetails extends AddSeries {

    public ShowDetails() throws IOException {
    }

    public AddMovie showMovieDetails(String titleAndType) throws IOException, ClassNotFoundException {
        String[] splitBoth=titleAndType.split("---");
        String[] splitTitle=splitBoth[0].split(":");
        String title=splitTitle[1].trim();
        AddMovie movieSelected=new AddMovie();
        try (ObjectInputStream moviesData=new ObjectInputStream(new FileInputStream("MOVIES.txt"))){
            @SuppressWarnings("unchecked")
            ArrayList<AddMovie> movies= (ArrayList<AddMovie>) moviesData.readObject();
            for (AddMovie movie:movies) {
                if (title.equals(movie.getTitle())){
                        movieSelected=movie;
                }
            }
        }

        return movieSelected;
    }
    public AddSeries showSeriesDetails(String titleAndType) throws IOException, ClassNotFoundException {
        String[] splitBoth=titleAndType.split("---");
        String[] splitTitle=splitBoth[0].split(":");
        String title=splitTitle[1].trim();
        AddSeries seriesSelected=new AddSeries();
        try (ObjectInputStream seriesData=new ObjectInputStream(new FileInputStream("SERIES.txt"))){
            @SuppressWarnings("unchecked")
            ArrayList<AddSeries> series= (ArrayList<AddSeries>) seriesData.readObject();
            for (AddSeries seriesOne:series) {
                if (title.equals(seriesOne.getTitle())){
                    seriesSelected=seriesOne;
                }
            }
        }
        return seriesSelected;
    }


}

