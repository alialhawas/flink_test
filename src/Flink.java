import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/*
steps :
1- create a flink process with addional rows ()
2- create a kafka topic  ()
3- create a pipeline with CH ()
4- add MV for movie_appearance  ()
*/

public class Flink {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        List<MovieTitle> Titles = Files.lines(Paths.get("../data/csv/netflix_titles.csv"))
                .map(line -> line.split(","))
                .map(fields -> new MovieTitle(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                        Integer.parseInt(fields[6].trim()), fields[7]
                        ,fields[8], fields[9]))
                .collect(Collectors.toList());

        List<MovieRating> Rating = Files.lines(Paths.get("../data/csv/imdb_rating.csv"))
                .map(line -> line.split(","))
                .map(fields -> new MovieRating(fields[0], fields[1],  Float.parseFloat(fields[2])))
                .toList();

        DataStream<MovieTitle> MovieTitleStream = env.fromCollection(Titles);
        DataStream<MovieRating> movieRatingStream = env.fromCollection(Rating);


        DataStream<MovieInfo> joinedStream = MovieTitleStream
                .join(movieRatingStream)
                .where(MovieTitle::getShow_id)
                .equalTo(MovieRating::getShow_id)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .apply((MovieTitle movieTitle, MovieRating rating) ->
                        new MovieInfo(movieTitle, rating.getImdb_rating()));

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");

        FlinkKafkaProducer<MovieInfo> myProducer = new FlinkKafkaProducer<>(
                "Movie-Info",
                new MySerializationSchema(),
                properties,
                FlinkKafkaProducer.Semantic.EXACTLY_ONCE);


    }
}



class MovieInfo {
    private MovieTitle movieTile;
    private float imdbRating;

    public MovieInfo( MovieTitle movieTile, float imdbRating) {
        this.movieTile = movieTile;
        this.imdbRating = imdbRating;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public MovieTitle getmovieTile() {
        return movieTile;
    }

    public void setmovieTile(MovieTitle movieTile) {
        this.movieTile = movieTile;
    }
}


class MovieInfoSerializationSchema implements SerializationSchema<MovieInfo> {
    private transient ObjectMapper objectMapper;

    public MovieInfoSerializationSchema() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(MovieInfo movie) {
        try {
            return objectMapper.writeValueAsBytes(movie);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MovieTitle", e);
        }
    }
}


