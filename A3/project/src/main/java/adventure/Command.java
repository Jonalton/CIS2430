package adventure;



/* TODO add a static data structure or another enum class
that lists all the valid commands.  Then add methods for validating
commands 

You may add other methods to this class if you wish*/

public class Command implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 7562854493540136762L;
    private String action;
    private String noun;
    private String[] commands = {"go", "look", "take", "quit", "inventory", "eat", "wear", "toss", "read"};

  /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }



  /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command The first word of the command. 
     * 
     */
    public Command(String command) throws InvalidCommandException{
        /*Boolean state;
        state = false;
        command = command.toLowerCase();*/
        //TODO validate the action word here and throw an exception if it isn't
        /*for(int i = 0; i < commands.length; i++){
            if (commands[i].equals(command)){
                state = true;
            }
        }*/
        // a single-word action
        this(command, null);
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        //TODO validate the command here and ensure that the noun provided
        // is a legitimate second word for the command
        // throw an exception if not
        if (validate(command)){
            this.action = command;
            this.noun = what;
        } else{
            throw new InvalidCommandException();
        }

    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }



    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     * Lists commands
     * @return all commands
     */
    public String[] listCommands(){
        System.out.println("List");
        return commands;
    }

    /**
     * Checks if user inputted proper command 
     * @param command
     * @return true if command is valid
     */
    public boolean validate(String command){
        boolean state = false;
        for(int i = 0; i < commands.length; i++){
            if (commands[i].equals(command)){
                state = true;
            }
        }
        return state;
    }

    /**
     * prints the class
     * eturn all the commands
     
    public String toString(){
        return commands[0] + " " + commands[1] + " " + 
        commands[2] + " " + commands[3] + " " + commands[4] + " " + commands[5];
    }*/
    
}
