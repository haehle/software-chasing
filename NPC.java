import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NPC {

    private String name;
    private String type;
    private Color color;
    private String encounter;
    private int[] location;
    private int hp;
    private int maxHP;
    private int speed;
    private int stamina;
    private int maxStamina;
    private String[] skills;
    private ArrayList<String> abilities;


    public NPC(String name, String type) {
        this.type = type;
        this.name = name;
        this.location = new int[]{0, 0};
        switch (type) {
            case "Boss" -> {
                this.encounter = "You are about to encounter a boss";
                this.color = Color.RED;
                this.hp = 500;
                this.maxHP = 500;
                this.speed = 5;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
            case "Enemy" -> {
                this.encounter = "You are about to encounter an enemy";
                this.color = Color.ORANGE;
                this.hp = 60;
                this.maxHP = 100;
                this.speed = 5;
                this.stamina = 20;
                this.maxStamina = 20;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
            case "Neutral" -> {
                this.encounter = "Interact with [name]";
                this.color = Color.BLUE;
                this.hp = 200;
                this.maxHP = 200;
                this.speed = 5;
                this.stamina = 5;
                this.maxStamina = 5;
                this.skills = new String[]{};
                this.abilities = new ArrayList<>();
            }
        }
    }

    public String getName () {
        return this.name;
    }

    public void setType (String type){
        this.type = type;
    }

    public int[] getLocation () {
        return this.location;
    }

    public void setLocation ( int[] location){
        this.location = location;
    }

    public int getHp () {
        return this.hp;
    }

    public void setHp ( int hp){
        this.hp = hp;
    }

    public int getMaxHP () {
        return this.maxHP;
    }

    public void setMaxHP ( int maxHP){
        this.maxHP = maxHP;
    }

    public int getSpeed () {
        return this.speed;
    }

    public void setSpeed ( int speed){
        this.speed = speed;
    }

    public int getStamina () {
        return this.stamina;
    }

    public void setStamina ( int stamina){
        this.stamina = stamina;
    }

    public int getMaxStamina () {
        return this.maxStamina;
    }

    public void setMaxStamina ( int maxStamina){
        this.maxStamina = maxStamina;
    }



}
