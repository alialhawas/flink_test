import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple;
//import org.apache.flink.avro.AvroOutputFormat;
import org.apache.flink.core.fs.Path;

import java.io.File;

public class csvToAvro {

    public static void main(String[] args) throws Exception {


        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();


        DataSource<Tuple> csvInput = env.readCsvFile("./")
                .types(/* specify types here, e.g., String.class, Integer.class, etc. */);


        String avroSchema = "{\"type\":\"record\",\"name\":\"MyRecord\",\"fields\":[{\"name\":\"field1\",\"type\":\"string\"}]";


//        AvroOutputFormat<Tuple> avroOutputFormat = new AvroOutputFormat<>(new Path("path/to/your/output.avro"), Tuple.class);
//        avroOutputFormat.setSchema(avroSchema);


//        csvInput.write(avroOutputFormat, "path/to/your/output.avro");


        env.execute("CSV to Avro Conversion");
    }
}
