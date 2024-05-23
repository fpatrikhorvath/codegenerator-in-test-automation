package com.automation.regression.stepdef;

import com.automation.regression.TestCore;
import com.automation.regression.context.ScenarioContext;
import com.automation.regression.rest.model.ContextBook;
import com.automation.regression.rest.model.ContextUser;
import com.automation.regression.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

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

        ResponseEntity<Book> response = getBookService().registerBook(book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));

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


    }

    @When("delete book {word} for user {word} -> {}")
    public void deleteBook(final String bookId, final String userId, final HttpStatus httpStatus) {
        ContextUser user = (ContextUser) scenarioContext.getContextObject(userId);
        ContextBook book = (ContextBook) scenarioContext.getContextObject(bookId);

        ResponseEntity<Void> response = getBookService().deleteBook(user, book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));

    }

}
