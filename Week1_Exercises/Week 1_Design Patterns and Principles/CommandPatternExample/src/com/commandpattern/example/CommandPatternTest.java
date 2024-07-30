package com.commandpattern.example;

public class CommandPatternTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(lightOn);
        remote.pressButton(); // Output: The light is on.

        remote.setCommand(lightOff);
        remote.pressButton(); // Output: The light is off.
    }
}