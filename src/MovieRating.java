public class MovieRating {
    private String show_id;
    private String streaming_company;
    private Float imdb_rating;


    public MovieRating(String show_id, String streaming_company, Float imdb_rating) {
        this.show_id = show_id;
        this.streaming_company = streaming_company;
        this.imdb_rating = imdb_rating;
    }

    public String getStreaming_company() {
        return streaming_company;
    }

    public void setStreaming_company(String streaming_company) {
        this.streaming_company = streaming_company;
    }

    public Float getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(Float imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }
}
