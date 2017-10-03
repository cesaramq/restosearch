package dominio.mi.restaurant.models;

/**
 * Created by Dumevi Cruces on 15/07/17.
 */

public class RestaurantLocationModel {
    private String address;
    private String locality;
    private String city;
    private String longitude;
    private String latitude;

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
