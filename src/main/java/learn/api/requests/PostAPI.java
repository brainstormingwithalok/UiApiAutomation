package learn.api.requests;

import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import learn.api.dto.request.PostApiRequest;
import learn.api.specs.RequestSpec;
import learn.api.utils.HttpInvoker;

import static io.restassured.RestAssured.given;

public class PostAPI {

    public static Response post(PostApiRequest request) {
        String body = new Gson().toJson(request);
//        String body="{\"userId\": 1,\"title\": \"test\",\"body\": \"rest\"}";
        RequestSpecification requestSpec = given().spec(RequestSpec.getRequestConfig())
                .header("Content-type", "application/json; charset=UTF-8")
                .body(body);
        return HttpInvoker.call(Method.POST, "/posts", requestSpec, new ResponseSpecBuilder().build());
    }
}
