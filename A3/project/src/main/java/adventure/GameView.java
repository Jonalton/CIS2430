package adventure;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
//import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

public class GameView extends JFrame{

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    private Game myGame;
    private Player myPlayer;
    private TextArea text;
    private JList<String> inv;
    private DefaultListModel<String> data;
    private JLabel playerName;


    public GameView(Game theGame){
        super();
        myGame = theGame;
        data = new DefaultListModel<String>();
        myPlayer = myGame.getPlayer();
        text = new TextArea();
        text.setBackground(Color.WHITE);
        setSize();
        setContainer();
        this.setJMenuBar(setMenu());
        pack();
        
    }

    private JMenuBar setMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        menu.add(menuSave());
        menu.add(menuJSON());
        menu.add(menuLoadSave());
        menu.add(menuPlayerName());
        return menuBar;
    }

    private JMenuItem menuPlayerName(){
        JMenuItem name = new JMenuItem("Change Player name");
        name.addActionListener(ev->changeName());
        return name;
    }

    private void changeName(){
        String input = JOptionPane.showInputDialog("Enter new name:");
        myPlayer.setName(input);
        playerName.setText(input);
    }
    
    private JMenuItem menuSave(){
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(ev->saveGame());
        return saveGame;
    }
    private void saveGame(){
        myGame.saveGame(myGame,saveGameWindow());
    }

    private String saveGameWindow(){
        String input = JOptionPane.showInputDialog("Enter filename");
        return input;
    }

    private JMenuItem menuJSON(){
        JMenuItem loadJSON = new JMenuItem("Load JSON file");
        loadJSON.addActionListener(ev->loadJSON(loadJSON));
        return loadJSON;
    }

    private void loadJSON(JMenuItem parent){
        JFileChooser choose = new JFileChooser();
        int returnVal = choose.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = choose.getSelectedFile().getName();
            myGame = myGame.loadJSONfile(filename,myGame);
            setupNewGame();
        }
    }

    private JMenuItem menuLoadSave(){
        JMenuItem loadFile = new JMenuItem("Load Save File");
        loadFile.addActionListener(ev->loadSave(loadFile));
        return loadFile;
    }

    private void loadSave(JMenuItem parent){
        JFileChooser choose = new JFileChooser();
        int returnVal = choose.showOpenDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = choose.getSelectedFile().getName();
            myGame = myGame.load(filename);
            setupNewGame();
        }
    }

    private void setupNewGame(){
        text.setText("");
        myPlayer = myGame.getPlayer();
        playerName.setText(myPlayer.getName());
        Room room = myPlayer.getCurrentRoom();
        printRoomDesc(room);
        updateInventory();
    }

    private void setSize(){
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*FIXME */
    private void setContainer(){
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        contentPane.add(defaultButton(constraints),BorderLayout.LINE_START);
        contentPane.add(showPlayerName(constraints),BorderLayout.NORTH);
        contentPane.add(showOutput(constraints),BorderLayout.CENTER);
        contentPane.add(showInput(constraints),BorderLayout.SOUTH);
        contentPane.add(showInventory(constraints),BorderLayout.LINE_END);

    }

    private JPanel defaultButton(GridBagConstraints c){
        JPanel loadPanel = new JPanel();
        JButton defaultGame = new JButton("Load Default Adventure");
        changeConstraints(c, 0, 0);
        defaultGame.addActionListener(ev->loadDefaultGame());
        loadPanel.add(defaultGame,c);
        return loadPanel;
    }

    private void loadDefaultGame(){
        myGame = myGame.loadDefaultJSON("my_adventure.json",myGame);
        myPlayer= myGame.getPlayer();
        Room room = myPlayer.getCurrentRoom();
        printRoomDesc(room);
       // System.out.println(temPlayer.getCurrentRoom().getName());
    }

    private void changeConstraints(GridBagConstraints c,int x,int y){
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(10,10,10,10);
    }

    private JPanel showPlayerName(GridBagConstraints c){
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());
        displayPlayerName(namePanel, c);
        return namePanel;
    }
    /*FIXME*/
    private void displayPlayerName(JPanel panel,GridBagConstraints c){
       // JLabel playerName = new JLabel(myGame.myPlayer.getName());
        //return playerName;
        playerName = new JLabel("Player Name");
        changeConstraints(c,0,0);
        panel.add(playerName,c);
    }

    private void changeName(JTextField userInput, JLabel label){
        String name = userInput.getText();
        myGame.changePlayerName(name);
        label.setText(name);
        userInput.setText("");
    }

    private JPanel showOutput(GridBagConstraints c){
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridBagLayout());
        changeConstraints(c,0,0);
        outputPanel.add(startingOutput("Welcome to the Game!\n"),c);
        return outputPanel;
    }

    /*FIXME*/
    private TextArea startingOutput(String output){
        //TextField userOutput = new TextField(myGame.printRoomItem(myGame.myRoom));
        text.setText(output);
        return text;
    }

    public void setOutput(String output){
        text.append(output);
    }

    private JPanel showInput(GridBagConstraints c){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        changeConstraints(c,0,0);
        inputPanel.add(userInput(),c);
        return inputPanel;
    }

    private JTextField userInput(){
        JTextField input = new JTextField();
        input.setColumns(40);
        input.addActionListener(ev->parseInput(input));
        return input;
    }

    private void parseInput(JTextField input){
        String t = input.getText();
        input.setText("");
        text.append(String.format("%s\n",t));
        myGame = myGame.runGameGUI(myGame, t);
        myPlayer = myGame.getPlayer();
        Room room = myPlayer.getCurrentRoom();

        printOutput(room,t);
        
    }

    private void printOutput(Room room,String t){
        if(t.contains("go")){
            printRoomDesc(room);
        } else if(t.contains("look")){
            text.append(String.format("%s.\n",room.getLongDescription()));
        } else if (t.contains("inventory")){
            printInventory();
        } else if(t.contains("take")){
            updateInventory();
        } else if(t.contains("eat")){
            eatCommand();
        } else if(t.contains("toss")){
            tossCommand();
        } else if(t.contains("read")){
            readCommand();
        }
    }

    private void eatCommand(){
        Food foodItem = new Food();
        text.append(String.format("%s\n",foodItem.eat()));
        updateInventory();
    }

    private void tossCommand(){
        Weapon weaponItem = new Weapon();
        text.append(String.format("%s\n",weaponItem.toss()));
        updateInventory();
    }

    public void readCommand(){
        Spell spellItem = new Spell();
        text.append(String.format("%s\n",spellItem.read()));
    }

    public void wearCommand(){
        Clothing clothItem = new Clothing();
        text.append(String.format("%s\n",clothItem.wear()));
    }

    private void printRoomDesc(Room r){
        text.append(String.format("You are in %s. %s.\n",r.getName(),r.getShortDescription()));
        printItems(r);
    }

    private void printInventory(){
        ArrayList<Item> items = myPlayer.getInventory();
        text.append(String.format("Inventory: \n"));
        for(Item item: items){
            text.append(String.format("- %s\n",item.getName()));
        }
    }
    private void updateInventory(){
        ArrayList<Item> items = myPlayer.getInventory();
        data.removeAllElements();
        for(Item item: items){
            if (!data.contains(item.getName())){
                data.addElement(item.getName());
            }
        }
        inv = new JList<String>(data);
    }

    private void printItems(Room room){
        ArrayList<Item> items = room.listItems();
        for(Item item: items){
            text.append(String.format("Item: %s\n",item.getName()));
        }
    }

    private JPanel showInventory(GridBagConstraints c){
        JPanel invPanel = new JPanel();
        invPanel.setLayout(new GridBagLayout());
        JLabel inventory = new JLabel("Inventory");
        changeConstraints(c,0,0);
        invPanel.add(inventory,c);
        changeConstraints(c,0,1);
        invPanel.add(setInventory(),c);
        return invPanel;
    }

    private JList<String> setInventory(){
        if (myPlayer.getInventory() == null){
            System.out.println("empty");
        }else{
            ArrayList<Item> items = myPlayer.getInventory();
            DefaultListModel<String> data = new DefaultListModel<String>();
            for(Item item: items){
                System.out.println(item.getName());
                data.addElement(item.getName());
            }
            inv = new JList<String>(data);
        }
        inv = new JList<String>(data);
        return inv;
    }

    public static void main(String[] args){
        Game theGame = new Game();
        GameView game = new GameView(theGame);
        game.setVisible(true);
    }

}
