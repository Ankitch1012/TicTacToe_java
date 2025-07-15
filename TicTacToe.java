import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame frame;
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].addActionListener(new ButtonClickListener(i));
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[index].getText().isEmpty()) {
                buttons[index].setText(isXTurn ? "X" : "O");
                isXTurn = !isXTurn;
                checkWinner();
            }
        }
    }

    private void checkWinner() {
        // Winning combinations (rows, columns, diagonals)
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}              // Diagonals
        };

        for (int[] combo : winCombinations) {
            if (!buttons[combo[0]].getText().isEmpty() &&
                    buttons[combo[0]].getText().equals(buttons[combo[1]].getText()) &&
                    buttons[combo[0]].getText().equals(buttons[combo[2]].getText())) {
                JOptionPane.showMessageDialog(frame, buttons[combo[0]].getText() + " wins!");
                resetGame();
                return;
            }
        }

        // Check for a draw
        boolean isDraw = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetGame();
        }
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}