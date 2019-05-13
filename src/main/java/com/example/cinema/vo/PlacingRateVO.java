package com.example.cinema.vo;

/**
 * @author tianduyingcai
 */
public class PlacingRateVO {
    private int movieId;
    private float placingRate;
    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int movieId){
        this.movieId=movieId;
    }

    public float getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(float placingRate) {
        this.placingRate = placingRate;
    }

    public PlacingRateVO(int movieId,float placingRate){
        this.movieId=movieId;this.placingRate=placingRate;
    }
}
