package com.joe.oauth.algorithm;

import com.google.common.collect.Lists;
import com.joe.oauth.utils.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date 2019/2/19
 * 调用API接口获取一年的节假日和双休日
 */
public class HolidayUtil {


    @Autowired
    private RestTemplate restTemplate;

    public void addHolidayForOneYear() throws Exception {
        List<String> dateStr = getInitMonthMapWithZero();
        String apiURL = "http://www.easybots.cn/api/holiday.php?m=" + dateStr.stream().collect(Collectors.joining(","));
        String result = restTemplate.getForObject(apiURL, String.class);
        if (StringUtils.isNotBlank(result)) {

            Map<String, Object> map = FastJsonUtils.json2map(result);
            Map<String, Object> orderByResult = new LinkedHashMap<>();
            map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered( x -> orderByResult.put(x.getKey(), x.getValue()));
            for (Map.Entry<String, Object> entry : orderByResult.entrySet()) {
                Map<String, Object> mapValue = FastJsonUtils.json2map(orderByResult.get(entry.getKey()).toString());
                Map<String, Object> orderByMapValueKeyResult = new LinkedHashMap<>();
                mapValue.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> orderByMapValueKeyResult.put(x.getKey(), x.getValue()));
                for (Map.Entry<String, Object> entryValue : orderByMapValueKeyResult.entrySet()) {
                    String holiday=LocalDate.parse(entry.getKey() + "" + entryValue.getKey(), DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
                    System.out.println(holiday);
                }
            }

        }


    }


    private List<String> getInitMonthMapWithZero() {
        List<String> list = Lists.newArrayList();
        LocalDate localDate = LocalDate.now();
        int month = 12;
        for (int j = 1; j <= month; j++) {
            String date = "";
            date = localDate.getYear() + (StringUtils.leftPad(String.valueOf(j), 2, "0"));
            list.add(date);
        }

        return list;
    }


    public static void main(String[] args) throws Exception {
        HolidayUtil util=new HolidayUtil();
        util.addHolidayForOneYear();

    }
}
