package admin;

import app.IMSEntry;
import files.FileManipulation;
import guest.Guest;

import java.util.Scanner;

public class Admin extends IMSEntry
{
    private static final int MEMBER_ID = 224456789;
    private FileManipulation fm;
    public Admin()
    {
        if(IdVerification())
        {
            System.out.println("Connected to Admin.....\n");
            //TODO Add Admin Function Statements Here
            Functions();
            Relocate();
        }
        else
        {
            System.out.println("Exiting System.....");
            System.exit(0);
        }
    }

    private void Relocate()
    {
        System.out.println("Would you like to use another Admin function, move to being a guest, or exit?\n" +
                "                       [admin, guest, exit]");
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if ("admin".equals(choice)) {
            Admin admin = new Admin();

            Guest guest = new Guest();
        } else if ("guest".equals(choice)) {
            Guest guest = new Guest();
        }
        System.exit(0);
        super.exitSystem();
    }

    private boolean IdVerification()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your Admin ID: ");
        boolean verify = scanner.next().equals(Integer.toString(MEMBER_ID));
        if(verify)
        {
            scanner.reset();
            return true;
        }
        else
        {
            System.out.println("Invalid entry or password, exiting the Instrument Management System...");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            super.exitSystem();
        }
        return false;
    }

    private void Functions()
    {
        System.out.println("            Welcome Admin! What would you like to do today?\n" +
                            "Available Access: View Stock, Remove from Stock, Add to Stock, Manage Money.\n" +
                            "                   [View, Remove, Add, Manage]");
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if ("view".equals(choice))
        {
            if (this.fm == null)
            {
                this.fm = new FileManipulation();
                fm.ReadStock(fm.getTestFile());
            }
            else
                {
                    getfm().ReadStock(fm.getTestFile());
                }
        }
        else if ("remove".equals(choice))
        {
            System.out.println("remove connected.....");
        }
        else if ("add".equals(choice))
        {
            if (this.fm == null)
            {
                this.fm = new FileManipulation();
                fm.PushToStock(fm.getTestFile());
            }
            else
                {
                    getfm().PushToStock(fm.getTestFile());
                }
        }
        else if ("manage".equals(choice))
        {
            System.out.println("manage connected");
        }
        else
            {
                super.exitSystem();
            }
    }

    public FileManipulation getfm()
    {
        return this.fm;
    }
}