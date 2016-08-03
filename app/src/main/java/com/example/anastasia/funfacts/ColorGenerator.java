package com.example.anastasia.funfacts;

/**
 * Created by Anastasia on 8/2/2016.
 */
import android.graphics.Color;
import java.util.Random;
public class ColorGenerator {


    public int getColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return  color;
    }
}
