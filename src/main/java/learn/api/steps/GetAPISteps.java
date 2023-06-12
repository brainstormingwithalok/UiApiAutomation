package learn.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import learn.api.dto.response.GetPostApiResponse;
import learn.api.requests.GetAPI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetAPISteps {

    @Step
    public GetPostApiResponse get() {
        Response response = GetAPI.get();
        assertThat(response.getStatusCode()).isEqualTo(200);
        return response.jsonPath().getObject("", GetPostApiResponse.class);
    }
}
