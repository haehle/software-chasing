import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    
    private String username;
    private String name;
    private int[] location;
    private int hp;
    private int speed;
    private int stamina;
    private String[] skills;
    private String[] abilities;

    public Player(String username, String name, int type){
        this.username = username;
        this.name = name;
        this.location = new int[]{0,0};
        if(type == 1){
            this.hp = 100;
            this.speed = 15;
            this.stamina = 20;
            this.skills = new String[]{};
            this.abilities = new String[]{};
        } else if(type == 2){
            this.hp = 60;
            this.speed = 25;
            this.stamina = 30;
            this.skills = new String[]{};
            this.abilities = new String[]{};
        } else if(type == 3){
            this.hp = 200;
            this.speed = 5;
            this.stamina = 5;
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


}
