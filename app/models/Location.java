package models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Location
{
    private String ip;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String regionName;
    private String city;
    private String zipCode;
    private String timeZone;
    private String latitude;
    private String longitude;
    private String metroCode;

    public String getIp()
    {
        return ip;
    }

    @JsonSetter("ip")
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    @JsonSetter("country_code")
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountryName()
    {
        return countryName;
    }

    @JsonSetter("country_name")
    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    public String getRegionCode()
    {
        return regionCode;
    }

    @JsonSetter("region_code")
    public void setRegionCode(String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getRegionName()
    {
        return regionName;
    }

    @JsonSetter("region_name")
    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public String getCity()
    {
        return city;
    }

    @JsonSetter("city")
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    @JsonSetter("zip_code")
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    @JsonSetter("time_zone")
    public void setTimeZone(String timeZone)
    {
        this.timeZone = timeZone;
    }

    public String getLatitude()
    {
        return latitude;
    }

    @JsonSetter("latitude")
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    @JsonSetter("longitude")
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getMetroCode()
    {
        return metroCode;
    }

    @JsonSetter("metro_code")
    public void setMetroCode(String metroCode)
    {
        this.metroCode = metroCode;
    }

    @JsonAnySetter()
    public void extraValue(String value)
    {
        System.out.println("value: " + value);
    }
}