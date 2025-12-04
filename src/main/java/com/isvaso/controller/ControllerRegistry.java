package com.isvaso.controller;

import com.isvaso.exception.ControllerRegistryException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerRegistry {

    private final Map<ControllerNameEnum, Controller> registry = new ConcurrentHashMap<>();

    public void add(ControllerNameEnum controllerName, Controller controller) {
        if(!registry.containsKey(controllerName))
            registry.put(controllerName, controller);
        else
            throw new ControllerRegistryException("Controller for name '%s' already registered".formatted(controllerName));
    }

    public Controller get(ControllerNameEnum controllerName) {
        if(!registry.containsKey(controllerName))
            throw new ControllerRegistryException("No controller registered name '%s'".formatted(controllerName));
        return registry.get(controllerName);
    }
}
