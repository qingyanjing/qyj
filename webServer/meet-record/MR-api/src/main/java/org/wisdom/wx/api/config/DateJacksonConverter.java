package org.wisdom.wx.api.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJacksonConverter extends JsonDeserializer<Date> {
    private static String[] pattern =
            new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S"};

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Date targetDate = null;
        String originDate = p.getText();
        if (!StringUtils.isEmpty(originDate)) {
            try {
                long longDate = Long.valueOf(originDate.trim());
                targetDate = new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    targetDate = this.parseDate(originDate, DateJacksonConverter.pattern);
                } catch (ParseException pe) {
                    throw new IOException(String.format(
                            "'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)",
                            originDate,
                            StringUtils.arrayToCommaDelimitedString(pattern)));
                }
            }
        }
        return targetDate;
    }

    private Date parseDate(String unParse,String [] pattern) throws ParseException {
        Date parseDate=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        for (int i = 0; i < pattern.length; i++) {
            simpleDateFormat.applyPattern(pattern[i]);
            try {
                parseDate=simpleDateFormat.parse(unParse);
            } catch (ParseException e) {
                if (i==pattern.length-1){
                    throw e;
                }
                continue;
            }
            if (parseDate!=null){
                break;
            }
        }
        return parseDate;
    }
    @Override
    public Class<?> handledType() {
        return Date.class;
    }
}
