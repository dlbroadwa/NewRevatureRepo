package TestData;

import data.PostRepository;
import model.Location;
import model.Post;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

public class testPostRepo {

    PostRepository postRepo = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    Location mockLocation = mock(Location.class);
    List<Post> list = new ArrayList<>();
    List<Post> spy = Mockito.spy(list);

    @Test
    public void testFindAllByUser(){

        Mockito.when(postRepo.findAllByUser(Mockito.anyString())).thenReturn(spy);

        Assert.assertEquals(postRepo.findAllByUser(""),spy);

        Mockito.verify(postRepo,times(1)).findAllByUser(anyString());

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

    @Test
    public  void testFindAllByLocation(){

        Mockito.when(postRepo.findAllByLocation(1)).thenReturn(spy);

Assert.assertEquals(spy, postRepo.findAllByLocation(1));

    }

    @Test

    public  void testdeleteAllAtLocation(){

        postRepo.deleteAllAtLocation(1);

        Mockito.verify(postRepo,times(1)).deleteAllAtLocation(1);

    }





}
