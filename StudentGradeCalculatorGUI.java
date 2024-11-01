import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField[] subjectFields;
    private JTextField numSubjectsField;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;
    private JButton calculateButton;
    private JButton resetButton;
    private JPanel subjectsPanel;
    private int numSubjects;

    public StudentGradeCalculatorGUI() {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for number of subjects
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter number of subjects: "));
        numSubjectsField = new JTextField(5);
        inputPanel.add(numSubjectsField);
        JButton enterButton = new JButton("Enter");
        inputPanel.add(enterButton);

        add(inputPanel, BorderLayout.NORTH);

        // Panel for subjects
        subjectsPanel = new JPanel(new GridLayout(0, 1));
        add(subjectsPanel, BorderLayout.CENTER);

        // Panel for results
        JPanel resultsPanel = new JPanel(new GridLayout(4, 1));
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        resultsPanel.add(totalMarksLabel);
        resultsPanel.add(averagePercentageLabel);
        resultsPanel.add(gradeLabel);

        // Buttons for calculate and reset
        calculateButton = new JButton("Calculate");
        resetButton = new JButton("Reset");
        resultsPanel.add(calculateButton);
        resultsPanel.add(resetButton);
        add(resultsPanel, BorderLayout.SOUTH);

        // Action listener to enter subjects
        enterButton.addActionListener(e -> setupSubjectFields());

        // Action listener for calculate button
        calculateButton.addActionListener(e -> calculateResults());

        // Action listener for reset button
        resetButton.addActionListener(e -> resetFields());

        setVisible(true);
    }

    private void setupSubjectFields() {
        try {
            numSubjects = Integer.parseInt(numSubjectsField.getText());
            subjectsPanel.removeAll();
            subjectFields = new JTextField[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                JPanel subjectPanel = new JPanel(new FlowLayout());
                subjectPanel.add(new JLabel("Marks for subject " + (i + 1) + ": "));
                subjectFields[i] = new JTextField(5);
                subjectPanel.add(subjectFields[i]);
                subjectsPanel.add(subjectPanel);
            }

            subjectsPanel.revalidate();
            subjectsPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of subjects.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateResults() {
        int totalMarks = 0;
        try {
            for (JTextField field : subjectFields) {
                int marks = Integer.parseInt(field.getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Please enter marks between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                totalMarks += marks;
            }

            double averagePercentage = totalMarks / (double) numSubjects;
            char grade = calculateGrade(averagePercentage);

            // Display results
            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText(String.format("Average Percentage: %.2f%%", averagePercentage));
            gradeLabel.setText("Grade: " + grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        numSubjectsField.setText("");
        subjectsPanel.removeAll();
        totalMarksLabel.setText("Total Marks: ");
        averagePercentageLabel.setText("Average Percentage: ");
        gradeLabel.setText("Grade: ");
        subjectsPanel.revalidate();
        subjectsPanel.repaint();
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeCalculatorGUI::new);
    }
}
