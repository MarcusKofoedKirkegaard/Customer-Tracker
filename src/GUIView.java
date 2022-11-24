import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIView
{
    JFrame frame; 

    Container mainContainer;
    Container leftSubContainer;
    Container rightSubContainer;

    JPanel buttonPanel;
    JPanel outputInputPanel;

    JTextField output;
    JTextField input;  

    Font buttonFont;
    Font outputFont; 
    
    MockDB db;
    CustomerTracker cTracker;
    
    public GUIView()
    {
        frame = new JFrame("Customer Tracker");
        mainContainer = frame.getContentPane();
        leftSubContainer = new Container();
        rightSubContainer = new Container();

        buttonPanel = new JPanel();
        outputInputPanel = new JPanel();

        buttonFont = new Font("Sans-Serif", Font.PLAIN, 25);
        outputFont = new Font("Sans-Serif", Font.PLAIN, 14);
        output = new JTextField("");
        input = new JTextField("Input week number here...");
        
        db = new MockDB();
        cTracker = new CustomerTracker(db, 47);
        buildView();
    }

    private void buildView()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setMinimumSize(new Dimension(800, 400));

        mainContainer.setLayout(new GridLayout(1, 2));
        leftSubContainer.setLayout(new GridLayout(1,1));
        rightSubContainer.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(3, 1));

        output.setFont(outputFont);
        output.setEditable(false);
        output.setHorizontalAlignment(JTextField.CENTER);

        input.setFont(outputFont);
        input.setEditable(true);
        input.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                input.setText("");
            }
        });

        addButtons();
        
        mainContainer.add(leftSubContainer);
        mainContainer.add(rightSubContainer);

        leftSubContainer.add(buttonPanel);
        rightSubContainer.add(output, BorderLayout.PAGE_START);
        rightSubContainer.add(input, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    private void addButtons() throws NumberFormatException
    {
        JButton todayButton = new JButton("TODAY");
        todayButton.setFont(buttonFont);

        JButton avgThisWeekButton = new JButton("AVG. THIS WEEK");
        avgThisWeekButton.setFont(buttonFont);

        JButton compareButton = new JButton("COMPARE");
        compareButton.setFont(buttonFont);

        todayButton.addActionListener(e -> {
            output.setText("Today: " + cTracker.today() + " customers");
        });

        avgThisWeekButton.addActionListener(e -> {
            output.setText("Avg. customer this week: " + cTracker.avgThisWeek() + " customers");
        });

        compareButton.addActionListener(e -> 
        {
            try
            {
            int week = getValue();
            
            if( !(getValue() > 0 && getValue() < 53)) 
            {
                output.setText("Invalid input!");
                return;
            }

            if(db.getCustomerData().get(week) == null)
            {
                output.setText("There is no statistics for this week.");
            }

            if (cTracker.comparedToWeek(week) > 0)
            {
                output.setText("Increase by " + cTracker.comparedToWeek(week) + " customers");
            } 
            else if (cTracker.comparedToWeek(week) < 0)
            {
                output.setText("Decrease by " + cTracker.comparedToWeek(week) + " customers");
            } 
            else if (cTracker.comparedToWeek(week) == 0)
            {
                output.setText("No difference");
            } 
            }
            catch (NumberFormatException n)
            {
                output.setText("Invalid input!");
            }
        });

        buttonPanel.add(todayButton);
        buttonPanel.add(avgThisWeekButton);
        buttonPanel.add(compareButton);
    }

    private int getValue() {return Integer.parseInt(input.getText());}
}