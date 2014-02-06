package org.jboss.forge.arquillian.container;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class ContainerDirectoryParserTest {

    @Test
    public void should_resolve_container_descriptors() {
        // given
        ContainerDirectoryParser provider = new ContainerDirectoryParser();
        provider.parse();

        // when
        List<Container> containers = provider.getContainers();

        // then
        assertFalse(containers.isEmpty());
    }

}
