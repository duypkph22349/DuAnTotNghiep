package datn.goodboy.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FilterRequest {
  @JsonProperty("pageno")
  private int pageno;

  @JsonProperty("rowcount")
  private int rowcount;

  @JsonProperty("sortBy")
  private String sortBy;

  @JsonProperty("sortDir")
  private boolean sortDir;

  @JsonProperty("filter")
  private ProductFilter filter;

  @JsonCreator
  public FilterRequest(
      @JsonProperty("pageno") int pageno,
      @JsonProperty("rowcount") int rowcount,
      @JsonProperty("sortBy") String sortBy,
      @JsonProperty("sortDir") boolean sortDir,
      @JsonProperty("filter") ProductFilter filter) {
    this.pageno = pageno;
    this.rowcount = rowcount;
    this.sortBy = sortBy;
    this.sortDir = sortDir;
    this.filter = filter;
  }
}
