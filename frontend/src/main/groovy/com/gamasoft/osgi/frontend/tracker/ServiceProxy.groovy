package com.gamasoft.osgi.frontend.tracker

import org.osgi.framework.BundleContext
import org.osgi.util.tracker.ServiceTracker

class ServiceProxy<T> {

    def ServiceTracker tracker
    private Class serviceClass

    ServiceProxy(Class serviceClass) {
        this.serviceClass = serviceClass
    }

    def start(BundleContext context) {
        def serviceName = serviceClass.getName()
        println("!!! serviceName " + serviceName)
        def serviceRef = context.getServiceReference(serviceName)
        tracker = new ServiceTracker(context, serviceRef, null)
        tracker.open()
    }

    def stop() {
        tracker?.close()
    }

    def call(Closure<T> closure) {

        println("--- " + tracker.getService())

        T service = tracker.service as T
        if (service != null) {
            closure(service)
        } else {
            println "service " + tracker.getServiceReference()
        }
        this
    }

    def orElse(Closure closure) {
        T service = tracker.service as T
        if (service == null) {
            closure()
        }
        this
    }
}
