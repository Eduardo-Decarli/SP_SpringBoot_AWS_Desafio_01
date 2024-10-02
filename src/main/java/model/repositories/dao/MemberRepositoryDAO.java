package model.repositories.dao;

import model.entities.Member;

public interface MemberRepositoryDAO {
    public void insertMember(Member member);
    public Member selectMemberByEmail(String email);
}
