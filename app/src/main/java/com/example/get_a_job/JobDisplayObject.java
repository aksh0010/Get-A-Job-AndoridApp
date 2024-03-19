package com.example.get_a_job;



public class JobDisplayObject {

//    public String text;
    public String job_name;
    public String job_company;
    public String job_location;
    public String job_applied_date;

//    public  int image;
//    public String button;

//    public JobDisplayObject(String text, int image, String btn_text){
//
//        this.image=image;
//        this.text= text;
//        this.button = btn_text;
//
//    }
    public JobDisplayObject(String name, String company, String location,String date){
    this.job_name=name;
    this.job_location=location;
    this.job_company=company;
    this.job_applied_date = date;

    }

    public String getJob_applied_date() {
        return job_applied_date;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getJob_name() {
        return job_name;
    }

    public String getJob_company() {
        return job_company;
    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public String getText() {
//        return text;
//    }
//    public String getButton(){return  button;}
}
