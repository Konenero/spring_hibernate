package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByCar(Car car) {
        List<User> users = listUsers();
        User[] userWithCar = new User[1];
        for(User user:users){
            if((user.getCarUser().getSeries() == car.getSeries()) && (user.getCarUser().getModel().equals(car.getModel()))){
                userWithCar[0] = user;
                break;
            }
        }
       return userWithCar[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
