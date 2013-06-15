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
        def serviceRef = context.getServiceReference(serviceClass.getName())
        tracker = new ServiceTracker(context, serviceRef, null)
        tracker.open()
    }

    def stop() {
        tracker?.close()
    }

    def call(Closure closure) {

        T service = tracker.service as T
        if (service != null) {
            closure(service)
        }
        this
    }

    def orElse = { Closure closure ->
        T service = tracker.service as T
        if (service == null) {
            closure()
        }
        this
    }
}
