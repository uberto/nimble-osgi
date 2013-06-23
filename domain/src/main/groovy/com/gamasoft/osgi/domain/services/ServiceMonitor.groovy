package com.gamasoft.osgi.domain.services
import org.osgi.framework.ServiceReference
import org.osgi.util.tracker.ServiceTrackerCustomizer

class ServiceMonitor implements ServiceTrackerCustomizer {
    @Override
    Object addingService(ServiceReference serviceReference) {
        println "adding service " + serviceReference.getPropertyKeys() + "  " + serviceReference.getBundle().bundleId
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void modifiedService(ServiceReference serviceReference, Object o) {
        println "modified service " + serviceReference.getPropertyKeys() + "  " + serviceReference.getBundle().bundleId
    }

    @Override
    void removedService(ServiceReference serviceReference, Object o) {
        println "removed service " + serviceReference.getPropertyKeys() + "  " + serviceReference.getBundle().bundleId
    }
}
