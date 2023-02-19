package jpabook.jpashop.Dto;

import jpabook.jpashop.Entity.Member;
import jpabook.jpashop.Enum.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter @Setter
public class MemberResponseDto {
    private Long id;
    private String name;
}
