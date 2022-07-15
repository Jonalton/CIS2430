package adventure;
/** Parses user input
 *  @author Jonalton Jude Hamilton
 */

public class Parser implements java.io.Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 5484243884672671555L;
    /* Private member variables */
    private Command theCommand;
    private String[] commands;
    private String listOfCommands;
    //private String commands;

    /**
     * Initializes command object
     */
    public Parser(){
        theCommand = null;
    }

    /**
     * Parses user input into verb and noun
     * @param userCommand
     * @return object of Command type
     * @throws InvalidCommandException
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException{
        String[] input;
        String verb;
        String noun;
        userCommand = userCommand.toLowerCase();
        input = userCommand.split(" ",2);
        
        if(input.length == 1){
            verb = input[0];
            Command oneCommand = new Command(verb);
            return oneCommand;
        } else if(input.length == 2){
            verb = input[0];
            noun = input[1];
            Command twoCommand = new Command(verb,noun);
            return twoCommand;
        } else{
            throw new InvalidCommandException();
        }
    }

    /**
     * lists all commands
     * @return returns the list of all commands
     */
    public String allCommands(){
        commands = theCommand.listCommands();
        for (int i = 0; i < commands.length; i++){
            listOfCommands.concat(commands[0]);
            listOfCommands.concat(" ");
        }
        return listOfCommands;
        
    }

    /**
     * prints the class
     * @return temp
     */
    public String toString(){
        String temp = "This is the parser class";
        return temp;
    }
}

