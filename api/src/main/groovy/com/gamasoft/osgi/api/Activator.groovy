package com.gamasoft.osgi.api

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext

public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        println "Hello from Api bundle activator ${this.class.name}"

    }

    public void stop(BundleContext context) {

    }
}