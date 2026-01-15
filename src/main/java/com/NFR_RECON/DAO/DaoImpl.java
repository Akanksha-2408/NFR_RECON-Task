package com.NFR_RECON.DAO;

import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Entity.QueryBean;
import com.NFR_RECON.Exception.GSPException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class DaoImpl implements Dao {

    private SessionFactory sessionFactory;

    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int updateBySqlQuery(String dbQuery, List<KeyValue> conditions) throws GSPException {
        Session session = null;
        int result = 0;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createNativeQuery(dbQuery);
            if (conditions != null) {
                for (KeyValue kv : conditions) {
                    if (kv.getValue() instanceof Collection) {
                        query.setParameterList(kv.getKey(), (Collection) kv.getValue());
                    }else{
                        query.setParameter(kv.getKey(), kv.getValue());
                    }
                }
            }

            result = query.executeUpdate();
            if (null != session.getTransaction()) {
                session.getTransaction().commit();
            }

        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new GSPException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;

    }

    @Override
    public List<Object[]> executeSqlQuery(QueryBean sQuery, List<KeyValue> conditions) throws GSPException {
        Session session = null;
        List<Object[]> list = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createNativeQuery(sQuery.getQuery()).setCacheable(sQuery.isCacheable());
            if (conditions != null) {
                for (KeyValue kv : conditions) {
                    if (kv.getValue() instanceof Collection) {
                        query.setParameterList(kv.getKey(), (Collection) kv.getValue());
                    } else {
                        query.setParameter(kv.getKey(), kv.getValue());
                    }
                }
            }

            list = query.list();
            if (null != session.getTransaction()) {
                session.getTransaction().commit();
            }
            if (list == null) {
                list = null;
            }
            return list;
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new GSPException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
