package com.likelion.cheg;

import org.springframework.boot.SpringApplication;

import java.util.Random;

public class Testcode {
    public static void main(String[] args)  {
        Random random = new Random();
        String number = "23_";
        for(int i=0;i<7;i++){
            number += Integer.toString(random.nextInt(9));
        }
        System.out.println(number);
    }
}
