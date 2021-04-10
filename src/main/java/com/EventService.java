package com;

import com.annotation.Inject;

public class EventService extends EventDao implements Provider<EventDao> {

    private final EventDao eventDao;

    @Inject
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public EventService(EventDao eventDao, Object obj) {
        this.eventDao = eventDao;
    }

    @Override
    public EventDao getInstance() {
        return eventDao;
    }
}
