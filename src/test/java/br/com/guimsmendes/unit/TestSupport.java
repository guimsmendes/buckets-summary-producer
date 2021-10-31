package br.com.guimsmendes.unit;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

@QuarkusTest
public abstract class TestSupport {

    @BeforeAll
    public static void setupTemplate() {
        FixtureFactoryLoader.loadTemplates("br.com.guimsmendes.template");
    }

    public abstract void init() throws IOException;
}
