import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int numberToGuess;
    private int attempts;
    private final int maxAttempts = 10;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;
    private JButton newGameButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Initialize components
        messageLabel = new JLabel("Guess the number between 1 and 100!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(SwingConstants.CENTER);

        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");

        // Add components to the frame
        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(newGameButton);

        // Add action listeners
        guessButton.addActionListener(new GuessButtonListener());
        newGameButton.addActionListener(e -> startNewGame());

        // Start a new game
        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = maxAttempts;
        messageLabel.setText("Guess the number between 1 and 100!");
        attemptsLabel.setText("Attempts left: " + maxAttempts);
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts--;

                if (guess == numberToGuess) {
                    messageLabel.setText("Correct! You've guessed the number!");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }

                attemptsLabel.setText("Attempts left: " + attempts);

                if (attempts == 0 && guess != numberToGuess) {
                    messageLabel.setText("Out of attempts! The number was " + numberToGuess);
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }

            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame frame = new NumberGuessingGame();
            frame.setVisible(true);
        });
    }
}
