package com.gamasoft.osgi.frontend

import com.gamasoft.osgi.api.interfaces.TalksService
import com.gamasoft.osgi.frontend.tracker.ServiceProxy
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference
import org.osgi.service.http.HttpService

public class Activator implements BundleActivator {

    def ServiceProxy<TalksService> talks = new ServiceProxy(TalksService)
    def servletRegistration
    def HttpService servletService


    public void start(BundleContext context) {
        println "Hello from Frontend bundle activator ${this.class.name}"

        talks.start(context)

        registerNewServlet context

    }

    def registerNewServlet(BundleContext context) {
        ServiceReference sRef = context.getServiceReference(HttpService.class.name)
        if (sRef != null) {
            servletService = (HttpService) context.getService(sRef)
            servletRegistration = servletService.registerServlet("/conference", new RestServlet(talks), null, null)
        }
    }


    public void stop(BundleContext context) {

        servletService?.unregister("/conference")

        talks.stop()

        println "Bye from Frontend bundle activator ${this.class.name}"

    }
}