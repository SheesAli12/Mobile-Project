package com.example.healthcare;

public class User {

    String name;
    String email;
    String password;
    int type; //user admin or Patient

    User()
    {
        name=null;
        email=null;
        password=null;
        type=0;
    }
    User(String n, String e, String p,int t)
    {
        this.name=n;
        this.email=e;
        this.password=p;
        this.type=t;
    }

    public void setName(String n)
    {
        this.name=n;
    }

    public void setPassword(String p)
    {
        this.password=p;
    }


    public void setEmail(String e)
    {
        this.email=e;
    }

    public void setType(int t)
    {
        this.type=t;
    }


    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return  email;
    }

    public String getPassword()
    {
        return  password;
    }

    public int getType()
    {
        return  type;
    }

}
