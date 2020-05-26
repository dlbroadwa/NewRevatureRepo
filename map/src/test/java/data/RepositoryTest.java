package data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import system.PostContextListener;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    MockitoRule mockitoRule = MockitoJUnit.rule();
    Repository repo = mock(Repository.class);

    @Before

    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void repoTestClass(){


        Comparable<Repository> compare = mock(Comparable.class);

        when(compare.compareTo(isA(Repository.class))).thenReturn(0);

        Assert.assertTrue(true);

    }
}
