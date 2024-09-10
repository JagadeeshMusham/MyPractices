package com.musham.mySpringProject.api.response;

//public class WeatherResponse {
//}

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//public class Root{
public class WeatherResponse {
    private Location location;
    private Current current;
}


// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

@Getter
@Setter
class Condition {
    private String text;
    private String icon;
    private int code;
}

@Getter
@Setter
class Current {
    @JsonProperty("last_updated_epoch")
    private int lastUpdatedEpoch;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("temp_f")
    private double tempF;

    @JsonProperty("is_day")
    private int isDay;

    private Condition condition;

    @JsonProperty("wind_mph")
    private double windMPH;

    @JsonProperty("wind_kph")
    private double windKPH;

    @JsonProperty("wind_degree")
    private int windDegree;

    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("pressure_mb")
    private double pressureMB;

    @JsonProperty("pressure_in")
    private double pressureIn;

    @JsonProperty("precip_mm")
    private double precipMM;

    @JsonProperty("precip_in")
    private double precipIn;

    private int humidity;
    private int cloud;

    @JsonProperty("feelslike_c")
    private double feelsLikeC;

    @JsonProperty("feelslike_f")
    private double feelsLike_F;

    @JsonProperty("windchill_c")
    private double windChillC;

    @JsonProperty("windchill_f")
    private double windChillF;

    @JsonProperty("heatindex_c")
    private double heatIndexC;

    @JsonProperty("heatindex_f")
    private double heatIndexF;

    @JsonProperty("dewpoint_c")
    private double dewPointC;

    @JsonProperty("dewpoint_f")
    private double dewPointF;

    @JsonProperty("vis_km")
    private double visKM;

    @JsonProperty("vis_miles")
    private double visMiles;

    private double uv;

    @JsonProperty("gust_mph")
    private double gustMPH;

    @JsonProperty("gust_kph")
    private double gustKPH;
}

@Getter
@Setter
class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;

    @JsonProperty("tz_id")
    private String tzId;

    @JsonProperty("localtime_epoch")
    private int localtimeEpoch;

    @JsonProperty("localtime")
    private String localTime;
}





