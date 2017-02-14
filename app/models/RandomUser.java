package models;

import java.util.ArrayList;
import java.util.List;

public class RandomUser
{

    private Info info;

    public List<Results> getResults ()
    {
        return results;
    }

    public void setResults (List<Results> results)
    {
        this.results = results;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", info = "+info+"]";
    }

    public List<Results> results = new ArrayList();

}
			
			