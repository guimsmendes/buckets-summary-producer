package br.com.guimsmendes.template;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.domain.Status;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

import static br.com.guimsmendes.template.FixtureFactoryTemplateLoader.VALID;

public class BucketsSummaryTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(BucketsSummary.class)
                .addTemplate(VALID.name(), new Rule() {{
                    add("id", "validId");
                    add("bucket", "guardRails");
                    add("quantity", 1200);
                    add("status", Status.WAITING);
                    add("created", LocalDateTime.now());
                    add("updated", LocalDateTime.now());
                }});
    }
}
