package crownandanchor;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AllInTest.class, DoubleOrNothingTest.class, OrdinaryTest.class, PlayerTest.class })
public class AllTests {

}
