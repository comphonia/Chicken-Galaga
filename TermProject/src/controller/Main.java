package controller;

import controller.observer.UFOObserverAddNew;
import model.*;
import model.ufo.UFO;
import view.MyWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static  PlayerInputEventQueue playerInputEventQueue;
    public static boolean running = false;

    public static int INDEX_MOUSE_POINTER = 0; // in gameData.fixedObject
    public static int INDEX_SHOOTER = 1;

    public static int FPS = 20; // frames per second

    public static void main(String[] args) {
        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();
        initGame();
        gameLoop();
    }

    static void startScreen(){
        //show initial message on canvas
        Font font = new Font("Courier New",Font.BOLD,40);
        gameData.friendObject.add(new Text("Press Start Button",100,200, Color.YELLOW,font));
        while(!running){
            Main.win.canvas.render();
            try {
               Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //finish when running == true
    }

    static void initGame(){
        gameData.clear();
        gameData.fixedObject.add(new MousePointer(0,0));
        int x = Main.win.getWidth() / 2;
        int y = Main.win.getHeight() - 100;
        gameData.fixedObject.add(new Shooter(x,y));

        addUFOwithListener(100,100);
    }

    public static void addUFOwithListener(int x, int y) {
        var ufo = new UFO(x,y);
        ufo.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo);
    }

    private static void gameLoop() {
        running = true;
        // game loop
        while (running) {
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
            processCollisions();
            gameData.update();
            win.canvas.render();
            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            long sleepTIme =  (long)(1000.0 / FPS - timeSpent);
            try {
                if(sleepTIme > 0)Thread.sleep(sleepTIme);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static void processCollisions() {
        var shooter = (Shooter)Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        for(var enemy: Main.gameData.enemyObject){
            if(shooter.colllideWith(enemy)){
                ++shooter.hitCount;
                ++enemy.hitCount;
            }
        }
        for(var friend: Main.gameData.friendObject){
            for(var enemy: Main.gameData.enemyObject){
                if(friend.colllideWith(enemy)){
                    ++friend.hitCount;
                    ++enemy.hitCount;
                }
            }
        }
    }
}
