package gradebook.dao;

public interface UserDAO<T,ID> {
	T getUser(ID user_id);
	void updateUser(T user);
}
