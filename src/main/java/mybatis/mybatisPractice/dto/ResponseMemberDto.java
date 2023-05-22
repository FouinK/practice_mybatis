package mybatis.mybatisPractice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMemberDto {
    private Long memberId;
    private String username;
    private String nickname;
}
