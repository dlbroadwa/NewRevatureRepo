package testModel;

import model.Location;
import model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testPost {

@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    Post mockPost = mock(Post.class);
    Timestamp timestamp;
    InputStream inputStream;
    Location location;

    @Before
public void init(){

         timestamp =mock(Timestamp.class);
         inputStream = mock(InputStream.class);
        location = mock(Location.class);
}


 @Test

    public  void testPostGetters(){


    // Assert.assertEquals("Damier 's location", mockPost.getLocation(),location);

   Mockito.when(mockPost.getRating()).thenReturn(5);
     Assert.assertEquals(5, mockPost.getRating());

     Mockito.when(mockPost.getImageFileName()).thenReturn("image.png");
     Assert.assertEquals("image.png",mockPost.getImageFileName());



 }
 @Test
    public void testUserId(){

     when(mockPost.getPostID()).thenReturn(-1);
     Assert.assertEquals(-1,mockPost.getPostID());
 }

 @Test
    public void testTimestamp(){

     Assert.assertNotEquals(mockPost.getCreateTime(),timestamp);
 }

 @Test
    public  void testImage(){

     Mockito.when(mockPost.getImage()).thenReturn(inputStream);

     Assert.assertEquals(inputStream,mockPost.getImage());

 }

 @Test
    public void testUri(){

     ByteArrayOutputStream out = new ByteArrayOutputStream();
     byte[] bytes = out.toByteArray();
     String base64bytes = Base64.getEncoder().encodeToString(bytes);

     Comparable<String> comparable = mock(Comparable.class);

     String Uri = "image.png" + base64bytes;
     Mockito.when(mockPost.getURI()).thenReturn(Uri);

     Assert.assertNotEquals(base64bytes,Uri);

 }

    @Test
    public void testConstructor(){

        InputStream inputStream = mock(InputStream.class);
        Timestamp timestamp = mock(Timestamp.class);
        Post mockOb = Mockito.spy(new Post(1,"","",1,"",inputStream,timestamp,location,"",""));
        Comparable<Post> comparable = mock(Comparable.class);


        Mockito.when((mockOb.getImageFileName())).thenReturn("null");

        Assert.assertEquals("null", mockOb.getImageFileName());

    }

}
