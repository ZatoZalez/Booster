package me.zato.booster.config;

public class Data {

    private String key;
    private String value;
    private String description;
    private boolean isdescription;

    public Data(String key, String value){
        this.key = key;
        this.value = value;
    }

    public Data(String description){
        isdescription = true;
        this.description = description;
    }

    public boolean isValid(){
        if(key == null || value == null)
            return false;
        else if(key.length() > 0 && value.length() > 0)
            return true;
        return false;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return getString();
    }

    public String getInline(){
        if(!isDescription())
            return (getKey() + ": " + getValue());
        if(!getDescription().equals(""))
            return ("# " + getDescription());
        return ("");
    }

    public String getString(){
        return value;
    }

    public boolean getBoolean(){
        return Boolean.parseBoolean(value);
    }

    public int getInt(){
        return Integer.parseInt(value);
    }

    public double getDouble(){
        return Double.parseDouble(value);
    }

    public void setValue(Object value){
        this.value = value.toString();
    }

    public String getDescription(){
        return description;
    }

    public boolean isDescription(){
        return isdescription;
    }
}