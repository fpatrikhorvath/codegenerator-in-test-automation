package com.automation.regression.stepdef;

import com.automation.regression.context.ScenarioContext;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.BookDTO;
import org.openapitools.model.GenericErrorResponse;
import org.openapitools.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.CREATED;
import static org.testng.AssertJUnit.*;

public class BookSteps extends TestCore {
    public BookSteps(final UserLayerContextStore userLayerContextStore,
                     final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @When("(create )a new book for user {word} and store it as {word} -> {}")
    public void createANewBookForUserAndStoreItAs
            (final String userId, final String bookId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = getBookService().initContextBook(user.getId());

        if (CREATED.isSameCodeAs(httpStatus)) {
            ResponseEntity<BookDTO> response = getBookService().registerBook(book);
            assertTrue(response.getStatusCode().isSameCodeAs(httpStatus));
            book.setId(Objects.requireNonNull(response.getBody()).getId());

        } else if (httpStatus.isError()) {
            ResponseEntity<GenericErrorResponse> response = getBookService().registerBookNegative(book);
            assertTrue(response.getStatusCode().isSameCodeAs(httpStatus));
            scenarioContext.storeResponse(Objects.requireNonNull(response.getBody()).getError());

        } else {
            throw new InvalidParameterException("Invalid parameter exception");
        }
        scenarioContext.storeContextObject(bookId, book);
    }


    @Then("verify that book {word} does not exist")
    public void verifyThatBookDoesNotExist(final String bookId) {
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);
        ResponseEntity<List<BookDTO>> response = getBookService().getBooks(book);
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        BookDTO actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), book.getTitle()))
                .findFirst()
                .orElse(null);

        assertNull(actBook);
    }

    @Then("verify that book {word} exist")
    public void verifyThatBookExist(final String bookId) {
        BookDTO expBook = (BookDTO) scenarioContext.getContextObject(bookId);
        ResponseEntity<List<BookDTO>> response = getBookService().getBooks(expBook);
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        BookDTO actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), expBook.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook != null;
        assertEquals(actBook, expBook);
    }

    @When("delete book {word} for user {word} -> {}")
    public void deleteBook(final String bookId, final String userId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);
        ResponseEntity<Void> response = getBookService().deleteBook(user, book);
        assertTrue(response.getStatusCode().isSameCodeAs(httpStatus));
    }
}
