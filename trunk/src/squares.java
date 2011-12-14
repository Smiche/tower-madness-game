import java.awt.*;
import javax.swing.*;

public class squares {
	public static void main(String args[]){
	System.out.println("Zdrasti kak si");
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(null);
    
    JRadioButton rdbtnHi = new JRadioButton("Hi?");
    rdbtnHi.setBounds(62, 128, 39, 23);
    frame.getContentPane().add(rdbtnHi);
    
    JToggleButton tglbtnBlablabla = new JToggleButton("blablabla");
    tglbtnBlablabla.setBounds(139, 108, 121, 23);
    frame.getContentPane().add(tglbtnBlablabla);
    
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(10, 51, 307, 217);
    frame.getContentPane().add(tabbedPane);
    
    JButton btnKillMachine = new JButton("KILL MACHINE");
    tabbedPane.addTab("New tab", null, btnKillMachine, null);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("New tab", null, tabbedPane_1, null);
    
    Component glue = Box.createGlue();
    tabbedPane_1.addTab("New tab", null, glue, null);
    
    JLabel lblAloha = new JLabel("ALOHA!");
    lblAloha.setBounds(100, 11, 46, 14);
    frame.getContentPane().add(lblAloha);
    
    JTextArea textArea = new JTextArea();
    textArea.setBounds(225, 24, 136, 23);
    frame.getContentPane().add(textArea);
	}
}