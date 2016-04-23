/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayqueue;

/**
 *
 * @author Zachary Murphy
 */
import java.awt.event.*;
import java.awt.*;
import java.nio.charset.Charset;
import javax.swing.*;
import java.util.*;

/**
   This program is a graphical user interface
   to the ArrQueue class.
*/

public class GUIQueueDemo extends JFrame
{    
    private JTextField [ ] qViewTextField;
    private ArrayQueue queue;
    
    private JTextField commandEntryTextField;
    
    
    /**
      Constructor.
    */    
    
    GUIQueueDemo()
    {   
        setTitle("Array Based Dequeue");       
        
        // Create queue
        queue = new ArrayQueue(10);
        int qSize = queue.capacity();
        
        // Create view for queue and put 
		  // it at top of frame.
        qViewTextField = new JTextField[qSize];        
        LayoutManager layout = new GridLayout(1, qSize);
        JPanel qViewPanel = new JPanel(layout);
        for (int k = 0; k < qViewTextField.length; k++)
        {
            qViewTextField[k] = new JTextField();
            JTextField t = qViewTextField[k];
            qViewPanel.add(t);
            t.setEditable(false);
            t.setBackground(Color.WHITE);
        }
        add(qViewPanel, BorderLayout.NORTH);
        
        // Create commandEntryTextField and put it
		  // in a panel at the bottom of the frame.
        commandEntryTextField = new JTextField(15);
		  ActionListener lis = new CmdTextListener();
        commandEntryTextField.addActionListener(lis);
        
        //to list the available commands
        JPanel avialableCommands = new JPanel();
        JLabel title = new JLabel("Available Commands: ");
        JLabel addfront = new JLabel ("addfront");
        JLabel addrear = new JLabel("addrear");
        JLabel removerear = new JLabel ("removerear");
        JLabel removefront = new JLabel ("removefront");
        
        //adding to panel for available commands
        avialableCommands.add(title);
        avialableCommands.add(addfront);
        avialableCommands.add(addrear);
        avialableCommands.add(removerear);
        avialableCommands.add(removefront);
        //adding panel west 
        add(avialableCommands,BorderLayout.WEST);
        

        JPanel commandEntryPanel = new JPanel();
        commandEntryPanel.add(new JLabel("Command: "));        
        commandEntryPanel.add(commandEntryTextField);
        add(commandEntryPanel, BorderLayout.SOUTH);         
        
        // Finish setting up frame
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        
    }
    
    /**
       This private inner class responds to the 
		 commands typed into command entry text field. 
    */
    private class CmdTextListener 
	       implements ActionListener
	 {
       public void actionPerformed(ActionEvent evt)
       {
		     String cmdText = commandEntryTextField.getText(); 
           Scanner sc = new Scanner(cmdText);
           if (!sc.hasNext()) return;
           String cmd = sc.next();
           //if addfront is cmd
           if(cmd.equals("addfront")){
               String item = sc.next();
               queue.addFront(item);
               refresh(queue.toString());
               
           }
           //if addrear is cmd
           if(cmd.equals("addrear")){
               String item2 = sc.next();
               queue.addRear(item2);
               refresh(queue.toString());
           }
           //if removefront is cmd
           if(cmd.equals("removefront")){
               queue.removeFront();
               refresh(queue.toString());
           }
           //if removerear is cmd
           if(cmd.equals("removerear")){
               queue.removeRear();
               refresh(queue.toString());
           }
        }
	 }
    
    /**
       The refresh  method stores the current 
		 queue entries in the corresponding text 
		 fields of the queue view.
       @param The string encoding the current
		 contents of the queue.
    */
    
    private void refresh(String qStr)
    {
        Scanner sc = new Scanner(qStr);
        sc.nextLine();    // Skip first, rear info      
        while (sc.hasNext())
        {
            int k = sc.nextInt();
            String qEntry = sc.next();
            qViewTextField[k].setText(qEntry);
        }
    }

    /**
       The main method creates the frame so the user
       can start interacting with the program.
    */
    
    public static void main(String [] arg)
    {
        new GUIQueueDemo();
    }
}