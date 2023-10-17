package datn.goodboy.service.serviceinterface;

import java.util.List;

/**
 * PanigationWithSearch
 */
public interface PanigationWithSearch<T> {
    List<T> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,String txtSearch);
  int getPageNumber(int rowcount, String txtSearch);
  int[] getPanigation(int rowcount, int pageno,String txtSearch);
}