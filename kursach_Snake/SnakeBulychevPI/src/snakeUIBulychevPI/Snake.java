package snakeUIBulychevPI;

import uiUtilitiesBulychevPI.HelpDialog;
import uiUtilitiesBulychevPI.SavingObject;
import uiUtilitiesBulychevPI.SnakeField;
import uiUtilitiesBulychevPI.CustomOptions;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import utilitiesBulychevPI.Serializator;
import utilitiesBulychevPI.SerializatorIntoServer;
import utilitiesBulychevPI.Timer;

// Class to represent the GUI for the game.

public class Snake extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
        private SerializatorIntoServer ser=new SerializatorIntoServer();
        private Serializator serl=new Serializator();

        
        private String name=new String("");
	private SnakeField snakeField;
	private JMenu jMenu1;
	private JButton pauseGame;
	private JButton exitGame;
	private JMenuItem customSnake;
	private JMenu jMenu4;
	
	private JMenuItem controlsMenu;
	private JMenuItem infoMenu;
	private JButton stopGame;
	private JButton startGame;
	private JTextField scoreField;
	private JMenuItem startMenu;
	private JMenuItem eatAllMenu;

        private JMenuItem saveMenu;
        private JMenuItem loadMenu;
	//private JMenuItem viewSourceMenu;
	//private JMenu jMenu3;
	private JMenuItem exitMenu;
	private JSeparator jSeparator1;
	private JMenuItem stopMenu;
	private JMenuItem pauseMenu;
	private JTextField timeField;
	private JLabel timeL;
	private JTextField applesField;
	private JLabel applesL;
	private JLabel scoreL;
	private JPanel startsPanel;
	private JPanel controlPanel;
	private JMenu jMenu2;
	private JMenuBar jMenuBar;
        public boolean eatAll;
	private Timer timer = new Timer( timeField );
	private boolean time;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Snake inst = new Snake();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Snake() {
		super( "Змейка (Булычев П.И.)" );
		this.time = true;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setResizable( false );
			{
				jMenuBar = new JMenuBar();
				setJMenuBar(jMenuBar);
				{
					jMenu1 = new JMenu();
					jMenuBar.add(jMenu1);
					jMenu1.setText("Игра");
					{
						startMenu = new JMenuItem();
						jMenu1.add(startMenu);
						startMenu.setText("Начать игру");
						startMenu.addActionListener( this );
					}
                                        {
						eatAllMenu = new JMenuItem();
						jMenu1.add(eatAllMenu);
						eatAllMenu.setText("Съесть все яблоки");
						eatAllMenu.addActionListener( this );
					}
                                        {
                                            	saveMenu = new JMenuItem();
						jMenu1.add(saveMenu);
						saveMenu.setText("Сохранить игру");
						saveMenu.addActionListener( this );
                                                saveMenu.setEnabled( false );
                                        }
                                        {
                                            	loadMenu = new JMenuItem();
						jMenu1.add(loadMenu);
						loadMenu.setText("Восстановить игру");
						loadMenu.addActionListener( this );
                                        }
                                        
					{
						pauseMenu = new JMenuItem();
						jMenu1.add(pauseMenu);
						pauseMenu.setText("Пауза");
						pauseMenu.addActionListener( this );
						pauseMenu.setEnabled( false );
					}
					{
						stopMenu = new JMenuItem();
						jMenu1.add(stopMenu);
						stopMenu.setText("Стоп");
						stopMenu.addActionListener( this );
						stopMenu.setEnabled( false );
					}
					{
						jSeparator1 = new JSeparator();
						jMenu1.add(jSeparator1);
					}
					{
						exitMenu = new JMenuItem();
						jMenu1.add(exitMenu);
						exitMenu.setText("Выход");
						exitMenu.addActionListener( this );
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar.add(jMenu4);
					jMenu4.setText("Настройки");
					{
						customSnake = new JMenuItem();
						jMenu4.add(customSnake);
						customSnake.setText("Изменить змейку");
						customSnake.addActionListener( this );
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar.add(jMenu2);
					jMenu2.setText("Помощь");
					{
						infoMenu = new JMenuItem();
						jMenu2.add(infoMenu);
						infoMenu.setText("Об игре.");
						infoMenu.addActionListener( this );
					}
					{
						controlsMenu = new JMenuItem();
						jMenu2.add(controlsMenu);
						controlsMenu.setText("Управление");
						controlsMenu.addActionListener( this );
					}
					
				}
//	
			}
			{
				snakeField = new SnakeField( Color.BLACK, Color.GREEN, this );
				snakeField.setBackground( Color.WHITE );
			}
			{
				startsPanel = new JPanel();
				GroupLayout startsPanelLayout = new GroupLayout((JComponent)startsPanel);
				startsPanel.setLayout(startsPanelLayout);
				startsPanel.setBorder(BorderFactory.createTitledBorder("Статистика"));
				{
					scoreL = new JLabel();
					scoreL.setText("Счет:");
					scoreL.setFont(new java.awt.Font("Comic Sans MS",0,14));
				}
				{
					scoreField = new JTextField();
					scoreField.setText("0");
					scoreField.setHorizontalAlignment( JTextField.CENTER );
					scoreField.setEditable( false );
					scoreField.setBackground( Color.WHITE );
					scoreField.setFocusable( false );
				}
				{
					applesL = new JLabel();
					applesL.setText("  Съеденно:");
					applesL.setFont(new java.awt.Font("Comic Sans MS",0,14));
				}
				{
					timeField = new JTextField();
					timeField.setText("00:00:00");
					timeField.setHorizontalAlignment( JTextField.CENTER );
					timeField.setEditable( false );
					timeField.setBackground( Color.WHITE );
					timeField.setFocusable( false );
				}
				{
					timeL = new JLabel();
					timeL.setText("Время:");
					timeL.setFont(new java.awt.Font("Comic Sans MS",0,14));
				}
				{
					applesField = new JTextField();
					applesField.setText("0");
					applesField.setHorizontalAlignment( JTextField.CENTER );
					applesField.setEditable( false );
					applesField.setBackground( Color.WHITE );
					applesField.setFocusable( false );
				}
				startsPanelLayout.setHorizontalGroup(startsPanelLayout.createSequentialGroup()
					.addContainerGap(21, 21)
					.addGroup(startsPanelLayout.createParallelGroup()
					    .addGroup(startsPanelLayout.createSequentialGroup()
					        .addComponent(applesL, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE))
					    .addGroup(startsPanelLayout.createSequentialGroup()
					        .addPreferredGap(applesL, timeField, LayoutStyle.ComponentPlacement.INDENT)
					        .addGroup(startsPanelLayout.createParallelGroup()
					            .addGroup(startsPanelLayout.createSequentialGroup()
					                .addComponent(timeField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 0, Short.MAX_VALUE))
					            .addGroup(startsPanelLayout.createSequentialGroup()
					                .addComponent(scoreField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 0, Short.MAX_VALUE))
					            .addGroup(GroupLayout.Alignment.LEADING, startsPanelLayout.createSequentialGroup()
					                .addComponent(applesField, 0, 60, Short.MAX_VALUE)
					                .addGap(6))
					            .addGroup(startsPanelLayout.createSequentialGroup()
					                .addPreferredGap(timeField, scoreL, LayoutStyle.ComponentPlacement.INDENT)
					                .addGroup(startsPanelLayout.createParallelGroup()
					                    .addGroup(startsPanelLayout.createSequentialGroup()
					                        .addComponent(scoreL, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					                        .addGap(0, 0, Short.MAX_VALUE))
					                    .addComponent(timeL, GroupLayout.Alignment.LEADING, 0, 43, Short.MAX_VALUE))
					                .addGap(11)))
					        .addGap(11)))
					.addContainerGap(17, 17));
				startsPanelLayout.setVerticalGroup(startsPanelLayout.createSequentialGroup()
					.addComponent(scoreL, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(scoreField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(applesL, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(applesField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(timeL, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(timeField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 6, Short.MAX_VALUE));
			}
			{
				controlPanel = new JPanel();
				GroupLayout controlPanelLayout = new GroupLayout((JComponent)controlPanel);
				controlPanel.setLayout(controlPanelLayout);
				controlPanel.setBorder(BorderFactory.createTitledBorder("Control"));
				{
					startGame = new JButton();
					startGame.setText("Начать игру");
					startGame.setFocusable( false );
					startGame.addActionListener( this );
				}
				{
					pauseGame = new JButton();
					pauseGame.setText("Пауза");
					pauseGame.setFocusable( false );
					pauseGame.setEnabled( false );
					pauseGame.addActionListener( this );
				}
				{
					stopGame = new JButton();
					stopGame.setText("Стоп");
					stopGame.setFocusable( false );
					stopGame.setEnabled( false );
					stopGame.addActionListener( this );
				}
				{
					exitGame = new JButton();
					exitGame.setText("Выход");
					exitGame.setFocusable( false );
					exitGame.addActionListener( this );
				}
				controlPanelLayout.setHorizontalGroup(controlPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(controlPanelLayout.createParallelGroup()
					    .addComponent(startGame, GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE)
					    .addComponent(pauseGame, GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE)
					    .addComponent(stopGame, GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE)
					    .addComponent(exitGame, GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE))
					.addContainerGap());
				controlPanelLayout.setVerticalGroup(controlPanelLayout.createSequentialGroup()
					.addComponent(startGame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(pauseGame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(stopGame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
					.addComponent(exitGame, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE));
			}
			thisLayout.setVerticalGroup(thisLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
				    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    .addComponent(startsPanel, 0, 168, Short.MAX_VALUE)
				    .addGap(12))
				.addComponent(snakeField, GroupLayout.Alignment.LEADING, 0, 343, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(snakeField, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(controlPanel, GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE)
				    .addComponent(startsPanel, GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE))
				.addGap(7));
			pack();
			setSize(550, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if (e.getSource() == exitGame || e.getSource() == exitMenu ) {
                        pauseGame.setText( "Продолжить" );
			pauseMenu.setText( "Продолжить" );
			snakeField.pauseGame();
			timer.pauseTimer();
                        
			int ch = JOptionPane.showConfirmDialog( getContentPane(), "Сохранить перед закрытием?" );
			if ( ch == 1 ) {
				System.exit( 0 );
			}
                        if(ch==0){
                            name= JOptionPane.showInputDialog( getContentPane(), "Введите имя" );
                            System.exit( 0 );
                        }
		}
		else if ( e.getSource() == startGame || e.getSource() == startMenu ) {
			resetApples();
			resetScore();
			resetTime();
			snakeField.startGame();
			startGame.setEnabled( false );
			pauseGame.setEnabled( true );
			stopGame.setEnabled( true );
			startMenu.setEnabled( false );
			pauseMenu.setEnabled( true );
                        saveMenu.setEnabled( true );
			stopMenu.setEnabled( true );
                        eatAll=false;
			timer = new Timer( timeField );
			if ( time )
				timer.start();
		}
		else if ( ( e.getSource() == pauseGame && snakeField.isPaused() ) || ( e.getSource() == pauseMenu && snakeField.isPaused() ) ) {
			pauseGame.setText( "Пауза" );
			pauseMenu.setText( "Пауза" );
			snakeField.startGame();
			if ( time )
				timer.resumeTimer();
		}
                else if( e.getSource() == saveMenu){
                    
                    pauseGame.setText( "Продолжить" );
                    pauseMenu.setText( "Продолжить" );
                    snakeField.pauseGame();
                    timer.pauseTimer();
                    name=JOptionPane.showInputDialog( getContentPane(), "Введите имя" );
                    if(!name.equals("")){
                        SavingObject savObj=new SavingObject(snakeField, timer);
                        savObj.setScore(Integer.parseInt( scoreField.getText() ));
                        savObj.setApples(Integer.parseInt( applesField.getText() ));
                        try {
                            ser.serialization(savObj, name+".save");
                            serl.serialization(savObj, name+".save");
                        } catch (IOException ex) {
                            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                                             
                    }
                }
                else if( e.getSource() == loadMenu){
                    
                    snakeField.pauseGame();
                    
                    timer.pauseTimer();
                    name=JOptionPane.showInputDialog( getContentPane(), "Введите имя" );
                    if(!name.equals("")){
                        
                       try {
                           SavingObject savObj=new SavingObject(snakeField, timer);
                           ser.deserialization(name + ".save");
                           savObj=serl.deserialization(name + ".save");
                           
                           snakeField.load(savObj.getField());
                           timer.stopTimer();
                           timer = new Timer( timeField );
                           timer.SetTime(savObj.getTimer().getHours(), savObj.getTimer().getMinutes(),savObj.getTimer().getSeconds());
                           applesField.setText( Integer.toString(savObj.getApples()));
                           scoreField.setText( Integer.toString(savObj.getScore()));
                       } catch (InvalidObjectException ex) {
                            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        saveMenu.setEnabled( true );
                        pauseGame.setText( "Продолжить" );
                        pauseMenu.setText( "Продолжить" );
                        
			if ( time ){
                            timer.start();
                            timer.pauseTimer();
                        }
                        
                        
                        startGame.setEnabled( false );
                        pauseGame.setEnabled( true );
                        stopGame.setEnabled( true );
                        startMenu.setEnabled( false );
                        pauseMenu.setEnabled( true );
                        stopMenu.setEnabled( true );
                        
		
                                             
                    }
                }
                
		else if ( ( e.getSource() == pauseGame && !snakeField.isPaused() ) || ( e.getSource() == pauseMenu && !snakeField.isPaused() ) ) {
			pauseGame.setText( "Продолжить" );
			pauseMenu.setText( "Продолжить" );
			snakeField.pauseGame();
			timer.pauseTimer();
		}
		else if ( e.getSource() == stopGame || e.getSource() == stopMenu ) {
			snakeField.stopGame();
			snakeField.reset();
			startGame.setEnabled( true );
			pauseGame.setEnabled( false );
			pauseGame.setText( "Пауза" );
			stopGame.setEnabled( false );
			startMenu.setEnabled( true );
                        saveMenu.setEnabled( false );
			pauseMenu.setEnabled( false );
			pauseMenu.setText( "Пауза" );
			stopMenu.setEnabled( false );
			timer.stopTimer();
		}

		else if ( e.getSource() == infoMenu ) {
			JDialog dlgHelp = new HelpDialog( this, "Об игре.", "src/Game Info.txt" );
			dlgHelp.setVisible( true );
		}
		else if ( e.getSource() == controlsMenu ) {
			JDialog dlgHelp = new HelpDialog( this, "Управление", "src/Controls.txt" );
			dlgHelp.setVisible( true );
		}
		else if ( e.getSource() == customSnake ) {
			JFrame customSettings = new CustomOptions( this );
			customSettings.setVisible( true );
		}
                else if ( e.getSource() == eatAllMenu ) {
                    eatAll=true;
                    snakeField.stopGame();
                    snakeField.reset();
                    startGame.setEnabled( true );
                    pauseGame.setEnabled( false );
                    pauseGame.setText( "Пауза" );
                    stopGame.setEnabled( false );
                    startMenu.setEnabled( true );
                    saveMenu.setEnabled( false );
                    pauseMenu.setEnabled( false );
                    pauseMenu.setText( "Пауза" );
                    stopMenu.setEnabled( false );
                    timer.stopTimer();
                    JFrame customSettings = new CustomOptions( this );
                    customSettings.setVisible( true );
                    
                    
		}
	}
	
	public void resetButtons() {
		startGame.setEnabled( true );
		pauseGame.setEnabled( false );
		stopGame.setEnabled( false );
	}
	
	public void increaseScore() {
		scoreField.setText( Integer.toString( Integer.parseInt( scoreField.getText() ) + 25 ) );
	}
	public void resetScore() {
		scoreField.setText( "0" );
	}
	public void increaseApples() {
		applesField.setText( Integer.toString( Integer.parseInt( applesField.getText() ) + 1 ) );
	}
	public void resetApples() {
		applesField.setText( "0" );
	}
	public void resetTime() {
		timeField.setText( "00:00:00" );
	}
	public void stopTime() {
		timer.stopTimer();
	}
	public Color getHeadColor() {
		return snakeField.getHeadColor();
	}
	public Color getBodyColor() {
		return snakeField.getBodyColor();
	}
	public void changeHeadColor( Color c ) {
		snakeField.setHeadColor( c );
	}
	public void changeBodyColor( Color c ) {
		snakeField.setBodyColor( c );
	}
	public int getSpeed() {
		return snakeField.getSpeed();
	}
	public void setSpeed( int ms ) {
		snakeField.setSpeed( ms );
	}
	public int getApples() {
		return snakeField.getApples();
	}
	public void setApples( int a ) {
		snakeField.setApples( a );
	}
	public void enableWalls() {
		snakeField.activeWalls();
	}
	public void disableWalls() {
		snakeField.disableWalls();
	}
	public boolean wallsEnabled() {
		return snakeField.wallsEnabled();
	}
	public void activeTimer() {
		time = true;
	}
	public void deactiveTimer() {
		time = false;
	}
	public boolean isActiveTimer() {
		return time;
	}
        public void resetGame(){
            snakeField.stopGame();
            snakeField.reset();
            startGame.setEnabled( true );
            pauseGame.setEnabled( false );
            pauseGame.setText( "Пауза" );
            stopGame.setEnabled( false );
            startMenu.setEnabled( true );
            saveMenu.setEnabled( false );
            pauseMenu.setEnabled( false );
            pauseMenu.setText( "Пауза" );
            stopMenu.setEnabled( false );
            timer.stopTimer();
            resetApples();
            resetScore();
            resetTime();
            snakeField.startGame();
            startGame.setEnabled( false );
            pauseGame.setEnabled( true );
            stopGame.setEnabled( true );
            startMenu.setEnabled( false );
            pauseMenu.setEnabled( true );
            saveMenu.setEnabled( true );
            stopMenu.setEnabled( true );
            timer = new Timer( timeField );
            if ( time )
                    timer.start();
        }
}
