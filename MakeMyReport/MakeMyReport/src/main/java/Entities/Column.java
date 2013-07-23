package Entities;

/**
 * Created by Kristina on 23.07.13.
 */
public class Column {

    private String mName ;
    private String mType;

    public Column(String name, String type){
        mName = name;
        mType = type;
    }

    public String Name(){
        return mName;
    }

    public String Type(){
        return mType;
    }

    public String Create(){
        return mName + " " + mType;
    }


}
