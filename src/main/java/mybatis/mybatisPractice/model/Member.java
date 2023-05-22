package mybatis.mybatisPractice.model;

import lombok.*;


@Builder
@Data
public class Member {
    private Long memberId;
    private String username;
    private String nickname;
}
