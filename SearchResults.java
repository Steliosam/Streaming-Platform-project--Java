package api;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class SearchResults extends AddSeries implements Serializable {
    protected String type;
    protected String age;

    public SearchResults() throws IOException {
    }
    @SuppressWarnings("unchecked")
    public ArrayList<String> displayData(String title,boolean itsMovie,boolean itsSeries,String actor,boolean adult,boolean nonAdult, String category, int rating) {
         int fields=0;
        if (!title.equals("Enter the title here")) {
            fields += 1;
        }
        if (itsMovie | itsSeries) {
            fields += 1;
        } else {
            type = "";
        }
        if (!actor.equals("Enter a name")) {
            fields += 1;
        }
        if (adult | nonAdult) {
            if (adult) {
                age = "18+";
            } else {
                age = "18-";
            }
            fields += 1;
        } else {
            age = "";
        }
        if (!category.equals("-")) {
            fields += 1;
        }
        if (rating != 0) {
            fields += 1;
        }


        try (ObjectInputStream movieInput = new ObjectInputStream(new FileInputStream("MOVIES.txt"))) {
            this.movies.addAll((ArrayList<AddMovie>) movieInput.readObject());
        } catch (IOException | ClassNotFoundException e) {/* end of file exception*/}
        try (ObjectInputStream seriesInput = new ObjectInputStream(new FileInputStream("SERIES.txt"))) {
            this.series.addAll((ArrayList<AddSeries>) seriesInput.readObject());
        } catch (IOException | ClassNotFoundException e) {/*end of file exception*/}

        ArrayList<String> data = new ArrayList<>();


        if (fields == 0) {
            for (AddMovie movie : movies) {
                data.add("Title: " + movie.getTitle() + "---Type: Movie");
            }
            for (AddSeries seriesOne : series) {
                data.add("Title: " + seriesOne.getTitle() + "---Type: Series");
            }
        } else {
            if (itsMovie) {
                for (AddMovie movie : movies) {
                    int fieldsCompleted = 1;
                    if (movie.getTitle().equals(title)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getActors().contains(actor)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getContentRating().equals(age)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getCategory().equals(category)) {
                        fieldsCompleted += 1;
                    }
                    if (rating == 5) {
                        fieldsCompleted += 1;
                    }
                    if (fieldsCompleted == fields) {
                        data.add("Title: " + movie.getTitle() + "---Type: Movie");
                    }
                }
            } else if (itsSeries) {
                for (AddSeries series : series) {
                    int fieldsCompleted = 1;
                    if (series.getTitle().equals(title)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getActors().contains(actor)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getContentRating().equals(age)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getCategory().equals(category)) {
                        fieldsCompleted += 1;
                    }
                    if (rating == 5) {
                        fieldsCompleted += 1;
                    }
                    if (fieldsCompleted == fields) {
                        data.add("Title: " + series.getTitle() + "---Type: Series");
                    }
                }
            } else {
                for (AddMovie movie : movies) {
                    int fieldsCompleted = 0;
                    if (movie.getTitle().equals(title)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getActors().contains(actor)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getContentRating().equals(age)) {
                        fieldsCompleted += 1;
                    }
                    if (movie.getCategory().equals(category)) {
                        fieldsCompleted += 1;
                    }
                    if (rating == 5) {
                        fieldsCompleted += 1;
                    }
                    if (fieldsCompleted == fields) {
                        data.add("Title: " + movie.getTitle() + "---Type: Movie");
                    }
                }

                for (AddSeries series : series) {
                    int fieldsCompleted = 0;
                    if (series.getTitle().equals(title)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getActors().contains(actor)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getContentRating().equals(age)) {
                        fieldsCompleted += 1;
                    }
                    if (series.getCategory().equals(category)) {
                        fieldsCompleted += 1;
                    }
                    if (rating == 5) {
                        fieldsCompleted += 1;
                    }
                    if (fieldsCompleted == fields) {
                        data.add("Title: " + series.getTitle() + "---Type: Series");
                    }
                }
            }
        }

        this.movies.clear();
        this.series.clear();

        if (data.isEmpty()){
            data.add("NO DATA FOUND");
            return data;
        }
        Collections.shuffle(data);
        return data;
    }
}

