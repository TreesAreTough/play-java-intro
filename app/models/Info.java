package models;

public class Info
{
    private String results;

    private String page;

    private String seed;

    private String version;

    public String getResults ()
    {
        return results;
    }

    public void setResults (String results)
    {
        this.results = results;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getSeed ()
    {
        return seed;
    }

    public void setSeed (String seed)
    {
        this.seed = seed;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", page = "+page+", seed = "+seed+", version = "+version+"]";
    }
}