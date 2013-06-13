package com.gamasoft.osgi.backend

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        println "Hello from Backend bundle activator ${this.class.name}"
    }

    public void stop(BundleContext context) {

    }
}