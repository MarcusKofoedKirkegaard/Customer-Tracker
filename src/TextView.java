public class TextView 
{
    ICustomerTracker cTracker;

    public TextView (ICustomerTracker cTracker)
    {
        this.cTracker = cTracker;
    }

    public void printSomething()
    {
        System.out.println("TESTING!!!");
    }

    public void printToday()
    {
        System.out.println("Today: " + cTracker.today() + " customers");
    } 

    public void printAvgThisWeek()
    {
        System.out.println("Average this week: " + cTracker.avgThisWeek()  + " customers");
    }

    public void printComparedToWeek(int week)
    {
        if (cTracker.comparedToWeek(week) > 0)
        {
            System.out.println("Increase by " + cTracker.comparedToWeek(week) + " customers");
        } 
        else if (cTracker.comparedToWeek ( week ) < 0)
        {
            System.out.println("Decrease by " + (cTracker.comparedToWeek(week) * -1) + " customers");
        } 
        else
        {
            System.out.println("No difference");
        }    
    }
}
