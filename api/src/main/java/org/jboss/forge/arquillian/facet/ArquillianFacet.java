package org.jboss.forge.arquillian.facet;

import org.jboss.forge.addon.projects.ProjectFacet;
import org.jboss.forge.arquillian.container.Container;

public interface ArquillianFacet extends ProjectFacet {

    Container container(String profileName);

}
