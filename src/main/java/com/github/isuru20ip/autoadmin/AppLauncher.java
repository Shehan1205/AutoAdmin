package com.github.isuru20ip.autoadmin;

// import com.yourcompany.sms.ui.MainFrame; // Assuming your main Swing frame

import com.github.isuru20ip.autoadmin.config.HibernateUtil;
import com.github.isuru20ip.autoadmin.ui.MainFrame;

public class AppLauncher {

    public static void main(String[] args) {
        // 1. Initialize Hibernate SessionFactory (must be done first)
        try {
            HibernateUtil.initialize();
        } catch (ExceptionInInitializerError e) {
            System.err.println("FATAL: Could not initialize Hibernate. Exiting.");
            e.printStackTrace();
            return; // Stop application launch
        }

        // 2. Launch your Swing GUI on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
             new MainFrame().setVisible(true); // Replace with your main UI component launch
        });

        // 3. Setup a shutdown hook to close the SessionFactory cleanly
        Runtime.getRuntime().addShutdownHook(new Thread(HibernateUtil::shutdown));
    }
}