import java.util.*;

public class CustomerTracker implements ICustomerTracker
{
    private HashMap<Integer, int[]> customerData;
    private int currentWeek;

    CustomerTracker(MockDB db, int currentWeek)
    {
        this.customerData = db.getCustomerData();
        this.currentWeek = currentWeek;
    }

    public int today()
    {    
        int today = 0;
        
        for (int customerPerDay : customerData.get(this.currentWeek))
        {
            today = customerPerDay;
        }
        return today;
    }

    public double avgThisWeek()
    {
        double thisWeek = 0.0;
    
        for (int customerPerDay : customerData.get(this.currentWeek))
        {
            thisWeek += customerPerDay;
        }
        return thisWeek / 7.0;
    }

    public double comparedToWeek(int week)
    {
        return Math.round((avgThisWeek() - avgWeek(week)) * 100.0) / 100.0;
    }

    private double avgWeek(int week) throws NullPointerException
    {
        try 
        {
            double customersWeek = 0.0;
        
            for (int customerPerDay : customerData.get(week))
            {
                customersWeek += customerPerDay;
            }
        return customersWeek / 7.0;
        } 
        catch (NullPointerException e) 
        {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }
}