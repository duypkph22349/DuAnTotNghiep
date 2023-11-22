package datn.goodboy.service.serviceinterface;

import java.util.List;

/**
 * PanigationWithSearch
 */
public interface PanigationWithSearchStatus<T> {
    List<T> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,String txtSearch ,int status);
  int getPageNumber(int rowcount, String txtSearch, int status);
  int[] getPanigation(int rowcount, int pageno,String txtSearch,int status);
}
