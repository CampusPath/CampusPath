package campuspath.app.serialize;

import campuspath.util.Coordinate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Brady
 */
public final class CoordinateSerializer extends JsonSerializer<Coordinate> {

    @Override
    public void serialize(Coordinate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        var pos = new double[]{value.getLatitude(), value.getLongitude()};
        gen.writeArray(pos, 0, pos.length);
    }
}
