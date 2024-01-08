package api;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SeasonDetails implements Serializable {
    private final static ArrayList<SeasonDetails> savedDetails=new ArrayList<>();
    private int season;
    private int year;
    private int [] lengths;
    public SeasonDetails() throws IOException {
    }
    protected SeasonDetails(int season, int year, int [] lengths) {
        this.season=season;
        this.year=year;
        this.lengths=lengths;
    }
    public void addSeason(int season,int year, String length) throws IOException {
        setSeason(season);
        setDate(year);
        setLengths(length);
        savedDetails.add(new SeasonDetails(getSeason(),getYear(),getLengths()));
        System.out.println(savedDetails);
    }
    protected ArrayList<SeasonDetails> getSeasons(){
        return savedDetails;
    }
    @Override
    public String toString(){
        return "Season:"+getSeason()+"\nDate:"+getYear()+"\nEpisode Lengths:"+ Arrays.toString(lengths)+"\n";
    }
    private void setSeason(int season){
        this.season=season;
    }
    private void setDate(int year){
        this.year=year;
    }
    private void setLengths(String lengths){
        String[] lengthArray=lengths.split(",");
        this.lengths = new int[lengthArray.length];
        for (int i=0; i<lengthArray.length;i++) {
            this.lengths[i] =Integer.parseInt(lengthArray[i].trim());
        }
    }
    public int getSeason(){
        return season;
    }
    public int getYear(){
        return year;
    }
    public int[] getLengths(){
        return lengths;
    }
}
