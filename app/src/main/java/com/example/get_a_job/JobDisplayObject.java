package com.example.get_a_job;



public class JobDisplayObject {

//    public String text;
    public String job_id;
    public String job_title;
    public String job_company;
    public String job_location;
    public String job_salary;
    public String job_applied_date;
    public String job_description;

    // job_id,title,company,location,salary,date,description

    public JobDisplayObject(String id,String title, String company, String location,String salary,String date,String description){
    this.job_id=id;
    this.job_title =title;
    this.job_location=location;
    this.job_company=company;
    this.job_applied_date = date;
    this.job_salary=salary;
    this.job_description=description;
    }

    public String getJob_id() {
        return job_id;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public String getJob_description() {
        return job_description;
    }

    public String getJob_applied_date() {
        return job_applied_date;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getJob_company() {
        return job_company;
    }

}
