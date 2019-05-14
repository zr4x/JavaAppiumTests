package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.GetStartedTest;
import tests.MyListTests;
import tests.SearchTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        GetStartedTest.class,
        MyListTests.class,
        SearchTests.class
})
public class TestSuite {
}
