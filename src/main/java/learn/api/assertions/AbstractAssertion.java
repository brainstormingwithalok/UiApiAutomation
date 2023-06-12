package learn.api.assertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AbstractAssertion {

    public void isNotNull(Object actual) {
        assertThat(actual).isNotNull();
    }
}
