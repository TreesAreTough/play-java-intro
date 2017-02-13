package models;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.List;

public class JobSearch
{
    private int count;
    private int firstDocument;
    private int lastDocument;
    public List<ResultItem> resultItemList = new ArrayList();

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getFirstDocument()
    {
        return firstDocument;
    }

    public void setFirstDocument(int firstDocument)
    {
        this.firstDocument = firstDocument;
    }

    public int getLastDocument()
    {
        return lastDocument;
    }

    public void setLastDocument(int lastDocument)
    {
        this.lastDocument = lastDocument;
    }

    @JsonAnySetter
    public void setAnyOther(String other)
    {
        System.out.println(other);
    }

    public List<ResultItem> getResultItems()
    {
        return resultItemList;
    }
}