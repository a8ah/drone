package com.demo.drone.data.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class AbstractController {

    @Autowired
    protected ApplicationContext context;

}
