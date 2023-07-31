package com.axceldev.central_library.services;

import com.axceldev.central_library.exceptions.BusinessException;
import com.axceldev.central_library.mapper.IMapper;
import com.axceldev.central_library.mapper.impl.LoanRqToLoan;
import com.axceldev.central_library.models.dto.rq.LoanRq;
import com.axceldev.central_library.models.dto.rs.BookRs;
import com.axceldev.central_library.models.dto.rs.CustomerRs;
import com.axceldev.central_library.models.dto.rs.LoanBookRs;
import com.axceldev.central_library.models.dto.rs.LoanRs;
import com.axceldev.central_library.models.model.Book;
import com.axceldev.central_library.models.model.Customer;
import com.axceldev.central_library.models.model.Loan;
import com.axceldev.central_library.repositories.BookRepository;
import com.axceldev.central_library.repositories.CustomerRepository;
import com.axceldev.central_library.repositories.LoanRepository;
import com.axceldev.central_library.repositories.StockRepository;
import com.axceldev.central_library.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    private final BookRepository bookRepository;

    private final StockRepository stockRepository;

    private final StockService stockService;

    public Mono<LoanRs> createNewLoanOfBook(LoanRq loanRq) {
        Mono<Customer> customerMono = customerRepository.findById(loanRq.getCustomerId());
        Mono<Book> bookMono = bookRepository.findById(loanRq.getBookId());

        return Mono.zip(customerMono, bookMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Book book = tuple.getT2();

                    Loan loan = new LoanRqToLoan().map(loanRq);

                    return stockService.findStockOfBook(book.getBookId())
                            .flatMap(stock -> {
                                if (stock.getQuantity() > 0) {
                                    return loanRepository.save(loan)
                                            .map(savedLoan -> {
                                                LoanRs loanRs = new LoanRs();
                                                loanRs.setLoanId(savedLoan.getLoanId());
                                                loanRs.setBorrowDate(savedLoan.getBorrowDate());
                                                loanRs.setReturnDate(savedLoan.getReturnDate());

                                                CustomerRs customerRs = new CustomerRs(customer.getFirstName(), customer.getDocumentNumber(), customer.getLastName(), customer.getEmail(), customer.getPhone());
                                                loanRs.setCustomer(customerRs);

                                                BookRs bookRs = new BookRs(book.getAuthor(), book.getTitle(), book.yearPublished, book.getPublisher(), book.getCost());
                                                loanRs.setBook(bookRs);

                                                return loanRs;
                                            })
                                            .doOnSuccess(ignored -> stockService.updateQuantityOfBookByBookId(book.getBookId()));
                                } else {
                                    return Mono.error(new BusinessException(HttpStatus.BAD_REQUEST, Boolean.FALSE, Message.BOOK_WITHOUT_STOCK));
                                }
                            });
                });
    }

    public Mono<LoanBookRs> findAllLoanByCustomerId(Integer customerId) {
        return Mono.just(new LoanBookRs());
    }
}
