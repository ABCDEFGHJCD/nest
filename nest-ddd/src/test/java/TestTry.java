import org.junit.Test;

public class TestTry {
    @Test
    public void testTry() throws Exception {
        try {
            System.out.println("d 1");

            throw new Exception("出错了");

        } catch (Exception ex) {
            System.out.println("catch 1");
            throw ex;

        } finally {
            System.out.println("finally");
        }
    }

}
