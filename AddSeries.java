package api;

import java.io.*;
import java.util.ArrayList;

public class AddSeries extends AddMovie implements Serializable {
    protected ArrayList<AddSeries> series=new ArrayList<>();
    protected ArrayList<SeasonDetails> seasonsDetails=new ArrayList<>();
    public AddSeries() throws IOException {}
    protected AddSeries(String title, String description, String contentRating, String category, String actors,ArrayList<SeasonDetails> seasonsDetails) throws IOException {
        this.title=title;
        this.description=description;
        this.contentRating=contentRating;
        this.category=category;
        this.actors=actors;
        this.seasonsDetails=seasonsDetails;
    }

    public void addSeriesToFile(String title,String description,boolean contentRating,String category,String actors) throws IOException {
        setTitle(title);
        setDescription(description);
        setContentRating(contentRating);
        setCategory(category);
        setActors(actors);
        setSeasonsDetails();
        File series=new File("SERIES.txt");
        if (series.exists()) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(series))) {
                @SuppressWarnings("unchecked")
                ArrayList<AddSeries> movieData=(ArrayList<AddSeries>) input.readObject();
                this.series.addAll(movieData);
            } catch (EOFException | ClassNotFoundException e) {
                // end of file exception
            }
            try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(series))) {

                this.series.add(new AddSeries(getTitle(),getDescription(),getContentRating(),getCategory(),getActors(),getSeasonDetails()));
                output.writeObject(this.series);
                this.series.clear();
            }
        }
        else {
            try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(series))){
                this.series.add(new AddSeries(getTitle(),getDescription(),getContentRating(),getCategory(),getActors(),getSeasonDetails()));
                output.writeObject(this.series);
                this.series.clear();
            }
        }
    }
    protected void setSeasonsDetails() throws IOException {
        SeasonDetails savedDetails=new SeasonDetails();
        seasonsDetails.addAll(savedDetails.getSeasons());
    }
    public ArrayList<SeasonDetails> getSeasonDetails(){
        return seasonsDetails;
    }
}
