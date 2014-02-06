package org.jboss.forge.arquillian.ui.util;

import org.jboss.forge.addon.convert.Converter;
import org.jboss.forge.addon.dependencies.Coordinate;
import org.jboss.forge.arquillian.container.Container;

public final class Converters {

    public static final CoordinateVersionConverter coordinates = new CoordinateVersionConverter();
    public static final ContainerConverter containers = new ContainerConverter();

    public static final class CoordinateVersionConverter implements Converter<Coordinate, String> {

        @Override
        public String convert(Coordinate coordinate) {
            return coordinate.getVersion();
        }

    }

    public static final class ContainerConverter implements Converter<Container, String> {

        private static final String NAME_PREFIX = "Arquillian Container ";

        @Override
        public String convert(Container container) {
            String name = container.getName();
            if (name.startsWith(NAME_PREFIX)) {
                name = name.substring(NAME_PREFIX.length());
            }
            return name;
        }

    }
}
