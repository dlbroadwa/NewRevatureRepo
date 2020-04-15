package models;

public class InstrumentModel
{
    private int id;
    private String instrumentName;
    private int used;
    private float price;

    public InstrumentModel() {};

    @Override
    public String toString()
    {
        return "InstrumentModel{"+
                "id=" + id +
                ", name='" + '\'' +
                "used=" + used +
                ",price=" + price+
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getInstrumentName()
    {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName)
    {
        this.instrumentName = instrumentName;
    }

    public int getUsed()
    {
        return used;
    }

    public void setUsed(int used)
    {
        this.used = used;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
}
