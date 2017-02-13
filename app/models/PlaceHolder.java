package models;


import com.fasterxml.jackson.annotation.JsonSetter;

public class PlaceHolder
{
    private int id;
    private String title;
    private String body;
    private int userId;

    public int getId()
    {
        return id;
    }

    @JsonSetter("id")
    public void setid(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    @JsonSetter("userId")
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getTitle()
    {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getBody()
    {
        return body;
    }

    @JsonSetter("body")
    public void setBody(String body)
    {
        this.body = body;
    }


}
