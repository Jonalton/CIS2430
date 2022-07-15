package adventure;

public class Weapon extends Item implements Tossable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    private String weapon;

    public Weapon(Item item){
        super(item);
    }
    public Weapon(){
        super();
    }
    public String toss() {
        weapon = "You just dropped something";
        return weapon;
    }
}

