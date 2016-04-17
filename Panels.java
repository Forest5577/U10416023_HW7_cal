//U10416023

import java.awt.*;
import javax.swing.*;

class TestPanels extends JFrame{
	public TestPanels(){
	// Create panel p1 for the buttons and set GridLayout
	JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(5, 4));
 
    // Add buttons to the panel
    p1.add(new JButton("AC"));
    p1.add(new JButton("+/-"));
    p1.add(new JButton("%"));
    p1.add(new JButton("DEL"));
    p1.add(new JButton("7"));
    p1.add(new JButton("8"));
    p1.add(new JButton("9"));
    p1.add(new JButton("/"));
    p1.add(new JButton("4"));
    p1.add(new JButton("5"));
    p1.add(new JButton("6"));
    p1.add(new JButton("*"));
    p1.add(new JButton("1"));
    p1.add(new JButton("2"));
    p1.add(new JButton("3"));
    p1.add(new JButton("-"));
    p1.add(new JButton("."));
    p1.add(new JButton("0"));
    p1.add(new JButton("="));
    p1.add(new JButton("+"));
    
    // Create panel p2 to hold a text field and p1
    JPanel p2 = new JPanel(new BorderLayout());
    p2.add(new JLabel("  The result"), BorderLayout.NORTH);
    p2.add(p1, BorderLayout.CENTER);
    this.add(p2);
	}
}

class Panels{
	/** Main method */
	public static void main(String[] args){
    TestPanels frame = new TestPanels();
    frame.setTitle("U10416023 Calculator");
    frame.setSize(250, 400);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}
}