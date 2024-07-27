import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OnlineTest extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private JButton bookmarkButton;
    private JPanel optionsPanel;
    private ButtonGroup buttonGroup;

    private int currentQuestionIndex = 0;
    private int correctAnswerCount = 0;

    private String[] questions = {
        "Which one among these is not a primitive datatype?",
        "Which class is available to all the class automatically?",
        "Which package is directly available to our class without importing it?",
        "String class is defined in which package?",
        "Which institute is best for java coaching?",
        "Which one among these is not a keyword?",
        "Which one among these is not a class?",
        "Which one among these is not a function of Object class?",
        "Which function is not present in Applet class?",
        "Which one among these is not a valid component?"
    };

    private String[][] options = {
        {"int", "Float", "boolean", "char"},
        {"Swing", "Applet", "Object", "ActionEvent"},
        {"swing", "applet", "net", "lang"},
        {"lang", "Swing", "Applet", "awt"},
        {"Utek", "Aptech", "SSS IT", "jtek"},
        {"class", "int", "get", "if"},
        {"Swing", "Actionperformed", "ActionEvent", "Button"},
        {"toString", "finalize", "equals", "getDocumentBase"},
        {"init", "main", "start", "destroy"},
        {"JButton", "JList", "JButtonGroup", "JTextArea"}
    };

    private int[] correctAnswers = {1, 2, 3, 0, 2, 2, 1, 3, 1, 2}; // Indices of correct answers for each question

    public OnlineTest(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);
        add(questionPanel, BorderLayout.NORTH);

        optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextButton = new JButton("Next");
        bookmarkButton = new JButton("Bookmark");
        nextButton.addActionListener(this);
        bookmarkButton.addActionListener(this);
        buttonPanel.add(nextButton);
        buttonPanel.add(bookmarkButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int index) {
        questionLabel.setText(questions[index]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[index][i]);
            optionButtons[i].setSelected(false);
        }
    }

    private boolean checkAnswer(int index) {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected() && i == correctAnswers[index]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer(currentQuestionIndex)) {
                correctAnswerCount++;
            }
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                loadQuestion(currentQuestionIndex);
            } else {
                showResult();
            }
        } else if (e.getSource() == bookmarkButton) {
            // Implement bookmark functionality if needed
            // Currently not implemented in this refactored version
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Correct answers: " + correctAnswerCount);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineExam("Online Exam of Java").setVisible(true);
        });
    }
}
