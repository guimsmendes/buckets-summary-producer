package br.com.guimsmendes.template;

import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.guimsmendes.template.FixtureFactoryTemplateLoader.VALID;

public class SummaryTagProducerRequestTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(SummaryTagProducerRequest.class)
                .addTemplate(VALID.name(), new Rule() {{
                    add("index", "i-mkp-hot");
                    add("bucket", "guardRails");
                    add("quantity", 1200);
                }});

    }
}
