
import com.mycompany.simpleswing.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.EventHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthias. DOLA at matthiasdola.netylify.org
 */
public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
                SimpleFrame frame = new SimpleFrame();
                frame.setSize(750, 400);
                frame.setLocation(200, 200);
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    public SimpleFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth-20, screenHeight-20);
        setLocation(10, 10);
        setLocationByPlatform(true);
        Image img = new ImageIcon("icon.gif").getImage();
        ColorChooserPanel c = new ColorChooserPanel();
        add(c);
        setIconImage(img);
        pack();
    }
}

class NotHelloWorld {
    
}

class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth-20, screenHeight-20);
        setLocation(10, 10);
        setLocationByPlatform(true);
        add(new NotHelloWorldComponent());
        pack();
    }
}

class NotHelloWorldComponent extends JComponent {
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 50;
    
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    public void paintComponent(Graphics g) {
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        System.out.println(Arrays.toString(fontNames));
        FontRenderContext context = ((Graphics2D)g).getFontRenderContext();
        
        g.drawString(Arrays.toString(fontNames), MESSAGE_X, MESSAGE_Y);
    }
    
    public Dimension getPreferredSize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // setSize(screenWidth-20, screenHeight-20);
        // setLocation(10, 10);
        return new Dimension(screenWidth-20, screenHeight-20);
    }
}

class DrawFrame extends JFrame {
    public DrawFrame(){
        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent {
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        // draw a rectangle
        double leftX = 100;
        double topY = 100;
        double width = 100;
        double height = 150;
        
        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);
        // draw the enclosed ellipse
        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);
        
        // draw a circle with the same center
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;
        
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);
    }
    
            
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }   
}

class FontFrame extends JFrame {
    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 20;
    
    private JComboBox<String> face;
    private JComboBox<Integer> size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JTextArea sample;
    
    public FontFrame() {
        // add(new FontComponent());
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        ActionListener listener = EventHandler.create(
                ActionListener.class, this, "updateSample");
        // construct components
        JLabel faceLabel = new JLabel("Face: ");
        face = new JComboBox<>(new String[] {"Serif", "SansSerif", "Monospaced", 
            "Dialog", "DialogInput"});
        face.addActionListener(listener);
        JLabel sizeLabel = new JLabel("Size: ");
        size = new JComboBox<>(new Integer[] {8, 10, 12, 15, 18, 24, 36, 73});
        size.addActionListener(listener);
        
        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        
        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);
        
        sample = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        sample.setText("The quick brown fox jumps over the lazy dog");
        sample.setEditable(false);
        sample.setLineWrap(true);
        sample.setBorder(BorderFactory.createEtchedBorder());
        
        // add components to grid, using GBC convenience class
        add(faceLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(face, new GBC(1,0).setFill(GBC.HORIZONTAL).setWeight(100, 100).setInsets(1));
        
        add(sizeLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(size, new GBC(1,1).setFill(GBC.HORIZONTAL).setWeight(100, 100).setInsets(1));

        add(bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        add(italic, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        add(sample, new GBC(2,0, 1, 4).setFill(GBC.BOTH).setWeight(100, 100));

        pack();
        updateSample();
    }
    
    public void updateSample() {
        String fontFace = (String) face.getSelectedItem();
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0)
                + (italic.isSelected() ? Font.ITALIC : 0);
        int fontSize = size.getItemAt(size.getSelectedIndex());
        Font font = new Font(fontFace, fontStyle, fontSize);
        sample.setFont(font);
        sample.repaint();
    }
}

class GBC extends GridBagConstraints {
    
    /**
     * Constructs a GBC with a given gridx and gridy position and all other grid
     * bag constraint values set to the default
     * @param gridx the gridx position
     * @param gridy the gridy position
     */
    public GBC(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridx;
    }
    
    /**
     * Constructs a GBC with a given gridx, gridy, gridwidth, gridheight and all other grid
     * bag constraint values set to the default
     * @param gridx the gridx position
     * @param gridy the gridy position
     * @param gridwidth the cell span in x-direction
     * @param gridheight the cell span in y-direction
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }
    
    /**
     * Set the anchor
     * @param anchor the anchor value
     * @return this object for further modification
     */
    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }
    
    /**
     * Sets the fill direction
     * @param fill the fill direction
     * @return this object for further modification
     */
    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }
    
    /**
     * Sets the cell weights.
     * @param weightx
     * @param weighty
     * @return this object
     */
    public GBC setWeight(double weightx, double weighty ) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }
    
    /**
     * Sets the insets of this cell
     * @param distance
     * @return this object
     */
    public GBC setInsets(int distance){
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }
    
    /**
     * Sets the insets of this cell
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @return this object
     */
    public GBC setInsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
    
    /**
     * Sets the internal padding
     * @param ipadx
     * @param ipady
     * @return this object for further modification
     */
    public GBC setIpad(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}

class FontComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        String message = "Hello, World!";
        
        Font f = new Font("Helvetica", Font.BOLD, 36);
        g2.setFont(f);
        
        // Measure the size of the message
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);
        
        // set (x,y) = top left corner of the text
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;
        
        // add ascent to y to reach the baseline
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        
        // draw the message
        g2.drawString(message, (int) x, (int) baseY);
        
        g2.setPaint(Color.LIGHT_GRAY);
        
        // draw the baseline
        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
        
        // draw the enclosing rectangle
        Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g2.draw(rect);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

class ImageFrame extends JFrame {
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}

class ImageComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    
    private Image image;
    
    public ImageComponent() {
        this.image = new ImageIcon("sallon.gif").getImage();
    }
    
    public void paintComponent(Graphics g) {
        System.out.println(image.toString());
        if (this.image == null) {
            System.out.println("Image null");
           return;
        }
        
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        
        // draw the image in the upper-left corner
        g.drawImage(image, 0, 0, null);
        
        // tile the imafe accross the component
        for(int i = 0; i * imageWidth <= getWidth(); i++) {
            for (int j = 0; j * imageHeight <= getHeight(); j++) {
                // if ( i + j > 0)
                    // g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
            }
        }
        
    }
    
    public Dimension getPreferredSize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // setSize(screenWidth-20, screenHeight-20);
        // setLocation(10, 10);
        return new Dimension(screenWidth-20, screenHeight-20);
    }
}

class ButtonFrame extends JFrame {
    private JPanel buttonPanel;
    
    public ButtonFrame() {
        // Set size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth-40, screenHeight - 40);
        
        // create buttons
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");
        
        buttonPanel = new JPanel();
        String plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();

        try {
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(buttonPanel);
        } catch (Exception e) {
            System.out.println("Error change the look and feel");
        }
        // add buttons to panel
        buttonPanel.add(yellowButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);
        for (UIManager.LookAndFeelInfo info : infos) {
            JTextField txt = new JTextField(String.format("Name: %s, Class: %s", info.getName(), info.getClassName()));
            System.out.println(String.format("Name: %s, Class: %s", info.getName(), info.getClassName()));
            buttonPanel.add(txt);
        }
        
        // Add panel to frame
        add(buttonPanel);
        
        // Create button actions
        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);
        
        // associate actions with buttons
        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
    }
    
    /**
     * An action listener that sets the panel's background color.
     */
    private class ColorAction implements ActionListener {
        private Color backgroundColor;
        
        public ColorAction(Color c) {
            backgroundColor = c;
        }
        
        public void actionPerformed(ActionEvent event) {
            buttonPanel.setBackground(backgroundColor);
        }
    }
    
    public Dimension getPreferredSize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // setSize(screenWidth-20, screenHeight-20);
        // setLocation(10, 10);
        return new Dimension(screenWidth-20, screenHeight-20);
    }
}

class PlafFrame extends JFrame {
    private JPanel buttonPanel;
    
    public PlafFrame() {
        buttonPanel = new JPanel();
        
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            makeButton(info.getName(), info.getClassName());
        }
        add(buttonPanel);
        pack();
    }
    
    void makeButton(String name, final String plafName) {
        // add button to panel
        JButton button = new JButton(name);
        buttonPanel.add(button);
        
        // set button action
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // button action: switch to the new look-and-feel
                try {
                    UIManager.setLookAndFeel(plafName);
                    SwingUtilities.updateComponentTreeUI(PlafFrame.this);
                    pack();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
    }
}

class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        add(new CalculatorPanel());
        pack();
    }
}

class CalculatorPanel extends JPanel 
{
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
    
    public CalculatorPanel() {
        setLayout(new BorderLayout());
        result = 0;
        lastCommand = "";
        start = true;
        
        // Add the display
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);
        
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();
        
        // add the buttons in a 4 x 4 grid
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        
        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);
        
        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);  
        
        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
        addButton("+", command);  
        
        add(panel, BorderLayout.CENTER);
    }
    
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    
    private class InsertAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            if(start){
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
        
    }
    
    private class CommandAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else 
                    lastCommand = command;
               
            } else {
              calculate(Double.parseDouble(display.getText()));
              lastCommand = command;
              start = true;
            }
        }
        
    }
    
    public void calculate(double x) {
        if (lastCommand.equals("+")) result += x;
        if (lastCommand.equals("-")) result -= x;
        if (lastCommand.equals("*")) result *= x;
        if (lastCommand.equals("/")) result /= x;
        if (lastCommand.equals("=")) result = x;
        
        display.setText("" + result);
    }
}


/**
 * A frame with JTextField component and a text field to show slider values
 */
class TextComponentFrame extends JFrame {
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;
    
    public TextComponentFrame() {
        final JTextField textField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();
        
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("User name: ", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password: ", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        
        add(northPanel, BorderLayout.NORTH);
        
        final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // add button to append text into the text area
        
        JPanel southPanel = new JPanel();
        
        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("User name: " + textField.getText() +
                        " Password: " + new String(passwordField.getPassword()) + "\n");
            }
        });
        add(southPanel, BorderLayout.SOUTH);
        setSize(750, 350);
        setLocation(400, 250);
        pack();                                                      
    }
}

/**
 * A frame with a simple text label and radio buttons for selecting sizes
 * @author Matthias. DOLA at matthiasdola.netylify.org
 */
class RadioButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;
    
    public RadioButtonFrame() {
        // add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);
        // add the radio buttons
        buttonPanel = new JPanel();
        group = new ButtonGroup();
        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Large", 18);
        addRadioButton("Extra large", 36);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    
    /**
     * Adds a radio button that sets the font size of the sample text
     * @param name the string to appear on the button
     * @param size the font size that this button sets
     */
    public void addRadioButton(String name, final int size) {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);
        
        // this listener sets the label font size
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // size refers to the final parameter of the addRadionButton
                label.setFont(new Font("Serif", Font.PLAIN, size));
            }
        };
        button.addActionListener(listener);
    }  
}

/**
 * A frame with a combo box and a text field to show slider values
 */
class ComboBoxFrame extends JFrame {
    private JComboBox<Element> faceCombo;
    private JLabel label;
    private static final int DEFAULT_SIZE = 24;
    
    public ComboBoxFrame() {
        // add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);
        
        // make a combo box and add face names
        faceCombo = new JComboBox<>();
        faceCombo.addItem(new Element("Serif"));
        faceCombo.addItem(new Element("SansSerif"));
        faceCombo.addItem(new Element("Monospaced"));
        faceCombo.addItem(new Element("Dialog"));
        faceCombo.addItem(new Element("DialogInput"));
        
        // the combo box listener changes the label font to the selected face name
        faceCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new 
        Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()).toString(), Font.PLAIN, DEFAULT_SIZE));
            }
        });
        
        // add combo box to a panel at the frame's sourthern border
        JPanel comboPanel = new JPanel();
        comboPanel.add(faceCombo);
        add(comboPanel, BorderLayout.SOUTH);
        setSize(750, 350);
        setLocation(400, 250);
        pack();
    }
    
    class Element {
        private String elementName;
        
        public Element(String name) {
            elementName = name;
        }

        public String getElementName() {
            return elementName;
        }

        public void setElementName(String elementName) {
            this.elementName = elementName;
        }

        @Override
        public String toString() {
            return elementName; //To change body of generated methods, choose Tools | Templates.
        }
    }
}

/**
 * A frame with many sliders and a text field to show slider values
 */
class SliderFrame extends JFrame {
    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener listener;
    
    public SliderFrame() {
        sliderPanel = new JPanel();
        sliderPanel.setSize(750, 400);
        sliderPanel.setLayout(new GridBagLayout());
        
        // Common listener for all sliders
        listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // update text field when the slider value changes
                JSlider source = (JSlider) e.getSource();
                textField.setText("" + source.getValue());
            }
        };
        
        // add a plain slider
        JSlider slider = new JSlider();
        addSlider(slider, "Plain");
        
        // add a slider with major and minor ticks
        slider = new JSlider();
        slider.setSize(700, 2);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Ticks");
  
        // add a slider that snap to ticks
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Snap to ticks");

        // add a slider with no track
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(false);
        addSlider(slider, "No track");

        // add an inverted  slider
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlider(slider, "Inverted");   
        
        // add a slider with numeric labels
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Labels");  
        
        // add a slider with alphabetic labels  
        slider = new JSlider();
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));
        
        slider.setLabelTable(labelTable);
        addSlider(slider, "Custom labes"); 
        
        // add a slider with icon labels
        slider = new JSlider();
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(20);
        
        labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
        labelTable.put(20, new JLabel(new ImageIcon("ten.gif")));
        labelTable.put(40, new JLabel(new ImageIcon("jack.gif")));
        labelTable.put(60, new JLabel(new ImageIcon("queen.gif")));
        labelTable.put(80, new JLabel(new ImageIcon("king.gif")));
        labelTable.put(100, new JLabel(new ImageIcon("ace.gif")));
        
        slider.setLabelTable(labelTable);
        addSlider(slider, "Icon labes"); 
        
        // add the text field that displays the slider value
        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        pack();
           
    }
    
    /**
     * adds a slider to the slider panel and hooks up the listener
     * @param s the slider
     * @param description the slider description
     */
    private void addSlider(JSlider s, String description) {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = sliderPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.WEST;
        panel.setSize(700, 5);
        sliderPanel.add(panel, gbc);
    }
}


/**
 * A frame with a sample menu bar
 * @author Matthias. DOLA at matthiasdola.netylify.org
 */
class MenuFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPopupMenu popup;
    
    /**
     * A sample action that prints the action name to System.out
     */
    class TestAction extends AbstractAction
    {
        public TestAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(Action.NAME) + " Selected");
        }
    }
    
    public MenuFrame() {
        setSize(750, 400);
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new TestAction("New"));
        
        // demonstrate accelerators
        JMenuItem openItem = fileMenu.add(new TestAction("Open"));
        openItem.setAccelerator(KeyStroke.getKeyStroke("Ctrl O"));
        
        fileMenu.addSeparator();
        saveAction = new TestAction("Save");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("Ctrl S"));
        
        saveAsAction = new TestAction("Save As");
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        
        fileMenu.add(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // demonstrate check box and radio button menus
        readonlyItem = new JCheckBoxMenuItem("Read-only");
        readonlyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean saveOk = !readonlyItem.isSelected();
                saveAction.setEnabled(saveOk);
                saveAsAction.setEnabled(saveOk);
            }
        });
        
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
        group.add(insertItem);
        group.add(overtypeItem);
        
        // demonstrate icons
        Action cutAction = new TestAction("Cut");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
        
        Action copyAction = new TestAction("Copy");
        copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
        
        Action pasteAction = new TestAction("Paste");
        pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        
        // demonstrate nested menus
        JMenu optionMenu = new JMenu("Options");
        optionMenu.add(readonlyItem);
        optionMenu.addSeparator();
        optionMenu.add(insertItem);
        optionMenu.add(overtypeItem);
        
        editMenu.addSeparator();
        editMenu.add(optionMenu);
        
        // demonstrate mnemonics
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        
        JMenuItem indexItem = new JMenuItem("Index");
        indexItem.setMnemonic('I');
        indexItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog abd = new AboutDialog(null);
                abd.setVisible(true);
            }
        });
        helpMenu.add(indexItem);
        
        // you can also add the mnemonic key to an action
        Action aboutAction  = new TestAction("About");
        aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
        helpMenu.add(aboutAction);
        
        // Add all top-level menus to menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        // demonstrate pop-ups
        popup = new JPopupMenu();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);
        
        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popup);
        add(panel);
        
        // the following line is a workaround for bug 4966109
        panel.addMouseListener(new MouseAdapter(){});
        
        
        
    }
}

/**
 * A frame that contains settings for selection various option dialog
 * @author Matthias. DOLA at matthiasdola.netylify.org
 */
class OptionDialogFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;
    
    private ButtonPanel typePanel;
    private ButtonPanel messagePanel;
    private ButtonPanel messageTypePanel;
    private ButtonPanel optionTypePanel;
    private ButtonPanel optionsPanel;
    private ButtonPanel inputPanel;
    private String messageString = "Message";
    private Icon messageIcon = new ImageIcon("blue-ball.gif");
    private Object messageObject = new Date();
    private Component messageComponent = new SampleComponent();
    
    public OptionDialogFrame() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        // Add About and exit menu items.
        
        // The About item shows the About dialog
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialog == null) // first time
                    dialog = new AboutDialog(OptionDialogFrame.this);
                dialog.setVisible(true); // pop up dialog
            }
        });
        fileMenu.add(aboutItem);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        
        JPanel gridPanel = new JPanel();
        
        typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", "Input");
        messageTypePanel = new ButtonPanel("Message Type", "ERROR_MESSAGE", 
                "INFORMATION_MESSAGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE");
        messagePanel = new ButtonPanel("Message", "String", "Icon", "Component", "Other", "Object[]");
        optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTION", "YES_NO_OPTION",
                "YES_NO_CANCEL", "OK_CANCEL_OPTION");
        optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
        inputPanel = new ButtonPanel("Input", "Text field", "Combo box");
        
        gridPanel.add(typePanel);
        gridPanel.add(messageTypePanel);
        gridPanel.add(messagePanel);
        gridPanel.add(optionTypePanel);
        gridPanel.add(optionsPanel);
        gridPanel.add(inputPanel);
        
        
        // add a panel with a show button
        JPanel showPanel = new JPanel();
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ShowAction());
        showPanel.add(showButton);
        
        add(gridPanel, BorderLayout.CENTER);
        add(showPanel, BorderLayout.SOUTH);
        pack();
    }
    
    /**
     * Gets the currently selected message.
     * @return an array of String, icon, or object array, depending on the Message panel selection
     */
    public Object getMessage() {
        String s = messagePanel.getSelection();
        if (s.equals("String")) return messageString;
        else if (s.equals("Icon")) return messageIcon;
        else if (s.equals("Object[]")) return new Object[]{messageString, messageIcon,
                    messageComponent, messageObject};
        else if (s.equals("Other")) return messageObject;
        else return null;
    }
    
    /**
     * Gets the currently selected options
     * @return an array of strings, icons, or objects, depending on the option panel selection
     */
    public Object[] getOptions() {
        String s = optionsPanel.getSelection();
        if (s.equals("String[]")) return new String[]{"Yellow", "Blue", "Red"};
        else if (s.equals("Icon[]")) return new Icon[]{ new ImageIcon("yellow-ball.gif"),
                    new ImageIcon("blue-ball.gif"), 
                    new ImageIcon("red-ball.gif")};
        else if (s.equals("Object[]")) return new Object[]{messageString, messageIcon,
                    messageComponent, messageObject};
        else return null;
    }
    
    /**
     * Gets the selected message or optin type
     * @param panel the Message Type or Conform panel
     * @return the selected XX_MESSAGE or XXX_OPTION constant from the JOptionPane class
     */
    public int getType(ButtonPanel panel) {
        String s = panel.getSelection();
        try {
            return JOptionPane.class.getField(s).getInt(null);
        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * The action listener for the Show button shows a Confirm, Input, Message, or Option dialog
     * depending on the Type panel selection.
     */
    private class ShowAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (typePanel.getSelection().equals("Confirm"))
                JOptionPane.showConfirmDialog(OptionDialogFrame.this, getMessage(), "Title",
                        getType(optionTypePanel), getType(messageTypePanel));
            else if(typePanel.getSelection().equals("Input")) {
                if (inputPanel.getSelection().equals("Text field")) {
                    JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(), "Title",
                        getType(messageTypePanel));
                } else {
                    JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(), "Title",
                        getType(messageTypePanel), null, new String[] {"Yellow", "Blue", "Red"}, "Blue");
                }
            } else if (typePanel.getSelection().equals("Message")) {
                JOptionPane.showMessageDialog(OptionDialogFrame.this, getMessage(), "Title",
                        getType(messageTypePanel));
            } else if (typePanel.getSelection().equals("Option")) {
                JOptionPane.showOptionDialog(OptionDialogFrame.this, getMessage(), "Title",
                        getType(optionTypePanel), getType(messageTypePanel), null, getOptions(), 
                        getOptions()[0]);
            }
                
        }
    }
    
    /**
     * A component with a painted surface
     */
    class SampleComponent extends JComponent {
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
            g2.setPaint(Color.YELLOW);
            g2.fill(rect);
            g2.setPaint(Color.BLUE);
            g2.draw(rect);
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(10, 10);
        }
    }
    
    
    class ButtonPanel extends JPanel {
        
        private ButtonGroup group;
        
        /**
         * Constructs a button panel
         * @param title the title shown in the border
         * @param options an array of radio button labels
         */
        public ButtonPanel(String title, String... options){
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            group = new ButtonGroup();
            
            // make one radio button for each option
            for(String option : options) {
                JRadioButton b = new JRadioButton(option);
                b.setActionCommand(option);
                add(b);
                group.add(b);
                b.setSelected(option == options[0]);
            }
        }      
        
        
        /**
         * Gets the currently selected option
         * @return the label of the currently selected radio button
         */
        private String getSelection() {
            return group.getSelection().getActionCommand();
        }
    }
    
}

class AboutDialog extends JDialog {
    
    public AboutDialog(JFrame owner) {
        super(owner, "About DialogTest", true);
        add(new JLabel(
          "<html><h1><i>Core Java</i></h1><hr> By Cay Hosrtmann and Gary Cornell</html>"
        ), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        
        setSize(250, 150);
    }
    
}

class DataExchangeFrame extends JFrame {
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 40;
    private PasswordChooser dialog = null;
    private JTextArea textArea;
    
    public DataExchangeFrame() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        // Add About and exit menu items.
        
        // The About item shows the About dialog
        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new ConnectAction());
        fileMenu.add(connectItem);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();
    }
    
    private class ConnectAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            // if fisrt time, construct dialog
            if (dialog == null) {
                dialog = new PasswordChooser();
                dialog.setSize(300, 200);
                dialog.setLocation(300, 300);
            }
            // set default values
            dialog.setUser(new User("yourname", ""));
          
            // pop up dialog
            if (dialog.showDialog(DataExchangeFrame.this, "Connect")) {
                // if accepted, retrieve user input
                User u = dialog.getUser();
                textArea.append("user name = " + u.getName() + ", password = " 
                        + (new String(u.getPassword())) + "\n");
            }
        }
        
    }
    
    private class PasswordChooser extends JPanel {
        private JTextField username;
        private JPasswordField password;
        private JButton okButton;
        private boolean ok;
        private JDialog dialog;
        
        
        public PasswordChooser() {
            setLayout(new BorderLayout());
            setSize(300, 200);
            setLocation(300, 300);
            // construct a panel with user name and password fields
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2,2));
            panel.add(new JLabel("User name:"));
            panel.add(username = new JTextField(""));
            panel.add(new JLabel("Password:"));
            panel.add(password = new JPasswordField(""));
            
            add(panel, BorderLayout.CENTER);
            
            // Create OK and Cancel buttons that terminate the dialog
            okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ok = true;
                    dialog.setVisible(false);
                }
            });
            
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                }
            });
            
            // add buttons to southern border
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }
        
        /**
         * Sets the dialog defaults
         * @param u the default user information
         */
        public void setUser(User u) {
            username.setText(u.getName());
            
        }
        
        /**
         * Gets the dialog entries
         * @return a User object whose state represents the dialog entries
         */
        public User getUser() {
            return new User(username.getText(), password.getPassword());
        }
        
        public boolean showDialog(Component parent, String title) {
            ok = false;
            
            // locate the owner frame
            Frame owner = null;
            if (parent instanceof Frame) 
                owner = (Frame) parent;
            else 
                owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
            
            // If first time, or tf owner has changed, make new dialog
            if (dialog == null || dialog.getOwner() != owner){
                dialog = new JDialog(owner, true);
                dialog.add(this);
                dialog.getRootPane().setDefaultButton(okButton);
                dialog.pack();
            }
            
            // set title and show dialog
            dialog.setSize(300, 200);
            dialog.setLocation(300, 300);
            dialog.setTitle(title);
            dialog.setVisible(true);
            return ok;
        }
    }
    
    private class User {
        private String name;
        private String password;
        
        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }
        
        public User(String name, char[] password) {
            this.name = name;
            this.password = Arrays.toString(password);
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getPassword() {
            return this.password;
        }
        
        public void setPassword(String pass) {
            this.password = pass;
        }
    }
}

class ImageViewerFrame extends JFrame {
    private JLabel label;
    private JFileChooser chooser;
    
    public ImageViewerFrame() {
       
        // set up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        // Add About and exit menu items.
        
        // The About item shows the About dialog
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  chooser.setCurrentDirectory(new File("."));
                  
                  // show file chooser dialog
                  int result = chooser.showOpenDialog(ImageViewerFrame.this);
                  
                  // if image file accepted, set it as icon of the label
                  if (result == JFileChooser.APPROVE_OPTION) {
                      String name = chooser.getSelectedFile().getPath();
                      label.setIcon(new ImageIcon(name));
                      pack();
                  }
            }
        });
        fileMenu.add(openItem);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        
        label = new JLabel();
        add(label);
        
        // set up file chooser
        chooser = new JFileChooser();
        
        // accept all image files ending with .jpg, .jpeg, .gif
        /*
        final ExtensionFileFilter filter = new ExtensionFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.setDescription("Image files");
        */
        FileNameExtensionFilter filter = 
                new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png");
        chooser.setFileFilter(filter);
        chooser.setAccessory(new ImagePreviewer(chooser));
        chooser.setFileView(new FileIconView(filter, new ImageIcon("pallete.gif")));
        
        // pack();
    }
}

class ImagePreviewer extends JLabel {
    /**
     * Constructs an imagePreviewer
     * @param chooser  the file chooser whose property changes trigger an image change
     * in this previewer
     */
    public ImagePreviewer(JFileChooser chooser) {
        setPreferredSize(new Dimension(300, 300));
        setBorder(BorderFactory.createEtchedBorder());
        
        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == JFileChooser.SELECTED_FILES_CHANGED_PROPERTY){
                    // the suer has selected a new file
                    File f = (File) evt.getNewValue();
                    if (f == null) {
                        setIcon(null);
                        return;
                    }
                    // read the image into an icon
                    ImageIcon icon = new ImageIcon(f.getPath());
                    
                    // if the icon is too large to fit, scale it
                    if (icon.getIconWidth() > getWidth())
                        icon = new ImageIcon(icon.getImage().getScaledInstance(
                                getWidth(), -1, Image.SCALE_DEFAULT));
                    setIcon(icon);
                }
            }
        });
    }
}

class FileIconView extends FileView {
    private FileNameExtensionFilter filter;
    private Icon icon;
    
    /*
    public FileIconView(FileFilter f, Icon anIcon) {
        this.filter = f;
        this.icon = anIcon;
    }
    */

    FileIconView(FileNameExtensionFilter filter, ImageIcon imageIcon) {
        this.filter = filter;
        this.icon = imageIcon;
    }

    @Override
    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f))
            return icon;
        return null;
    }
}

/**
 * A panel with buttons to pop up three types of color choosers
 * @author Matthias. DOLA at matthiasdola.netylify.org
 */
class ColorChooserPanel extends JPanel {
    public ColorChooserPanel() {
        JButton modalButton = new JButton("Modal");
        modalButton.addActionListener(new ModalListener());
        add(modalButton);
        
        
        JButton modelessButton = new JButton("Modeless");
        modelessButton.addActionListener(new ModelessListener());
        add(modelessButton);
        
        JButton immediateButton = new JButton("Immediate");
        modelessButton.addActionListener(new ImmediateListener());
        add(immediateButton);
    }
    
    /**
     * This listener pops up a modal color chooser
     */
    private class ModalListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Color defaultColor = getBackground();
            Color selected = JColorChooser.showDialog(
                    ColorChooserPanel.this, "Set background", defaultColor);
            if (selected != null)
                setBackground(selected);
        }
    }
    
    /**
     * The listener pops up a modeless color chooser
     */
    private class ModelessListener implements ActionListener {

        private JDialog dialog;
        private JColorChooser chooser;
        
        public ModelessListener() {
            chooser = new JColorChooser();
            dialog = JColorChooser.createDialog(ColorChooserPanel.this,
                    "Background color", false, 
                    chooser, 
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setBackground(chooser.getColor());
                        }
                    }, null /* No cancel button listener */);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }                     
        
    }
    
    private class ImmediateListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;
        
        public ImmediateListener() {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    setBackground(chooser.getColor());
                }
            });
            dialog = new JDialog((Frame)null, false);
            dialog.add(chooser);
            dialog.pack();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }             
    }
}






