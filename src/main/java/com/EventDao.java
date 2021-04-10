package com;

public class EventDao implements Provider<EventDao> {

    @Override
    public EventDao getInstance() {
        return new EventDao();
    }



}
