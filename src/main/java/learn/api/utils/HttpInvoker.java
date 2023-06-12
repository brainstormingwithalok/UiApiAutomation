package learn.api.utils;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class HttpInvoker {
    private static int MAX_RETRY = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInvoker.class);

    public static Response call(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return call(httpMethod, path, requestSpecification, responseSpecification, false, true, false);
    }

    public static Response call(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification, boolean retry) {
        return call(httpMethod, path, requestSpecification, responseSpecification, retry, true, false);
    }

    public static Response call(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification, boolean retry, boolean withLog) {
        return call(httpMethod, path, requestSpecification, responseSpecification, retry, withLog, false);
    }

    public static Response call(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification, boolean retry, boolean withLog, boolean customHeader) {
        int retryCount = 0;
        Response response = null;
        requestSpecification.header("X-correlation-id", "api" + UUID.randomUUID());
        while (retryCount <= MAX_RETRY) {
            if (withLog) {
                response = callWithLog(httpMethod, path, requestSpecification, responseSpecification);
            } else {
                response = callWithoutLog(httpMethod, path, requestSpecification, responseSpecification);
            }
            if (response.getBody().asString().contains("retry")) {
                LOGGER.info("###Retry request###");
                retryCount++;
            } else {
                //No retry
                break;
            }
        }
        return response;
    }

    private static Response callWithLog(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return invoke(httpMethod, path, requestSpecification).then().log().all().spec(responseSpecification).extract().response();
    }

    private static Response callWithoutLog(Method httpMethod, String path, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        return invoke(httpMethod, path, requestSpecification).then().log().ifError().spec(responseSpecification).extract().response();
    }

    private static Response invoke(Method httpMethod, String path, RequestSpecification requestSpecification) {
        switch (httpMethod) {
            case POST:
                return given(requestSpecification).when().post(path);
            case PUT:
                return given(requestSpecification).when().put(path);
            case GET:
                return given(requestSpecification).when().get(path);
            case DELETE:
                return given(requestSpecification).when().delete(path);
            case PATCH:
                return given(requestSpecification).when().patch(path);
            default:
                throw new RuntimeException("Invalid request method..." + httpMethod);
        }
    }
}
