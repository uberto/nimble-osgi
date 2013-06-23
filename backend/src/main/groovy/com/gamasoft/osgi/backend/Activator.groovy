package com.gamasoft.osgi.backend

import com.gamasoft.osgi.backend.persistence.PersistenceServiceBackend
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.persistence.PersistenceService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        println "Hello from Backend bundle activator ${this.class.name}"

        context.registerService PersistenceService.class.getName(),
                new PersistenceServiceBackend(), null;

    }

    public void stop(BundleContext context) {
        println "Bye from Backend bundle activator ${this.class.name}"


    }
}