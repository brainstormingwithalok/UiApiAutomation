package learn.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import learn.selenium.utils.ConfigReader;

public class RequestSpec {
    private static RequestSpecification getRequestSpec(String contentType, String baseUri) {
        boolean logPayload = true;
        RequestSpecBuilder specBuilder = new RequestSpecBuilder().setBaseUri(baseUri);
        if (logPayload) {
            specBuilder.log(LogDetail.URI)
                    .log(LogDetail.BODY)
                    .log(LogDetail.HEADERS)
                    .log(LogDetail.METHOD);
        }
        HeaderConfig config = new HeaderConfig();
        /*
        To overwrite the request header configs
        config.overwriteHeadersWithName("","");
         */
        specBuilder.setConfig(RestAssuredConfig.config().headerConfig(config));
        if (contentType != null) {
            specBuilder.setContentType(contentType);
        }
        return specBuilder.build();
    }

    public static RequestSpecification getRequestConfig() {
        return getRequestSpec("application/json", ConfigReader.INSTANCE.getProperty("baseURI"));
    }
}
