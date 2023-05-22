package mybatis.mybatisPractice.mapper;

import mybatis.mybatisPractice.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MemberMapper {

    void insertMember(Member member);
}
