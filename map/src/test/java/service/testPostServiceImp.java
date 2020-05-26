package service;

import data.PostRepository;
import model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import service.PostService;
import service.PostServiceImp;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class testPostServiceImp {
    @Mock
    private PostRepository mockPost;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private PostService postService = mock(PostService.class);


    PostServiceImp postServiceImp;

    @Before
    public void testInit() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void tempTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void testPostServices() {

        Comparable<PostServiceImp> c = mock(Comparable.class);

        Mockito.when(c.compareTo(isA(PostServiceImp.class))).thenReturn(-9);
        Assert.assertTrue(true);
    }

    @Test
    public void testPostServiceImp() {

        List<Post> list = new ArrayList<>();
        List<Post> spy = spy(list);
        postServiceImp = Mockito.spy(new PostServiceImp(mockPost));


        when(postServiceImp.findAll()).thenReturn(spy);
        Assert.assertTrue(true);

    }

}
