package com.example.puyuan.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "請求狀態")
public class StatusResponse {
    @Schema(description = "訊息代碼, 0=成功, 1=失敗")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code; //[測試用]驗證碼

    /**
     * @return 成功狀態
     */
    public static StatusResponse SUCCESS() {
        return StatusResponse.builder().status(RC.SUCCESS.getCode()).build();
    }

    /**
     * @return 失敗狀態
     */
    public static StatusResponse FAILED() {
        return StatusResponse.builder().status(RC.FAILED.getCode()).build();
    }

    @Getter
    @AllArgsConstructor
    public enum RC {
        /** 操作成功 */
        SUCCESS("0"),
        /** 操作失敗 */
        FAILED("1");

        /** 定義狀態碼 **/
        private final String code;
    }
}
