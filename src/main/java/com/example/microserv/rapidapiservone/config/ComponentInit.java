package com.example.microserv.rapidapiservone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentInit {


    @Value("${app.other.dbserv}")
    private String dbServPath;

    public String getDbServPath() {
        return dbServPath;
    }

    public void setDbServPath(String dbServPath) {
        this.dbServPath = dbServPath;
    }



}
