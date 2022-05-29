package br.com.barata.baragym;

import br.com.barata.infrastructure.stereotype.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertNotNull;

@IntegrationTest
class BaragymApplicationIT {

 @Autowired
 private ApplicationContext context;

 @Test
 void contextLoads() {
  Assertions.assertNotNull(context);
 }
}
