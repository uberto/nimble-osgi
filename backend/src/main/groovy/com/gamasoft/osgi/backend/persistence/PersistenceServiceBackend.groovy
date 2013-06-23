package com.gamasoft.osgi.backend.persistence

import com.gamasoft.osgi.interfaces.persistence.PersistenceService


class PersistenceServiceBackend implements PersistenceService {
    @Override
    def InputStream loadTracks() {
        getClass().getResourceAsStream("/talks.xml")
    }
}
