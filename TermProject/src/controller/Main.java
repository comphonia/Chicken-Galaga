package controller;

import controller.observer.BirdObserverAddNew;
import model.*;
import model.asteroid.Asteroid;
import model.bird.Bird;
import model.dropping.Dropping;
import model.poweup.Powerup;
import model.shooter.Shooter;
import view.MyWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static  PlayerInputEventQueue playerInputEventQueue;
    public static boolean running = false;
    public static int INDEX_MOUSE_POINTER = 0; // in gameData.fixedObject
    public static int INDEX_SHOOTER = 1;
    public static int playedTime = 0;
    private static int powerUpSpawnTimer = 0;

    public static int FPS = 30; // frames per second

    public static void main(String[] args) {
        openStartScreen();

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();
        initGame();
        gameLoop();
    }

    public static void endGame(){
        Font font = new Font("Courier New",Font.BOLD,40);
        gameData.friendObject.add(new Text("GAME OVER",100,200, Color.RED,font));
    }

    static void startScreen(){
        //show initial message on canvas
       // Font font = new Font("Courier New",Font.BOLD,40);
      //  gameData.friendObject.add(new Text("Press Start Button",100,200, Color.YELLOW,font));
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
      //  gameData.fixedObject.add(new MousePointer(0,0));
        spawnShooter();
        startWave("birds");
    }

    private static void startWave(String type) {
        // kill off enemies
        for(var enemy: Main.gameData.enemyObject){
            enemy.hitCount += 5000;
        }
        if(type.equals("asteroids")) {
            spawnAsteroid();
        }else if(type.equals("birds")){
            spawnBirds();
        }
    }

    private static void spawnBirds(){

        int j = 50;
        for (int i = 0; i < 6; i++) {
            addBirdWithListener(0,0,8 + j,50);
            j+=80;
        }
        j=50;
        for (int i = 0; i < 8; i++) {
            addBirdWithListener(0,0,20 + j,100);
            j+=80;
        }
    }

    private static void spawnAsteroid(){
        int j = 50;

        for (int i = 0; i < 5; i++) {
            var asteroid = new Asteroid(j,0,8 + j,50,40);
            gameData.enemyObject.add(asteroid);
            Asteroid.AVAILABLE_UNITS++;
            j+=80;
        }
    }

    public static void addBirdWithListener(int x, int y, int a, int b) {
        var bird = new Bird(x,y,a,b);
        var dropping = new Dropping(a,b);
        gameData.enemyObject.add(dropping);
        Bird.AVAILABLE_UNITS++;
        bird.attachListener(new BirdObserverAddNew());
        gameData.enemyObject.add(bird);
}

    private static void gameLoop() {
        running = true;
        // game loop
        while (running) {
            long startTime = System.currentTimeMillis();
            playedTime++;
            updatePlayedTimeLabel();

            powerUpSpawnTimer++;
            if(powerUpSpawnTimer >= 250){
                powerUpSpawnTimer = 0;
                spawnPowerUp();
            }

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

            if(Bird.AVAILABLE_UNITS <= 0 && Asteroid.AVAILABLE_UNITS <= 0){
                startWave("asteroids");
            }
        }
    }


    private static void processCollisions() {
        var shooter = (Shooter)Main.gameData.fixedObject.get(0);
        for(var enemy: Main.gameData.enemyObject){
            if(shooter.collideWith(enemy)){
                ++shooter.hitCount;
                ++enemy.hitCount;
            }
        }
        for(var friend: Main.gameData.friendObject){
            if(shooter.collideWith(friend) && friend.getClass().getName().equals("model.poweup.Powerup")){
                Powerup pu = (Powerup) friend;
                pu.hitCount += 500;
                shooter.doEffect(pu.effect);
            }
            for(var enemy: Main.gameData.enemyObject){
                if(friend.collideWith(enemy) && !friend.getClass().getName().equals("model.poweup.Powerup")){
                    ++friend.hitCount;
                    if(friend.hitCount == 1)
                    ++enemy.hitCount;
                }
            }
        }
    }

    private static void openStartScreen(){
        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    public static void updateScoreLabel(int playerScore) {
        Shooter.updateScore(playerScore);
        MyWindow.updateScoreLabel(Shooter.getPlayerScore());
    }
    private static void updatePlayedTimeLabel() {
        MyWindow.updatePlayedTimeLabel(playedTime);
    }
    public static void updatePlayerLifeLabel() {
        MyWindow.updatePlayerLifeLabel(Shooter.PLAYER_LIVES);
    }

    static Random rand = new Random();
    public static void spawnPowerUp(){
        int randNumber = rand.nextInt(2);
        if(randNumber == 0){
            gameData.friendObject.add(new Powerup(0));
        }else{
            gameData.friendObject.add(new Powerup(1));
        }
    }

    public static void spawnShooter(){
        if(Main.gameData.fixedObject.size() > 0)
         Main.gameData.fixedObject.remove(0);
        int x = Main.win.getWidth() / 2;
        int y = Main.win.getHeight() - 100;
        gameData.fixedObject.add(new Shooter(x,y));
    }
}


