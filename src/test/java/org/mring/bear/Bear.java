package org.mring.bear;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bear {

    @JsonProperty("bear_id")
    private Integer bear_id;

    @JsonProperty("bear_type")
    private String bear_type;

    @JsonProperty("bear_name")
    private String bear_name;

    @JsonProperty("bear_age")
    private String bear_age;

    public Integer getBear_id() {
        return bear_id;
    }

    public void setBear_id(Integer bear_id) {
        this.bear_id = bear_id;
    }

    public String getBear_type() {
        return bear_type;
    }

    public void setBear_type(String bear_type) {
        this.bear_type = bear_type;
    }

    public String getBear_name() {
        return bear_name;
    }

    public void setBear_name(String bear_name) {
        this.bear_name = bear_name;
    }

    public String getBear_age() {
        return bear_age;
    }

    public void setBear_age(String bear_age) {
        this.bear_age = bear_age;
    }

}
