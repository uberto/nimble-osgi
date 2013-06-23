package com.gamasoft.osgi.backend.persistence

import com.gamasoft.osgi.interfaces.persistence.PersistenceService


class PersistenceServiceBackend implements PersistenceService {
    @Override
    def InputStream loadTalks() {

        println "loadTalks: sending stream"
//        println "data " +  getClass().getResourceAsStream("/talks.xml").text

        getClass().getResourceAsStream("/talks.xml")


    }
}
