package chacMool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class MenuRockPaperScissorsLizardSpock implements Runnable {
  JTextArea output;
  JScrollPane scrollPane;
  java.util.Random roll = new java.util.Random();

  protected boolean rockPaperScissors = true;
  protected boolean rockPaperScissorsLizardSpock = false;
  
  protected int score_you = 0;
  protected int score_computer = 0;
  protected boolean cheat_enabled = false;
  protected boolean score_board_enabled = false;

  protected JFrame frame;
  protected JPanel contentPane;
  protected JPanel scoreBoard;
  
  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new MenuRockPaperScissorsLizardSpock());
  }

  @Override
  public void run() {
    createAndShowGUI();
  }

/**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  private void createAndShowGUI() {
    //Create and set up the window.
    frame = new JFrame("Rock Paper Scissors");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setJMenuBar(createMenuBar());
    frame.setContentPane(createContentPane());

    //Display the window.
    //frame.setSize(450, 260);
    frame.setVisible(true);
  }

  protected boolean enableRockPaperScissors() {
    boolean changed = !rockPaperScissors;
    rockPaperScissors = true;
    rockPaperScissorsLizardSpock = false;
    
    if (changed) {
      frame.setContentPane(createContentPane());
      frame.validate();
      frame.repaint();
    }

    return changed;
  }
  
  protected boolean enableRockPaperScissorsLizardSpock() {
    boolean changed = rockPaperScissors;
    
    rockPaperScissors = false;
    rockPaperScissorsLizardSpock = true;
    
    if (changed) {
        frame.setContentPane(createContentPane());
        frame.validate();
        frame.repaint();
    }

    return changed;
  }
  
  protected boolean enableCheat(boolean flag) {
    boolean formerly = cheat_enabled;
    cheat_enabled = flag;
    return formerly;
  }

  protected boolean enableScoreBoard(boolean flag) {
    boolean formerly = score_board_enabled;
    score_board_enabled = flag;
    return formerly;
  }

  public JMenuBar createMenuBar() {
    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    //Create the menu bar.
    menuBar = new JMenuBar();

    //Build the first menu.
    menu = new JMenu("Menu");
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription( "The only menu in this program that has menu items");
    menuBar.add(menu);

    /*
    //a group of JMenuItems
    menuItem = new JMenuItem("A text-only menu item", KeyEvent.VK_T);
    //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
    menu.add(menuItem);

    ImageIcon icon = createImageIcon("/images/middle.gif");
    menuItem = new JMenuItem("Both text and icon", icon);
    menuItem.setMnemonic(KeyEvent.VK_B);
    menu.add(menuItem);

    menuItem = new JMenuItem(icon);
    menuItem.setMnemonic(KeyEvent.VK_D);
    menu.add(menuItem);
    */

    //a group of radio button menu items
    menu.addSeparator();
    ButtonGroup group = new ButtonGroup();

    rbMenuItem = new JRadioButtonMenuItem("Rock Paper Scissors");
    rbMenuItem.setSelected(true);
    rbMenuItem.setMnemonic(KeyEvent.VK_R);
    rbMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        enableRockPaperScissors();
      }
    });
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Lizard Spock");
    rbMenuItem.setMnemonic(KeyEvent.VK_L);
    rbMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        enableRockPaperScissorsLizardSpock();
      }
    });
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    //a group of check box menu items
    menu.addSeparator();
    cbMenuItem = new JCheckBoxMenuItem("Keep Score");
    cbMenuItem.setMnemonic(KeyEvent.VK_S);
    cbMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        enableScoreBoard(((AbstractButton) evt.getSource()).getModel().isSelected());
          if (score_board_enabled) {
            scoreBoard = new JPanel();
            contentPane.add(scoreBoard, BorderLayout.SOUTH);
            JLabel lab = new JLabel("this is the score");
            scoreBoard.add(lab);
          } else {
            if (null != scoreBoard)
              contentPane.remove(scoreBoard);
            scoreBoard = null;
          }
          frame.validate();
          frame.repaint();
      }
    });
    menu.add(cbMenuItem);

    cbMenuItem = new JCheckBoxMenuItem("Cheat");
    cbMenuItem.setMnemonic(KeyEvent.VK_C);
    cbMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        enableCheat(((AbstractButton) evt.getSource()).getModel().isSelected());
      }
    });
    menu.add(cbMenuItem);

    /*
    //a submenu
    menu.addSeparator();
    submenu = new JMenu("A submenu");
    submenu.setMnemonic(KeyEvent.VK_S);

    menuItem = new JMenuItem("An item in the submenu");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
    submenu.add(menuItem);

    menuItem = new JMenuItem("Another item");
    submenu.add(menuItem);
    menu.add(submenu);
    */

    //Build second menu in the menu bar.
    menu = new JMenu("About");
    menu.setMnemonic(KeyEvent.VK_N);
    menu.getAccessibleContext().setAccessibleDescription("What's this all about");
    menuBar.add(menu);

    menu.addSeparator();
    menuItem = new JMenuItem("Help");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        JDialog dialog = rockPaperScissorsLizardSpock ? new HelpRockPaperScissorsLizardSpockDialog(frame) : new HelpRockPaperScissorsDialog(frame);
        dialog.setSize(rockPaperScissorsLizardSpock ? new Dimension(400,250) : new Dimension(300,200));
        // dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
      }
    });
    menu.add(menuItem);

    menuItem = new JMenuItem("About");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        JDialog dialog = new AboutDialog(frame);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
      }
    });
    menu.add(menuItem);

    return menuBar;
  }

  public Container createContentPane() {
    //Create the content-pane-to-be.
    contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);

    //Create a scrolled text area.
    output = new JTextArea(5, 30);
    output.setEditable(false);
    scrollPane = new JScrollPane(output);

    //Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.CENTER);

    JPanel button_rack = new JPanel();
    contentPane.add(button_rack, BorderLayout.NORTH);

    if (score_board_enabled) {
      scoreBoard = new JPanel();
      contentPane.add(scoreBoard, BorderLayout.SOUTH);
      JLabel lab = new JLabel("this is the score");
      scoreBoard.add(lab);
    } else {
      if (null != scoreBoard)
        contentPane.remove(scoreBoard);
      scoreBoard = null;
    }

    javax.swing.JButton rock = new javax.swing.JButton("");
    rock.setIcon(createImageIcon("/button/rock.jpg", 75));
    button_rack.add(rock);
    rock.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        contest(0);
      }
    });
    
    javax.swing.JButton paper = new javax.swing.JButton("");
    paper.setIcon(createImageIcon("/button/paper.jpg", 75));
    button_rack.add(paper);
    paper.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        contest(1);
      }
    });

    javax.swing.JButton scissors = new javax.swing.JButton("");
    scissors.setIcon(createImageIcon("/button/scissors.jpg", 75));
    button_rack.add(scissors);
    scissors.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        contest(2);
      }
    });
    
    if (rockPaperScissorsLizardSpock) {
      javax.swing.JButton lizard = new javax.swing.JButton("");
      lizard.setIcon(createImageIcon("/button/lizard.jpg", 75));
      button_rack.add(lizard);
      lizard.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          contest(3);
        }
      });

      javax.swing.JButton spock = new javax.swing.JButton("");
      spock.setIcon(createImageIcon("/button/spock.jpg", 75));
      button_rack.add(spock);
      spock.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          contest(4);
        }
      });
      frame.setSize(525, 260);
    } else {
      frame.setSize(450, 260);
    }

    return contentPane;
  }

  protected static ImageIcon createImageIcon(String path) {
    return createImageIcon(path, null);
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path, Integer resize) {
    java.net.URL imgURL = MenuRockPaperScissorsLizardSpock.class.getResource(path);
    if (imgURL != null) {
      if (null == resize)
        return new ImageIcon(imgURL);
      
      ImageIcon icon = new ImageIcon(imgURL);
      Image img = icon.getImage();
      Image newimg = img.getScaledInstance(resize, (int) (((1.0*resize)*img.getHeight(null))/img.getWidth(null)), java.awt.Image.SCALE_SMOOTH);
      return new ImageIcon(newimg);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }

  protected void contest(int choice) {
    boolean winner = rockPaperScissors ? contest1(choice) : contest2(choice);
    if (winner)
      score_you++;
    else
      score_computer++;
  }
    
  protected boolean contest1(int choice) {
    String[] weapon = { "rock", "paper", "scissors" };
    String[][] winsay = {
      { "tie", "Paper covers Rock", "Rock breaks Scissors" },
      { "Paper covers Rock", "tie", "Scissors cut Paper" },
      { "Rock breaks Scissors", "Scissors cut Paper", "tie" }
    };
    int computer = cheat_enabled ? -1 : (0 == roll.nextInt(2) ? -1 : 1);

    computer = (choice + computer + 3) % 3;
    boolean looser = 1 == computer-choice || -2 == computer-choice;
    String result = looser ? "you loose" : "you win";
    String analysis = winsay[choice][computer];
    String synopsis = "you picked " + weapon[choice] + ", computer picked " + weapon[computer] + ": " + analysis + " - " + result + "!" ;
    output.append(synopsis + "\n");
    return !looser;
  }

  protected boolean contest2(int choice) {
    String[] weapon = { "rock", "paper", "scissors", "lizard", "spock" };
    int[][] win = {
      { 0, 0, 1, 1, 0 },
      { 1, 0, 0, 0, 1 },
      { 0, 1, 0, 1, 0 },
      { 0, 1, 0, 0, 1 },
      { 1, 0, 1, 0, 0 }
    };
    String[][] winsay = {
      { "tie", "Paper covers Rock", "Rock breaks Scissors", "Rock crushes lizard", "Spock phasers Rock" },
      { "Paper covers Rock", "tie", "Scissors cut Paper", "Lizard eats Paper", "Paper disproves Spock" },
      { "Rock breaks Scissors", "Scissors cut Paper", "tie", "Scissors decapitate Lizard", "Spock smashes Scissors" },
      { "Rock crushes Lizard", "Lizard eats Paper", "Scissors decapitate Lizard", "tie", "Lizard poisons Spock" },
      { "Spock phasers Rock", "Paper disproves Spock", "Spock smashes Scissors", "Lizard poisons Spock", "tie" }
    };
    
    int computer;
    if (cheat_enabled) {
      int cc = roll.nextInt(2);
      for (computer = 0; computer < 5 && cc >= 0; computer++)
        if (1 == win[choice][computer]) {
          // this is a win.  there are two wins.  randomize the win between the two of them.
          cc--;
          if (0 > cc) break;
        }
    } else {
      computer = roll.nextInt(4);
      computer = 2 > computer ? computer-2 : computer-1;
      computer = (choice + computer + 5) % 5;
    }
    
    boolean winner = 1 == win[choice][computer];
    String analysis = winsay[choice][computer];
    String result = winner ? "you win" : "you loose";
    String synopsis = "you picked " + weapon[choice] + ", computer picked " + weapon[computer] + ": " + analysis + " - " + result + "!" ;
    output.append(synopsis + "\n");
    return winner;
  }
}
