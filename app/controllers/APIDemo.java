package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.JobSearch;
import models.Location;
import models.PlaceHolder;
import models.RandomUser;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;



import java.net.HttpURLConnection;
import java.net.URL;

public class APIDemo extends Controller
{
    public Result getDemo()
    {

        JsonNode jsonNode = null;
        // Connect to the URL using java's native library
        try
        {
            //The web service I am calling
            String myURL = "http://freegeoip.net/json/";
            //String myURL = "http://service.dice.com/api/rest/jobsearch/v1/simple.json?text=java&city=72118";
            //String myURL = "invalid url"; //just a string
            //Turn the string into a URL
            URL url = new URL(myURL);

            //Get the request setup
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            //Make the actual call to the web service
            request.connect();

            //We need to take the data coming back and pout it into
            //some data structure well can deal with
            //Use a generic tree structure here
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readValue(url, JsonNode.class);
            String zipCode = jsonNode.get("zip_code").asText();

            System.out.println(jsonNode);
        }
        catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        if (jsonNode == null)
        {
            Logger.warn("oh no! got nothing back from url");
        }

        return ok(jsonNode);
    }

    public Result getDemoPojo()
    {

        Location location = null;
        // Connect to the URL using java's native library
        try
        {
            //The web service I am calling
            String myURL = "http://freegeoip.net/json/";
            //Turn the string into a URL
            URL url = new URL(myURL);

            //Get the request setup
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            //Make the actual call to the web service
            request.connect();

            //We need to take the data coming back and put it into
            //some data structure we can deal with
            //Use a generic tree structure here
            ObjectMapper objectMapper = new ObjectMapper();
            location = objectMapper.readerFor(Location.class).readValue(url);
        }
        catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.location.render(location));
    }

    public Result getDemoPojoTree()
    {

        JobSearch jobSearch = null;
        // Connect to the URL using java's native library
        try
        {
            //The web service I am calling
            String myURL = "http://service.dice.com/api/rest/jobsearch/v1/simple.json?text=java&city=72118";
            //String myURL = "invalid url"; //just a string
            //Turn the string into a URL
            URL url = new URL(myURL);

            //Get the request setup
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            //Make the actual call to the web service
            request.connect();

            //We need to take the data coming back and put it into
            //some data structure we can deal with
            //Use a generic tree structure here
            ObjectMapper objectMapper = new ObjectMapper();
            jobSearch = objectMapper.readerFor(JobSearch.class).readValue(url);
        }
        catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.jobsearch.render(jobSearch));
    }
    public Result getPlaceHolder()
    {

        PlaceHolder placeHolder = null;
        try
        {
            String myURL = "http://jsonplaceholder.typicode.com/posts/1";

            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();


            ObjectMapper objectMapper = new ObjectMapper();
            placeHolder = objectMapper.readerFor(PlaceHolder.class).readValue(url);
        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.placeHolder.render(placeHolder));
    }
    public Result getRandomUser()
    {

        RandomUser randomUser = null;
        try
        {
            String myURL = "https://randomuser.me/api";

            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();


            ObjectMapper objectMapper = new ObjectMapper();
            randomUser = objectMapper.readerFor(RandomUser.class).readValue(url);
        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.randomUser.render(randomUser));
    }
}