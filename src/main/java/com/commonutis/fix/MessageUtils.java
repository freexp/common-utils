package com.commonutis.fix;

import quickfix.*;

import java.util.*;

public class MessageUtils {
    public static Map convertToMap(DataDictionary dd, Message message) throws FieldNotFound {

        Map resultMap = new HashMap();

        parseFieldMap(dd, message.getHeader(), resultMap);
        parseFieldMap(dd, message, resultMap);
        parseFieldMap(dd, message.getTrailer(), resultMap);

        return resultMap;
    }

    private static void parseFieldMap(DataDictionary dd, FieldMap fieldMap, Map resultMap)
            throws FieldNotFound {


        Iterator fieldIterator = fieldMap.iterator();
        while (fieldIterator.hasNext()) {
            Field field = (Field) fieldIterator.next();
            if (!isGroupCountField(dd, field)) {
                String value = fieldMap.getString(field.getTag());
                resultMap.put(dd.getFieldName(field.getTag()), value);
            }
        }

        Iterator groupsKeys = fieldMap.groupKeyIterator();

        while (groupsKeys.hasNext()) {
            int groupCountTag = ((Integer) groupsKeys.next()).intValue();
            List groupItems = new ArrayList(fieldMap.getInt(groupCountTag));
            Group g = new Group(groupCountTag, 0);
            int i = 1;
            while (fieldMap.hasGroup(i, groupCountTag)) {
                fieldMap.getGroup(i, g);
                Map groupItemMap = new HashMap();
                parseFieldMap(dd, g, groupItemMap);
                groupItems.add(groupItemMap);
                i++;
            }
            resultMap.put(dd.getFieldName(groupCountTag), groupItems);
        }
    }

    private static boolean isGroupCountField(DataDictionary dd, Field field) {
        return dd.getFieldTypeEnum(field.getTag()) == FieldType.NumInGroup;
    }
}
