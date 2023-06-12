package api;

import learn.api.dto.request.PostApiRequest;
import learn.api.dto.response.GetPostApiResponse;
import learn.api.steps.GetAPISteps;
import learn.api.steps.PostAPISteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetPostAPITest {

    private GetAPISteps getAPISteps;
    private PostAPISteps postAPISteps;

    @BeforeClass
    public void setup() {
        getAPISteps = new GetAPISteps();
        postAPISteps = new PostAPISteps();
    }

    @Test
    public void getApiTest() {
        GetPostApiResponse response = getAPISteps.get();
        System.out.println(response);
    }

    @Test
    public void postApiTest() {
        PostApiRequest request=new PostApiRequest();
        GetPostApiResponse response = postAPISteps.post(request);
        System.out.println(response);
    }
}
