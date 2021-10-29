package br.com.guimsmendes.dataprovider.repository;

import br.com.guimsmendes.core.domain.BucketsSummary;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BucketsSummaryRepository implements PanacheMongoRepository<BucketsSummary> {
}
