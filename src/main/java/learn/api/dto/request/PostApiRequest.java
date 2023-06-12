package learn.api.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class PostApiRequest {
    @Builder.Default
    private String title = "test";
    @Builder.Default
    private String body = "test";
    @Builder.Default
    private int userId = new Random().nextInt(1000);
}
