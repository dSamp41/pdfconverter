package com.example;

// mvn clean compile assembly:single
public class App 
{
    public static void main(String[] args){
        if(args.length < 1 ){
            System.out.println("Please provide a path");
            System.exit(-1);
        }

        Converter converter = new Converter();

        for(String path: args){
            converter.convert(path);
        }

        System.out.println("Conversion completed successfully!");
    }
}
