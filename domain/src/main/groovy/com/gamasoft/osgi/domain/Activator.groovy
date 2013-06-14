package com.gamasoft.osgi.domain
import com.gamasoft.osgi.api.interfaces.SpeakersService
import com.gamasoft.osgi.api.interfaces.TalksService
import com.gamasoft.osgi.api.interfaces.UserScheduleService
import com.gamasoft.osgi.api.persistence.PersistenceService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.util.tracker.ServiceTracker


public class Activator implements BundleActivator {

    private ServiceTracker myTracker

    public void start(BundleContext context) {
        println "Hello from Domain bundle activator ${this.class.name}"

        context.registerService(SpeakersService.class.getName(),
                new SpeakersServiceDomain(), null);

        context.registerService(UserScheduleService.class.getName(),
                new UserScheduleServiceDomain(), null);

        myTracker = createTracker context, PersistenceService.class

        context.registerService TalksService.class.getName(),
                new TalksServiceDomain(myTracker), null;



    }

    public void stop(BundleContext context) {
        myTracker?.close()
    }


    def createTracker(BundleContext bundleContext, Class serviceClass) {

        return new ServiceTracker(bundleContext, serviceClass.getName(), null)

    }
}