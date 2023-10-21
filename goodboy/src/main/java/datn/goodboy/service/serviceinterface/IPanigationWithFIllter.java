package datn.goodboy.service.serviceinterface;

import java.util.List;

public interface IPanigationWithFIllter<T,C> {
  List<T> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir,C filter);
  int getPageNumber(int rowcount, C filter);
  int[] getPanigation(int rowcount, int pageno,C filter);
}
