package com.vtiger.pages;

import com.github.javafaker.Faker;

public class HomePage {

    static Faker fkr;
    public static void G_data(){
        fkr=new Faker();
        System.out.println(fkr.name().firstName());
        System.out.println(fkr.address().cityName());
        System.out.println(fkr.animal().name().toUpperCase());
        System.out.println(fkr.number().randomNumber());
    }

    public static void main(String[] args) {
        G_data();
    }

}
