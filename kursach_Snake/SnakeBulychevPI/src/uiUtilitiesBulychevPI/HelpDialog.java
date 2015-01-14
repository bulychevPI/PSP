package uiUtilitiesBulychevPI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

// Class to display a simple help dialog.


public class HelpDialog extends JDialog {
	
	private static final long serialVersionUID = -3423319978001459835L;
	private JTextArea helpField;
	private JScrollPane jScrollPane1;
	private JButton closeButton;

	public HelpDialog( JFrame parent, String title, String helpfile ) {
		super( parent, title, true );
		
		Listener events = new Listener( this );
		setResizable( false );

		addWindowListener( new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					Window aboutDialog = e.getWindow();
					aboutDialog.dispose();
				}
			}
		);
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		setLocationRelativeTo( parent );
		{
			jScrollPane1 = new JScrollPane();
			{
				helpField = new JTextArea();
				jScrollPane1.setViewportView(helpField);
				helpField.setEditable( false );
				helpField.setFont(new java.awt.Font("Lucida Console",0,11));
                                

			}
		}
		{
			closeButton = new JButton();
			closeButton.setText("Закрыть");
			closeButton.addActionListener( events );
		}
		thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap(12, 12)
			.addGroup(thisLayout.createParallelGroup()
			    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
			    .addGroup(thisLayout.createSequentialGroup()
			        .addGap(175)
			        .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
			.addContainerGap(12, 12));
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addContainerGap(12, 12)
			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
			.addGap(13)
			.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(11, 11));
		setSize( 470, 400 );
		pack();
		try {
			insertHelpTextFromFile( new Scanner( new FileInputStream( helpfile ) ) );
		} catch ( FileNotFoundException e1 ) {
			JOptionPane.showMessageDialog( getContentPane(), "Help file cannot be located " + e1.getMessage() );
		}
	}
	
	// Insert the text from the help document into the text field.
	private void insertHelpTextFromFile( Scanner scanner ) {
		while ( scanner.hasNext() ) {
			helpField.append( scanner.nextLine() + "\n" );
		}
	}

	/** Inner class to handle button events. */
	private class Listener implements ActionListener {
		
		private JDialog parent;
		
		Listener( JDialog parent ) {
			this.parent = parent;
		}
		
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == closeButton ) {
				parent.dispose();
			}
		}
	}
}