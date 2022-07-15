package adventure;

public class Clothing extends Item implements Wearable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    private String wear;

    public Clothing(Item item){
        super(item);
    }

    public Clothing(){
        super();
    }

    public String wear() {
        wear = "Your fashion sense could use some work...";
        return wear;
    }
}

