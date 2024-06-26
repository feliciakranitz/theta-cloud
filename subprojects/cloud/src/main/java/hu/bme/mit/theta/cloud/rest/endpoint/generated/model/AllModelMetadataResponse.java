package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 * AllModelMetadataResponse
 */



public class AllModelMetadataResponse   {
  @JsonProperty("modelList")
  
  private List<GetModelMetadataResponse> modelList = null;

  public AllModelMetadataResponse modelList(List<GetModelMetadataResponse> modelList) {
    this.modelList = modelList;
    return this;
  }

  public AllModelMetadataResponse addModelListItem(GetModelMetadataResponse modelListItem) {
    if (this.modelList == null) {
      this.modelList = new ArrayList<>();
    }
    this.modelList.add(modelListItem);
    return this;
  }

  /**
   * Get modelList
   * @return modelList
   **/
  @Schema(description = "")
    public List<GetModelMetadataResponse> getModelList() {
    return modelList;
  }

  public void setModelList(List<GetModelMetadataResponse> modelList) {
    this.modelList = modelList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AllModelMetadataResponse allModelMetadataResponse = (AllModelMetadataResponse) o;
    return Objects.equals(this.modelList, allModelMetadataResponse.modelList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllModelMetadataResponse {\n");
    
    sb.append("    modelList: ").append(toIndentedString(modelList)).append("\n");
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
