package isuru.config;

import isuru.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

    // Use SLF4J logger as defined in your dependencies
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    // The SessionFactory is heavy to create, so we make it static (Singleton)
    private static SessionFactory sessionFactory;

    /**
     * Initializes the SessionFactory.
     */
    public static void initialize() {
        if (sessionFactory == null) {
            try {
                // 1. Create Configuration object
                Configuration configuration = new Configuration();

                // 2. Load the hibernate.cfg.xml file
                configuration.configure("hibernate.cfg.xml");

                // 3. Register entity classes programmatically (if you didn't use <mapping> in XML)
                // It's good practice to do this here even if you use XML mapping.
                configuration.addAnnotatedClass(Student.class);
                // configuration.addAnnotatedClass(Payment.class);

                // 4. Build the SessionFactory
                sessionFactory = configuration.buildSessionFactory();
                LOGGER.info("Hibernate SessionFactory initialized successfully.");

            } catch (Exception ex) {
                // Log the exception details
                LOGGER.error("Initial SessionFactory creation failed.", ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
    }

    /**
     * Returns the initialized SessionFactory.
     * Call initialize() first before calling this method.
     *
     * @return The initialized SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initialize(); // Ensure initialization happens on first call
        }
        return sessionFactory;
    }

    /**
     * Shuts down the SessionFactory and connection pool.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            LOGGER.info("Hibernate SessionFactory shutdown complete.");
        }
    }

}
