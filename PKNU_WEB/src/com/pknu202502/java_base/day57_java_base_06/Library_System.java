package com.pknu202502.java_base.day57_java_base_06;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// 메인 클래스
public class Library_System {
    public static void main(String[] args) {
        System.out.println("[도서 대출 시스템]");
        System.out.println("----------------------------------------");

        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        // 데이터 로드
        service.loadData();

        boolean running = true;
        while (running) {
            System.out.println("\n1. 도서 등록");
            System.out.println("2. 도서 대출");
            System.out.println("3. 도서 반납");
            System.out.println("4. 도서 예약");
            System.out.println("5. 전체 도서 목록");
            System.out.println("6. 회원 등록");
            System.out.println("7. 전체 회원 목록");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");
            String input = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ 숫자를 입력하세요.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("도서 ID 입력: ");
                        String bookId = sc.nextLine().trim();
                        if (bookId.isEmpty()) throw new LibraryException("도서 ID는 비어 있을 수 없습니다.");
                        System.out.print("도서 이름 입력: ");
                        String bookName = sc.nextLine().trim();
                        if (bookName.isEmpty()) throw new LibraryException("도서 이름은 비어 있을 수 없습니다.");
                        service.registerBook(new Book(bookId, bookName));
                        System.out.println("현재 도서 수: " + service.getBookCount());
                    }
                    case 2 -> {
                        System.out.print("회원 ID 입력: ");
                        String customerId = sc.nextLine().trim();
                        if (customerId.isEmpty()) throw new LibraryException("회원 ID는 비어 있을 수 없습니다.");
                        System.out.print("도서 ID 입력: ");
                        String bookId = sc.nextLine().trim();
                        if (bookId.isEmpty()) throw new LibraryException("도서 ID는 비어 있을 수 없습니다.");
                        service.rentBook(customerId, bookId);
                    }
                    case 3 -> {
                        System.out.print("대출 ID 입력: ");
                        String rentalId = sc.nextLine().trim();
                        if (rentalId.isEmpty()) throw new LibraryException("대출 ID는 비어 있을 수 없습니다.");
                        service.returnBook(rentalId);
                    }
                    case 4 -> {
                        System.out.print("회원 ID 입력: ");
                        String customerId = sc.nextLine().trim();
                        if (customerId.isEmpty()) throw new LibraryException("회원 ID는 비어 있을 수 없습니다.");
                        System.out.print("도서 ID 입력: ");
                        String bookId = sc.nextLine().trim();
                        if (bookId.isEmpty()) throw new LibraryException("도서 ID는 비어 있을 수 없습니다.");
                        service.reserveBook(customerId, bookId);
                    }
                    case 5 -> service.printAllBooks();
                    case 6 -> {
                        System.out.print("이름: ");
                        String name = sc.nextLine().trim();
                        if (name.isEmpty()) throw new LibraryException("이름은 비어 있을 수 없습니다.");
                        System.out.print("이메일: ");
                        String email = sc.nextLine().trim();
                        if (email.isEmpty()) throw new LibraryException("이메일은 비어 있을 수 없습니다.");
                        System.out.print("주소: ");
                        String address = sc.nextLine().trim();
                        if (address.isEmpty()) throw new LibraryException("주소는 비어 있을 수 없습니다.");
                        System.out.print("연락처 (숫자만): ");
                        String numberInput = sc.nextLine().trim();
                        long number;
                        try {
                            number = Long.parseLong(numberInput);
                        } catch (NumberFormatException e) {
                            throw new LibraryException("유효한 연락처를 입력하세요 (숫자만).");
                        }
                        
                        System.out.println("---------------------------2");
                        service.registerCustomer(name, email, address, number);
                        System.out.println("---------------------------3");
                        System.out.println("현재 회원 수: " + service.getCustomerCount());
                    }
                    case 7 -> service.printAllCustomers();
                    case 0 -> {
                        service.saveData();
                        running = false;
                        System.out.println("시스템 종료. 데이터가 저장되었습니다.");
                    }
                    default -> System.out.println("⚠ 잘못된 메뉴입니다.");
                }
            } catch (LibraryException e) {
                System.out.println("⚠ 오류: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠ 시스템 오류: " + e.getMessage());
                e.printStackTrace();
            }
        }
        

        sc.close();
    }
}

// ================= 도메인 클래스 =================
class Custard {   
    private long serialVersionUID = 1L;
    private String customerId;
    private String name;
    private String email;
    private String address;
    private long number;

    // public Custard(String customerId, String name, String email, String address) {
    //     System.out.println("--------------------0");
    //     this.customerId = customerId;
    //     this.name = name;
    //     this.email = email;
    //     this.address = address;
    //     // this.number = number;
    //     System.out.println("--------------------0-1");
    // }
    public Custard(String customerId, String name, String email, String address, long number) {
        System.out.println("--------------------0");
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.number = number;
        System.out.println("--------------------0-1");
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
}

class Book {
    private static  long serialVersionUID = 1L;
    private  String bookId;
    private  String bookName;
    private boolean isRented;
    private boolean isReserved;

    public Book(String bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.isRented = false;
        this.isReserved = false;
    }

    public String getBookId() { return bookId; }
    public String getBookName() { return bookName; }
    public boolean isRented() { return isRented; }
    public boolean isReserved() { return isReserved; }
    public void setRented(boolean rented) { this.isRented = rented; }
    public void setReserved(boolean reserved) { this.isReserved = reserved; }
}

class Rental {
    private static  long serialVersionUID = 1L;
    private  String rentalId;
    private  Book book;
    private  Customer customer;
    private  LocalDate rentalDate;
    private  LocalDate returnDate;

    public Rental(String rentalId, Customer customer, Book book) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.book = book;
        this.rentalDate = LocalDate.now();
        this.returnDate = rentalDate.plusWeeks(2);
    }

    public String getRentalId() { return rentalId; }
    public Book getBook() { return book; }
    public Customer getCustomer() { return customer; }
    public LocalDate getReturnDate() { return returnDate; }
    public LocalDate getRentalDate() { return rentalDate; }
}

class Reservation {
    private static long serialVersionUID = 1L;
    private  Customer customer;
    private  Book book;
    private  LocalDate reservationDate;

    public Reservation(Customer customer, Book book) {
        this.customer = customer;
        this.book = book;
        this.reservationDate = LocalDate.now();
        book.setReserved(true);
    }
}

// ================= 예외 클래스 =================
class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }
}

class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String id) {
        super("도서를 찾을 수 없습니다. (ID: " + id + ")");
    }
}

class CustomerNotFoundException extends LibraryException {
    public CustomerNotFoundException(String id) {
        super("회원을 찾을 수 없습니다. (ID: " + id + ")");
    }
}

class AlreadyRentedException extends LibraryException {
    public AlreadyRentedException(String bookName) {
        super("도서 [" + bookName + "]는 이미 대출 중입니다.");
    }
}

class AlreadyReservedException extends LibraryException {
    public AlreadyReservedException(String bookName) {
        super("도서 [" + bookName + "]는 이미 예약되어 있습니다.");
    }
}

// ================= 서비스 클래스 =================
class LibraryService {
    private  Map<String, Custard> customerMap = new HashMap<>();
    private  Map<String, Book> bookMap = new HashMap<>();
    private  Map<String, Rental> rentalMap = new HashMap<>();
    private  List<Reservation> reservationList = new ArrayList<>();
    private static  String CUSTOMER_FILE = "customers.dat";
    private static  String BOOK_FILE = "books.dat";
    private static  String RENTAL_FILE = "rentals.dat";
    private static  String RESERVATION_FILE = "reservations.dat";

    public void registerCustomer(String name, String email, String address, long number) {
        try {
            String id = UUID.randomUUID().toString();
            System.out.println("---------------------------1");
            Custard customer = new Custard(id, name, email, address, number);
            System.out.println("---------------------------1-1");
            customerMap.put(id, customer);
            System.out.println("회원 등록 완료: " + name + " (ID: " + id + ")");
            System.out.println("현재 customerMap 상태: " + customerMap.size() + "명 등록됨");
        } catch (Exception e) {
            throw new LibraryException("회원 등록 중 오류: " + e.getMessage());
        }
    }

    public void registerBook(Book book) {
        if (bookMap.containsKey(book.getBookId())) {
            throw new LibraryException("이미 등록된 도서입니다. (ID: " + book.getBookId() + ")");
        }
        bookMap.put(book.getBookId(), book);
        System.out.println("도서 등록 완료: " + book.getBookName());
    }

    public void rentBook(String customerId, String bookId) {
        Custard customer = customerMap.get(customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);

        Book book = bookMap.get(bookId);
        if (book == null) throw new BookNotFoundException(bookId);
        if (book.isRented()) throw new AlreadyRentedException(book.getBookName());

        String rentalId = UUID.randomUUID().toString();
        Rental rental = new Rental(rentalId, customer, book);
        rentalMap.put(rentalId, rental);
        book.setRented(true);

        System.out.println(customer.getName() + "님이 도서 [" + book.getBookName() + "]를 대출하였습니다. (대출ID: " + rentalId + ")");
    }

    public void returnBook(String rentalId) {
        Rental rental = rentalMap.remove(rentalId);
        if (rental == null) throw new LibraryException("해당 대출 ID를 찾을 수 없습니다.");

        rental.getBook().setRented(false);
        long overdue = ChronoUnit.DAYS.between(rental.getReturnDate(), LocalDate.now());
        System.out.println("도서 반납 완료: " + rental.getBook().getBookName());

        if (overdue > 0) {
            System.out.println("⚠ 연체일: " + overdue + "일");
        }
    }

    public void reserveBook(String customerId, String bookId) {
        Custard customer = customerMap.get(customerId);
        if (customer == null) throw new CustomerNotFoundException(customerId);

        Book book = bookMap.get(bookId);
        if (book == null) throw new BookNotFoundException(bookId);
        if (book.isReserved()) throw new AlreadyReservedException(book.getBookName());

        Reservation reservation = new Reservation(customer, book);
        reservationList.add(reservation);
        System.out.println("예약 완료: " + customer.getName() + "님이 도서 [" + book.getBookName() + "]를 예약했습니다.");
    }

    public void printAllBooks() {
        System.out.println("\n[도서 목록]");
        if (bookMap.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
            return;
        }
        for (Book book : bookMap.values()) {
            System.out.printf("ID: %s | 제목: %s | 대출중: %s | 예약중: %s%n",
                    book.getBookId(),
                    book.getBookName(),
                    book.isRented() ? "예" : "아니오",
                    book.isReserved() ? "예" : "아니오");
        }
    }

    public void printAllCustomers() {
        System.out.println("\n[회원 목록]");
        if (customerMap.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        for (Custard c : customerMap.values()) {
            System.out.printf("ID: %s | 이름: %s%n", c.getCustomerId(), c.getName());
        }
    }

    public int getBookCount() {
        return bookMap.size();
    }

    public int getCustomerCount() {
        return customerMap.size();
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) {
            Map<String, Custard> loadedCustomers = (Map<String, Custard>) ois.readObject();
            customerMap.clear();
            customerMap.putAll(loadedCustomers);
            System.out.println("회원 데이터 로드 완료: " + customerMap.size() + "명");
        } catch (FileNotFoundException ignored) {
            System.out.println("회원 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ 회원 데이터 로드 중 오류: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            Map<String, Book> loadedBooks = (Map<String, Book>) ois.readObject();
            bookMap.clear();
            bookMap.putAll(loadedBooks);
            System.out.println("도서 데이터 로드 완료: " + bookMap.size() + "권");
        } catch (FileNotFoundException ignored) {
            System.out.println("도서 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ 도서 데이터 로드 중 오류: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RENTAL_FILE))) {
            Map<String, Rental> loadedRentals = (Map<String, Rental>) ois.readObject();
            rentalMap.clear();
            rentalMap.putAll(loadedRentals);
            System.out.println("대출 데이터 로드 완료: " + rentalMap.size() + "건");
        } catch (FileNotFoundException ignored) {
            System.out.println("대출 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ 대출 데이터 로드 중 오류: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RESERVATION_FILE))) {
            List<Reservation> loadedReservations = (List<Reservation>) ois.readObject();
            reservationList.clear();
            reservationList.addAll(loadedReservations);
            System.out.println("예약 데이터 로드 완료: " + reservationList.size() + "건");
        } catch (FileNotFoundException ignored) {
            System.out.println("예약 데이터 파일이 없습니다. 새로 시작합니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ 예약 데이터 로드 중 오류: " + e.getMessage());
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            oos.writeObject(customerMap);
            System.out.println("회원 데이터 저장 완료: " + customerMap.size() + "명");
        } catch (IOException e) {
            System.out.println("⚠ 회원 데이터 저장 중 오류: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(bookMap);
            System.out.println("도서 데이터 저장 완료: " + bookMap.size() + "권");
        } catch (IOException e) {
            System.out.println("⚠ 도서 데이터 저장 중 오류: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RENTAL_FILE))) {
            oos.writeObject(rentalMap);
            System.out.println("대출 데이터 저장 완료: " + rentalMap.size() + "건");
        } catch (IOException e) {
            System.out.println("⚠ 대출 데이터 저장 중 오류: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESERVATION_FILE))) {
            oos.writeObject(reservationList);
            System.out.println("예약 데이터 저장 완료: " + reservationList.size() + "건");
        } catch (IOException e) {
            System.out.println("⚠ 예약 데이터 저장 중 오류: " + e.getMessage());
        }
    }
}