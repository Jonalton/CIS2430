package adventure;

public class Food extends Item implements Edible{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String eat;

    public Food(Item item){
        super(item);
    }

    public Food(){
        super();
    }
    
    public String eat() {
        eat = "That was filling";
        return eat;
    }
}

