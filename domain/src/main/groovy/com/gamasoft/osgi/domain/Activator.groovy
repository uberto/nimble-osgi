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

    private ServiceTracker myTracker

    public void start(BundleContext context) {

        println "Hello from Domain bundle activator ${this.class.name}"

        context.registerService(UserScheduleService.class.getName(),
                new UserScheduleServiceDomain(), null);

        myTracker = new ServiceTracker(context, PersistenceService.class.getName(), null)

        context.registerService TalksService.class.getName(),
                new TalksServiceDomain(myTracker), null;



    }

    public void stop(BundleContext context) {
        myTracker?.close()
        println "Bye from Domain bundle activator ${this.class.name}"
    }


    }