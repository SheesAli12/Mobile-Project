package com.example.healthcare;

public class Patient {

    String name;

    String age;

    String illness;

    String time;

    String appointment_num;


    Patient()
    {
        name=null;
        age=null;
        illness=null;
        time=null;
        appointment_num=null;
    }
    Patient(String name, String e, String p,String t,String a)
    {
        this.name=name;
        this.age=e;
        this.illness=p;
        this.time=t;
        this.appointment_num=a;
    }

    public void setName(String n)
    {
        this.name=n;
    }

    public void setAge(String age)
    {
        this.age=age;
    }


    public void setIllness(String e)
    {
        this.illness=e;
    }

    public void setTime(String t)
    {
        this.time=t;
    }

    public void setAppointment_num(String a)
    {
        this.appointment_num=a;
    }


    public String getName()
    {
        return name;
    }

    public String getAge()
    {
        return  age;
    }

    public String getTime()
    {
        return  time;
    }

    public String getIllness()
    {
        return  illness;
    }

    public String getAppointment_num()
    {
        return appointment_num;
    }

}
