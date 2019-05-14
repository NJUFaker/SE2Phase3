package com.example.cinema.vo;

/**
 * @author tianduyingcai
 */
public class PlacingRateVO {
    private int movieId;
    private double placingRate;
    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int movieId){
        this.movieId=movieId;
    }

    public double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(double placingRate) {
        this.placingRate = placingRate;
    }

    public PlacingRateVO(int movieId,double placingRate){
        this.movieId=movieId;this.placingRate=placingRate;
    }
}
