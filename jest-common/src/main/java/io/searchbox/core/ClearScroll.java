package io.searchbox.core;

import org.apache.commons.lang3.StringUtils;

import io.searchbox.action.AbstractMultiIndexActionBuilder;
import io.searchbox.action.GenericResultAbstractAction;
import io.searchbox.params.Parameters;

import static io.searchbox.core.SearchScroll.MAX_SCROLL_ID_LENGTH;

public class ClearScroll extends GenericResultAbstractAction {

  public ClearScroll(Builder builder) {
    this.payload = builder.getScrollId();
    setURI(buildURI());
  }

  @Override
  public String getRestMethodName() {
    return "DELETE";
  }

  @Override
  protected String buildURI() {
    return super.buildURI() + "/_search/scroll";
  }

  public static class Builder extends AbstractMultiIndexActionBuilder<ClearScroll, Builder> {

    private final String scrollId;

    public Builder(String scrollId) {
      this.scrollId = scrollId;
      if (scrollId.length() <= MAX_SCROLL_ID_LENGTH) {
        setParameter(Parameters.SCROLL_ID, scrollId);
      }
    }

    @Override
    public String getJoinedIndices() {
      if (indexNames.size() > 0) {
        return StringUtils.join(indexNames, ",");
      } else {
        return null;
      }
    }

    @Override
    public ClearScroll build() {
      return new ClearScroll(this);
    }

    public String getScrollId() {
      return scrollId;
    }

  }
}
