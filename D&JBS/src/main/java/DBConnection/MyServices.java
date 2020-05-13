package DBConnection;

public class MyServices {

    private MyDAOtesting myDao;

    public MyServices(MyDAOtesting myDao) {
        this.myDao = myDao;
    }

    public User findById(int id) {
        return myDao.findById(id);
    }
}