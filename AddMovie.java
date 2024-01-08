package api;

import java.io.*;
import java.util.ArrayList;

public class AddMovie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected ArrayList<AddMovie> movies=new ArrayList<>();
    protected String title;
    protected String description;
    protected String contentRating;
    private int releaseDate;
    private int length;
    protected String category;
    protected String actors;

    public AddMovie() throws IOException {

    }
    protected AddMovie(String title, String description, String contentRating, int releaseDate, int length, String category, String actors) {
        this.title=title;
        this.description=description;
        this.contentRating=contentRating;
        this.releaseDate=releaseDate;
        this.length=length;
        this.category=category;
        this.actors=actors;
    }

    public String toString(){
        return getTitle()+getDescription()+getContentRating()+getReleaseDate()+getLength()+getCategory()+getActors();
    }


    public void addMovieToFile(String title,String description,boolean contentRating,int year,int length,String category,String actors) throws IOException, ClassNotFoundException {

        setTitle(title);
        setDescription(description);
        setContentRating(contentRating);
        setReleaseDate(year);
        setLength(length);
        setCategory(category);
        setActors(actors);
        File movies=new File("MOVIES.txt");
        if (movies.exists()) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(movies))) {
                @SuppressWarnings("unchecked")
                ArrayList<AddMovie> movieData=(ArrayList<AddMovie>) input.readObject();
                this.movies.addAll(movieData);
            } catch (EOFException e) {
                // end of file exception
            }
            try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(movies))) {

                this.movies.add(new AddMovie(getTitle(), getDescription(), getContentRating(), getReleaseDate(), getLength(), getCategory(), getActors()));
                output.writeObject(this.movies);
                this.movies.clear();
            }
        }
        else {
            try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(movies))){
                this.movies.add(new AddMovie(getTitle(), getDescription(), getContentRating(), getReleaseDate(), getLength(), getCategory(), getActors()));
                output.writeObject(this.movies);
                this.movies.clear();
            }
        }
    }


    protected void setTitle(String title){
        this.title=title;
    }
    protected void setDescription(String description){
        this.description=description;
    }
    protected void setContentRating(boolean contentRating){
        if (contentRating) {
            this.contentRating = "18+";
        }
        else {
            this.contentRating="18-";
        }
    }
    private void setReleaseDate(int year){
        this.releaseDate=year;
    }
    private void setLength(int length){
        this.length=length;
    }
    protected void setCategory(String category){
        this.category=category;
    }
    protected void setActors(String actors){
        this.actors=actors;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getContentRating(){
        return contentRating;
    }
    public int getReleaseDate(){
        return releaseDate;
    }
    public int getLength(){
        return length;
    }
    public String getCategory(){
        return category;
    }
    public String getActors(){
        return actors;
    }
}
