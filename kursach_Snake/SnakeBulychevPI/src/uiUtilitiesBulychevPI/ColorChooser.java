package uiUtilitiesBulychevPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A simple color chooser UI to change the color of a component.

public class ColorChooser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JColorChooser jColorChooser1;
	private JButton okButton;
	private Color col;
	private JTextField targetPanel;
	
	public ColorChooser( JTextField panel ) {
		super( "Выбор цвета" );
		this.targetPanel = panel;
		initGUI();
		this.setLocationRelativeTo( panel );
	}

	private void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		{
			jColorChooser1 = new JColorChooser();
		}
		{
			okButton = new JButton();
			okButton.setText("Подтвердить");
			okButton.addActionListener( this );
		}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jColorChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(okButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(0, 6, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jColorChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(171)
				        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 170, Short.MAX_VALUE)))
				.addContainerGap());
			pack();
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == okButton ) {
			col = jColorChooser1.getColor();
			targetPanel.setBackground( col );
			this.dispose();
		}
	}
}

