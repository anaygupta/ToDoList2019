package cse360.todo.Window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cse360.todo.ListBox.ListBox;

public class StartEndDateWindow extends JDialog{
	
	private static final long serialVersionUID = -1760487918848205500L;
	private ListBox box;
	
	public StartEndDateWindow(ListBox box) {
		this.box = box;
	}
	
	private void build() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setTitle("Start and End Dates");
		this.setSize(screenSize.width / 6, screenSize.height / 6);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		JPanel datesPanel = datesPanel();
		
		this.add(datesPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setResizable(true);
		this.setVisible(true);
	}
	
	public JPanel datesPanel() {
		JPanel datesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		
		Font theFont = new Font(Font.DIALOG, Font.BOLD, 18);
		//0 0
		c.gridy = 0;
		c.gridx = 0;
		JLabel startDate = new JLabel("Start Date: " + box.getSaveData().getStatus().getStartDate().getMonth() + "/" + box.getSaveData().getStatus().getStartDate().getDay() + "/" + box.getSaveData().getStatus().getStartDate().getYear());
		startDate.setHorizontalAlignment(SwingConstants.CENTER);
		startDate.setFont(theFont);
		datesPanel.add(startDate, c);
		
		//1 0
		c.gridy = 1;
		c.gridx = 0;
		c.weighty = 1.0;
		JLabel endDate = new JLabel("End Date: " + box.getSaveData().getStatus().getEndDate().getMonth() + "/" + box.getSaveData().getStatus().getEndDate().getDay() + "/" + box.getSaveData().getStatus().getEndDate().getYear());
		endDate.setHorizontalAlignment(SwingConstants.CENTER);
		endDate.setFont(theFont);
		datesPanel.add(endDate, c);
		
		return datesPanel;
	}
	
	public void start(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				build();
			}
		});
		
	}
}
