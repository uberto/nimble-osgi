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
// Use reference if you want to track only specific version of service
//        def serviceRef = context.getServiceReference(serviceClass.getName())

        tracker = new ServiceTracker(context, serviceClass.getName(), null)
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
