package lab;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class VectorTest
{
    private Vector V1;
    private Vector V2;

    @BeforeEach
    void initAll(){
        V1 = new Vector(new int[]{1,2,3});
        V2 = new Vector(new int[]{1,2,3});
    }

    @DisplayName("Test success vector creation")
    @Test
    void testSuccessVector(){
        assertEquals(3, V1.getM());
        assertEquals("[1, 2, 3]", Arrays.toString(V1.getData()));

    }

    @DisplayName("Test invalid vector creation")
    @Test
    void testInvalidVector(){
        assertAll(
                () -> assertEquals("Wrong vector", assertThrows(IllegalArgumentException.class, () -> new Vector(new int[]{1,2})).getMessage()),
                () -> assertEquals("Wrong vector", assertThrows(IllegalArgumentException.class, () -> new Vector(new int[]{})).getMessage()),
                () -> assertEquals("Wrong vector", assertThrows(IllegalArgumentException.class, () -> new Vector(new int[]{1,2,3,4})).getMessage()),
                () -> assertEquals("Wrong vector", assertThrows(IllegalArgumentException.class, () -> new Vector(null)).getMessage())
        );
    }

    @DisplayName("Test null in function arguments")
    @Test
    void testNullArguments(){
        assertEquals("Wrong cross vector", assertThrows(IllegalArgumentException.class, () -> V1.cross(null)).getMessage());
        assertEquals("Wrong multiply vector", assertThrows(IllegalArgumentException.class, () -> V1.multiply(null)).getMessage());
    }

    @DisplayName("Test vector multiplication")
    @Test
    void testVectorMulti(){
        assertEquals(new Vector(new int[]{0,0,0}), V1.cross(V2));
    }

    @DisplayName("Vectors equals")
    @Test
    void testEquals(){
        assertAll(
                () -> assertEquals(V1, V2),
                () -> assertEquals(V1, V1)
        );
    }

    @DisplayName("Vectors equals failed")
    @Test
    void testEqualsFailed(){
        assertNotEquals(V1, null);
    }

    @DisplayName("Vector and matrix multiplication")
    @Test
    void testMatrixVectorMulti(){
        Matrix m = new Matrix(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
        assertEquals(new Vector(new int[]{14,32,50}), V1.multiply(m));
    }

    @DisplayName("Test to string method")
    @Test
    void testToString(){
        assertEquals("X: 1|Y: 2|Z: 3", V1.toString());
    }

    @DisplayName("Task")
    @Test
    void task(){
        Matrix m = new Matrix(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
        assertEquals(new Vector(new int[]{30,36,42}), V1.multiply(m.transpose()));
    }

    @AfterEach
    void tearDown(){
        V1 = null;
        V2 = null;
    }
}
