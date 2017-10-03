package dominio.mi.restaurant.network.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dominio.mi.restaurant.models.RestaurantListModel;

/**
 * Created by Dumevi Cruces on 10/09/17.
 */

public class JsonObjectToArray implements JsonDeserializer<List<RestaurantListModel>>{
    @Override
    public List<RestaurantListModel> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<RestaurantListModel> vals = new ArrayList<RestaurantListModel>();
        if (json.isJsonArray()) {
            for (JsonElement e : json.getAsJsonArray()) {
                vals.add((RestaurantListModel) context.deserialize(e, RestaurantListModel.class));
            }
        } else if (json.isJsonObject()) {
            vals.add((RestaurantListModel) context.deserialize(json, RestaurantListModel.class));
        } else {
            throw new RuntimeException("Unexpected JSON type: " + json.getClass());
        }
        return vals;
    }
}
