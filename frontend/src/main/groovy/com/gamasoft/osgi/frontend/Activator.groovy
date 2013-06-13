package com.gamasoft.osgi.frontend

import com.gamasoft.osgi.api.interfaces.TalksService
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference
import org.osgi.service.http.HttpService
import org.osgi.util.tracker.ServiceTracker

public class Activator implements BundleActivator {

    def ServiceTracker tracker
    def servletRegistration
    private HttpService servletService

    public void start(BundleContext context) {
        println "Hello from Frontend bundle activator ${this.class.name}"

        openTracker context, TalksService.class

        registerNewServlet context

    }

    def registerNewServlet(BundleContext context) {
        ServiceReference sRef = context.getServiceReference(HttpService.class.name)
        if (sRef != null) {
            servletService = (HttpService) context.getService(sRef)
            servletRegistration = servletService.registerServlet("/conference", new SimpleServlet(tracker), null, null)
        }
    }

    def openTracker(BundleContext bundleContext, Class serviceClass) {

        tracker = new ServiceTracker(bundleContext, serviceClass.getName(), null)

        tracker.open()
    }

    public void stop(BundleContext context) {

        servletService?.unregister("/conference")

        tracker?.close()

        println "Bye from Frontend bundle activator ${this.class.name}"

    }
}