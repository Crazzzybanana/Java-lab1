package lab;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Arrays;


public class MatrixTest {
    private Matrix matrix;
    private Matrix m;

    @BeforeEach
    void init(){
        matrix = new Matrix(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
        m = new Matrix(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        });
    }

    @DisplayName("Test invalid matrix")
    @Test
    void testInvalidMatrix(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, ()-> new Matrix(new int[][]{
                        {1,2,3},
                        {4,5},
                        {7,8,9}
                })),
                () -> assertThrows(IllegalArgumentException.class, ()-> new Matrix(null)),
                () -> assertThrows(IllegalArgumentException.class, ()-> new Matrix(new int[][]{{1}})),
                () -> assertThrows(IllegalArgumentException.class, ()-> new Matrix(new int[][]{{}}))
        );

    }

    @DisplayName("Test equals success")
    @Test
    void testEquals(){
        assertAll(
                () -> assertEquals(matrix, matrix),
                () -> assertEquals(m, matrix)
        );
    }

    @DisplayName("Test equals failed")
    @Test
    void testEqualsFiled(){
        assertAll(
                () -> assertNotEquals(null, matrix)
        );
    }

    @DisplayName("Test transpose")
    @Test
    void testTranspose(){
        assertEquals(new Matrix(new int[][]{
                {1,4,7},
                {2,5,8},
                {3,6,9}
        }), matrix.transpose());
    }

    @DisplayName("Test to string method")
    @Test
    void testToString(){
        assertEquals("1 2 3\n4 5 6\n7 8 9\n", matrix.toString());
    }

    @AfterEach
    void tearDown(){
        matrix = null;
    }
}
