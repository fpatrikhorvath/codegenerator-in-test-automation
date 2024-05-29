package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateBookForUserRequestDTO
 */
import org.openapitools.model.GenericErrorResponse;


@JsonTypeName("createBookForUser_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.5.0")
public class CreateBookForUserRequestDTO {

  protected String title;

  protected String author;

  /**
   * Constructor 
   */
  public CreateBookForUserRequestDTO() {

  }

  public CreateBookForUserRequestDTO title(String title) {
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

  public CreateBookForUserRequestDTO author(String author) {
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
    CreateBookForUserRequestDTO createBookForUserRequest = (CreateBookForUserRequestDTO) o;
    return Objects.equals(this.title, createBookForUserRequest.title) &&
        Objects.equals(this.author, createBookForUserRequest.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateBookForUserRequestDTO {\n");
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

