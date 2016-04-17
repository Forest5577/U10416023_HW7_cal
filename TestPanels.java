//U10416023 佛瑞

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TestPanels extends JFrame {

	JButton objc = new JButton("C");
	JButton objms = new JButton("+/-");
	JButton objper = new JButton("%");
	JButton objdel = new JButton("DEL");
	JButton obj7 = new JButton("7");
	JButton obj8 = new JButton("8");
	JButton obj9 = new JButton("9");
	JButton objsub = new JButton("÷");
	JButton obj4 = new JButton("4");
	JButton obj5 = new JButton("5");
	JButton obj6 = new JButton("6");
	JButton objstar = new JButton("*");
	JButton obj1 = new JButton("1");
	JButton obj2 = new JButton("2");
	JButton obj3 = new JButton("3");
	JButton objsubstr = new JButton("-");
	JButton objdot = new JButton(".");
	JButton obj0 = new JButton("0");
	JButton objequal = new JButton("=");
	JButton objplus = new JButton("+");
	JTextField result  = new JTextField("");

	public TestPanels() {
		// Create panel p1 for the buttons and set GridLayout
		result.setHorizontalAlignment(JTextField.RIGHT);
		ButtonListener listener = new ButtonListener();
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5, 4));
		// Add buttons to the panel
		p1.add(objc);
		objc.addActionListener(listener);
		p1.add(objms);
		objms.addActionListener(listener);
		p1.add(objper);
		objper.addActionListener(listener);
		p1.add(objdel);
		objdel.addActionListener(listener);
		p1.add(obj7);
		obj7.addActionListener(listener);
		p1.add(obj8);
		obj8.addActionListener(listener);
		p1.add(obj9);
		obj9.addActionListener(listener);
		p1.add(objsub);
		objsub.addActionListener(listener);
		p1.add(obj4);
		obj4.addActionListener(listener);
		p1.add(obj5);
		obj5.addActionListener(listener);
		p1.add(obj6);
		obj6.addActionListener(listener);
		p1.add(objstar);
		objstar.addActionListener(listener);
		p1.add(obj1);
		obj1.addActionListener(listener);
		p1.add(obj2);
		obj2.addActionListener(listener);
		p1.add(obj3);
		obj3.addActionListener(listener);
		p1.add(objsubstr);
		objsubstr.addActionListener(listener);
		p1.add(objdot);
		objdot.addActionListener(listener);
		p1.add(obj0);
		obj0.addActionListener(listener);
		p1.add(objequal);
		objequal.addActionListener(listener);
		p1.add(objplus);
		objplus.addActionListener(listener);
		// Create panel p2 to hold a text field and p1
		JPanel p2 = new JPanel(new BorderLayout());
		result.setEditable(false);
		p2.add(result, BorderLayout.NORTH);
		p2.add(p1, BorderLayout.CENTER);
		this.add(p2);

	}

	class ButtonListener implements ActionListener{
		//Listener fot buttons
		public void actionPerformed(ActionEvent e){
			int strsumfordel = result.getText().length();
			if (e.getSource() == objc)
				result.setText("0");
			else if(e.getSource() == objdel)
				while(result.getText().length()==strsumfordel){
					result.setText(result.getText().substring(0,result.getText().length()-1));
				}
			else if(e.getSource() == obj7)
				result.setText(Integer.toString(Integer.parseInt(result.getText()+"7")));
			else if(e.getSource() == obj8)
				result.setText(result.getText()+"8");
			else if(e.getSource() == obj9)
				result.setText(result.getText()+"9");
			else if(e.getSource() == obj4)
				result.setText(result.getText()+"4");
			else if(e.getSource() == obj5)
				result.setText(result.getText()+"5");
			else if(e.getSource() == obj6)
				result.setText(result.getText()+"6");
			else if(e.getSource() == obj1)
				result.setText(result.getText()+"1");
			else if(e.getSource() == obj2)
				result.setText(result.getText()+"2");
			else if(e.getSource() == obj3)
				result.setText(result.getText()+"3");
			else if(e.getSource() == obj0)
				result.setText(result.getText()+"0");
			else if(e.getSource() == objdot)
				result.setText(result.getText()+".");
			else if(e.getSource() == objplus)
				result.setText(result.getText()+"+");
			else if(e.getSource() == objsubstr)
				result.setText(result.getText()+"-");
			else if(e.getSource() == objstar)
				result.setText(result.getText()+"*");
			else if(e.getSource() == objsub)
				result.setText(result.getText()+"÷");
		}
	}

	/** Main method */
	public static void main(String[] args) {
		TestPanels frame = new TestPanels();
		frame.setTitle("U10416023計算機");
		frame.setSize(350, 400);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
