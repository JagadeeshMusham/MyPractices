package com.musham.mySpringProject.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CricketResponse {
    @JsonProperty("list")
    private ArrayList<Team> teamsList;
    private AppIndex appIndex;
}


// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Getter
@Setter
class AppIndex {
    private String seoTitle;
    private String webURL;
}

@Getter
@Setter
class Team {
    private String teamName;
    private int teamId;
    private String teamSName;
    private int imageId;
    private String countryName;
}



