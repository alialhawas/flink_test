import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;


/*
steps :
1- create a flink process with addional rows ()
2- create a kafka topic  ()
3- create a pipeline with CH ()
4- add MV for movie_appearance  ()
*/

public class flink{
        public static void main(String[] args) throws Exception {

            final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

//            Path filePath = new Path("../data/netflix_titles.csv");

            DataSet<NetflixTitle> netflixTitles = env.readCsvFile("../data/netflix_titles.csv")
                    .pojoType(NetflixTitle.class,
                            "show_id", "type", "title", "director" , "cast" , "country", "date_added",
                            "duration", "listed_in", "description");

            DataSet<NetflixTitle> processedProducts = netflixTitles.map(new MapFunction<NetflixTitle, Netflixtitle>() {
                public NetflixTitle map(NetflixTitle movie) throws Exception {
                    double movie_appearance = movie.getShow_id();
                    movie.setTotalCost(totalCost);
                    return movie;
                }
            });

            env.execute("CSV Reading Example");
        }
}

