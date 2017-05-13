package com.epam.www.domain;

import java.util.Calendar;

/**
 * Created by Farkas on 2017.04.01..
 */
public class QueryBuilder {

    private StringBuilder builder;

    public QueryBuilder(){
        this.builder = new StringBuilder();
    }

    public QueryBuilder withBaseString(String base){
        this.builder.append(base);
        return this;
    }

    public QueryBuilder withId(String id){
        this.builder.append(" id=");
        this.builder.append(id);
        return this;
    }

    public QueryBuilder withTitle(String title){
        this.builder.append(" title='");
        this.builder.append(title);
        this.builder.append("'");
        return this;
    }

    public QueryBuilder withDateRange(String from, String to){
        this.builder.append(" airDate BETWEEN ");
        this.builder.append(from);
        this.builder.append(" AND ");
        this.builder.append(to);
        return this;
    }

    public QueryBuilder withEmail(String email) {
        this.builder.append(" email='");
        this.builder.append(email);
        this.builder.append("'");
        return this;
    }

    public QueryBuilder withName(String name) {
        this.builder.append(" name='");
        this.builder.append(name);
        this.builder.append("'");
        return this;
    }

    public String build(){
        return this.builder.toString();
    }


    public QueryBuilder withEventId(int eventId) {
        this.builder.append(" event=");
        this.builder.append(eventId);
        return this;
    }

    public QueryBuilder withActive(String active) {
        this.builder.append(" active=");
        this.builder.append(active);
        return this;
    }

    public QueryBuilder withUser(int userId) {
        this.builder.append(" user=");
        this.builder.append(userId);
        return this;
    }
}
