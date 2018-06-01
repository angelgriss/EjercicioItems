package model;

public class Pictures {

    private String source;

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    @Override
    public String toString()
    {
        return this.source;
    }
}
