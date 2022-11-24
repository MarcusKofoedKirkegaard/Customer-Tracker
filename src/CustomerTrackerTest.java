import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTrackerTest {

    private MockDB db;
    private CustomerTracker cTracker;

    @Before
    public void setUp() 
    {
        this.db = new MockDB();
        this.cTracker = new CustomerTracker(db, 47);
    }

    @After
    public void tearDown()
    {
        this.db = null;
        this.cTracker = null;
    }

    @Test
    public void today_returns35()
    {
        //Arrange
        int expected = 35;

        //Act 
        int actual = cTracker.today();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void avgThisWeek_returns30()
    {
        //Arrange
        double expected = 30.0;

        //Act
        double actual = cTracker.avgThisWeek();

        //Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void comparedToWeek_returns2point85()
    {
        //Arrange
        double expected = 2.85;

        //Act
        double actual = cTracker.comparedToWeek(43);

        //Assert
        assertEquals(expected, actual, 0.05);
    }

    @Test
    public void comparedToWeek_returns0()
    {
        //Arrange
        double expected = 0;

        //Act
        double actual = cTracker.comparedToWeek(47);

        //Assert
        assertEquals(expected, actual, 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void comparedToWeek_given0_throwsAnException()
    {
        cTracker.comparedToWeek(0);
    }

    @Test(expected = NullPointerException.class)
    public void comparedToWeek_given48_throwsAnException()
    {
        cTracker.comparedToWeek(48);
    }
}