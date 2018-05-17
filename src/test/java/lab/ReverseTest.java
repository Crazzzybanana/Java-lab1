package lab;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTest {
    static class FSMProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("", ""),
// не понял как поймать nullpointerexception
//                    Arguments.of(null, new NullPointerException),
                    Arguments.of("s", "s"),
                    Arguments.of("qw", "wq"),
                    Arguments.of("random string", "gnirts modnar")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FSMProvider.class)
    void test(String str, String res) {
        assertEquals(new Reverse().reverse(str), res);
    }
}
