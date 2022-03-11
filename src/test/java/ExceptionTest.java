/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lizy
 * @version 1.0
 * @date Created in 2022年02月25日 10:28
 * @since 1.0
 */
public class ExceptionTest {

    public static void main(String[] args) {

        try{
            int a = 10 / 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("....");

    }
}
