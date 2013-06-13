package com.gamasoft.osgi.domain

import com.gamasoft.osgi.api.interfaces.SpeakersService
import com.gamasoft.osgi.api.interfaces.TalksService
import com.gamasoft.osgi.api.values.UserSchedule
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        println "Hello from Domain bundle activator ${this.class.name}"

        context.registerService(SpeakersService.class.getName(),
                new SpeakersServiceDomain(), null);

        context.registerService(TalksService.class.getName(),
                new TalksServiceDomain(), null);

        context.registerService(UserSchedule.class.getName(),
                new UserScheduleDomain(), null);
    }

    public void stop(BundleContext context) {

    }
}