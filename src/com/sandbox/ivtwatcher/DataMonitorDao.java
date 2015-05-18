package com.sandbox.ivtwatcher;

import java.util.ArrayList;
import java.util.List;

import com.sandbox.ivtwatcher.model.IvtProcess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMonitorDao {

    /**
     * Hibernate session factory singleton instance.
     */
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration conf = new Configuration();
            conf.configure(ClassLoader.getSystemResource("database/hibernate.cfg.xml"));
            SESSION_FACTORY = conf.buildSessionFactory();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns session factory.
     *
     * @return session factory.
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Closes session factory.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }

    public static List<IvtProcess> getActiveProcesses() {
        List<IvtProcess> processes = new ArrayList<IvtProcess>();

        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Object[]> items = (List<Object[]>) session.getNamedQuery("getProcessesList").list();
        tx.commit();

        if (items != null){
            for (Object[] data : items){
                IvtProcess process = new IvtProcess();
                process.setTaskId((Integer) data[1]);
                process.setName((String) data[2]);
                process.setPath((String) data[3]);
                processes.add(process);
            }
        }

        return processes;

    }
}
