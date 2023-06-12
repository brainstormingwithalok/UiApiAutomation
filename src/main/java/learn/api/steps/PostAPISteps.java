package learn.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import learn.api.dto.request.PostApiRequest;
import learn.api.dto.response.GetPostApiResponse;
import learn.api.requests.PostAPI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostAPISteps {

    @Step
    public GetPostApiResponse post(PostApiRequest request) {
        Response response = PostAPI.post(request);
        assertThat(response.getStatusCode()).isEqualTo(201);
        return response.jsonPath().getObject("", GetPostApiResponse.class);
    }
}
