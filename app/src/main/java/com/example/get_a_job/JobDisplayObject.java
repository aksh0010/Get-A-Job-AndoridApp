package com.example.get_a_job;



public class JobDisplayObject {

    public String text;
    public  int image;
    public String button;

    public JobDisplayObject(String text, int image, String btn_text){

        this.image=image;
        this.text= text;
        this.button = btn_text;

    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
    public String getButton(){return  button;}
}
