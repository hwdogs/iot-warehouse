package com.example.iotwarehouse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;

/**
 * <p>
 * 
 * </p>
 *
 * @author hwshou
 * @since 2025-04-21
 */
@Data
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @TableId(value = "warehouse_id", type = IdType.ASSIGN_ID)
    @Schema(description = "雪花算法仓库ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer warehouseId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称", example = "华北中心仓", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 地理坐标，格式: "纬度 经度"，如: "31.2304 121.4737"
     */
    @JsonSerialize(using = LocationSerializer.class)
    @JsonDeserialize(using = LocationDeserializer.class)
    @Schema(description = "地理位置坐标（'纬度 经度'格式）", example = "39.9042 116.4074", requiredMode = Schema.RequiredMode.REQUIRED)
    private byte[] location;

    /**
     * 面积（平方米）
     */
    @Schema(description = "仓库面积",example = "250",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal area;

    @Schema(description = "仓库环境区（'常温区','冷链区','危险品区'）",example = "危险区",requiredMode = Schema.RequiredMode.REQUIRED)
    private String environmentZone;

    /**
     * 0：存在，1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除",hidden = true,requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer deleted = 0;

    /**
     * 将坐标字符串转换为MySQL POINT类型所需的字节数组
     */
    public static class LocationDeserializer extends JsonDeserializer<byte[]> {
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

    /**
     * 将MySQL POINT类型的字节数组转换为坐标字符串
     */
    public static class LocationSerializer extends JsonSerializer<byte[]> {
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
}
