package com.kuaidi100.sdk.request;

import lombok.Data;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-07-14 15:56
 */
@Data
public class QueryTrackReq extends BaseRequest {
    /**
     *  ex:dhl、fedex
     */
    private String carrier_id;

    private String tracking_number;

    /*
     *  sender & recipient phone number (fill in with a mobile phone number or landline number)
     */
    private String phone;

    /*
     *  sender city
     */
    private String ship_from;

    /*
     *  recipient city. The tracking frequency will be increased when the shipment arrives at the recipient city.
     */
    private String ship_to;

    /*
     *  Adding this field means using the administrative area determination feature.
     *  0: close (default)
     *  1: return data about area_name, location, order_status_description
     */
    private Integer area_show;

    /*
     *  false Returned data sort: desc(default), asc.
     */
    private String order;

}
