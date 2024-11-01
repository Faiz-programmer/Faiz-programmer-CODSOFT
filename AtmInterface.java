import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AtmInterface {
    private double balance;

    // Constructor
    public AtmInterface(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance can't be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    // Method to deposit amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Successfully deposited $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Deposit amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to withdraw amount
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                JOptionPane.showMessageDialog(null, "Successfully withdrew $" + amount);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance. Transaction failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Withdrawal amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check balance
    public double checkBalance() {
        return balance;
    }
}

class ATMInterfaceGUI extends JFrame {
    private AtmInterface account;
    private JLabel balanceLabel;

    // Constructor for GUI setup
    public ATMInterfaceGUI(AtmInterface account) {
        this.account = account;
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Balance Label
        balanceLabel = new JLabel("Balance: $" + account.checkBalance(), SwingConstants.CENTER);
        add(balanceLabel);

        // Buttons for actions
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        // Adding action listeners for buttons
        checkBalanceButton.addActionListener(new CheckBalanceAction());
        depositButton.addActionListener(new DepositAction());
        withdrawButton.addActionListener(new WithdrawAction());
        exitButton.addActionListener(e -> System.exit(0));

        // Adding buttons to frame
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);

        setVisible(true);
    }

    // Action to check balance
    private class CheckBalanceAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            balanceLabel.setText("Balance: $" + account.checkBalance());
            JOptionPane.showMessageDialog(null, "Current balance: $" + account.checkBalance());
        }
    }

    // Action to deposit amount
    private class DepositAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(input);
                account.deposit(amount);
                balanceLabel.setText("Balance: $" + account.checkBalance());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Action to withdraw amount
    private class WithdrawAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(input);
                account.withdraw(amount);
                balanceLabel.setText("Balance: $" + account.checkBalance());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance
        AtmInterface userAccount = new AtmInterface(500.00); // Initial balance of $500

        // Launch the ATM Interface GUI
        SwingUtilities.invokeLater(() -> new ATMInterfaceGUI(userAccount));
    }
}

