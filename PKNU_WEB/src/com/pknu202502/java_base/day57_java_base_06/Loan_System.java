package com.pknu202502.java_base.day57_java_base_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Loan_System {
    public static void main(String[] args) {
        System.out.println("[Finance Loan System]");
        System.out.println("--------------------------------------------");

        Scanner sc = new Scanner(System.in);

        LoanService loanService = new LoanService();
        CreditService creditService = new CreditService();

        System.out.println("[Finance Loan System]");
        System.out.println("--------------------------------------------");

        boolean running = true;
        while (running) {
            System.out.println("\n1. 대출 신청");
            System.out.println("2. 대출 자동 승인");
            System.out.println("3. 대출 실행");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    sc.nextLine(); // 버퍼 클리어
                    System.out.print("고객 이름 입력: ");
                    String name = sc.nextLine();

                    Customer customer = new Customer();
                    customer.setName(name);

                    creditService.getCreditInfo(customer);

                    System.out.print("대출 금액 입력: ");
                    double amount = sc.nextDouble();

                    Loan loan = loanService.applyForLoan(customer, amount);
                    System.out.println("대출 ID: " + loan.getLoanId() + ", 승인 여부: " + loan.isApproved());
                }
                case 2 -> {
                    System.out.print("자동 승인 테스트용 대출 생성 중...\n");
                    Customer c = new Customer();
                    c.setName("홍길동");
                    creditService.getCreditInfo(c);
                    Loan l = loanService.applyForLoan(c, 500000);

                    try {
                        loanService.autoApproveLoan(l);
                    } catch (CreditScoreTooLowException e) {
                        System.out.println("자동 승인 실패: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("고객 이름으로 대출 실행 시도\n");
                    Customer c = new Customer();
                    c.setName("홍길동");
                    creditService.getCreditInfo(c);
                    Loan l = loanService.applyForLoan(c, 500000);

                    try {
                        loanService.autoApproveLoan(l);
                        loanService.disburseLoan(l);
                    } catch (Exception e) {
                        System.out.println("실행 오류: " + e.getMessage());
                    }
                }
                case 0 -> {
                    running = false;
                    System.out.println("시스템을 종료합니다.");
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }

        sc.close();
    }
}


// 도메인 클래스
// Loan: 대출 객체 (대출 금액, 금리, 상환 상태 등)
class Loan {
    private String loanId;
    private Customer customer;
    private double amount;
    private double interestRate;
    private boolean approved;
    private boolean disbursed;


    public String getLoanId() {
        return this.loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isApproved() {
        return this.approved;
    }

    public boolean getApproved() {
        return this.approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isDisbursed() {
        return this.disbursed;
    }

    public boolean getDisbursed() {
        return this.disbursed;
    }

    public void setDisbursed(boolean disbursed) {
        this.disbursed = disbursed;
    }
    
}

// Customer: 고객 정보
class Customer {
    private String customerId;
    private String name;
    private CreditInfo creditInfo;


    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditInfo getCreditInfo() {
        return this.creditInfo;
    }

    public void setCreditInfo(CreditInfo creditInfo) {
        this.creditInfo = creditInfo;
    }

}

// Repayment: 상환 내역
class Repayment {
    private String repaymentId;
    private Loan loan;
    private double amountPaid;
    private String paymentDate;


    public String getRepaymentId() {
        return this.repaymentId;
    }

    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId;
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

}

// CreditInfo: 신용 정보
class CreditInfo {
    private int creditScore;
    private String grade;


    public int getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}


// 2. 서비스 클래스 (비즈니스 로직)
// LoanService
// LoanService.java
class LoanService {

    public Loan applyForLoan(Customer customer, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("대출 금액은 0보다 커야 합니다.");
        }

        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setAmount(amount);
        loan.setApproved(false);
        loan.setDisbursed(false);
        System.out.println("대출 신청 완료.");
        return loan;
    }

    public void disburseLoan(Loan loan) throws LoanNotApprovedException {
        if (loan == null) {
            throw new LoanNotFoundException("대출 정보가 없습니다.");
        }

        if (!loan.isApproved()) {
            throw new LoanNotApprovedException("승인되지 않은 대출은 실행할 수 없습니다.");
        }

        loan.setDisbursed(true);
        System.out.println("대출 실행 완료.");
    }

    public void autoApproveLoan(Loan loan) throws CreditScoreTooLowException {
        if (loan.getCustomer().getCreditInfo() == null) {
            throw new IllegalArgumentException("신용 정보가 없습니다.");
        }

        int score = loan.getCustomer().getCreditInfo().getCreditScore();
        if (score < 700) {
            throw new CreditScoreTooLowException("신용 점수가 낮아 자동 승인이 거부되었습니다.");
        }

        loan.setApproved(true);
        System.out.println("자동 승인 완료.");
    }
}


//////////////////////////////////////
// CreditService
class CreditService {

    // 신용 정보 조회 (getCreditInfo)
    public CreditInfo getCreditInfo(Customer customer) {
        // 신용 정보 조회 mock
        CreditInfo info = new CreditInfo();
        info.setCreditScore(720);
        info.setGrade("A");
        customer.setCreditInfo(info);
        System.out.println("신용 정보 조회 완료.");
        return info;
    }
}


// 상환 내역 관리 (recordRepayment, getRepaymentHistory)
class RepaymentService {
    private List<Repayment> repaymentHistory = new ArrayList<>();

    public void recordRepayment(Loan loan, double amount, String date) {
        Repayment repayment = new Repayment();
        repayment.setLoan(loan);
        repayment.setAmountPaid(amount);
        repayment.setPaymentDate(date);
        repaymentHistory.add(repayment);
        System.out.println("상환 기록 추가 완료.");
    }

    public List<Repayment> getRepaymentHistory(Loan loan) {
        // 간단히 모든 상환 기록 리턴
        return repaymentHistory;
    }
}
    
// 연체 관리 (checkOverdue(), applyPenalty())
class OverdueService {
    public void checkOverdue(Loan loan) {
        // 연체 여부 체크 mock
        System.out.println("대출 연체 없음.");
    }

    public void applyPenalty(Loan loan) {
        System.out.println("연체 벌금 부과 완료.");
    }
}

    
// 정보 공유 (shareLoanInfo())
// LoanSharingService.java
class LoanSharingService {
    public void shareLoanInfo(Loan loan) {
        System.out.println("대출 정보 공유: 고객명 - " + loan.getCustomer().getName());
    }
}

// LoanNotApprovedException.java
class LoanNotApprovedException extends Exception {
    public LoanNotApprovedException(String message) {
        super(message);
    }
}

// CreditScoreTooLowException.java
class CreditScoreTooLowException extends Exception {
    public CreditScoreTooLowException(String message) {
        super(message);
    }
}

// LoanNotFoundException.java
class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}



////////////////////////////////////////////////////////////////////
// 3. 저장소 클래스 (Repository or DAO)
// LoanRepository
class LoanRepository {
    private Map<String, Loan> loanMap = new HashMap<>();

    public void save(Loan loan) {
        loanMap.put(loan.getLoanId(), loan);
    }

    public Loan findById(String loanId) {
        return loanMap.get(loanId);
    }

    public List<Loan> findAll() {
        return new ArrayList<>(loanMap.values());
    }

    public void delete(String loanId) {
        loanMap.remove(loanId);
    }
}

