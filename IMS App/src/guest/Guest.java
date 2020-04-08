package guest;

public class Guest
{
    private String welcome = "Welcome! What would you like to do today?\n" +
                              "             [buy, sell, exit]           ";
    public Guest()
    {
        System.out.println("Connected to Guest.....");
        System.exit(0);
    }
}
