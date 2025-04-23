package com.example.iotwarehouse.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Base64;

/**
 * 将MySQL POINT类型的字节数组转换为坐标字符串
 *
 * @author hwshou
 * @date 2025/4/23  22:49
 */
public class LocationSerializer extends JsonSerializer<byte[]> {
    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null || value.length < 25) {
            gen.writeNull();
            return;
        }

        try {
            // 解析经度值（X）
            long longitudeBits = 0;
            for (int i = 0; i < 8; i++) {
                longitudeBits |= ((long) (value[9 + i] & 0xFF)) << (i * 8);
            }
            double longitude = Double.longBitsToDouble(longitudeBits);

            // 解析纬度值（Y）
            long latitudeBits = 0;
            for (int i = 0; i < 8; i++) {
                latitudeBits |= ((long) (value[17 + i] & 0xFF)) << (i * 8);
            }
            double latitude = Double.longBitsToDouble(latitudeBits);

            // 输出为"纬度 经度"格式
            gen.writeString(latitude + " " + longitude);
        } catch (Exception e) {
            // 如果解析失败，则输出Base64编码
            gen.writeString(Base64.getEncoder().encodeToString(value));
        }
    }
}
