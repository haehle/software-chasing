import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    
    private String username;
    private String name;
    private int[] location;
    private int hp;
    private int maxHP;
    private int speed;
    private int stamina;
    private int maxStamina;
    private int level;
    private int levelXP;
    private int initialLevelXP;
    private String[] skills;
    private String[] abilities;

    public Player(String username, String name, int type){
        this.username = username;
        this.name = name;
        this.location = new int[]{0,0};
        this.level = 1;
        this.levelXP = 100;
        this.initialLevelXP = 100;
        if(type == 1){
            this.hp = 100;
            this.maxHP = 100;
            this.speed = 15;
            this.stamina = 20;
            this.maxStamina = 20;
            this.skills = new String[]{};
            this.abilities = new String[]{};
        } else if(type == 2){
            this.hp = 60;
            this.maxHP = 100;
            this.speed = 25;
            this.stamina = 30;
            this.maxStamina = 30;
            this.skills = new String[]{};
            this.abilities = new String[]{};
        } else if(type == 3){
            this.hp = 200;
            this.maxHP = 200;
            this.speed = 5;
            this.stamina = 5;
            this.maxStamina = 5;
            this.skills = new String[]{};
            this.abilities = new String[]{};
        }
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getLocation() {
        return this.location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() { return this.maxHP; }

    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStamina() {
        return this.stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMaxStamina() {
        return this.maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public int getLevel() { return this.level; }

    public void setLevel(int level) { this.level = level; }

    public int getLevelXP() { return this.levelXP; }

    public void setLevelXP(int levelXP ) { this.levelXP = levelXP; }

    public int getInitialLevelXP() { return this.initialLevelXP; }

    public void setInitialLevelXP(int initialLevelXP ) { this.initialLevelXP = initialLevelXP; }


    public String[] getSkills() {
        return this.skills;
    }

    public void addNewSkills(String[] skills) {
        List<String> list = new ArrayList<String>(Arrays.asList(skills));
        list.addAll(Arrays.asList(this.skills));
        String[] updatedSkills = (String[]) list.toArray();
        this.skills = updatedSkills;
    }

    public String[] getAbilities() {
        return this.abilities;
    }

    public void setAbilities(String[] abilities) {
        List<String> list = new ArrayList<String>(Arrays.asList(abilities));
        list.addAll(Arrays.asList(this.abilities));
        String[] updatedAbilities = (String[]) list.toArray();
        this.abilities = updatedAbilities;
    }

    public void gainXP(int amt) {
        //Check for level up
        if (amt >= this.levelXP)
        {
            int xpOver = (amt - this.levelXP);
            levelUp(xpOver);
        }
        else
        {
            //Subtract xp gained from remaining levelXP
            setLevelXP(this.levelXP - amt);
        }
    }

    public void levelUp(int xpOver) {
        while (true) {
            setMaxHP((int) ((this.maxHP * 0.2) + this.maxHP));
            setSpeed((int) ((this.speed * 0.2) + this.speed));
            setMaxStamina((int) ((this.maxStamina * 0.2) + this.maxStamina));
            setLevel(this.level + 1);
            setInitialLevelXP((int)((this.initialLevelXP * 0.5) + this.initialLevelXP));

            //Check for more levels gained
            if (xpOver > this.initialLevelXP)
            {
                xpOver = xpOver - this.initialLevelXP;
                //Keep executing loop
            }
            else
            {
                break;
            }
        }

        //Remove xp amt from current xp needed to level up
        setLevelXP(this.initialLevelXP - xpOver);

        //Later on, will also have to check for new skills/abilities unlocked at each level
    }


}
