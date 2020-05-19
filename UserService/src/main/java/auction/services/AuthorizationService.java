package auction.services;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class AuthorizationService {
    // We need a signing ky, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
}
