package com.automation.regression.stepdef;

import com.automation.regression.context.ScenarioContext;
import com.automation.regression.rest.model.ContextBook;
import com.automation.regression.rest.model.ContextUser;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.Book;
import org.openapitools.model.CreateBookForUser404Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.CREATED;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

public class BookSteps extends TestCore {
    //private static final Logger LOG = LogManager.getLogger(UserSteps.class);

    public BookSteps(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @When("(create )a new book for user {word} and store it as {word} -> {}")
    public void createANewBookForUserAndStoreItAs(final String userId, final String bookId, final HttpStatus httpStatus) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(userId);
        ContextBook book = getBookService().initContextBook(bookId, user.getId());

        if (CREATED.isSameCodeAs(httpStatus)) {
            ResponseEntity<Book> response = getBookService().registerBook(book);
            book.setId(Objects.requireNonNull(response.getBody()).getId());

        } else if (httpStatus.is4xxClientError() || httpStatus.is5xxServerError()) {
            ResponseEntity<CreateBookForUser404Response> response = getBookService().registerBook4xxAnd5xx(book);
            scenarioContext.storeResponse(Objects.requireNonNull(response.getBody()).getError());

        } else {
            throw new InvalidParameterException("Invalid parameter exception");
        }
        scenarioContext.storeContextObject(bookId, book);
    }


    @Then("verify that book {word} does not exist")
    public void verifyThatBookDoesNotExist(final String bookId) {
        ContextBook book = (ContextBook) scenarioContext.getContextObject(bookId);

        ResponseEntity<List<Book>> response = getBookService().getBooks(book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        Book actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), book.getTitle()))
                .findFirst()
                .orElse(null);

        assertNull(actBook);
    }

    @Then("verify that book {word} exist")
    public void verifyThatBookExist(final String bookId) {
        ContextBook book = (ContextBook) scenarioContext.getContextObject(bookId);

        ResponseEntity<List<Book>> response = getBookService().getBooks(book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        Book actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), book.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook != null;
        getBookVerifyService().verifyBook(book, actBook);
    }

    @When("delete book {word} for user {word} -> {}")
    public void deleteBook(final String bookId, final String userId, final HttpStatus httpStatus) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(userId);
        ContextBook book = (ContextBook) scenarioContext.getContextObject(bookId);

        ResponseEntity<Void> response = getBookService().deleteBook(user, book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));
    }
}
