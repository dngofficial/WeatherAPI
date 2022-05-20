import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

// this class implements ActionListener interface, which allows for interactivity with JButtons
public class GUIControl implements ActionListener, ItemListener {
    private JTextField weatherEntryField;
    private JLabel temperature;
    private JLabel condition;
    private JLabel pictureLabel;
    private JCheckBox checkbox;
    private CurrentWeather currentWeather;


    public GUIControl() {
        weatherEntryField = new JTextField();
        temperature = new JLabel();
        condition = new JLabel();
        pictureLabel = new JLabel();
        checkbox = new JCheckBox();
        setupGui();
    }


    // private helper method, called by constructor
    // to set up the GUI and display it
    private void setupGui() {
        //Creating a Frame
        JFrame frame = new JFrame("Current Weather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ends program when you hit the X

        JLabel welcomeLabel = new JLabel("Current Weather©");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.darkGray);

        JLabel nameLabel = new JLabel("Devan Ng");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 10));
        nameLabel.setBackground(Color.darkGray);

        //top panel
        JPanel logoWelcomePanel = new JPanel(); // the panel is not visible in output
        logoWelcomePanel.add(welcomeLabel);
        logoWelcomePanel.add(nameLabel);

        //middle panel with text field and buttons
        JPanel entryPanel = new JPanel(); // the panel is not visible in output
        JLabel zipCodeLabel = new JLabel("Enter Zip Code:");
        weatherEntryField = new JTextField(7); // accepts up to 7 characters
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
         checkbox = new JCheckBox("Enable C");
        entryPanel.add(zipCodeLabel);
        entryPanel.add(weatherEntryField);
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);
        entryPanel.add(checkbox);

        // bottom panel w/ placeholder
        JPanel displayPanel = new JPanel();
        temperature = new JLabel("");
        condition = new JLabel("");
        pictureLabel = new JLabel(new ImageIcon("src/placeholder.jpg"));
        displayPanel.add(temperature);
        displayPanel.add(condition);
        displayPanel.add(pictureLabel);


        //Adding Components to the frame
        frame.add(logoWelcomePanel, BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.CENTER);
        frame.add(displayPanel, BorderLayout.SOUTH);

        // PART 2 -- SET UP EVENT HANDLING
        //setting up buttons to use ActionListener interface and actionPerformed method
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        checkbox.addItemListener(this);

        // showing the frame
        frame.pack();
        frame.setVisible(true);


    }


    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) (e.getSource());  // cast source to JButton
        String text = button.getText();

        if (text.equals("Submit")) {


                String zipCode = weatherEntryField.getText();
                updateDisplayPanel(zipCode);

        }

        // if user clicked "Reset" button, set the text field back to empty string
        // and load the Now Playing list again
        else if (text.equals("Clear")) {
            weatherEntryField.setText("");
            temperature.setText("");
            condition.setText("");
            currentWeather = null;
            pictureLabel.setIcon(new ImageIcon("src/placeholder.jpg"));




        }
    }

    private void updateDisplayPanel(String text) {


        WeatherNetworking api = new WeatherNetworking();
        currentWeather = api.parseCurrent(api.makeAPICallForForecast(text));
        double currentF = currentWeather.getCurrentF();
        double currentC = currentWeather.getCurrentC();
        String filePath = currentWeather.getFilePath();
        String condition1 = currentWeather.getCondition();


System.out.println(checkbox.isSelected());
if (checkbox.isSelected())
{
    temperature.setText("Temperature: " + currentC + " C°");
}
else
        {
            temperature.setText("Temperature: " + currentF + " F°");
        }

        condition.setText("Condition: " + condition1);

        try {

            URL imageURL = new URL("https:" + filePath);
            BufferedImage image = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(image);
            pictureLabel.setIcon(icon);

        }
        catch (IOException e)
        {
            System.out.println("image no works");
        }


    }

    public void itemStateChanged(ItemEvent e) {
        JCheckBox box = (JCheckBox) (e.getSource());
        if (box.isSelected() && currentWeather != null) {
            temperature.setText("Temperature: " + currentWeather.getCurrentC() + " C°");
        } else if (!box.isSelected() && currentWeather != null) {
            temperature.setText("Temperature: " + currentWeather.getCurrentF() + " F°");
        }
    }
}




