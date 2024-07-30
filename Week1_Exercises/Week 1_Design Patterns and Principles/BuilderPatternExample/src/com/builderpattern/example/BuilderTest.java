package com.builderpattern.example;

public class BuilderTest {
    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB")
                                   .build();
        
        Computer gamingComputer = new Computer.Builder("Intel i7", "16GB")
                                   .setGraphicsCard("NVIDIA RTX 3080")
                                   .setStorage("1TB SSD")
                                   .setPowerSupply("750W")
                                   .setCoolingSystem("Liquid Cooling")
                                   .build();

        System.out.println("Basic Computer: " + basicComputer);
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
