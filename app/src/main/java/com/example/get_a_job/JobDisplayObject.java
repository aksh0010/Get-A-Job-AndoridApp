package com.example.get_a_job;



public class JobDisplayObject {

//    public String text;
    public String job_name;
    public String job_company;
    public String job_location;
    public String job_applied_date;
    public String is_applied;

    public String job_id;

//    public  int image;
//    public String button;

//    public JobDisplayObject(String text, int image, String btn_text){
//
//        this.image=image;
//        this.text= text;
//        this.button = btn_text;
//
//    }
    public JobDisplayObject(String name, String company, String location,String date,String id,String is_applied){
    this.job_name=name;
    this.job_location=location;
    this.job_company=company;
    this.job_applied_date = date;
    this.job_id = id;
    this.is_applied = is_applied;
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

    public String getJob_id() {
        return job_id;
    }

    public String getIs_applied() {
        return is_applied;
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
