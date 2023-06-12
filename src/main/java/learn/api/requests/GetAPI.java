package learn.api.requests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import learn.api.utils.HttpInvoker;
import learn.api.specs.RequestSpec;

import static io.restassured.RestAssured.given;

public class GetAPI {

    public static Response get() {
        RequestSpecification requestSpec = given().spec(RequestSpec.getRequestConfig()).header("Accept-Language","en");
        return HttpInvoker.call(Method.GET, "/posts/1", requestSpec, new ResponseSpecBuilder().build());
    }
}
