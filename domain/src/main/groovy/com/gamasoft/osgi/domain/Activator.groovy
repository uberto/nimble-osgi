package com.gamasoft.osgi.domain

import com.gamasoft.osgi.domain.talks.TalksServiceDomain
import com.gamasoft.osgi.domain.users.UserScheduleServiceDomain
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService
import com.gamasoft.osgi.interfaces.persistence.PersistenceService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.util.tracker.ServiceTracker

public class Activator implements BundleActivator {

    private ServiceTracker serviceTracker

    public void start(BundleContext context) {

        println "Hello from Domain bundle activator ${this.class.name}"

        context.registerService UserScheduleService.class.getName(),
                new UserScheduleServiceDomain(), null

        serviceTracker = new ServiceTracker(context, PersistenceService.class.getName(), null)

        serviceTracker.open()

        context.registerService TalksService.class.getName(),
                createServiceDomain(serviceTracker), null

    }

    private TalksServiceDomain createServiceDomain(serviceTracker) {
        new TalksServiceDomain({ serviceTracker.getService() as PersistenceService })
    }

    public void stop(BundleContext context) {
        serviceTracker?.close()
        println "Bye from Domain bundle activator ${this.class.name}"
    }


    }