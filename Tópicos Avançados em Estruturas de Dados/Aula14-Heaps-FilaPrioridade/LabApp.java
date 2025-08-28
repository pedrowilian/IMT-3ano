import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class LabApp extends JFrame {

    private final PatientHeap queue = new PatientHeap(50);
    private JLabel statusLabel;
    private JLabel queueLabel;

    public LabApp() {
        setTitle("PW Laboratory");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Build UI
        add(createMainPanel());

        // Show default status
        updateStatus("Waiting...");
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Welcome message
        JLabel welcome = new JLabel("Welcome to PW Laboratory", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcome, BorderLayout.NORTH);

        // Center panel for button and status
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        // Take Ticket button
        JButton takeTicketBtn = new JButton("Take Ticket");
        takeTicketBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(takeTicketBtn);
        centerPanel.add(buttonPanel, BorderLayout.NORTH);

        // Status label
        statusLabel = new JLabel("Waiting...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(statusLabel, BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for queue info and Next button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        queueLabel = new JLabel("Queue size: 0", SwingConstants.LEFT);
        queueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(queueLabel, BorderLayout.WEST);

        JButton nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(nextBtn, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        takeTicketBtn.addActionListener(e -> openRegistrationForm());
        nextBtn.addActionListener(e -> callNextPatient());

        return panel;
    }

    private void openRegistrationForm() {
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        JCheckBox pregnantBox = new JCheckBox("Pregnant");
        JCheckBox postpartumBox = new JCheckBox("Postpartum");

        Object[] fields = {
            "Name:", nameField,
            "Age:", ageField,
            pregnantBox,
            postpartumBox
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Patient Registration", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                    return;
                }

                int age = Integer.parseInt(ageField.getText().trim());
                if (age <= 0) {
                    JOptionPane.showMessageDialog(this, "Age must be positive.");
                    return;
                }

                boolean preg = pregnantBox.isSelected();
                boolean post = postpartumBox.isSelected();

                Patient p = new Patient(name, age, preg, post);
                queue.insert(p);

                JOptionPane.showMessageDialog(this,
                        "Ticket registered: " + p,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                updateQueueLabel();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for age.");
            }
        }
    }

    private void callNextPatient() {
        if (queue.isEmpty()) {
            updateStatus("No patients in queue.");
        } else {
            Patient next = queue.remove();
            updateStatus(next.getName() + "'s turn !");
            updateQueueLabel();
        }
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }

    private void updateQueueLabel() {
        queueLabel.setText("Queue size: " + queue.getSize());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabApp().setVisible(true));
    }
}