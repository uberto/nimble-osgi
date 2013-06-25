package com.gamasoft.osgi.backend.persistence

import com.gamasoft.osgi.interfaces.persistence.PersistenceService


class PersistenceServiceBackend implements PersistenceService {
    @Override
    def InputStream loadTalks() {

        def fileName = "/talks.xml"

        println "loadTalks: sending stream from ${fileName}"

        getClass().getResourceAsStream(fileName)

    }
}
