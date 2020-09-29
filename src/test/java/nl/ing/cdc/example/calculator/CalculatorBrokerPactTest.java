package nl.ing.cdc.example.calculator;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.Pact;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8081")
@PactBroker
@ExtendWith(SpringExtension.class)
@Provider("CalculationAPI")
public class CalculatorBrokerPactTest {

    @BeforeEach
    void setTarget(PactVerificationContext context) {
        HttpTestTarget target = new HttpTestTarget("localhost", 8081);
        context.setTarget(target);
    }

    @BeforeAll
    static void pactBrokerSetup(@Value("${pactbroker.host}") String host,
                                @Value("${pactbroker.port}") String port,
                                @Value("${pactbroker.scheme}") String scheme,
                                @Value("${pactbroker.auth.username}") String username,
                                @Value("${pactbroker.auth.password}") String password,
                                @Value("${pact.verifier.publishResults}") String publishResults,
                                @Value("${pact.consumer.version}") String pactConsumerVersion) {
        System.setProperty("pact.verifier.publishResults", publishResults);
        System.setProperty("pact.consumer.version", pactConsumerVersion);

        System.setProperty("pactbroker.host", host);
        System.setProperty("pactbroker.port", port);
        System.setProperty("pactbroker.scheme", scheme);
        System.setProperty("pactbroker.auth.username", username);
        System.setProperty("pactbroker.auth.password", password);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(Pact<Interaction> pact, Interaction interaction, HttpRequest request, PactVerificationContext context) {
        context.verifyInteraction();
    }
}