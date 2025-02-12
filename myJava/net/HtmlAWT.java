package net;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class HtmlAWT extends JFrame implements ActionListener {

	JTextField jt;
	JButton btn;
	Choice ch;
	JTextPane editorPane;

	public HtmlAWT() {
		setTitle("HTML Example");
		setSize(550, 200);

		editorPane = new JTextPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");

		JScrollPane scrollPane = new JScrollPane(editorPane);
		getContentPane().add(scrollPane);
		JPanel p = new JPanel();
		p.add(jt = new JTextField(30));
		p.add(btn = new JButton("SEND"));
		p.add(ch = new Choice());
		ch.add("RED");
		ch.add("GREEN");
		ch.add("BLUE");
		btn.addActionListener(this);
		jt.addActionListener(this);
		jt.requestFocus();
		add(p, BorderLayout.SOUTH);
		setVisible(true);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String color = ch.getSelectedItem();
		String str = "<font color='" + color + "'>" + jt.getText() + "</font><br>";
		editorPane.setText(editStr(editorPane.getText()+str));
		jt.setText("");
		jt.requestFocus();
	}
	
	public String editStr(String str) {
		str =  str.replace("<html>", "");
		str =  str.replace("<head>", "");
		str =  str.replace("</head>", "");
		str =  str.replace("<body>", "");
		str =  str.replace("</body>", "");
		str =  str.replace("</html>", "");
		return str;
	}

	public static void main(String[] args) {
		HtmlAWT htmlExample = new HtmlAWT();
		htmlExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
