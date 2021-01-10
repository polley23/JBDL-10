package com.gfg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MovieRepositoryImpl implements MovieRepository {
    SessionFactory sessionFactory;

    public MovieRepositoryImpl() {
    }

    public void init(){
        sessionFactory = new Configuration().configure("com/gfg/hibernate.cfg.xml")
                .buildSessionFactory();
    }
    @Override
    public void create(Movie movie) {
        if(sessionFactory == null ){
            init();
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(movie);
        transaction.commit();
        session.close();
    }

    @Override
    public Movie get(Long id) {
        if(sessionFactory == null ){
            init();
        }
        Session session = sessionFactory.openSession();
        return session.get(Movie.class,id);
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void delete(String name) {

    }
}
