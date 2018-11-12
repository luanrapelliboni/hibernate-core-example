package br.com.hibernatemavenexample.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;

import br.com.hibernatemavenexample.util.HibernateUtil;

public class HibernateRepository<T> implements GenericRepository<T> {

	private Class<T> entityClass;
		
	public HibernateRepository() {
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.entityClass = (Class) pt.getActualTypeArguments()[0];
	}

	protected final Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void create(T obj) {
		Session session = getCurrentSession();
		session.beginTransaction();
		getCurrentSession().persist(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(T obj) {
		Session session = getCurrentSession();
		session.beginTransaction();
		getCurrentSession().delete(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public T find(Long id) {
		Session session = getCurrentSession();
		session.beginTransaction();
		T obj = (T) session.find(this.entityClass, id);
		session.close();
		return obj;
	}

	@Override
	public void update(T obj) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.merge(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<T> findAll() {
		Session session = getCurrentSession();
		session.beginTransaction();
		List<T> objects = session.createQuery("from " + this.entityClass.getName(), this.entityClass).list();
		session.close();
		return objects;
	}
}
