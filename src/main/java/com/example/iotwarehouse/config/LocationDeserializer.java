package com.example.iotwarehouse.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * 将坐标字符串转换为MySQL POINT类型所需的字节数组
 * @author hwshou
 * @date 2025/4/23  22:48
 */
public class LocationDeserializer extends JsonDeserializer<byte[]> {
    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String locationStr = p.getText();
        if (locationStr == null || locationStr.isEmpty()) {
            return null;
        }

        try {
            // 解析经纬度
            String[] parts = locationStr.split("\\s+");
            if (parts.length != 2) {
                throw new IOException(
                        "Invalid location format. Expected 'latitude longitude', got: " + locationStr);
            }

            double latitude = Double.parseDouble(parts[0]);
            double longitude = Double.parseDouble(parts[1]);

            // 创建MySQL POINT类型的WKB (Well-Known Binary)格式
            // MySQL使用SRID + WKB格式，前4字节为SRID (0)，后面是WKB
            byte[] wkb = new byte[25]; // 4字节SRID + 1字节byte order + 4字节type + 8字节X + 8字节Y

            // SRID (0, little-endian)
            wkb[0] = 0;
            wkb[1] = 0;
            wkb[2] = 0;
            wkb[3] = 0;

            // Byte order (1 = little-endian)
            wkb[4] = 1;

            // Type (1 = POINT, little-endian)
            wkb[5] = 1;
            wkb[6] = 0;
            wkb[7] = 0;
            wkb[8] = 0;

            // X coordinate (longitude, IEEE 754 double-precision)
            long longBits = Double.doubleToLongBits(longitude);
            wkb[9] = (byte) (longBits & 0xFF);
            wkb[10] = (byte) ((longBits >> 8) & 0xFF);
            wkb[11] = (byte) ((longBits >> 16) & 0xFF);
            wkb[12] = (byte) ((longBits >> 24) & 0xFF);
            wkb[13] = (byte) ((longBits >> 32) & 0xFF);
            wkb[14] = (byte) ((longBits >> 40) & 0xFF);
            wkb[15] = (byte) ((longBits >> 48) & 0xFF);
            wkb[16] = (byte) ((longBits >> 56) & 0xFF);

            // Y coordinate (latitude, IEEE 754 double-precision)
            longBits = Double.doubleToLongBits(latitude);
            wkb[17] = (byte) (longBits & 0xFF);
            wkb[18] = (byte) ((longBits >> 8) & 0xFF);
            wkb[19] = (byte) ((longBits >> 16) & 0xFF);
            wkb[20] = (byte) ((longBits >> 24) & 0xFF);
            wkb[21] = (byte) ((longBits >> 32) & 0xFF);
            wkb[22] = (byte) ((longBits >> 40) & 0xFF);
            wkb[23] = (byte) ((longBits >> 48) & 0xFF);
            wkb[24] = (byte) ((longBits >> 56) & 0xFF);

            return wkb;
        } catch (NumberFormatException e) {
            throw new IOException("Invalid location format. Could not parse latitude or longitude: " + locationStr,
                    e);
        }
    }
}
