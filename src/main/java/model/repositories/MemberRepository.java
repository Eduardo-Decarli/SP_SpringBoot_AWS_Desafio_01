package model.repositories;

import model.entities.Member;

public interface MemberRepository {
    public void registerNewMember(Member member);
    public Member findMemberByEmail(String email);
}
