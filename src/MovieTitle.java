
public class MovieTitle {

    private String show_id;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String  country;
    private Integer date_added;
    private String duration;
    private String listed_in;
    private String description;

    public MovieTitle(String type , String show_id , String title, String director , String cast, String country,
                      Integer date_added, String duration, String listed_in, String description) {
        this.type = type;
        this.show_id = show_id;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.duration= duration;
        this.listed_in = listed_in;
        this.description= description;
    }

    public String getListed_in() {
        return listed_in;
    }
    public String getShow_id() {
        return show_id;
    }

    public void setListed_in(String listed_in) {
        this.listed_in = listed_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDate_added() {
        return date_added;
    }

    public void setDate_added(Integer date_added) {
        this.date_added = date_added;
    }
}


