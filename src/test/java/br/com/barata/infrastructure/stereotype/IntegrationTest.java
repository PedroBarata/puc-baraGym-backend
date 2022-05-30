package br.com.barata.infrastructure.stereotype;

import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ActiveProfiles(profiles = {"integrationtest"})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationTest {
}
