package adventure;

public class BrandedClothing extends Clothing implements Readable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String brandedClothing;

    public BrandedClothing(Item item){
        super(item);
    }

    public String read() {
        brandedClothing = "This is very expensive clothing...";
        return brandedClothing;
    }
}

