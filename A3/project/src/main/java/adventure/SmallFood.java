package adventure;

public class SmallFood extends Food implements Tossable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String toss;

    public SmallFood(Item item){
        super(item);
    }

    public String toss(){
        toss = "You just threw something away";
        return toss;
    }
}
