package adventure;

public class Spell extends Item implements Readable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String spell;

    public Spell(Item item){
        super(item);
    }

    public Spell(){
        super();
    }

    public String read() {
        spell = "It is written in an ancient language you can't understand";
        return spell;
    }
}

