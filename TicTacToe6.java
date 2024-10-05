import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe6 {
	
	
	JFrame frame=new JFrame("Tic Tac Toe");
	JPanel[] panel=new JPanel[3];
	JLabel label=new JLabel("First Player turn...");
	JButton[] button=new JButton[9];
	JButton reset=new JButton("Reset");
	JButton exit=new JButton("Exit");
	
	//code to load both images
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	int player=1, count=0;
	boolean winnerFound=false;
	
	public TicTacToe6() {
		frame.setSize(500,580);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addPanels();
		frame.setVisible(true);	
	}
	
	private void addPanels() {
		for(int i=0;i<3;i++)
		{
			panel[i]=new JPanel();
			frame.add(panel[i]);
		}
	
		panel[0].setBounds(50,20,400,40);
		panel[0].setBackground(Color.blue);
		panel[1].setBounds(50,90,400,360);
		panel[2].setBounds(50,480,400,40);	
		addLabel();
		
		}
	
	private void addLabel() {
		panel[0].add(label);
		label.setFont(new Font("Arial",1,25));
		label.setForeground(Color.black);
		addButtons();
		
	}
	
	private void addButtons() {
		panel[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0;i<9;i++) 
		{
			button[i]=new JButton();
			button[i].addActionListener(listener);
			button[i].setBackground(Color.GREEN);
			panel[1].add(button[i]);	
			
		}
		addResetAndExitButton();
	}
	
	private void addResetAndExitButton() {
		panel[2].add(reset);
		panel[2].add(exit);
		panel[2].setOpaque(false);
		Font font=new Font("Arial",1,15);
		reset.setFont(font);
		exit.setFont(font);
		exit.setForeground(Color.red);
		reset.addActionListener(new ResetListener());
		exit.addActionListener(new ExitListener());
		reset.setEnabled(false);	
	}
	
	//creating listener for all tic buttons
	class TicListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			//JOptionPane.showMessageDialog(frame,"Button clicked");
			JButton bb=(JButton)e.getSource();
			if(player==1)//when first player will click on button
			{
				bb.setIcon(icon1);;
				label.setText("Second player turn...");
				label.setForeground(Color.white);
				panel[0].setBackground(Color.blue);
				player=2;
			}
			else if(player==2)//when second player will click on button
			{
				bb.setIcon(icon2);
				label.setText("First player turn...");
				label.setForeground(Color.white);
				panel[0].setBackground(Color.orange);
				player=1;
			}
			bb.setEnabled(false);
			findWinner();
			count++;
			if(count==9 && !winnerFound)
			{
				reset.setEnabled(true);
				label.setText("GAME OVER");
				label.setForeground(Color.red);
				panel[0].setBackground(Color.black);
				JOptionPane.showMessageDialog(frame,"No one has won...");
				
			}
		}
		
		private void findWinner() {
			//first row
			if(button[0].getIcon()==icon1 && button[1].getIcon()==icon1 && button[2].getIcon()==icon1)
				announceWinner(0,1,2);
			else if(button[0].getIcon()==icon2 && button[1].getIcon()==icon2 && button[2].getIcon()==icon2)
				announceWinner(0,1,2);
			//second row
			else if(button[3].getIcon()==icon1 && button[4].getIcon()==icon1 && button[5].getIcon()==icon1)
				announceWinner(3,4,5);
			else if(button[3].getIcon()==icon2 && button[4].getIcon()==icon2 && button[5].getIcon()==icon2)
				announceWinner(3,4,5);
			
			//third  row
			else if(button[6].getIcon()==icon1 && button[7].getIcon()==icon1 && button[8].getIcon()==icon1)
				announceWinner(6,7,8);
			else if(button[6].getIcon()==icon2 && button[7].getIcon()==icon2 && button[8].getIcon()==icon2)
				announceWinner(6,7,8);
			
			//first column 
			else if(button[0].getIcon()==icon1 && button[3].getIcon()==icon1 && button[6].getIcon()==icon1)
				announceWinner(0,3,6);
			else if(button[0].getIcon()==icon2 && button[3].getIcon()==icon2 && button[6].getIcon()==icon2)
				announceWinner(0,3,6);
			
			//second column 
			else if(button[1].getIcon()==icon1 && button[4].getIcon()==icon1 && button[7].getIcon()==icon1)
				announceWinner(1,4,7);
			else if(button[1].getIcon()==icon2 && button[4].getIcon()==icon2 && button[7].getIcon()==icon2)
				announceWinner(1,4,7);
			
			//third column 
			else if(button[2].getIcon()==icon1 && button[5].getIcon()==icon1 && button[8].getIcon()==icon1)
				announceWinner(2,5,8);
			else if(button[2].getIcon()==icon2 && button[5].getIcon()==icon2 && button[8].getIcon()==icon2)
				announceWinner(2,5,8);
			
			//first diagonal
			else if(button[0].getIcon()==icon1 && button[4].getIcon()==icon1 && button[8].getIcon()==icon1)
				announceWinner(0,4,8);
			else if(button[1].getIcon()==icon2 && button[4].getIcon()==icon2 && button[8].getIcon()==icon2)
				announceWinner(0,4,8);
			
			//second diagonal 
			else if(button[2].getIcon()==icon1 && button[4].getIcon()==icon1 && button[6].getIcon()==icon1)
				announceWinner(2,4,6);
			else if(button[2].getIcon()==icon2 && button[4].getIcon()==icon2 && button[6].getIcon()==icon2)
				announceWinner(2,4,6);
		}//end of findWinner() method
		
		
		
		private void disableButtons() {
			for(int i=0;i<9;i++) {
				button[i].setEnabled(false);
			}
		}//end of disabled button
		
		
		private void announceWinner(int i,int j,int k) {
			reset.setEnabled(true);
			winnerFound=true;
			button[i].setBackground(Color.black);
			button[j].setBackground(Color.black);
			button[k].setBackground(Color.black);
			label.setText("GAME OVER");
			label.setForeground(Color.black);
			panel[0].setBackground(Color.red);
			disableButtons();
			if(player==2)
				JOptionPane.showMessageDialog(frame,"First player has won...");
			else if(player==1)
				JOptionPane.showMessageDialog(frame,"Second player has won...");
		}
	}
	
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent args)
		{
			for(int i=0;i<9;i++)
			{
				button[i].setIcon(null);
				button[i].setBackground(Color.yellow);
				button[i].setEnabled(true);
			}
			count=0;
			player=1;
			winnerFound=false;
			label.setText("First player turn");
			label.setForeground(Color.BLUE);
			panel[0].setBackground(Color.white);
			reset.setEnabled(false);
		}	
	}
	
	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent args)
		{
			int ch=JOptionPane.showConfirmDialog(frame, "Are you sure to exit");
			if(ch==0)
			{
				System.exit(0);//exit method of system class will terminate  JVM
			}
		
		}
	}
		
	public static void main(String[] args) {

		new TicTacToe6();
		}
	}


	


