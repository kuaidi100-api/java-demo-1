import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.request.QueryTrackReq;
import org.junit.Test;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-07-15 16:20
 */
public class BaseServiceTest {


    /**
     * QueryTrack
     */
    @Test
    public void testQueryTrack() throws Exception{

        QueryTrackReq queryTrackReq = new QueryTrackReq();

        queryTrackReq.setCarrier_id("dhl");
        queryTrackReq.setTracking_number("9926933413");
        queryTrackReq.setPhone("95279527");
        queryTrackReq.setArea_show(1);
        queryTrackReq.setOrder("desc");
        queryTrackReq.setShip_from("Toronto, Canada");
        queryTrackReq.setShip_to("Los Angeles, CA, United States");

        QueryTrack queryTrack = new QueryTrack();
        System.out.println(queryTrack.queryTrack(queryTrackReq));
    }


}
