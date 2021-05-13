package com.lut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class ProvideTestData {

    @GetMapping("/vue-element-admin/transaction/list")
    public String list() {

        String listData = "{\n" +
                "    \"code\": 20000,\n" +
                "    \"data\": {\n" +
                "        \"total\": 20,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"order_no\": \"2dD318fb-A14f-af8F-c5a3-3d3F8a1Bb5F9\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Brian Jones\",\n" +
                "                \"price\": 14546.7,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"aFfe5F1A-98f3-2EdF-23aE-E4FcB4AcD3dB\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Thomas Lee\",\n" +
                "                \"price\": 7560.7,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"E05990AC-2db8-F97C-A2D6-bd2904B2F315\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Jose Brown\",\n" +
                "                \"price\": 8812.82,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"38FABDb0-e52A-beFd-adA4-36A7C11FbEfB\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Charles Martinez\",\n" +
                "                \"price\": 4497,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"4978a1d7-6eac-D6bc-B442-aa4Fd28D53bA\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Frank Jones\",\n" +
                "                \"price\": 6284.9,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"DbB52cF8-AF82-3A8d-4f7F-5cf5EB33fAC9\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"John Rodriguez\",\n" +
                "                \"price\": 3771.8,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"C2CE5C38-5fCC-48D3-bEAc-0808cC2eCe53\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Sharon Moore\",\n" +
                "                \"price\": 11878.62,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"EA2B5e89-9dCC-0cc0-eF14-B954136313dA\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Daniel Smith\",\n" +
                "                \"price\": 13714.2,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"9fceedAB-bFe6-b3bb-9Fc5-F8744B26E1ac\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Gary Clark\",\n" +
                "                \"price\": 14717.7,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"Fd7d9636-492B-83bd-D255-cbfdDd9EE464\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Jeffrey Walker\",\n" +
                "                \"price\": 3702.73,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"3FCfe2de-8FCF-c94F-Adf5-2B7FBdfEEe18\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Lisa Williams\",\n" +
                "                \"price\": 4041.7,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"FA7ecAb6-2e7d-9eBb-79b1-7CD48C4edFa6\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Joseph Miller\",\n" +
                "                \"price\": 11205.9,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"CAe85cBb-1e7F-aEBa-3fCb-6BB5daEC7EF4\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Frank White\",\n" +
                "                \"price\": 3715.8,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"C650d80E-9c53-30F9-31c6-EDBDcFb395F8\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Barbara Clark\",\n" +
                "                \"price\": 12993,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"bbde7138-74AB-bdda-35b3-27689A7B63C1\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Patricia Moore\",\n" +
                "                \"price\": 6583,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"9c549E4e-Fa21-bBa1-E18E-7e73BCfCBF74\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Michelle Lewis\",\n" +
                "                \"price\": 12611.8,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"9a8B287f-e817-3c02-578c-45be29F1aD2e\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Amy Wilson\",\n" +
                "                \"price\": 13555.5,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"8A1E6820-FcE1-3BE2-9FcD-94673330d2e3\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Patricia Walker\",\n" +
                "                \"price\": 9170.07,\n" +
                "                \"status\": \"success\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"16bC0268-94Cb-eb3f-0fC7-64D7366bDa4F\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Mary Hall\",\n" +
                "                \"price\": 7166.52,\n" +
                "                \"status\": \"pending\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"order_no\": \"F6bb2dd9-e289-55d6-3dCf-6D7F1c5B6acF\",\n" +
                "                \"timestamp\": 11885227203,\n" +
                "                \"username\": \"Mary Thomas\",\n" +
                "                \"price\": 10183.2,\n" +
                "                \"status\": \"success\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        return listData;
    }

}
