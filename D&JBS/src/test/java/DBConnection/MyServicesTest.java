package DBConnection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MyServicesTest {

    @Mock private MyDAOtesting myDao;

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testFindById() {
        MockitoAnnotations.initMocks(this);
        MyServices myService = new MyServices(myDao);
        myService.findById(1);
        Mockito.verify(myDao).findById(1);
    }

    @Test
    public void test() {
        MyServices myService = new MyServices(myDao);
        Mockito.when(myDao.findById(1)).thenReturn(createUser());
        User actual = myService.findById(1);
        Assert.assertEquals("Johnny", actual.getFirstName());
        Assert.assertEquals("Sheerin", actual.getSurname());
        Mockito.verify(myDao).findById(1);
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("Johnny");
        user.setSurname("Sheerin");
        return user;
    }
}
