package com.reactor.userInterface;

import javax.swing.*;
import java.util.Date;

import com.reactor.datamodel.TreeCheck;/**
 * This class manages the process of checking the Anomaly Cache for any inconsistencies
 * on a separate thread to make sure the ui is responsive
 */
public class AnomolyCheckAction implements Runnable {
    private JButton button;
    private JTextField textField;
    /**
     * Initializes a new instance of the action with the button and the  text- field
     * @param button    The button that starts the check and indicates status.
     * @param textField The text field to display messages and results.
     */
    public AnomolyCheckAction(JButton button, JTextField textField) {
        this.button = button;
        this.textField = textField;
    }
    /**
     * When this method is called, it updates the UI to make sure the button has the right text,
     * it runs the anomaly check, and shows the results.
     * It starts by making the button say "Anomaly Cache Check in Progress
     * and disables it to prevent re clicking.
     * Then,  the thread goes to sleep to simulate a delay,
     * performs the check, and displays either consistent or inconsistent based on the tree
     * then it shows  a timestamp of the check completion.
     */
    @Override
    public void run() {
        System.out.println("Starting the anomaly Check...");
        SwingUtilities.invokeLater(() -> {
            button.setText("Anomaly Cache Check in Progress");
            button.setEnabled(false);
            textField.setText("Checking...");

        });
        try {
            Thread.sleep(2345);


            boolean isConsistent = TreeCheck.anomaly();
            System.out.println("Anomaly was check completed: " + isConsistent);
            Date checkTime = new Date();
            String result = isConsistent ? "Consistent as of " + checkTime.toString() : "Inconsistent";
            System.out.println("got the results  " + result);
            button.setText("Check Anomaly Cache");
            button.setEnabled(true);
            textField.setText(result);
            System.out.println("updated the ui with results");

        } catch (Exception e) {
            System.out.println("there is an error during the anomaly check: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
