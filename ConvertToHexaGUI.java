/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converttohexagui;

/**
 *
 * @author Adam
 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    Node top;

    Stack() {
        this.top = null;
    }

    void push(int data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    int pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty!");
        }
        int popped = top.data;
        top = top.next;
        return popped;
    }

    boolean isEmpty() {
        return top == null;
    }
}

public class ConvertToHexaGUI extends JFrame {
    private JTextField inputField;
    private JButton convertButton;
    private JTextArea outputArea;
    
    public ConvertToHexaGUI() {
        setTitle("Pogram TO Convert To Hexadecimal");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); 

        
        
        inputField = new JTextField(10);

        inputField.setBackground(Color.WHITE); 
        inputField.setForeground(Color.BLACK); 
        convertButton = new JButton("CONVERT TO HEXADECIMAl");
        convertButton.setBackground(Color.PINK); 
        convertButton.setForeground(Color.WHITE);

        outputArea = new JTextArea(5,20);
        outputArea.setEditable(false);
outputArea.setBackground(Color.WHITE); 
        outputArea.setForeground(Color.BLACK); 
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToHexadecimal();
            }
        });

        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(convertButton, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void convertToHexadecimal() {
        try {
            int number = Integer.parseInt(inputField.getText());

            Stack s = new Stack();

            while (number > 0) {
                int remainder = number % 16;
                s.push(remainder);
                number /= 16;
            }

            StringBuilder hexadecimal = new StringBuilder();
            while (!s.isEmpty()) {
                int digit = s.pop();
                if (digit < 10) {
                    hexadecimal.append(digit);
                } else {
                    char hexChar = (char) ('A' + digit - 10);
                    hexadecimal.append(hexChar);
                }
            }

            outputArea.setText("The number in hexadecimal: " + hexadecimal.toString());
        } catch (NumberFormatException ex) {
            outputArea.setText("Wrong input! Please Renter a positive integer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConvertToHexaGUI gui = new ConvertToHexaGUI();
                gui.setVisible(true);
            }
         
        });
    }
}
