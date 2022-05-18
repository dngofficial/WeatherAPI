// lots of classes get used here!
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// this class implements ActionListener interface, which allows for interactivity with JButtons
public class GUIControl implements ActionListener
{
    // we set and use these across different methods
    // so we add them as instance variables
    private JTextArea weatherInfo;
    private JTextField weatherEntryField;
    private CurrentWeather currentWeather;
    private WeatherNetworking weatherNetworking;

    // constructor, which calls helper methods
    // to setup the GUI then load the now playing list
    public GUIControl()
    {
        weatherInfo = new JTextArea(30, 30);
        weatherEntryField = new JTextField();
//        client = new MovieNetworkingClient();  // our "networking client"

        // setup GUI and load Now Playing list
        setupGui();
//        loadNowPlaying();
    }

    // private helper method, called by constructor
    // to set up the GUI and display it
    private void setupGui()
    {
        //Creating a Frame
        JFrame frame = new JFrame("Current Weather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ends program when you hit the X

        // Creating an image from a jpg file stored in the src directory
//        ImageIcon image = new ImageIcon("src/download.jpg");
//        Image imageData = image.getImage(); // transform it
//        Image scaledImage = imageData.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        image = new ImageIcon(scaledImage);  // transform it back
//        JLabel pictureLabel = new JLabel(image);
        JLabel welcomeLabel = new JLabel("Current Weather");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 70));
        welcomeLabel.setForeground(Color.pink);

        //top panel
        JPanel logoWelcomePanel = new JPanel(); // the panel is not visible in output
        logoWelcomePanel.add(welcomeLabel);

        //middle panel with text field and buttons
        JPanel entryPanel = new JPanel(); // the panel is not visible in output
        JLabel zipCodeLabel = new JLabel("Enter Zip Code:");
        weatherEntryField = new JTextField(7); // accepts up to 7 characters
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        entryPanel.add(zipCodeLabel);
        entryPanel.add(weatherEntryField);
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);

        // bottom panel w/ placeholder
        ImageIcon image = new ImageIcon("src/placeholder.jpg");
        Image imageData = image.getImage(); // transform it
        Image scaledImage = imageData.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        image = new ImageIcon(scaledImage);  // transform it back
        JLabel pictureLabel = new JLabel(image);


        //Adding Components to the frame
        frame.add(logoWelcomePanel, BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);

        // PART 2 -- SET UP EVENT HANDLING
        //setting up buttons to use ActionListener interface and actionPerformed method
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        // showing the frame
        frame.pack();
        frame.setVisible(true);
    }

    // private helper method to load the Now Playing
    // movie list into the GUI by making a network call,
    // obtaining an arraylist of Movie objects, then
    // creating a string that gets displayed in a GUI label;
    // this method gets called by the constructor as part of
    // the initial set up of the GUI, and also when the user
    // clicks the "Reset" button
//    private void loadNowPlaying()
//    {
//        // use client to make network call to Now Playing, which returns an arraylist
//        // which gets assigned to the nowPlaying instance variable
//        nowPlaying = client.getNowPlaying();
//
//        // build the string to display in the movieInfo label
//        String labelStr = "";
//        int count = 1;
//        for (Movie movie : nowPlaying)
//        {
//            labelStr += count + ". " + movie.getTitle() + "\n";
//            count++;
//        }
//        movieInfo.setText(labelStr);
//    }

    // private helper method to load the details for
    // a particular movie into the GUI by making a network call,
    // obtaining a DetailedMovie, then
    // creating a string that gets displayed in a GUI label;
    // this method gets called when the user clicks the "Send" button
//    private void loadMovieInfo(Movie movie)
//    {
//        // make network call to Movie Details, which returns a DetailedMovie object
//        DetailedMovie detail = client.getMovieDetails(movie.getID());
//
//        // build the string with movie details
//        String info = "Title: " + detail.getTitle() +
//                "\n\nOverview: " + detail.getOverview() +
//                "\n\nTagline: " + detail.getTagline() +
//                "\n\nPopularity: " + detail.getPopularity() +
//                "\n\nReleased on: " + detail.getReleaseDate();
//
//        if (detail.getTitle().equals("Morbius"))
//        {
//            info += "\n\n #MORBIUS SWEEP BITCH MORBIUS SWEEP BITCH MORBIUS SWEEP BITCH";
//        }
//
//        movieInfo.setText(info);
//
//        // download and display poster image in a new window
//        try {
//            URL imageURL = new URL(detail.getPosterPath());
//            BufferedImage image = ImageIO.read(imageURL);
//            JFrame frame = new JFrame("Poster for " + movie.getTitle());
//            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            JLabel movieImage = new JLabel(new ImageIcon(image));
//            frame.add(movieImage);
//            frame.pack();
//            frame.setVisible(true);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // implement ActionListener interface method
//    // this method gets invoked anytime either button
//    // gets clicked; we need code to differentiate which
//    // button sent was clicked, so we use the text of the
//    // button ("Send" or "Reset") to determine this
//    public void actionPerformed(ActionEvent e) {
//        JButton button = (JButton) (e.getSource());  // cast source to JButton
//        String text = button.getText();
//
//        if (text.equals("Send"))
//        {
//            // obtain the numerical value that the user typed into the text field
//            // (getTest() returns a string) and convert it to an int
//            String selectedMovieNum = movieEntryField.getText();
//            int movieNumInt = Integer.parseInt(selectedMovieNum);
//
//            // obtain the movie in the nowPlaying arraylist that the number they
//            // typed in corresponds to
//            int movieIdx = movieNumInt - 1;
//            Movie selectedMovie = nowPlaying.get(movieIdx);
//
//
//            // call private method to load movie info for that Movie object
//            loadMovieInfo(selectedMovie);
//        }
//
//        // if user clicked "Reset" button, set the text field back to empty string
//        // and load the Now Playing list again
//        else if (text.equals("Reset"))
//        {
//            weatherEntryField.setText("");
//            loadNowPlaying();
//        }
//    }
}