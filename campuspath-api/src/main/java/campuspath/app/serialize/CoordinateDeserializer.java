package campuspath.app.serialize;

import campuspath.util.Coordinate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Brady
 */
public final class CoordinateDeserializer extends JsonDeserializer<Coordinate> {

    @Override
    public Coordinate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        double[] buf = p.readValueAs(double[].class);
        if (buf.length != 2) {
            // TODO: Is there a better-fit error provided by Jackson?
            throw new IllegalArgumentException("Coordinate array length was not equal to 2");
        }
        return new Coordinate(buf[0], buf[1]);
    }
}
