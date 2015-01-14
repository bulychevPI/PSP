package uiUtilitiesBulychevPI;

import snakeUIBulychevPI.Snake;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

// Class to display a simple about dialog.

public class CustomOptions extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -3423319978001459835L;
	private JButton okBtn;
	private JTextField appleNumField;
	private JTextField gameSpeedField;
	private JCheckBox disableTimer;
	private JCheckBox enableTime;
	private JLabel jLabel6;
	private JCheckBox disableTransparency;
	private JCheckBox enableTransparency;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JLabel jLabel2;
	private JPanel gameSettingsPanel;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JButton changeBodyCol;
	private JLabel jLabel3;
	private JButton changeHeadCol;
	private JLabel jLabel1;
	private JPanel settingsPanel;
	
	private Snake parent;

	public CustomOptions( Snake parent ) {
		this.parent = parent;
		setTitle( "Настройки" );
		
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
			okBtn = new JButton();
			okBtn.setText("Подтвердить");
			okBtn.setFocusable( false );
			okBtn.addActionListener( this );
		}
		{
			gameSettingsPanel = new JPanel();
			GroupLayout gameSettingsPanelLayout = new GroupLayout((JComponent)gameSettingsPanel);
			gameSettingsPanel.setLayout(gameSettingsPanelLayout);
			gameSettingsPanel.setBorder(BorderFactory.createTitledBorder("Настройки игры"));
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Скорость:");
			}
			{
				jLabel4 = new JLabel();
				jLabel4.setText("# яблок:");
			}
			{
				jLabel5 = new JLabel();
				jLabel5.setText("Сквозь стены:");
			}
			{
				enableTransparency = new JCheckBox();
				enableTransparency.setText("Да");
				if ( !parent.wallsEnabled() )
					enableTransparency.setSelected( true );
			}
			{
				disableTransparency = new JCheckBox();
				disableTransparency.setText("Нет");
				if ( parent.wallsEnabled() )
					disableTransparency.setSelected( true );
			}
			{
				jLabel6 = new JLabel();
				jLabel6.setText("Время:");
			}
			{
				appleNumField = new JTextField();
				appleNumField.setText( Integer.toString( parent.getApples() ) );
				appleNumField.setHorizontalAlignment( JTextField.CENTER );
			}
			{
				gameSpeedField = new JTextField();
				gameSpeedField.setText( Integer.toString( parent.getSpeed() ) );
				gameSpeedField.setHorizontalAlignment( JTextField.CENTER );
			}
			{
				enableTime = new JCheckBox();
				enableTime.setText("Вкл");
				if ( parent.isActiveTimer() )
					enableTime.setSelected( true );
			}
			{
				disableTimer = new JCheckBox();
				disableTimer.setText("Выкл");
				if ( !parent.isActiveTimer() )
					disableTimer.setSelected( true );
			}
			ButtonGroup group = new ButtonGroup();
			group.add( enableTransparency );
			group.add( disableTransparency );
			ButtonGroup group2 = new ButtonGroup();
			group2.add( enableTime );
			group2.add( disableTimer );
			gameSettingsPanelLayout.setHorizontalGroup(gameSettingsPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(gameSettingsPanelLayout.createParallelGroup()
				    .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
				.addGap(23)
				.addGroup(gameSettingsPanelLayout.createParallelGroup()
				    .addGroup(gameSettingsPanelLayout.createSequentialGroup()
				        .addGroup(gameSettingsPanelLayout.createParallelGroup()
				            .addComponent(enableTransparency, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, gameSettingsPanelLayout.createSequentialGroup()
				                .addComponent(enableTime, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				                .addGap(6)))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(gameSettingsPanelLayout.createParallelGroup()
				            .addComponent(disableTransparency, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, gameSettingsPanelLayout.createSequentialGroup()
				                .addComponent(disableTimer, 0, 70, Short.MAX_VALUE)
				                .addGap(4))))
				    .addGroup(GroupLayout.Alignment.LEADING, gameSettingsPanelLayout.createSequentialGroup()
				        .addComponent(gameSpeedField, 0, 98, Short.MAX_VALUE)
				        .addGap(17))
				    .addGroup(GroupLayout.Alignment.LEADING, gameSettingsPanelLayout.createSequentialGroup()
				        .addComponent(appleNumField, 0, 98, Short.MAX_VALUE)
				        .addGap(17))));
			gameSettingsPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {appleNumField, gameSpeedField});
			gameSettingsPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {enableTransparency, disableTransparency});
			gameSettingsPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {enableTime, disableTimer});
			gameSettingsPanelLayout.setVerticalGroup(gameSettingsPanelLayout.createSequentialGroup()
				.addGroup(gameSettingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(gameSpeedField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(gameSettingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(appleNumField, GroupLayout.Alignment.BASELINE, 0, 18, Short.MAX_VALUE)
				    .addComponent(jLabel4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(gameSettingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(disableTransparency, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(enableTransparency, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(gameSettingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(disableTimer, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				    .addComponent(enableTime, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(13, 13));
			gameSettingsPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {appleNumField, gameSpeedField});
		}
		{
			settingsPanel = new JPanel();
			GroupLayout settingsPanelLayout = new GroupLayout((JComponent)settingsPanel);
			settingsPanel.setLayout(settingsPanelLayout);
			settingsPanel.setBorder(BorderFactory.createTitledBorder("Настройки цвета"));
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Голова змейки");
			}
			{
				changeHeadCol = new JButton();
				changeHeadCol.setText("Задать");
				changeHeadCol.setFocusable( false );
				changeHeadCol.addActionListener( this );
			}
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Тело змейки");
			}
			{
				jTextField2 = new JTextField();
				jTextField2.setEditable( false );
				jTextField2.setBackground( parent.getHeadColor() );
			}
			{
				changeBodyCol = new JButton();
				changeBodyCol.setText("Задать");
				changeBodyCol.setFocusable( false );
				changeBodyCol.addActionListener( this );
			}
			{
				jTextField1 = new JTextField();
				jTextField1.setEditable( false );
				jTextField1.setBackground( parent.getBodyColor() );
			}
			settingsPanelLayout.setVerticalGroup(settingsPanelLayout.createSequentialGroup()
				.addGroup(settingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jTextField2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				    .addComponent(changeHeadCol, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(settingsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jTextField1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(changeBodyCol, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(17, 17));
			settingsPanelLayout.setHorizontalGroup(settingsPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(settingsPanelLayout.createParallelGroup()
				    .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(settingsPanelLayout.createParallelGroup()
				    .addComponent(jTextField2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jTextField1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
				.addGap(0, 23, Short.MAX_VALUE)
				.addGroup(settingsPanelLayout.createParallelGroup()
				    .addComponent(changeBodyCol, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				    .addComponent(changeHeadCol, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			settingsPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jTextField2, jTextField1});
		}
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addGap(6)
			.addComponent(settingsPanel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(gameSettingsPanel, 0, 127, Short.MAX_VALUE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(okBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap());
		thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup()
			    .addComponent(settingsPanel, GroupLayout.Alignment.LEADING, 0, 268, Short.MAX_VALUE)
			    .addComponent(gameSettingsPanel, GroupLayout.Alignment.LEADING, 0, 268, Short.MAX_VALUE)
			    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			        .addGap(88)
			        .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 90, Short.MAX_VALUE)))
			.addContainerGap());
		setSize( 300, 300 );
		pack();
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == okBtn )	{
			parent.changeHeadColor( jTextField2.getBackground() );
			parent.changeBodyColor( jTextField1.getBackground() );
			parent.setSpeed( Integer.parseInt( gameSpeedField.getText() ) );
			parent.setApples( Integer.parseInt( appleNumField.getText() ) );
			if ( enableTransparency.isSelected() )
				parent.disableWalls();
			else {
				parent.enableWalls();
			}
			if ( enableTime.isSelected() )
				parent.activeTimer();
			else {
				parent.deactiveTimer();
			}
                        
			this.dispose();
                        parent.resetGame();
                        
		}
		else if ( e.getSource() == changeHeadCol ) {
			ColorChooser ch = new ColorChooser( jTextField2 );
			ch.setVisible( true );
		}
		else if ( e.getSource() == changeBodyCol ) {
			ColorChooser ch = new ColorChooser( jTextField1 );
			ch.setVisible( true );
		}
	}
}

