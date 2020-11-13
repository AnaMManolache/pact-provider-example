package nl.ing.cdc.example.calculator;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.Pact;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
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
@PactBroker(scheme = "https", host = "${PACT_BROKER_HOST}",
        enablePendingPacts = "${PACT_ENABLE_PENDING},",
        tags={"${CONSUMER_TAGS}"},
        authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
@ExtendWith(SpringExtension.class)
@Provider("CalculationAPI")
@IgnoreNoPactsToVerify
public class CalculatorBrokerPactTest {

    @BeforeEach
    void setTarget(PactVerificationContext context) {
        HttpTestTarget target = new HttpTestTarget("localhost", 8081);

        if (context != null) {
            context.setTarget(target);
        }
    }

    @BeforeAll
    static void pactBrokerSetup(@Value("${pact.verifier.publishResults}") String publishResults,
                                @Value("${pact.consumer.version}") String pactConsumerVersion,
                                @Value("${pact.provider.version}") String pactProviderVersion) {
        System.setProperty("pact.verifier.publishResults", publishResults);
        System.setProperty("pact.consumer.version", pactConsumerVersion);
        System.setProperty("pact.provider.version", pactProviderVersion);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(Pact<Interaction> pact, Interaction interaction, HttpRequest request, PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }
}