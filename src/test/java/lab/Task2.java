package lab;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Task2 {
    private Map<String, Integer> hashMap;
    @BeforeEach
    void init(){
        hashMap = new HashMap<String, Integer>();
    }

    @Test
    void clearTest() {
        
    }

    @AfterEach
    void tearDown(){
        hashMap = null;
    }
}
