import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class htmlReaderProject implements ActionListener{
    private JFrame mainFrame;
    private JPanel top, right ;
    private JTextField keyWordArea, link;
    private JTextArea results;
    private JButton go;
    private JScrollPane resultDisplay;
    private JLabel keyWordHere, linkHere;




    public static void main(String[] args) {
        htmlReaderProject myCode = new htmlReaderProject();
    }

    public htmlReaderProject() {
        pullURLs();
        mainFrame = new JFrame("htmlReader");
        mainFrame.setSize(500,500);
        mainFrame.setLayout(new GridLayout(2, 1));



        top = new JPanel(new BorderLayout());
        mainFrame.add(top);

        right = new JPanel(new GridLayout(2,2));
        top.add(right, BorderLayout.CENTER);

        keyWordHere = new JLabel("Insert key word here:");
        keyWordHere.setHorizontalAlignment(SwingConstants.CENTER);
        keyWordHere.setFont(new Font ("SansSerif", Font.PLAIN,15));

        right.add(keyWordHere);
        keyWordHere.setBackground(Color.LIGHT_GRAY);

        keyWordArea = new JTextField();
        right.add(keyWordArea);
        keyWordArea.setBackground(Color.LIGHT_GRAY);

        linkHere = new JLabel("Insert link here:");
        linkHere.setHorizontalAlignment(SwingConstants.CENTER);
        right.add(linkHere);
        linkHere.setBackground(Color.LIGHT_GRAY);
        linkHere.setFont(new Font ("SansSerif", Font.PLAIN,15));


        link = new JTextField();
        right.add(link);
        link.setBackground(Color.LIGHT_GRAY);

        go = new JButton("SEARCH");
        top.add(go, BorderLayout.EAST);

        go.setBackground(Color.DARK_GRAY);
        go.setForeground(Color.WHITE);
        go.setFont(new Font ("SansSerif", Font.BOLD,15));



        results = new JTextArea();
        resultDisplay = new JScrollPane(results);
        //mainFrame.add(results);
        mainFrame.add(resultDisplay);
        resultDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        go.addActionListener(this);




        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void pullURLs() {
        try{
            String website = link.getText();
            URL url = new URL(website);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            String keyWord = keyWordArea.getText().toLowerCase();
            System.out.println(keyWord);

            while (line != null){
                if (line.contains("href=")){
                        int start = line.indexOf("href");
                        int end = line.indexOf("\"", start + 10);
                        String link = line.substring(start+6, end);
                        link =link.toLowerCase();
                            if(link.contains(keyWord)){

                                System.out.println(link);
                                results.append(link + "\n");
                            }
            }
                if(line.contains("src=")){
                    int start = line.indexOf("src");
                    int end = line.indexOf("\"", start + 10);
                    String link = line.substring(start+5, end);
                    link = link.toLowerCase();

                    if(link.contains(keyWord)){

                        System.out.println(link);
                        results.append(link + "\n");
                    }
                }

            line = reader.readLine();
                line= line.toLowerCase();


            }
        }catch(Exception e){
            System.out.println(e);
            //throw new RuntimeException(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
pullURLs();

    }

}
