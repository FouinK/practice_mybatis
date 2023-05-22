package mybatis.mybatisPractice.mapper;

import mybatis.mybatisPractice.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MemberMapper {

    void saveMember(Member member);

    List<Member> findAll();

    Member findById(Long memberId);

    void updateMember(Member member);

    void deleteById(Long memberId);
}
