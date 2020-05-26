package data;

import data.PostRepository;
import model.Location;
import model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.verification.VerificationMode;
import utils.ConnectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

public class PostRepositoryTest {

    PostRepository postRepo = mock(PostRepository.class);
    Post mockPost = mock(Post.class);

    List<Post> list = new ArrayList<>();
    List<Post> spy = Mockito.spy(list);

    @Mock
    ConnectionUtils mockConnection;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        PostRepository mockP = new PostRepository(mockConnection);
    }


    @Test
    public void testFindById(){



        when(postRepo.findById(1)).thenReturn(mockPost);

        Assert.assertEquals(mockPost,postRepo.findById(1));
        Mockito.verify(postRepo,Mockito.times(1)).findById(1);


    }

    @Test
    public void testFindAll(){

        Iterator<Post> iterator = mock(Iterator.class);

        when(iterator.next()).thenReturn(mockPost).thenReturn(mockPost);
        spy.add(mockPost);


        Assert.assertEquals(spy.get(0),mockPost);

    }

    @Test
    public void testFindRecent(){

        Mockito.when(postRepo.findRecent()).thenReturn(spy);

        postRepo.findRecent();

        Assert.assertTrue(String.valueOf(mockPost),true);

        Mockito.verify(postRepo,Mockito.times(1)).findRecent();

    }






}
