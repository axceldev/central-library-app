package com.axceldev.central_library.services;

import com.axceldev.central_library.exceptions.BusinessException;
import com.axceldev.central_library.mapper.IMapper;
import com.axceldev.central_library.mapper.impl.LoanRqToLoan;
import com.axceldev.central_library.models.dto.rq.LoanRq;
import com.axceldev.central_library.models.dto.rs.*;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        return loanRepository.findAllByCustomerId(customerId)
                .collectList()
                .flatMap(loans -> {
                    if (loans.isEmpty()) {
                        return Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.CUSTOMER_WITHOUT_BORROW));
                    }

                    Mono<Customer> customerMono = customerRepository.findById(customerId);

                    return Mono.zip(Mono.just(loans), customerMono)
                            .flatMap(tuple -> {
                                List<Loan> loanList = tuple.getT1();
                                Customer customer = tuple.getT2();

                                LoanBookRs loanBookRs = new LoanBookRs();
                                CustomerRs customerRs = new CustomerRs(customer.getFirstName(), customer.getDocumentNumber(), customer.getLastName(), customer.getEmail(), customer.getPhone());
                                loanBookRs.setCustomer(customerRs);

                                List<DetailLoanRs> detailLoanList = loanList.stream().map(loan -> {
                                    DetailLoanRs detailLoanRs = new DetailLoanRs();
                                    detailLoanRs.setLoanId(loan.getLoanId());
                                    detailLoanRs.setBorrowDate(loan.getBorrowDate());
                                    detailLoanRs.setReturnDate(loan.getReturnDate());

                                    return detailLoanRs;
                                }).collect(Collectors.toList());

                                loanBookRs.setBooks(detailLoanList);

                                // Obtener detalles de cada libro asociado a los préstamos
                                List<Mono<Book>> bookMonos = loanList.stream()
                                        .map(loan -> bookRepository.findById(loan.getBookId()))
                                        .collect(Collectors.toList());

                                // Esperar a que todos los libros se carguen usando Flux.concat
                                return Flux.concat(bookMonos)
                                        .collectList()
                                        .map(books -> {
                                            // Asignar los detalles de los libros a los DetailLoanRs correspondientes
                                            for (int i = 0; i < books.size(); i++) {
                                                Book book = books.get(i);
                                                DetailLoanRs detailLoanRs = detailLoanList.get(i);
                                                detailLoanRs.setBook(new BookRs(book.getAuthor(), book.getTitle(), book.getYearPublished(), book.getPublisher(), book.getCost()));
                                            }

                                            // Puedes realizar más operaciones con LoanBookRs y la lista de préstamos
                                            return loanBookRs;
                                        });
                            });
                });
    }
}
