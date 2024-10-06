package model.services;

import exceptions.ServicesException;
import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;
import model.repositories.MemberRepository;
import model.repositories.ReportRepository;
import model.services.dao.LoanDAO;
import model.services.dao.MemberDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MemberServices implements MemberRepository, ReportRepository<Member> {

    MemberDAO memberDao;

    public MemberServices(MemberDAO memberDao){
        this.memberDao = memberDao;
    }

    @Override
    public void registerNewMember(Member member) {
        if(member.getEmail().equals(memberDao.selectMemberByEmail(member.getEmail()))){
            throw new ServicesException("You does not create a member with this email, there is a member with this email");
        }
        memberDao.insertMember(member);
    }

    @Override
    public Member findMemberByEmail(String email) {
        if(email == null){
            throw new ServicesException("Write a valid email (Example@gmail.com)");
        }
        Member member = memberDao.selectMemberByEmail(email);

        if(member == null){
            throw new ServicesException("No member with this email");
        }

        return member;
    }

    @Override
    public List<Member> findAllMembers() {
        List<Member> members = memberDao.selectAllMembers();
        if(members.isEmpty()){
            throw new ServicesException("There is not nothing member registered");
        }
        return members;
    }

    @Override
    public String generateReport() {
        LoanDAO loanDao = new LoanDAO();
        List<Loan> listAllLoans = loanDao.selectAllLoan();
        HashSet<Member> listLoanByMember = new HashSet<>();
        for(Loan correntLoan : listAllLoans){
            if(correntLoan.getStateLoan().equals(StatusLoan.LATE)){
                listLoanByMember.add(correntLoan.getMember());
            }
        }
        if(listLoanByMember.isEmpty()){
            return "No members have pending loans.";
        }

        String report = "=== Members with pending loans ===\n";

        for(Member correntMember : listLoanByMember){
            report += "Member: " + correntMember.getName() + " has a pending loan." +
                    " His email is " + correntMember.getEmail() + "\n";
        }

        return report;
    }
}
