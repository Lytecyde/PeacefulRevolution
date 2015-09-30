

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Collections;
import java.util.LinkedList;

public class Main  {

    public static JPanel startUpScreen;
    static JButton ok = new JButton("OK");
    static LinkedList<String> identities;
    static JComboBox numberOfPlayers = new JComboBox();
    static LinkedList<PlayerData> playerData = new LinkedList<PlayerData>();
    static JFrame mainFrame = new JFrame();
    static JPanel[][] cityStreets = new JPanel[9][5];
    static JLabel[][] street = new JLabel[3][3];

    public static void main(String[] args) {
        for(int x =0;x<9;x++){
            for(int y =0;y<5;y++){
                cityStreets[x][y] =  new JPanel();
                //TODO cityStreets[x][y].add(street)
            }
        }

       new Main();

    }

    public Main() {
        startUpScreen = new JPanel();
        display(startUpScreen);

    }

    public void display(JPanel startUpScreen) {

        startUpScreen.setLayout(new GridLayout(5, 2));
        JLabel name = new JLabel("Name : ");
        JTextField nameField = new JTextField();
        JLabel numberOfPlayersLabel = new JLabel("Players : ");
        String[] players = new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "11"};
        numberOfPlayers = new JComboBox(players);

        OkListener okListener = new OkListener();
        ok.addActionListener(okListener);
        mainFrame.setSize(800, 600);
        startUpScreen.add(name);
        startUpScreen.add(nameField);
        startUpScreen.add(numberOfPlayersLabel);
        startUpScreen.add(numberOfPlayers);
        startUpScreen.add(ok);
        startUpScreen.setVisible(true);
        mainFrame.add(startUpScreen);
        mainFrame.pack();
        mainFrame.setVisible(true);


    }

    private void createPlayerData(int nofPlayers){

        for (int i = 0; i < nofPlayers; i++) {
            playerData.add(new PlayerData());
        }
    }

    private LinkedList<String> identityRoller() {
        LinkedList<String> ids = new LinkedList<String>();
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("loyalist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        ids.add("activist");
        Collections.shuffle(ids);
        return ids;
    }

    public class OkListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==ok) {
                System.out.println("MSG: reached the button");
                int nofPlayers = numberOfPlayers.getSelectedIndex() + 3;
                createPlayerData(nofPlayers );
                LinkedList<String> playerID = identityRoller();
                for (int i = 0; i < nofPlayers; i++) {
                    PlayerData pd = playerData.get(i);
                    pd.allegiance = playerID.get(i);
                }
                mainFrame.remove(startUpScreen);
                createGUI();
            }


        }

    }

    public void createGUI() {
        //main
        JPanel mainPanel = new JPanel(new BorderLayout());
        //board
        JPanel board = new JPanel(new GridLayout(5, 9));
        //data table
        JPanel data = new JPanel(new GridLayout(8, 2));
        //player screen

        JPanel playerScreenNorth = new JPanel(new GridLayout(1, 3));
        JPanel playerScreenWest = new JPanel(new GridLayout(3, 1));
        JPanel playerScreenSouth = new JPanel(new GridLayout(3, 1));
        JPanel playerScreenEast = new JPanel(new GridLayout(1, 3));

        //boardContents
        for(int x =0;x<9;x++){
            for(int y =0;y<5;y++){
                board.add(cityStreets[x][y]);
            }
        }
        mainFrame.setTitle("Peaceful Power");
        mainPanel.setSize(800, 640);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(playerScreenNorth, BorderLayout.NORTH);
        mainPanel.add(playerScreenEast, BorderLayout.EAST );
        mainPanel.add(playerScreenWest, BorderLayout.WEST);
        playerScreenSouth.add(data);
        mainPanel.add(playerScreenSouth, BorderLayout.SOUTH);
        mainPanel.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
