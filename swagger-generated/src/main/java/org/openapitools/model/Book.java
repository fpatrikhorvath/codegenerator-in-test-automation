package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Book
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.5.0")
public class Book {

  private Long id = null;

  private Long userId = null;

  private String title;

  private String author;

  public Book() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Book(Long id, Long userId, String title, String author) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.author = author;
  }

  public Book id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Book userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull
  @JsonProperty("userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Book title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Book author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @NotNull
  @JsonProperty("author")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(this.id, book.id) &&
        Objects.equals(this.userId, book.userId) &&
        Objects.equals(this.title, book.title) &&
        Objects.equals(this.author, book.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, title, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Book {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

