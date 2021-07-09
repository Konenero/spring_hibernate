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
    public void getUserByCar(Car car) {
        String hql = "FROM Car where model = :paramModel AND series = :paramSeries";
        Car carUser = sessionFactory.getCurrentSession()
                .createQuery(hql, Car.class)
                .setParameter("paramModel", car.getModel())
                .setParameter("paramSeries", car.getSeries())
                .getSingleResult();
        System.out.println(carUser.getUser());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
