/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.arquillian.container;

import static org.codehaus.jackson.map.PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import lombok.SneakyThrows;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Author Paul Bakker - paul.bakker.nl@gmail.com
 */
@ApplicationScoped
public class ContainerDirectoryParser {

    private List<Container> containers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    void parse() {
        try {
            objectMapper.setPropertyNamingStrategy(CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            List<Container> parsedContainers = objectMapper.readValue(getUrl(),
                    new TypeReference<List<Container>>() { });
            containers = Collections.unmodifiableList(parsedContainers);
        } catch (Exception e) {
            throw new RuntimeException("Containers could not be loaded", e);
        }
    }

    public List<Container> getContainers() {
        return containers;
    }

    @SneakyThrows
    protected URL getUrl() {
        return getClass().getClassLoader().getResource("containers.json").toURI().toURL();
    }

}
