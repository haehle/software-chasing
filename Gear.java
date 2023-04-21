public class Gear {
    private String gearName;
    private int hpBoost;
    private int speed;
    private int staminaBoost;

    public Gear(String gearName, int xpBoost, int staminaBoost, int speed){
        this.gearName = gearName;
        this.hpBoost = xpBoost;
        this.speed = speed;
        this.staminaBoost = staminaBoost;
    }


    public String getGearName(){
        return gearName;
    }

    public int getHpBoost(){
        return hpBoost;
    }

    public int getSpeedBoost(){
        return speed;
    }

    public int getStaminaBoost(){
        return staminaBoost;
    }
}
