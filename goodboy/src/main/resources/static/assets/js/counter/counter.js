// 90 %
// globle Variable
const defaultImage =
  "https://res.cloudinary.com/da30qcqmf/image/upload/v1699778968/No-Image-Placeholder.svg_jlyb5v.png";
const token = "234a71c7-7b2c-11ee-af43-6ead57e9219a";
const shop_id = 4676018;
const districtform = 3440; // quận nam từ liêm
const districtto = 3308; // huyện trực ninh
const WardCodeninhcuong = "800083";
const listtab = [];
const viewOrderTab = document.getElementById("taborders");
const formOrder = document.getElementById("formorders");
const OrderDetailJson = [];
// element gennerate
function buttonOrderTab(id) {
  const buttontmp = document.createElement("button");
  buttontmp.classList.add("btn");
  buttontmp.classList.add("btn-info");
  buttontmp.classList.add("tabbutton");
  buttontmp.id = `vieworder${id}`;
  buttontmp.setAttribute("onclick", `selectOrder(event,${id})`);
  const innerbtn = `
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-bookmark-check"
      viewBox="0 0 16 16"
    >
      <path
        fill-rule="evenodd"
        d="M10.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"
      ></path>
      <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"></path>
    </svg>
    <span>Hóa Đơn ${id}</span>
    `;
  buttontmp.innerHTML = innerbtn;
  return buttontmp;
}
function formInOrder(id) {
  viewOrderTab.appendChild(buttonOrderTab(id));
  const form = document.createElement("form");
  form.id = `hoaDon${id}`;
  const currentTime = new Date();
  form.setAttribute("timecreate", currentTime);
  const newTime = new Date(currentTime.getTime() + 30 * 60000); // 30 minutes in milliseconds
  form.setAttribute("endtime", newTime);
  form.setAttribute("timecreate", new Date());
  form.onchange = (e) => {
    updateHoaDon(id);
  };
  form.classList.add("taborder");
  form.classList.add("position-relative");
  form.setAttribute("idofform", id);
  form.setAttribute("onsubmit", `handleOrderSubmit(event)`);
  form.innerHTML = `<div class="form-container">
  <div class="boxes-model" style="height: calc(100vh - 162px);">
      <div class="box-row-order">
          <div class="col-100">
              <div class="box-row-order">
                  <div class="col-65">
                      <div class="box-row-order">
                          <div class="col-100">
                              <section>
                                  <div class="box-order-product">
                                      <div class="product-cart">
                                          <table class="table table-hover" id="cartTable">
                                              <thead>
                                                  <tr>
                                                      <th>Sản phẩm</th>
                                                      <th>Giá</th>
                                                      <th>Số lượng</th>
                                                      <th>Action</th>
                                                  </tr>
                                              </thead>
                                              <!-- Sản Phẩm trong Hoa Đơn -->
                                              <tbody></tbody>
                                          </table>
                                          <br><br><br>
                                          <!-- Search -->
                                          <div class="input-group mb-3">
    <input aria-label="Text input with segmented dropdown button"
        class="form-control" id="searchInput" onkeyup="filterTable(${id})"
        placeholder="Tìm kiếm theo mã sản phẩm, tên sản phẩm, giá bán...."
        type="text">
</div>
<style>
        .scrollable-table {
            max-height: 700px; /* Đặt chiều cao tối đa của bảng */
            overflow-y: auto;  /* Tạo thanh cuộn dọc khi vượt quá chiều cao */
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
         thead {
            background-color: #f2f2f2;
            position: sticky;
            top: 0;
        }
    </style>
                                          <!-- Sản phẩm-->
                                          <div class="scrollable-table">
                                          <table class="table table-hover" id="cartTableProduct">
                                              <thead>
                                                  <tr>
                                                      <th>#</th>
                                                      <th></th>
                                                      <th>Tên sản phẩm</th>
                                                      <th>Giá</th>
                                                      <th>Số lượng</th>
                                                      <th>Hoa văn</th>
                                                      <th>Kích thước</th>
                                                  </tr>
                                              </thead>
                                              <tbody></tbody>
                                          </table>
                                           </div>
                                      </div>
                                      <div style="width: 100%; margin-top: 12px; "></div>
                                  </div>
                              </section>
                              <div class="resize"></div>
                          </div>
                      </div>
                  </div>
                  <div class="col-35">
                      <section class="box-status">
                          <div id="formbox-status">
                              <div class="box-order-title flex-space-between">
                                  <div class="box-meta">
                                      <div class="box-icon">
                                          <span role="img" aria-label="star" class="anticon anticon-star"
                                              style="font-size: 18px;">
                                              <svg viewBox="64 64 896 896" focusable="false" data-icon="star"
                                                  width="1em" height="1em" fill="currentColor" aria-hidden="true">
                                                  <path
                                                      d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 00.6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0046.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3zM664.8 561.6l36.1 210.3L512 672.7 323.1 772l36.1-210.3-152.8-149L417.6 382 512 190.7 606.4 382l211.2 30.7-152.8 148.9z">
                                                  </path>
                                              </svg>
                                          </span>
                                      </div>
                                      <div>Thông tin nhân viên</div>
                                  </div>
                                  <div class="flex-space-between"></div>
                              </div>
                          </div>
                          <div style="padding: 4px 16px 16px;">
                              <div id="processor-body">
                                  <div class="processor" id="processor-assign-seller">
                                      <div id="title-assign-seller">NV xử lý:&nbsp;</div>
                                      <div style="display: flex;">
                                          <div class="processor-seller-at-shop">
                                              <div class="div-user">
                                              <!-- Add Nhân Viên-->
                                                  <select name="employee" class="form-select form-select-sm"
                                                      aria-label="Small select example" size="1" required>
                                                      <option disabled selected value=null>Chọn Nhân Viên</option>
                                                  </select>
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <br>
                          </div>
                          <style>
                              select {
                                  overflow-y: auto;
                                  max-height: calc(3 * 1.5em);
                              }

                              .processor {
                                  margin-top: 8px;
                                  height: 22px
                              }

                              .processor .processor-seller-at-shop,
                              .processor .processor-seller .div-user,
                              .processor .processor-seller-at-shop .div-user,
                              .processor .processor-care .div-user {
                                  display: flex;
                                  align-items: center
                              }
                          </style>
                      </section>
                      <section>
                          <div class="box-order-title flex-space-between">
                              <div>
                                  <div class="box-meta">
                                      <div class="box-icon">
                                          <span role="img" aria-label="user" class="anticon anticon-user"
                                              style="font-size: 18px;">
                                              <svg viewBox="64 64 896 896" focusable="false" data-icon="user"
                                                  width="1em" height="1em" fill="currentColor" aria-hidden="true">
                                                  <path
                                                      d="M858.5 763.6a374 374 0 00-80.6-119.5 375.63 375.63 0 00-119.5-80.6c-.4-.2-.8-.3-1.2-.5C719.5 518 760 444.7 760 362c0-137-111-248-248-248S264 225 264 362c0 82.7 40.5 156 102.8 201.1-.4.2-.8.3-1.2.5-44.8 18.9-85 46-119.5 80.6a375.63 375.63 0 00-80.6 119.5A371.7 371.7 0 00136 901.8a8 8 0 008 8.2h60c4.4 0 7.9-3.5 8-7.8 2-77.2 33-149.5 87.8-204.3 56.7-56.7 132-87.9 212.2-87.9s155.5 31.2 212.2 87.9C779 752.7 810 825 812 902.2c.1 4.4 3.6 7.8 8 7.8h60a8 8 0 008-8.2c-1-47.8-10.9-94.3-29.5-138.2zM512 534c-45.9 0-89.1-17.9-121.6-50.4S340 407.9 340 362c0-45.9 17.9-89.1 50.4-121.6S466.1 190 512 190s89.1 17.9 121.6 50.4S684 316.1 684 362c0 45.9-17.9 89.1-50.4 121.6S557.9 534 512 534z">
                                                  </path>
                                              </svg>
                                          </span>
                                      </div>
                                      <div>Khách hàng</div>
                                  </div>
                              </div>
                          </div>
                          <div class="box-customer">
                              <div style="display: flex; justify-content: space-between; align-items: center;">
                                  <div class="ant-radio-group ant-radio-group-solid">
                                      <label class="ant-radio-button-wrapper">
                                          <span class="ant-radio-button">
                                              <input autocomplete="off" checked class="btn-check" value="0"
                                                  id="success-outlined${id}" name="options-outlined" type="radio"
                                                  onchange="selectOrderType(this, ${id})">
                                              <label class="btn btn-outline-success" for="success-outlined${id}"><svg
                                                      class="bi bi-house-door" fill="currentColor" height="16"
                                                      viewBox="0 0 16 16" width="16"
                                                      xmlns="http://www.w3.org/2000/svg">
                                                      <path
                                                          d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146ZM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5Z">
                                                      </path>
                                                  </svg> Tại quầy</label>
                                              <input type="radio" class="btn-check" name="options-outlined" value="1"
                                                  id="danger-outlined${id}" autocomplete="off"
                                                  onchange="selectOrderType(this, ${id})">
                                              <label class="btn btn-outline-danger" for="danger-outlined${id}"><svg
                                                      xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                      fill="currentColor" class="bi bi-globe" viewBox="0 0 16 16">
                                                      <path
                                                          d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm7.5-6.923c-.67.204-1.335.82-1.887 1.855A7.97 7.97 0 0 0 5.145 4H7.5V1.077zM4.09 4a9.267 9.267 0 0 1 .64-1.539 6.7 6.7 0 0 1 .597-.933A7.025 7.025 0 0 0 2.255 4H4.09zm-.582 3.5c.03-.877.138-1.718.312-2.5H1.674a6.958 6.958 0 0 0-.656 2.5h2.49zM4.847 5a12.5 12.5 0 0 0-.338 2.5H7.5V5H4.847zM8.5 5v2.5h2.99a12.495 12.495 0 0 0-.337-2.5H8.5zM4.51 8.5a12.5 12.5 0 0 0 .337 2.5H7.5V8.5H4.51zm3.99 0V11h2.653c.187-.765.306-1.608.338-2.5H8.5zM5.145 12c.138.386.295.744.468 1.068.552 1.035 1.218 1.65 1.887 1.855V12H5.145zm.182 2.472a6.696 6.696 0 0 1-.597-.933A9.268 9.268 0 0 1 4.09 12H2.255a7.024 7.024 0 0 0 3.072 2.472zM3.82 11a13.652 13.652 0 0 1-.312-2.5h-2.49c.062.89.291 1.733.656 2.5H3.82zm6.853 3.472A7.024 7.024 0 0 0 13.745 12H11.91a9.27 9.27 0 0 1-.64 1.539 6.688 6.688 0 0 1-.597.933zM8.5 12v2.923c.67-.204 1.335-.82 1.887-1.855.173-.324.33-.682.468-1.068H8.5zm3.68-1h2.146c.365-.767.594-1.61.656-2.5h-2.49a13.65 13.65 0 0 1-.312 2.5zm2.802-3.5a6.959 6.959 0 0 0-.656-2.5H12.18c.174.782.282 1.623.312 2.5h2.49zM11.27 2.461c.247.464.462.98.64 1.539h1.835a7.024 7.024 0 0 0-3.072-2.472c.218.284.418.598.597.933zM10.855 4a7.966 7.966 0 0 0-.468-1.068C9.835 1.897 9.17 1.282 8.5 1.077V4h2.355z">
                                                      </path>
                                                  </svg> Online</label>
                                          </span><span><span role="img" aria-label="desktop"
                                                  class="anticon anticon-desktop"></span></span>
                                      </label>
                                  </div>

                              </div>
                              <div class="mt-2 drownsearch">
                              <div class="select-btn search-form d-flex align-items-center" id="selectkhachhang">
                                <input type="text" id="searchtext" name="query" placeholder="Chọn khách hàng" title="Enter search keyword" onkeyup="updateSearch(${id})">
                                <button type="button"><i class="bi bi-plus-lg" data-bs-toggle="modal" data-bs-target="#modelAddKhachHang"></i></button>
                              </div>
                              <div class="dropdrownselect-content">
                                <ul class="options list-group list-group-flush" id="khachhangchoose"  >
<li class="list-group-item " onclick="updateName(this)">Iceland</li><li class="list-group-item " onclick="updateName(this)">India</li><li class="list-group-item " onclick="updateName(this)">Indonesia</li><li class="list-group-item " onclick="updateName(this)">Iran</li><li class="list-group-item " onclick="updateName(this)">Italy</li>
                                </ul>
                              </div>
                            </div>

                              <div type="flex" class="ant-row ant-row-space-between box-row"
                                  style="margin-top: 12px;">
                                  <div class="row g-3">
                                      <div class="col-md-6">
                                          <label for="tenKhachHang" class="form-label">Tên
                                              Khách Hàng</label>
                                          <input type="text" class="form-control" placeholder="Tên khách hàng"
                                              id="tenKhachHang">
                                      </div>
                                      <div class="col-md-6">
                                          <label for="soDienThoai" class="form-label">Số
                                              Điện Thoại</label>
                                          <input type="text" class="form-control" placeholder="Số điện thoại"
                                              id="soDienThoai">
                                      </div>
                                      <!-- Địa chỉ
                                      <div  class="col-md-4 online-option d-none">
                                          <label class="form-label">Chọn địa chỉ</label>
                                      <select class="form-select" id="diachichoose" onchange="selectdiachikhachhang(${id})">
                                      </select>
                                      </div>
                                      -->
                                      <div class="col-md-4 online-option d-none">
                                          <label class="form-label">Thành
                                              Phố</label>
                                          <select class="form-select" id="city" onchange="getAllDistrict(${id})">
                                          </select>
                                      </div>
                                      <div class="col-md-4 online-option d-none">
                                          <label class="form-label">Huyện</label>
                                          <select class="form-select" id="district" onchange="getFullWardCode(${id})">
                                              <option value="-1" disabled selected>Chọn Huyện</option>
                                          </select>
                                      </div>
                                      <div class="col-md-4 online-option d-none">
                                          <label class="form-label">Xã</label>
                                          <select class="form-select" id="ward" onchange="getFullAddress(${id})">
                                              <option value="-1" disabled selected>Chọn Xã</option>
                                          </select>
                                      </div>

                                      <div class="col-6 online-option  d-none">
                                          <label class="form-label">Địa
                                              Chỉ Cụ Thể</label>
                                          <input type="text" class="form-control" id="address"
                                              placeholder="Địa chỉ cụ thể">
                                      </div>
                                      <div class="col-6 online-option  d-none">
                                          <label class="form-label"> Phương thức vận chuyển</label>
                                              <select class="form-select"  id="shipService" onchange="updateTongTien(${id})">
                                              </select>
                                      </div>
                                      <div>
                                      <input type="hidden" name="" id="FullAddress">
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </section>
                      <!-- Thanh toán -->
                      <section>
                          <div class="box-order-title flex-space-between">
                              <div>
                                  <div class="box-meta">
                                      <div class="box-icon">
                                          <span role="img" aria-label="solution" class="anticon anticon-solution"
                                              style="font-size: 18px;">
                                              <svg viewBox="64 64 896 896" focusable="false" data-icon="solution"
                                                  width="1em" height="1em" fill="currentColor" aria-hidden="true">
                                                  <path
                                                      d="M688 264c0-4.4-3.6-8-8-8H296c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8h384c4.4 0 8-3.6 8-8v-48zm-8 136H296c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8h384c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8zM480 544H296c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8h184c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8zm-48 308H208V148h560v344c0 4.4 3.6 8 8 8h56c4.4 0 8-3.6 8-8V108c0-17.7-14.3-32-32-32H168c-17.7 0-32 14.3-32 32v784c0 17.7 14.3 32 32 32h264c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm356.8-74.4c29-26.3 47.2-64.3 47.2-106.6 0-79.5-64.5-144-144-144s-144 64.5-144 144c0 42.3 18.2 80.3 47.2 106.6-57 32.5-96.2 92.7-99.2 162.1-.2 4.5 3.5 8.3 8 8.3h48.1c4.2 0 7.7-3.3 8-7.6C564 871.2 621.7 816 692 816s128 55.2 131.9 124.4c.2 4.2 3.7 7.6 8 7.6H880c4.6 0 8.2-3.8 8-8.3-2.9-69.5-42.2-129.6-99.2-162.1zM692 591c44.2 0 80 35.8 80 80s-35.8 80-80 80-80-35.8-80-80 35.8-80 80-80z">
                                                  </path>
                                              </svg>
                                          </span>
                                      </div>
                                      <div>Thanh toán</div>
                                  </div>
                              </div>
                              <div class="flex-space-between">

                                  <div
                                      style="width: 32px; height: 32px; border: 1px solid rgb(217, 217, 217); background: rgb(255, 255, 255); cursor: pointer; margin-left: 10px; display: flex; align-items: center; justify-content: center; border-radius: 2px;">
                                      <span role="img" aria-label="more" class="anticon anticon-more">
                                          <svg viewBox="64 64 896 896" focusable="false" data-icon="more" width="1em"
                                              height="1em" fill="currentColor" aria-hidden="true">
                                              <path
                                                  d="M456 231a56 56 0 10112 0 56 56 0 10-112 0zm0 280a56 56 0 10112 0 56 56 0 10-112 0zm0 280a56 56 0 10112 0 56 56 0 10-112 0z">
                                              </path>
                                          </svg>
                                      </span>
                                  </div>
                              </div>
                          </div>
                          <style>
                              .input-wrapper {
                                  position: relative;
                              }

                              .currency-symbol {
                                  position: absolute;
                                  left: 10px;
                                  top: 50%;
                                  transform: translateY(-50%);
                                  pointer-events: none;
                                  color: #999;
                              }
                          </style>
                          <div class="line"></div>
                          <div class="box-payment" id="box-payment-info">
                              <div class="row g-3">
                                  <div class="col-12 d-none">
                                      <div class="input-wrapper">
                                          <span class="currency-symbol">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                  fill="currentColor" class="bi bi-box2-heart" viewBox="0 0 16 16">
                                                  <path
                                                      d="M8 7.982C9.664 6.309 13.825 9.236 8 13 2.175 9.236 6.336 6.31 8 7.982Z">
                                                  </path>
                                              </svg> Giảm giá đơn hàng </span>
                                          <input type="text" class="form-control" id="giamgia" placeholder="0₫"
                                              oninput="updateDiscountAmount(this.value)">
                                      </div>
                                  </div>
                                  <div class="col-12">
                                      <div class="input-wrapper">
                                          <span class="currency-symbol">
                                          <i class="bi bi-cash-coin"></i>
                                          </span>
                                          <select type="number" class="form-control text-center" id="voucher-choose" onchange="updateTongTien(${id})">
                                          </select>
                                      </div>
                                      </div>
                                  <div class="col-12 incount-option">
                                      <div class="input-wrapper">
                                          <span class="currency-symbol">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                  fill="currentColor" class="bi bi-bank" viewBox="0 0 16 16">
                                                  <path
                                                      d="m8 0 6.61 3h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.38l.5 2a.498.498 0 0 1-.485.62H.5a.498.498 0 0 1-.485-.62l.5-2A.501.501 0 0 1 1 13V6H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 3h.89L8 0ZM3.777 3h8.447L8 1 3.777 3ZM2 6v7h1V6H2Zm2 0v7h2.5V6H4Zm3.5 0v7h1V6h-1Zm2 0v7H12V6H9.5ZM13 6v7h1V6h-1Zm2-1V4H1v1h14Zm-.39 9H1.39l-.25 1h13.72l-.25-1Z">
                                                  </path>
                                              </svg>
                                              Tiền chuyển khoản
                                          </span>
                                          <input type="number" class="form-control" id="transfer-amount" onchange="finalPrice(${id})"
                                              placeholder="0 ₫">
                                      </div>
                                  </div>

                                  <div class="col-12 incount-option">
                                      <div class="input-wrapper">
                                          <span class="currency-symbol">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                  fill="currentColor" class="bi bi-coin" viewBox="0 0 16 16">
                                                  <path
                                                      d="M5.5 9.511c.076.954.83 1.697 2.182 1.785V12h.6v-.709c1.4-.098 2.218-.846 2.218-1.932 0-.987-.626-1.496-1.745-1.76l-.473-.112V5.57c.6.068.982.396 1.074.85h1.052c-.076-.919-.864-1.638-2.126-1.716V4h-.6v.719c-1.195.117-2.01.836-2.01 1.853 0 .9.606 1.472 1.613 1.707l.397.098v2.034c-.615-.093-1.022-.43-1.114-.9H5.5zm2.177-2.166c-.59-.137-.91-.416-.91-.836 0-.47.345-.822.915-.925v1.76h-.005zm.692 1.193c.717.166 1.048.435 1.048.91 0 .542-.412.914-1.135.982V8.518l.087.02z">
                                                  </path>
                                                  <path
                                                      d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z">
                                                  </path>
                                                  <path
                                                      d="M8 13.5a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11zm0 .5A6 6 0 1 0 8 2a6 6 0 0 0 0 12z">
                                                  </path>
                                              </svg>
                                              Tiền mặt
                                          </span>
                                          <input type="number" class="form-control" id="surcharge-amount" onchange="finalPrice(${id})"
                                              placeholder="0 ₫">
                                      </div>
                                  </div>
                              </div>
                              <div data-show="true" class="ant-alert ant-alert-info ant-alert-no-icon" role="alert">
                                  <div class="ant-alert-content">
                                      <div class="ant-alert-message">
                                          <div class="box-monney-wrapper">
                                              <div class="box-money-section s1">
                                                  <div class="box-monney">
                                                      <span class="title">Tổng tiền sản
                                                          phẩm</span>
                                                      <span class="fw-500" id="totalAmount">0
                                                          đ</span>
                                                  </div>
                                                  <div class="box-monney">
                                                      <span class="title">Giảm
                                                          giá&nbsp;</span>
                                                      <span id="discount-amount" class="fw-500 red">0 ₫</span>
                                                  </div>
                                                  <div class="box-monney online-option d-none">
                                                      <span class="title">Tiền
                                                          Ship&nbsp;</span>
                                                      <span id="amount-ship" class="fw-500 red"> đ</span>
                                                         <input type="hidden" id="total-ship"/>
                                                  </div>
                                              </div>
                                              <div class="box-money-section s3">
                                                  <div class="box-monney"><span class="title">Cần
                                                          thanh toán</span>
                                                      <span class="fw-500" id="finalAmount">0
                                                          ₫</span>
                                                  </div>

                                                  <div class="box-monney incount-option" ><span class="title">Tiền
                                                          khách đưa</span>
                                                      <span class="fw-500 blue" id="final-price">0
                                                          ₫</span>
                                                  </div>
                                                  <div class="box-monney incount-option"><span class="title">Còn
                                                          thiếu</span>
                                                      <span class="fw-500" id="changeAmount">0₫</span>
                                                  </div>
                                              </div>
                                              <div class="box-monney incount-option" style="padding-bottom: 0px;">
                                                  <span class="title">Trả lại</span>
                                                  <span class="fw-500 red" id="remain-price">0₫</span>
                                                  <span id="origin-remain-price" style="display: none;">0</span>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="ant-alert-description"></div>
                                  </div>
                              </div>
                          </div>
                      </section>
                      <section>
                          <div class="ant-tabs ant-tabs-top box-note-tabs">
                              <div role="tablist" class="ant-tabs-nav">
                                  <div class="ant-tabs-nav-wrap">
                                      <div class="ant-tabs-nav-list" style="transform: translate(0px, 0px);">
                                          <div class="ant-tabs-tab ant-tabs-tab-active">
                                              <div role="tab" aria-selected="true" class="ant-tabs-tab-btn"
                                                  tabindex="0" id="rc-tabs-2-tab-internal"
                                                  aria-controls="rc-tabs-2-panel-internal">
                                                  Ghi
                                                  chú
                                              </div>
                                          </div>
                                          <div class="ant-tabs-ink-bar ant-tabs-ink-bar-animated"
                                              style="left: 0px; width: 77px;"></div>
                                      </div>
                                  </div>
                              </div>
                              <div role="tabpanel" tabindex="0" aria-hidden="false"
                                  class="ant-tabs-tabpane ant-tabs-tabpane-active" id="rc-tabs-2-panel-internal"
                                  aria-labelledby="rc-tabs-2-tab-internal">
                                  <div style="width: 100%; position: relative; padding: 16px;">
                                      <div class="ant-mentions" style="min-height: 122px;">
                                          <textarea placeholder="Viết ghi chú hoặc /shortcut để ghi chú nhanh"
                                              id="note" rows="1" class="rc-textarea"
                                              style="height: 134px; min-height: 134px; max-height: 904px; overflow-y: hidden; resize: none;"></textarea>
                                      </div>
                                      <div style="margin-top: 12px;">
                                          <div style="display: flex;">
                                              <span class="ant-upload-picture-card-wrapper note-image">
                                                  <div class="ant-upload-list ant-upload-list-picture-card">
                                                      <div
                                                          class="ant-upload ant-upload-select ant-upload-select-picture-card">
                                                          <span tabindex="0" class="ant-upload" role="button"><input
                                                                  type="file" accept="image/*" multiple=""
                                                                  style="display: none;">
                                                          </span>
                                                      </div>
                                                  </div>
                                              </span>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <div role="tabpanel" tabindex="-1" aria-hidden="true" class="ant-tabs-tabpane"
                                  id="rc-tabs-2-panel-forprinting" aria-labelledby="rc-tabs-2-tab-forprinting"
                                  style="display: none;"></div>
                          </div>
                          <div class="line"></div>
                          <style>
                              .dropzone-indicator span {
                                  font-size: 22px;
                                  font-weight: 500;
                                  color: #506dad
                              }
                          </style>
                          <div class="box-order-status">
                            <div style="display: flex;">
                                    <span class="order-action-button" style="margin-right: 8px;">
                                            <button type="button" id="removebill" class="ant-btn ant-btn-primary" onclick="removeOrderPage(${id})"
                                                    style="font-size: 14px; height: 100%; background: rgb(248, 13, 13); border-color: rgb(250, 112, 20);"><span><span
                                                                    role="img" aria-label="printer" class="anticon anticon-printer"><svg
                                                                            viewBox="64 64 896 896" focusable="false" data-icon="printer"
                                                                            width="1em" height="1em" fill="currentColor" aria-hidden="true">
                                                                            <path
                                                                                    d="M820 436h-40c-4.4 0-8 3.6-8 8v40c0 4.4 3.6 8 8 8h40c4.4 0 8-3.6 8-8v-40c0-4.4-3.6-8-8-8zm32-104H732V120c0-4.4-3.6-8-8-8H300c-4.4 0-8 3.6-8 8v212H172c-44.2 0-80 35.8-80 80v328c0 17.7 14.3 32 32 32h168v132c0 4.4 3.6 8 8 8h424c4.4 0 8-3.6 8-8V772h168c17.7 0 32-14.3 32-32V412c0-44.2-35.8-80-80-80zM360 180h304v152H360V180zm304 664H360V568h304v276zm200-140H732V500H292v204H160V412c0-6.6 5.4-12 12-12h680c6.6 0 12 5.4 12 12v292z">
                                                                            </path>
                                                                    </svg></span> Xóa (F10)</span>
                                            </button>
                                    </span>
                                    <span class="order-action-button" style="margin-right: 8px;">
                                            <button type="submit" class="ant-btn ant-btn-primary"
                                                    style="font-size: 14px; height: 100%;"><span><span role="img" aria-label="save"
                                                                    class="anticon anticon-save"><svg viewBox="64 64 896 896"
                                                                            focusable="false" data-icon="save" width="1em" height="1em"
                                                                            fill="currentColor" aria-hidden="true">
                                                                            <path
                                                                                    d="M893.3 293.3L730.7 130.7c-7.5-7.5-16.7-13-26.7-16V112H144c-17.7 0-32 14.3-32 32v736c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V338.5c0-17-6.7-33.2-18.7-45.2zM384 184h256v104H384V184zm456 656H184V184h136v136c0 17.7 14.3 32 32 32h320c17.7 0 32-14.3 32-32V205.8l136 136V840zM512 442c-79.5 0-144 64.5-144 144s64.5 144 144 144 144-64.5 144-144-64.5-144-144-144zm0 224c-44.2 0-80-35.8-80-80s35.8-80 80-80 80 35.8 80 80-35.8 80-80 80z">
                                                                            </path>
                                                                    </svg></span> Thanh Toán</span><span id="countdown" class="badge text-bg-secondary">12:22</span>
                                            </button>
                                    </span>
                            </div>
                    </div>
                      </section>
                  </div>
              </div>
          </div>
      </div>

  </div>
</div>
<div id="overlay">
      <button type="button" class="btn btn-danger px-5" onclick="removeOrderPage(${id})">
        Xóa (F5)
      </button>
</div>
`;
  return form;
}
// ui processtion
function addnewOrderPage() {
  if (listtab.length < 5) {
    var id = 0;
    if (listtab.length == 0) {
      id = 1;
    } else {
      id = Math.max(...listtab) + 1;
    }
    listtab.push(id);
    renderOrderPage(id);
    getAllprovide(id);
    fillAllEmployee(id);
    getFirstProductPage(id);
    getVoucherAble(`hoaDon${id}`);
    var i, taborder, tabbutton;
    taborder = document.getElementsByClassName("taborder");
    for (i = 0; i < taborder.length; i++) {
      taborder[i].style.display = "none";
    }
    tabbutton = document.getElementsByClassName("tabbutton");
    for (i = 0; i < tabbutton.length; i++) {
      tabbutton[i].className = tabbutton[i].className.replace(" active", "");
    }
    document.getElementById(`hoaDon${id}`).style.display = "block";
    const exitButton = document.getElementById(`vieworder${id}`);
    exitButton.className += " active";
    countdown(id);
  } else {
    new Notify({
      status: "warning",
      title: "Quá số lượng hóa đơn cho phép!!!",
      text: "Tối đa 5 hóa đơn",
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: true,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
  }
}
let timeouts = {};
// Create an object to store timeouts
function countdown(id) {
  const countdownElement = document.querySelector(`#hoaDon${id} #countdown`);

  if (countdownElement) {
    const endTime = new Date(
      document.getElementById(`hoaDon${id}`).getAttribute("endtime")
    );
    const currentTime = new Date();
    const remainingTimeMilliseconds = endTime - currentTime;
    const remainingTimeSeconds = Math.floor(remainingTimeMilliseconds / 1000);

    if (remainingTimeSeconds <= 0) {
      countdownElement.innerHTML = "Hết giờ!";
      const buttonsubmit = document.querySelector(
        `#hoaDon${id} button[type="submit"]`
      );
      buttonsubmit.disabled = true;
      on(id);
    } else {
      countdownElement.innerHTML = formatTime(remainingTimeSeconds);
      timeouts[id] = setTimeout(() => countdown(id), 1000);
    }
  } else {
    console.error(`Countdown element not found for form with id ${id}.`);
  }
}
function on(formid) {
  document.querySelector(`#hoaDon${formid} #overlay`).style.display = "flex";
}
function off(formid) {
  document.querySelector(`#hoaDon${formid} #overlay`).style.display = "none";
}
function stopCountdown(id) {
  clearTimeout(timeouts[id]);
}
function formatTime(seconds) {
  var minutes = Math.floor(seconds / 60);
  var remainingSeconds = seconds % 60;
  minutes = minutes < 10 ? "0" + minutes : minutes;
  remainingSeconds =
    remainingSeconds < 10 ? "0" + remainingSeconds : remainingSeconds;
  return minutes + ":" + remainingSeconds;
}
// time out end
function filterTable(orderId) {
  var input, filter, table, tbody, tr, td, i, j, txtValue;
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  var tbody = thisOrder.querySelector("#cartTableProduct tbody");
  input = thisOrder.querySelector("#searchInput");

  filter = input.value.toUpperCase();
  table = document.getElementById("cartTableProduct");
  // tbody = table.getElementsByTagName("tbody")[0];
  tr = tbody.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    // Tìm kiếm trong tất cả các cột (loại bỏ cột thứ 1, tức là stt)
    for (j = 1; j < tr[i].cells.length; j++) {
      td = tr[i].getElementsByTagName("td")[j];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
          break; // Nếu tìm thấy, thoát khỏi vòng lặp để hiển thị hàng
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }
}
function renderOrderPage(id) {
  formOrder.appendChild(formInOrder(id));
}
function selectOrder(evt, id) {
  var i, taborder, tabbutton;
  taborder = document.getElementsByClassName("taborder");
  for (i = 0; i < taborder.length; i++) {
    taborder[i].style.display = "none";
  }
  tabbutton = document.getElementsByClassName("tabbutton");
  for (i = 0; i < tabbutton.length; i++) {
    tabbutton[i].className = tabbutton[i].className.replace(" active", "");
  }
  document.getElementById(`hoaDon${id}`).style.display = "block";
  evt.currentTarget.className += " active";
}
async function removeOrderPage(orderId) {
  const data = await buildFormData(orderId);
  if (data.productViews.length == 0) {
    let indexToRemove = listtab.indexOf(orderId);
    if (indexToRemove !== -1) {
      listtab.splice(indexToRemove, 1);
    }
    stopCountdown(orderId);
    const orderbtnrmRemove = document.getElementById(`vieworder${orderId}`);
    const orderToRemove = document.getElementById(`hoaDon${orderId}`);
    if (orderToRemove) {
      orderToRemove.remove();
    }
    if (orderbtnrmRemove) {
      orderbtnrmRemove.remove();
    }
    deleteOrder(orderId);
    selectLastHoaDon();
    return;
  }
  if (confirm("Bạn muốn xóa sao")) {
    let indexToRemove = listtab.indexOf(orderId);
    if (indexToRemove !== -1) {
      listtab.splice(indexToRemove, 1);
    }
    stopCountdown(orderId);
    const orderbtnrmRemove = document.getElementById(`vieworder${orderId}`);
    const orderToRemove = document.getElementById(`hoaDon${orderId}`);
    removeOrder(orderId);
    if (orderToRemove) {
      orderToRemove.remove();
    }
    if (orderbtnrmRemove) {
      orderbtnrmRemove.remove();
    }
    selectLastHoaDon();
  }
}
function selectLastHoaDon() {
  try {
    var maxNumber = Math.max.apply(null, listtab);
    var idbtn = "vieworder" + maxNumber;
    const lastBtnHoaDon = document.getElementById(idbtn);
    if (lastBtnHoaDon) {
      lastBtnHoaDon.click();
    } else {
      console.error("Button not found:", idbtn);
    }
  } catch (error) {
    console.error("Error selecting or clicking the last button:", error);
  }
}
function selectOrderType(element, id) {
  orderType = element.value;
  const OptionOnline = document.querySelectorAll(`#hoaDon${id} .online-option`);
  const Optionincount = document.querySelectorAll(
    `#hoaDon${id} .incount-option`
  );
  if (orderType == 0) {
    OptionOnline.forEach(function (element) {
      element.classList.add("d-none");
    });
    Optionincount.forEach(function (element) {
      element.classList.remove("d-none");
    });
  } else if (orderType == 1) {
    OptionOnline.forEach(function (element) {
      element.classList.remove("d-none");
    });
    Optionincount.forEach(function (element) {
      element.classList.add("d-none");
    });
  }
  updateTongTien(id);
}
// ui processtion end
// GHN API Service
function updateshipService(orderId) {
  const districtSelect = document.querySelector(`#hoaDon${orderId} #district`);
  const selectedOption = districtSelect.options[districtSelect.selectedIndex];
  const customAttribute = selectedOption.getAttribute("districtcode");
  const districtto = parseInt(customAttribute);
  const shipServiceSelect = document.querySelector(
    `#hoaDon${orderId} #shipService`
  );
  fetch(
    "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        token: token,
      },
      body: JSON.stringify({
        to_district: districtto,
        shop_id: shop_id,
        from_district: districtform,
      }),
    }
  )
    .then((res) => res.json())
    .then((data) => {
      while (shipServiceSelect.firstChild) {
        shipServiceSelect.removeChild(shipServiceSelect.firstChild);
      }
      const defaultOption = document.createElement("option");
      defaultOption.value = "-1"; // Set the value as needed
      defaultOption.textContent = "Chọn Hình thức vận chuyển"; // Set the text content
      // Set the 'disabled' and 'selected' attributes to make it the default option
      defaultOption.disabled = true;
      defaultOption.selected = true;
      shipServiceSelect.appendChild(defaultOption);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        option.value = options[i].service_id; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].short_name; // Set the text of the option
        shipServiceSelect.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
function resetTotalShip(orderId) {
  document.querySelector(`#hoaDon${orderId} #total-ship`).value = 0;
  document.querySelector(`#hoaDon${orderId} #amount-ship`).innerHTML =
    formatToVND(0);
}
function getAllprovide(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectCity = thisOrder.querySelector("#city");
  fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/province", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      token: token,
    },
  })
    .then((res) => res.json())
    .then((data) => {
      const defaultOption = document.createElement("option");
      defaultOption.value = -1; // Set the value as needed
      defaultOption.textContent = "Chọn Tỉnh"; // Set the text content
      // Set the 'disabled' and 'selected' attributes to make it the default option
      defaultOption.disabled = true;
      defaultOption.selected = true;
      selectCity.appendChild(defaultOption);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        // option.value = options[i].ProvinceID; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].ProvinceName; // Set the text of the option
        option.setAttribute("providecode", options[i].ProvinceID);
        selectCity.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
function getAllDistrict(orderId) {
  const selectCity = document.querySelector(`#hoaDon${orderId} #city`);
  const selectedOption = selectCity.options[selectCity.selectedIndex];
  const customAttribute = selectedOption.getAttribute("providecode");
  const provinceid = parseInt(customAttribute);
  const selectDistrict = document.querySelector(`#hoaDon${orderId} #district`);
  fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/district", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      token: token,
    },
    body: JSON.stringify({ province_id: provinceid }),
  })
    .then((res) => res.json())
    .then((data) => {
      resetDistrict(orderId);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        option.value = options[i].DistrictID; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].DistrictName; // Set the text of the option
        option.setAttribute("districtcode", options[i].DistrictID);
        selectDistrict.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
function getFullWardCode(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selecteDistrict = thisOrder.querySelector("#district");
  const selectWardCode = thisOrder.querySelector("#ward");
  const selectedOption = selecteDistrict.options[selecteDistrict.selectedIndex];
  const customAttribute = selectedOption.getAttribute("districtcode");
  const districtid = parseInt(customAttribute);
  fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/ward", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      token: token,
    },
    body: JSON.stringify({ district_id: districtid }),
  })
    .then((res) => res.json())
    .then((data) => {
      //remove all child
      resetWard(orderId);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        option.value = options[i].WardCode; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].WardName; // Set the text of the option
        selectWardCode.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
function getFullAddress(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectCity = thisOrder.querySelector("#city");
  const selectDistrict = thisOrder.querySelector("#district");
  const selectWards = thisOrder.querySelector("#ward");
  const proselect = selectCity.options[selectCity.selectedIndex];
  const districselect = selectDistrict.options[selectDistrict.selectedIndex];
  const wardName = selectWards.options[selectWards.selectedIndex];
  const fullAdress =
    wardName.text + ", " + districselect.text + " ," + proselect.text;
  thisOrder.querySelector("#FullAddress").value = String(fullAdress);
  updateshipService(orderId);
}
function resetDistrict(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectProvide = thisOrder.querySelector("#district");
  while (selectProvide.firstChild) {
    selectProvide.removeChild(selectProvide.firstChild);
  }
  const defaultOption = document.createElement("option");
  defaultOption.value = -1; // Set the value as needed
  defaultOption.textContent = "Chọn Quận/ Huyện"; // Set the text content
  defaultOption.disabled = true;
  defaultOption.selected = true;
  selectProvide.appendChild(defaultOption);
  resetWard(orderId);
  resetServiceShip(orderId);
}
function resetWard(orderId) {
  const wardSelect = document.querySelector(`#hoaDon${orderId} #ward`);
  while (wardSelect.firstChild) {
    wardSelect.removeChild(wardSelect.firstChild);
  }
  const defaultOption = document.createElement("option");
  defaultOption.value = -1; // Set the value as needed
  defaultOption.textContent = "Chọn Thị Trấn/ Xã/ Phường"; // Set the text content
  defaultOption.disabled = true;
  defaultOption.selected = true;
  wardSelect.appendChild(defaultOption);
}
function resetServiceShip(orderId) {
  const shipSelect = document.querySelector(`#hoaDon${orderId} #shipService`);
  while (shipSelect.firstChild) {
    shipSelect.removeChild(shipSelect.firstChild);
  }
  resetTotalShip(orderId);
}

// GHN API Service

//load data
function fillAllEmployee(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  var select = thisOrder.querySelector('select[name="employee"]');
  fetch("/admin/counter/customers", {
    method: "GET",
  })
    .then((res) => res.json())
    .then((data) => {
      data.forEach(function (employee) {
        var option = document.createElement("option");
        option.value = employee.id;
        option.text = employee.name;
        select.appendChild(option);
      });
    });
}

function getFirstProductPage(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  var tbody = thisOrder.querySelector("#cartTableProduct tbody");

  fetch("/admin/counter/productDetails", {
    method: "GET",
  })
    .then((res) => res.json())
    .then((data) => {
      data.forEach(function (product) {
        var row = document.createElement("tr");
        var cells = [
          `<img src="${
            product.firstImage || defaultImage
          }" class="image-fluid" style="height: 60px; max-width : 60px;">`,
          `
          <div class="d-flex">
          <button type="button" class="btn btn-primary mx-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop${
            product.id
          }">
          <i class="fas fa-eye fa-xs"></i>
        </button>
        <div class="modal fade" id="staticBackdrop${
          product.id
        }" data-bs-backdrop="static" data-bs-keyboard="false"
            tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel" ${
                    product.idProduct?.name ?? ""
                  }>
                    ${product.idProduct?.name ?? ""}</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <p><strong>name :</strong>
                    <span>${product.name}</span>
                  </p>
                  <p><strong>price :</strong>
                    <span> ${product.price.toLocaleString("it-IT", {
                      style: "currency",
                      currency: "VND",
                    })}
                    </span>
                  </p>
                  </p>
                  <p><strong>quantity :</strong>
                    <span>${product.quantity}</span>
                  </p>
                  <p><strong>Product :</strong>
                    <span>${product.idProduct?.name ?? ""}</span>
                  </p>
                  <p><strong>Pattern :</strong>
                    <span>${product.idPattern?.name ?? ""}</span>
                  </p>
                  <p><strong>Color :</strong>
                    <span>${product.idSize?.name ?? ""}</span>
                  </p>
                  <p><strong>Origin :</strong>
                    <span>${product.idProduct?.idOrigin?.name ?? ""}</span>
                  </p>
                  <p><strong>Brand :</strong>
                    <span>${product.idProduct?.idBrand?.name ?? ""}</span>
                  </p>
                  <p><strong>Material :</strong>
                    <span>${product.idProduct?.idMaterial?.name ?? ""}</span>
                  </p>
                  <p><strong>Styles :</strong>
                    <span>${product.idProduct?.idStyles?.name ?? ""}</span>
                  </p>
                  <p><strong>description :</strong>
                    <span>${product.description}</span>
                  </p>
                </div>
                <div class=" modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
          <button type="button" class="btn btn-success  mx-1"  onclick="addProductIntoOrder(${orderId},${
            product.id
          })"><i class="far fa-plus"></i></button>
          </div>`,
          product.name,
          formatToVND(product.price),
          product.quantity,
          product.idPattern.name,
          product.idSize.name,
        ];
        cells.forEach(function (cellContent) {
          var cell = document.createElement("td");
          cell.innerHTML = cellContent;
          row.appendChild(cell);
        });

        tbody.appendChild(row);
      });
    });
}
//load data end
// add san pham in to order
async function addProductIntoOrder(idorderdetail, id) {
  const form = document.getElementById(`hoaDon${idorderdetail}`);
  try {
    const product = await getProductDetails(id);
    const productExists = await findInExitOrder(idorderdetail, product.id);

    if (productExists !== null) {
      increaseProductQuantity(productExists, product);
    } else {
      if (product.quantity < 1) {
        new Notify({
          status: "warning",
          title: "Sản phẩm đã hết",
          text: `Sản phẩm: " ${product.name} Màu sắc: ${
            product.idColor?.name ?? ""
          } Hoa văn: ${product.idPattern?.name ?? ""} Đã Hết`,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      } else {
        const productsOnOrder = form.querySelector("#cartTable tbody");
        const newProductRow = createProductRow(product, idorderdetail);
        productsOnOrder.appendChild(newProductRow);
      }
    }
    updateHoaDon(idorderdetail);
    updateTongTien(idorderdetail);
  } catch (error) {
    console.error(error);
  }
}
function createProductRow(product, idorderdetail) {
  const newProductRow = document.createElement("tr");
  newProductRow.classList.add("table-body-row", "order-product");
  newProductRow.setAttribute("idproduct", product.id);
  newProductRow.innerHTML = `

        <td>
        <img src="${
          product.firstImage || defaultImage
        }" class="image-fluid" style="height: 60px; max-width : 60px;     float: left;
        "><strong>Tên:</strong> ${product.name} <strong>Hoa văn:</strong> ${
    product.idPattern?.name ?? ""
  } </td>
        <td>${formatToVND(product.price)}</td>
        <td>
        <div class="d-flex w-100">
          <a class="btn btn-link px-2"
          onclick="giamProductIntoOrder(${idorderdetail},${product.id})">
           <i class="fas fa-minus"></i>
          </a>
          <input name="quantity" type="number" value=1 min="0" disabled class="form-control form-control-sm text-center">
          <a class="btn btn-link px-2"
          onclick="addProductIntoOrder(${idorderdetail},${product.id})">
            <i class="fas fa-plus"></i>
          </a>
        </div>
        </td>
        <td>
          <button type="button" class="btn btn-danger" onclick="removeProduct(${idorderdetail}, ${
    product.id
  })">Remove</button>
        </td>
      `;
  return newProductRow;
}
function removeProduct(orderId, idproduct) {
  const productsOnOrder = document.querySelector(
    `#hoaDon${orderId} #cartTable tbody`
  );
  const rows = productsOnOrder.getElementsByClassName("table-body-row");
  for (const row of rows) {
    const rowProductId = row.getAttribute("idproduct");
    if (rowProductId == idproduct) {
      productsOnOrder.removeChild(row);
    }
  }
  updateHoaDon(orderId);
  updateTongTien(orderId);
}
async function findInExitOrder(idorderdetail, productId) {
  const productsOnOrder = document.querySelector(
    `#hoaDon${idorderdetail} #cartTable tbody`
  );
  const rows = productsOnOrder.getElementsByClassName("table-body-row");

  for (const row of rows) {
    const rowProductId = row.getAttribute("idproduct");
    if (rowProductId == productId) {
      return row;
    }
  }
  return null;
}
function increaseProductQuantity(productExists, product) {
  if (productExists instanceof Element) {
    const inputNumber = productExists.querySelector("td:nth-child(3) input");
    if (inputNumber instanceof Element) {
      const currentValue = parseInt(inputNumber.value, 10) || 0;
      if (currentValue >= product.quantity) {
        new Notify({
          status: "warning",
          title: "Sản phẩm đã hết",
          text: `Không thể thêm ${product.name} sản phẩm chỉ còn ${product.quantity} sản phẩm vui lòng chọn sản phẩm khác`,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      } else {
        inputNumber.value = currentValue + 1;
      }
    } else {
      console.error("Input number element not found.");
    }
  } else {
    console.error("Product element not found.");
  }
}
async function giamProductIntoOrder(idorderdetail, id) {
  try {
    const product = await getProductDetails(id);
    const productExists = await findInExitOrder(idorderdetail, product.id);
    if (productExists !== null) {
      descreaseProductQuantity(productExists, product);
    }
    updateHoaDon(orderId);
    updateTongTien(idorderdetail);
  } catch (error) {
    console.error(error);
  }
}
function descreaseProductQuantity(productExists, product) {
  if (productExists instanceof Element) {
    const inputNumber = productExists.querySelector("td:nth-child(3) input");
    if (inputNumber instanceof Element) {
      const currentValue = parseInt(inputNumber.value, 10) || 0;
      if (currentValue >= product.quantity) {
        new Notify({
          status: "warning",
          title: "Sản phẩm đã hết",
          text: `Không thể thêm ${product.name} sản phẩm chỉ còn ${product.quantity} sản phẩm vui lòng chọn sản phẩm khác`,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      } else {
        if (inputNumber.value <= 1) {
          productExists.remove();
        } else {
          inputNumber.value = currentValue - 1;
        }
      }
    } else {
      console.error("Input number element not found.");
    }
  } else {
    console.error("Product element not found.");
  }
}
// add san pham in to order
const phoneNumberRegex = /^(09|\d{2}[2-9])\d{7}$/;
async function handleOrderSubmit(event) {
  event.preventDefault();
  let totalMoney = 0;
  const formId = event.currentTarget.id;
  const idform = event.currentTarget.getAttribute("idofform");
  var errorCount = 0;
  const formData = {};
  const form = document.getElementById(formId);
  const endTimeAttribute = form.getAttribute("endtime");
  const endTime = new Date(endTimeAttribute);
  const currentTime = new Date();
  if (endTime > currentTime) {
    console.log("End time is after current time.");
  } else if (endTime < currentTime) {
    console.log("End time is before current time.");
    alert("Hóa đơn chờ quá lâu");
    return;
  } else {
    console.log("End time is equal to current time.");
  }
  console.log(formData);
  formData.employeeID = form.querySelector("select[name='employee']").value;
  formData.orderTypes = parseInt(
    form.querySelector("input[name='options-outlined']:checked").value,
    10
  );
  formData.customerName = form.querySelector("#tenKhachHang").value;
  const inputCustomer = form.querySelector("#customerid");
  if (inputCustomer) {
    formData.customerID = inputCustomer.value;
  }
  formData.phoneNumber = form.querySelector("#soDienThoai").value;
  formData.city = form.querySelector("#city").value;
  formData.district = form.querySelector("#district").value;
  formData.ward = form.querySelector("#ward").value;
  formData.fullAddress = form.querySelector("#FullAddress").value;
  formData.specificAddress = form.querySelector("input#address").value;
  formData.note = form.querySelector("textarea#note").value;

  resetErrorElement(form.querySelector("#tenKhachHang"));
  resetErrorElement(form.querySelector("#soDienThoai"));
  resetErrorElement(form.querySelector("#city"));
  resetErrorElement(form.querySelector("#district"));
  resetErrorElement(form.querySelector("#ward"));
  resetErrorElement(form.querySelector("#FullAddress"));
  resetErrorElement(form.querySelector("input#address"));
  resetErrorElement(form.querySelector("select[name='employee']"));
  resetErrorElement(form.querySelector("#changeAmount"));
  resetErrorElement(form.querySelector(`#shipService`));
  if (formData.employeeID == "null") {
    errorCount++;
    setErrorElement(
      form.querySelector("select[name='employee']"),
      "Employee cần được chọn!!!"
    );
  }
  formData.products = await Promise.all(
    Array.from(form.querySelectorAll("#cartTable tbody tr.table-body-row")).map(
      async (row) => {
        const productId = row.getAttribute("idproduct");
        const quantityElement = row.querySelector(`input[name="quantity"]`);
        const productQuantity = parseInt(quantityElement.value, 10);
        resetErrorElement(row);
        if (productQuantity < 0 || isNaN(productQuantity)) {
          errorCount++;
          setErrorElement(row, "Số lượng sản phẩm không đúng");
          return null; // Return null for invalid entries
        }
        const product = await getProductDetails(productId);
        if (product) {
          totalMoney += product.price * productQuantity;
        } else {
          errorCount++;
          setErrorElement(row, "Sản phẩm có vấn đề!!!");
          return null; // Return null for invalid entries
        }
        return {
          id: parseInt(productId, 10),
          quantity: productQuantity,
        };
      }
    )
  );
  formData.products = formData.products.filter((product) => product !== null);
  resetErrorElement(form.querySelector("#cartTable tbody"));
  if (formData.products.length <= 0) {
    errorCount++;
    setErrorElement(
      form.querySelector("#cartTable tbody"),
      "Chưa có sản phẩm nào!!!"
    );
  }
  const transferAmount = form.querySelector(`#transfer-amount`);
  const surchargeAmount = form.querySelector(`#surcharge-amount`);
  var transferAmountvl = parseInt(transferAmount.value.trim(), 10);
  var surchargeAmountvl = parseInt(surchargeAmount.value.trim(), 10);
  if (isNaN(transferAmountvl)) {
    transferAmountvl = 0;
  }
  if (isNaN(surchargeAmountvl)) {
    surchargeAmountvl = 0;
  }
  formData.transferMoney = transferAmountvl;
  formData.cashMoney = surchargeAmountvl;
  const totalAmount = transferAmountvl + surchargeAmountvl;
  formData.cashReturn = Math.max(totalAmount - totalMoney, 0);
  formData.changeAmount = Math.max(totalMoney - totalAmount, 0);
  formData.voucherid = parseInt(
    form.querySelector("#voucher-choose").value,
    10
  );
  const discountValue = await checkVoucher(formId, totalMoney);
  if (isNaN(formData.voucherid) || discountValue <= 0) {
    formData.voucherid = 0;
  }
  formData.totalMoney = totalMoney - (isNaN(discountValue) ? 0 : discountValue);
  formData.reductionAmount = 0;
  if (formData.orderTypes === 0) {
    formData.totalShip = 0;
    if ((formData.cashMoney + formData.transferMoney) < formData.totalMoney) {
      errorCount++;
      // ("Tiền chưa đủ !!! \n");
      setErrorElement(form.querySelector("#changeAmount"), "Chưa đủ tiền !!!");
      new Notify({
        status: "warning",
        title: "Tiền thanh toán chưa đủ !!!",
        text: "Kiểm tra lại số tiền",
        effect: "fade",
        speed: 300,
        customClass: "",
        customIcon: "",
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: "right top",
        customWrapper: "",
      });
    }
  } else if (formData.orderTypes === 1) {
    formData.totalShip = form.querySelector(`#total-ship`).value;
    if (formData.totalShip < 0 || isNaN(formData.totalShip)) {
      errorCount++;
      setErrorElement(
        form.querySelector(`#shipService`),
        "Chọn lại phương thức vận chuyển !!!\n"
      );
    }
    if (formData.customerName.trim().length === 0) {
      errorCount++;
      setErrorElement(
        form.querySelector("#tenKhachHang"),
        "Tên khách hàng không được để trống !!!"
      );
    }
    if (formData.phoneNumber.trim().length === 0) {
      errorCount++;
      setErrorElement(
        form.querySelector("#soDienThoai"),
        "Số điện thoại không được để trống !!!"
      );
    } else if (!phoneNumberRegex.test(formData.phoneNumber.trim())) {
      errorCount++;
      setErrorElement(
        form.querySelector("#soDienThoai"),
        "Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại đúng định dạng."
      );
    }
    if (formData.city == -1) {
      errorCount++;
      setErrorElement(
        form.querySelector("#city"),
        "Thành Phố không được thiếu !!!"
      );
    }
    if (formData.district == -1) {
      errorCount++;
      setErrorElement(
        form.querySelector("#district"),
        "Quận/huyện không được thiếu !!!"
      );
    }
    if (formData.ward == -1) {
      errorCount++;
      setErrorElement(form.querySelector("#ward"), "Xã không được thiếu !!!");
    }
    if (formData.fullAddress.trim() === "") {
      errorCount++;
      setErrorElement(
        form.querySelector("#FullAddress"),
        "Kiểm tra lại địa chỉ !!!"
      );
    }
    if (formData.specificAddress.trim() === "") {
      errorCount++;
      setErrorElement(
        form.querySelector("input#address"),
        "Address không được thiếu !!!\n"
      );
    }
    const shipSelect = parseInt(form.querySelector(`#shipService`).value, 10);
    if (shipSelect == -1 || isNaN(shipSelect)) {
      errorCount++;
      setErrorElement(
        form.querySelector(`#shipService`),
        "Xem lại hình thức vận chuyển !!!\n"
      );
    }
  }
  console.log(formData);
  console.log(errorCount);
  if (errorCount > 0) {
    new Notify({
      status: "error",
      title: "Vui lòng kiểm tra lại thông tin",
      text: "Kiểm tra lại thông tin đầu vào",
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: true,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
    return;
  } else {
    console.log(formData);
    thanhtoan(JSON.stringify(formData), idform);
  }
}
async function thanhtoan(formValuesJSON, idform) {
  try {
    const response = await axios.post(
      "/admin/counter/checkout",
      formValuesJSON,
      {
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    );
    const data = response.data;
    console.log(data);
    if (data?.status === "BAD_REQUEST") {
      alert(data?.message);
      if (data.message) {
        new Notify({
          status: "error",
          title: "Lỗi khi lưu hóa đơn",
          text: data.message,
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
      }
      return;
    } else {
      printBill(data.id);
      handleOrderSuccess(idform);
    }
  } catch (error) {
    console.error("Error:", error);
  }
}
function printBill(id) {
  axios
    .get(`/admin/api/bill/${id}`)
    .then((response) => {
      const billData = response.data;
      console.log(billData);
      $("#bill #billcode").text(billData.code);
      $("#bill #customername").text(billData.customer_name);
      $("#bill #phonenumber").text(billData.phone);
      $("#bill #fulladdress").text(billData.address);
      // Update employee information
      $("#bill #hoadoncode").text(billData.code);
      $("#bill #employename").text(billData.employee?.name);
      $("#bill #orderstatus").html(getStatusBadge(billData.status)); // Assuming orderstatus is a property in your billData
      // Assuming ordertype is a property in your billData
      if (billData.loaiDon === 0) {
        $("#bill #ordertype").text("tại quầy");
      } else if (billData.loaiDon === 1) {
        $("#bill #ordertype").text("Online");
      }
      // Update table with product details
      const productsTable = $("#bill #sanphaminbill");
      productsTable.empty(); // Clear existing rows
      billData.billDetail.forEach((product, index) => {
        const row = `<tr>
                  <th scope="row">${index + 1}</th>
                  <td>${product.productDetail.name}</td>
                  <td>${product.quantity}</td>
                  <td>${formatToVND(product.productDetail.price)}</td>
                  <td>${formatToVND(
                    product.quantity * product.productDetail.price
                  )}</td>
                </tr>`;
        productsTable.append(row);
      });
      $("#bill #note").text(billData.note);
      $("#bill #total-products").text(formatToVND(billData.total_money));
      $("#bill #discount").text(formatToVND(billData.reduction_amount));
      $("#bill #shipping-cost").text(formatToVND(billData.money_ship));
      $("#bill #total-price").text(formatToVND(billData.deposit));
      $("#billPrint").modal("show");
    })
    .catch((error) => {
      console.error("Error fetching bill data:", error);
    });
  // Update customer information
}
function handleOrderSuccess(idform) {
  const indexToRemove = listtab.indexOf(idform);
  stopCountdown(idform);
  removeOrder(idform);
  if (indexToRemove !== -1) {
    listtab.splice(indexToRemove, 1);
  }
  const orderbtnrmRemove = document.getElementById(`vieworder${idform}`);
  const orderToRemove = document.getElementById(`hoaDon${idform}`);
  if (orderToRemove) {
    orderToRemove.remove();
  } else {
    console.log(`Order with ID ${idform} not found.`);
  }
  if (orderbtnrmRemove) {
    orderbtnrmRemove.remove();
  } else {
    console.log(`Order with ID ${idform} not found.`);
  }
}
async function checkVoucher(formId, totalMoney) {
  const voucherSelect = document.querySelector(`#${formId} #voucher-choose`);
  const voucCherId = parseInt(voucherSelect.value);
  const discountAmount = document.querySelector(`#${formId} #discount-amount`);
  resetErrorElement(voucherSelect);
  resetErrorElement(discountAmount);
  var currentTime = new Date().getTime();
  if (voucCherId) {
    if (voucCherId != -1) {
      try {
        const response = await fetch(`/admin/counter/voucher/${voucCherId}`, {
          method: "GET",
        });
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        if (data.code == 400) {
          setErrorElement(voucherSelect, data.message);
          discountAmount.innerHTML = formatToVND(0);
          getVoucherAble(formId);
          return 0;
        } else {
          if (data.min_order > totalMoney) {
            new Notify({
              status: "warning",
              title: "Voucher lỗi !!!",
              text: "Chọn hoặc kiểm tra lại",
              effect: "fade",
              speed: 300,
              customClass: "",
              customIcon: "",
              showIcon: true,
              showCloseButton: true,
              autoclose: true,
              autotimeout: 3000,
              gap: 20,
              distance: 20,
              type: 1,
              position: "right top",
              customWrapper: "",
            });
            setErrorElement(
              voucherSelect,
              "Không đủ điều kiện để áp dụng voucher này"
            );
            discountAmount.innerHTML = formatToVND(0);
            // getVoucherAble(formId);
            return 0;
          } else if (data.quantily <= 0) {
            new Notify({
              status: "warning",
              title: "Voucher lỗi !!!",
              text: "Chọn hoặc kiểm tra lại",
              effect: "fade",
              speed: 300,
              customClass: "",
              customIcon: "",
              showIcon: true,
              showCloseButton: true,
              autoclose: true,
              autotimeout: 3000,
              gap: 20,
              distance: 20,
              type: 1,
              position: "right top",
              customWrapper: "",
            });
            setErrorElement(voucherSelect, "Voucher đã hết hết số lượng");
            discountAmount.innerHTML = formatToVND(0);
            // getVoucherAble(formId);
            return 0;
          } else if (data.end_time <= currentTime) {
            new Notify({
              status: "warning",
              title: "Voucher lỗi !!!",
              text: "Chọn hoặc kiểm tra lại",
              effect: "fade",
              speed: 300,
              customClass: "",
              customIcon: "",
              showIcon: true,
              showCloseButton: true,
              autoclose: true,
              autotimeout: 3000,
              gap: 20,
              distance: 20,
              type: 1,
              position: "right top",
              customWrapper: "",
            });
            setErrorElement(voucherSelect, "Voucher đã hết hạn sử dụng");
            discountAmount.innerHTML = formatToVND(0);
            // getVoucherAble(formId);
            return 0;
          } else if (data.status != 1) {
            new Notify({
              status: "warning",
              title: "Voucher lỗi !!!",
              text: "Chọn hoặc kiểm tra lại",
              effect: "fade",
              speed: 300,
              customClass: "",
              customIcon: "",
              showIcon: true,
              showCloseButton: true,
              autoclose: true,
              autotimeout: 3000,
              gap: 20,
              distance: 20,
              type: 1,
              position: "right top",
              customWrapper: "",
            });
            setErrorElement(voucherSelect, "Voucher không còn");
            // getVoucherAble(formId);
            discountAmount.innerHTML = formatToVND(0);
            return 0;
          }
          if (data.types == true) {
            // giam theo %
            const discountValue = (totalMoney / 100) * data.discount;
            if (discountValue < data.max_discount) {
              discountAmount.innerHTML = "-" + formatToVND(discountValue);
              return discountValue;
            } else {
              discountAmount.innerHTML = "-" + formatToVND(data.max_discount);
              return data.max_discount;
            }
          } else {
            const discountValue = data.discount;
            if (discountValue < data.max_discount) {
              discountAmount.innerHTML = "-" + formatToVND(discountValue);
              return discountValue;
            } else {
              discountAmount.innerHTML = "-" + formatToVND(data.max_discount);
              return data.max_discount;
            }
          }
        }
      } catch (error) {
        new Notify({
          status: "warning",
          title: "Voucher lỗi !!!",
          text: "Chọn hoặc kiểm tra lại",
          effect: "fade",
          speed: 300,
          customClass: "",
          customIcon: "",
          showIcon: true,
          showCloseButton: true,
          autoclose: true,
          autotimeout: 3000,
          gap: 20,
          distance: 20,
          type: 1,
          position: "right top",
          customWrapper: "",
        });
        setErrorElement(
          voucherSelect,
          "Voucher đã không hoạt động chọn voucher khác"
        );
        getVoucherAble(formId);
        discountAmount.innerHTML = formatToVND(0);
        console.error("Error fetching data:", error);
        return 0;
      }
    }
  } else {
    discountAmount.innerHTML = formatToVND(0);
    return 0;
  }
}
function getStatusBadge(status) {
  if (status == 5) {
    return '<span class="badge bg-success">Thành Công</span>';
  }
  if (status == 1) {
    return '<span class="badge text-bg-warning">Chờ xác nhận</span>';
  }
  if (status == 2) {
    return '<span class="badge text-bg-secondary">Chờ giao hàng</span>';
  }
  if (status == 3) {
    return '<span class="badge text-bg-info">Đang giao hàng</span>';
  }
  if (status == 4) {
    return '<span class="badge badge text-bg-light">Đã giao hàng</span>';
  }
  if (status == -1 || status == -2) {
    return '<span class="badge text-bg-danger">Đã Hủy</span>';
  }
  return '<span class="badge text-bg-dark">Không xác định</span>';
}
async function getProductDetails(id) {
  try {
    const response = await fetch(`/admin/counter/productDetails/${id}`);
    const data = response.json();
    return data;
  } catch (error) {
    alert("Không thể tìm thấy sản phẩm !!!");
    throw error;
  }
}
async function updateTongTien(formId) {
  const tableRows = document.querySelectorAll(
    `#hoaDon${formId} #cartTable tbody tr.table-body-row`
  );
  const finalAmount = document.querySelector(`#hoaDon${formId} #finalAmount`);
  const totalAmount = document.querySelector(`#hoaDon${formId} #totalAmount`);
  let totalMoney = 0;
  let quantityofproduct = 0;
  for (const row of tableRows) {
    const productId = row.getAttribute("idproduct");
    const quantityElement = row.querySelector(`input[name="quantity"]`);
    const productQuantity = parseInt(quantityElement.value, 10);

    if (productQuantity <= 0 || isNaN(productQuantity)) {
      alert("Số lượng sản phẩm không đúng");
      return 0;
    }
    quantityofproduct += productQuantity;
    try {
      const product = await getProductDetails(productId);
      if (product) {
        totalMoney += product.price * productQuantity;
      }
    } catch (error) {
      console.error("Error fetching or calculating total money:", error);
    }
  }
  totalAmount.innerHTML = formatToVND(totalMoney);
  if (
    parseInt(
      document.querySelector(
        `#hoaDon${formId} input[name='options-outlined']:checked`
      ).value,
      10
    ) === 1
  ) {
    try {
      await getShipCost(formId, quantityofproduct);
      const totalShip = parseInt(
        document.querySelector(`#hoaDon${formId} #total-ship`).value,
        10
      );
      if (totalShip > 0) {
        totalMoney += totalShip;
      }
    } catch {}
  }

  const discountValue = await checkVoucher(`hoaDon${formId}`, totalMoney);
  finalAmount.innerHTML = formatToVND(
    totalMoney - (isNaN(discountValue) ? 0 : discountValue)
  );
  finalPrice(formId);
}
async function getShipCost(orderId, quantity) {
  if (quantity == 0) {
    document.querySelector(`#hoaDon${orderId} #amount-ship`).innerHTML =
      "Chưa có sản phẩm nào";
    document.querySelector(`#hoaDon${orderId} #total-ship`).value = -1;
    return;
  }
  try {
    const districtInput = document.querySelector(`#hoaDon${orderId} #district`);
    const wardInput = document.querySelector(`#hoaDon${orderId} #ward`);
    const serviceInput = document.querySelector(
      `#hoaDon${orderId} #shipService`
    );
    if (!districtInput || !wardInput || !serviceInput) {
      throw new Error("Missing input values");
    }

    const districtto = parseInt(districtInput.value);
    const ward_code = String(wardInput.value);
    const service_ids = parseInt(serviceInput.value);

    if (isNaN(districtto) || isNaN(service_ids)) {
      throw new Error("Invalid input values");
    }

    const heights = 2 * quantity + 2;
    const lengths = 60;
    const weights = 200 * quantity + 120;
    const widths = 20;
    await fetch(
      "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          token: token,
        },
        body: JSON.stringify({
          service_id: service_ids,
          insurance_value: 500000,
          coupon: null,
          from_district_id: 3440,
          to_district_id: districtto,
          to_ward_code: ward_code,
          height: heights,
          length: lengths,
          weight: weights,
          width: widths,
        }),
      }
    )
      .then((res) => res.json())
      .then((data) => {
        if (data.code == 400) {
          document.querySelector(`#hoaDon${orderId} #amount-ship`).innerHTML =
            "Không tìm thấy bảng giá hợp lệ";
          document.querySelector(`#hoaDon${orderId} #total-ship`).value = -1;
        } else {
          document.querySelector(`#hoaDon${orderId} #total-ship`).value =
            data.data.total;
          document.querySelector(`#hoaDon${orderId} #amount-ship`).innerHTML =
            formatToVND(data.data.total);
        }
      })
      .catch((error) => console.error("Error:", error));
  } catch (error) {
    console.error("Error:", error);
    resetTotalShip(orderId);
    errorShip(orderId);
  }
}
function errorShip(orderId) {
  document.querySelector(`#hoaDon${orderId} #total-ship`).value = 0;
  document.querySelector(`#hoaDon${orderId} #amount-ship`).innerHTML = "Không hợp lệ ";
}
async function finalPrice(formId) {
  const tableRows = document.querySelectorAll(
    `#hoaDon${formId} #cartTable tbody tr.table-body-row`
  );
  const finalAmount = document.querySelector(`#hoaDon${formId} #final-price`);
  const remainPrice = document.querySelector(`#hoaDon${formId} #remain-price`);
  const changeAmount = document.querySelector(`#hoaDon${formId} #changeAmount`);
  const discountAmount = document.querySelector(
    `#hoaDon${formId} #discount-amount`
  );
  const transferAmount = document.querySelector(
    `#hoaDon${formId} #transfer-amount`
  );
  const surchargeAmount = document.querySelector(
    `#hoaDon${formId} #surcharge-amount`
  );

  let totalMoney = 0;
  for (const row of tableRows) {
    const productId = row.getAttribute("idproduct");
    const quantityElement = row.querySelector(`input[name="quantity"]`);
    const productQuantity = parseInt(quantityElement.value, 10);

    if (productQuantity <= 0 || isNaN(productQuantity)) {
      alert("Số lượng sản phẩm không đúng");
      return 0;
    }
    try {
      const product = await getProductDetails(productId);
      if (product) {
        totalMoney += product.price * productQuantity;
      }
    } catch (error) {
      console.error("Error fetching or calculating total money:", error);
    }
  }
  var transferAmountvl = parseInt(transferAmount.value.trim(), 10);
  var surchargeAmountvl = parseInt(surchargeAmount.value.trim(), 10);
  if (isNaN(transferAmountvl)) {
    transferAmountvl = 0;
  }
  if (isNaN(surchargeAmountvl)) {
    surchargeAmountvl = 0;
  }
  finalAmount.innerHTML = formatToVND(transferAmountvl + surchargeAmountvl);
  const discountValue = await checkVoucher(`hoaDon${formId}`, totalMoney);
  const calMoney =
    transferAmountvl +
    surchargeAmountvl +
    (isNaN(discountValue) ? 0 : discountValue) -
    totalMoney;
  if (calMoney > 0) {
    remainPrice.innerHTML = formatToVND(calMoney);
    changeAmount.innerHTML = formatToVND(0);
  } else {
    remainPrice.innerHTML = formatToVND(0);
    changeAmount.innerHTML = formatToVND(
      totalMoney -
        (transferAmountvl +
          surchargeAmountvl +
          (isNaN(discountValue) ? 0 : discountValue))
    );
  }
}
function formatToVND(amount) {
  const formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
    minimumFractionDigits: 0, // Set to 0 to display whole numbers
  });
  return formatter.format(amount);
}
async function getVoucherAble(formId) {
  const voucherSelect = document.querySelector(`#${formId} #voucher-choose`);
  voucherSelect.innerHTML = "";
  var option = document.createElement("option");
  option.text = "Chọn Voucher"; // Set the text content
  // option.disabled = true;
  option.selected = true;
  option.value = -1;
  voucherSelect.appendChild(option);
  try {
    const response = await fetch("/admin/counter/voucherAble", {
      method: "GET",
    });
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    data.forEach((product) => {
      const option = document.createElement("option");
      option.value = product.id;
      option.text = `${product.name}`;
      voucherSelect.appendChild(option);
    });
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}
async function getQrThanhToan(orderId) {}

function showModalHoaDon() {
  $("#billPrint").modal("show");
}

async function showHoaDonChoSave(hoadonid) {
  const data = await buildFormData(hoadonid);
  if (data.productViews.length == 0) {
    new Notify({
      status: "warning",
      title: "Chưa có sản phẩm nào ",
      text: "Vui lòng chọn sản phẩm để tiếp tục",
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: true,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
    return;
  }
  genhoadoncho(data);
  console.log(data);
  $("#hoadoncho").modal("show");
}
function genhoadoncho(data) {
  const modalhoadoncho = document.getElementById("hoadoncho");
  const formhoadoncho = modalhoadoncho.querySelector("#billwait");
  formhoadoncho.querySelector("#employename").textContent =
    data.employeeID === "null" ? "Not assigned" : data.employeeID;
  formhoadoncho.querySelector("#orderstatus").textContent = "Pending"; //
  const productTableBody = formhoadoncho.querySelector("#sanphaminbill");
  productTableBody.innerHTML = ""; // Clear previous entries
  data.productViews.forEach((product, index) => {
    const row = productTableBody.insertRow();
    const cellIndex = row.insertCell(0);
    const cellName = row.insertCell(1);
    const cellQuantity = row.insertCell(2);
    const cellPrice = row.insertCell(3);
    const cellTotalPrice = row.insertCell(4);

    cellIndex.textContent = index + 1;
    cellName.textContent = product.name;
    cellQuantity.textContent = product.quantity;
    cellPrice.textContent = product.price;
    cellTotalPrice.textContent = product.totalprice;
  });
  formhoadoncho.querySelector("#note").textContent =
    data.note || "No additional notes.";
  formhoadoncho.querySelector("#total-products").textContent = data.totalMoney;
}
function saveHoaDonCho() {}
async function buildFormData(formId) {
  updateTongTien(formId)
  const formData = {};
  const form = document.getElementById(`hoaDon${formId}`);
  formData.id = formId;
  const getInputValue = (selector) => form.querySelector(selector).value;

  formData.customerName = getInputValue("#tenKhachHang");
  formData.employeeID = getInputValue("select[name='employee']");
  formData.orderTypes = parseInt(
    form.querySelector("input[name='options-outlined']:checked").value,
    10
  );
  const inputCustomer = form.querySelector("#customerid");
  if (inputCustomer) {
    formData.customerID = inputCustomer.value;
  }
  formData.phoneNumber = getInputValue("#soDienThoai");
  formData.city = getInputValue("#city");
  formData.district = getInputValue("#district");
  formData.ward = getInputValue("#ward");
  formData.fullAddress = getInputValue("#FullAddress");
  formData.specificAddress = getInputValue("input#address");
  formData.transferAmount = getInputValue("input#transfer-amount");
  formData.surchargeAmount = getInputValue("input#surcharge-amount");
  formData.note = getInputValue("textarea#note");
  formData.products = [];
  formData.productViews = [];
  let totalMoney = 0;
  for (const row of form.querySelectorAll(
    "#cartTable tbody tr.table-body-row"
  )) {
    const productId = row.getAttribute("idproduct");
    const quantityElement = row.querySelector(`input[name="quantity"]`);
    const productQuantity = parseInt(quantityElement.value, 10);
    if (productQuantity <= 0 || isNaN(productQuantity)) {
      alert("Số lượng sản phẩm không đúng");
      return null; // Return null to indicate an error
    }
    try {
      const product = await getProductDetails(productId);
      if (product) {
        totalMoney += product.price * productQuantity;
        formData.products.push({
          id: parseInt(productId, 10),
          quantity: productQuantity,
        });
        formData.productViews.push({
          id: product.id,
          name: product.name,
          price: product.price,
          totalprice: product.price * productQuantity,
          quantity: productQuantity,
          firstImage: product.firstImage,
        });
      }
    } catch (error) {
      console.error("Error fetching or calculating total money:", error);
      return null; // Return null to indicate an error
    }
  }
  formData.totalMoney = totalMoney;
  return formData;
}

document.addEventListener("keydown", function (event) {
  if (event.key === "F3") {
    event.preventDefault();
    var form = document.querySelector('.taborder[style="display: block;"]');
    if (form) {
      var searchInput = form.querySelector("#searchInput");
      if (searchInput) {
        searchInput.focus();
      }
    }
  }
  if (event.key === "F9") {
    event.preventDefault();
    var form = document.querySelector('.taborder[style="display: block;"]');
    if (form) {
      var submitButton = form.querySelector('button[type="submit"]');
      if (submitButton) {
        submitButton.click();
      }
    }
  }
  if (event.key === "F10") {
    event.preventDefault();
    var form = document.querySelector('.taborder[style="display: block;"]');
    if (form) {
      var removebill = form.querySelector("#removebill");
      if (removebill) {
        removebill.click();
      }
    }
  }
  if (event.key === "F1") {
    event.preventDefault();
    addnewOrderPage();
  }
});
// search customers
async function updateSearch(formid) {
  const form = $(`#hoaDon${formid}`);
  const drownsearch = form.find(".drownsearch");
  const dropselect = form.find(`#selectkhachhang`),
    searchInp = dropselect.find("#searchtext"),
    options = drownsearch.find("#khachhangchoose");
  let searchWord = searchInp.val();
  options.empty();
  if (searchWord.length > 0) {
    let url = `/admin/counter/customers/search?searchtext=${searchWord}`;
    try {
      const response = await axios.get(url);
      const data = response.data;
      if (data.length > 0) {
        data.forEach((customer) => {
          let li = `<li class="list-group-item" onclick="updateName(${formid},'${customer.id}')">${customer.name} - ${customer.phone}</li>`;
          options.append(li);
        });
      } else {
        let li = `<p style="margin-top: 10px;">Oops! Không tìm thấy khách hàng</p>`;
        options.append(li);
      }
    } catch (error) {
      console.error(error);
    }
    drownsearch.removeClass("active");
    drownsearch.toggleClass("active");
  }
  if (searchWord.length == 0) {
    console.log("Please select");
    drownsearch.removeClass("active");
  }
}

async function updateName(formid, id) {
  const form = $(`#hoaDon${formid}`);
  const drownsearch = form.find(".drownsearch");
  const dropselect = form.find(`#selectkhachhang`);
  let url = `/admin/counter/customers/${id}`;
  console.log(url);
  try {
    const response = await axios.get(url);
    const customer = response.data;
    console.log(customer);
    drownsearch.removeClass("active");
    dropselect.empty();
    dropselect.append(`<span>${customer.name} - ${customer.phone}</span>`); // Add the country name
    dropselect.append(
      `<input type="hidden" id="customerid" value="${customer.id}">`
    ); // Add the country name
    dropselect.append(
      `<a onclick="removeSelection(this,${formid})"><i class="bi bi-x"></i></i></a>`
    );
    const updateValue = (selector, value) => {
      const input = form.find(selector);
      if (input.length) {
        input.val(value);
      }
    };

    const getInputValue = (selector) => form.find(selector).val();

    if (getInputValue("#tenKhachHang").trim().length == 0) {
      updateValue("#tenKhachHang", customer.name);
    }
    if (getInputValue("#soDienThoai").trim().length == 0) {
      updateValue("#soDienThoai", customer.phone);
    }
    if (customer.country != null) {
      getAllCustomerprovide(formid, customer.country);
      if (customer.city != null) {
        getAllCustomerDistrict(formid, customer.city);
        if (customer.wardcode != null) {
          getFullCustomerWardCode(formid, customer.wardcode);
        }
      }
    }
    updateHoaDon(formid);
  } catch (error) {
    console.error(error);
  }
}
function removeSelection(element, formid) {
  console.log(element);
  const form = $(`#hoaDon${formid}`);
  const dropselect = form.find(`#selectkhachhang`);
  form.find("input#tenKhachHang").val("");
  form.find("input#soDienThoai").val("");
  dropselect.empty(); // Clear the current content
  dropselect.append(
    `<input type="text" id="searchtext" name="query" placeholder="Chọn khách hàng" title="Enter search keyword" autocomplete="off" onkeyup="updateSearch(${formid})">`
  );
  dropselect.append(
    '<button type="button"><i class="bi bi-plus-lg" data-bs-toggle="modal" data-bs-target="#modelAddKhachHang"></i></button>'
  );
}

function addNewKhachHang(event) {
  event.preventDefault();
  var form = event.target;
  var formData = new FormData(form);
  // Validation rules
  var name = formData.get("name");
  var phone = formData.get("phone");
  resetErrorElement(form.querySelector("input[name='name']"));
  resetErrorElement(form.querySelector("input[name='phone']"));
  resetErrorElement(form.querySelector("input[name='address']"));
  var errorcount = 0;
  if (!name || name.trim() === "") {
    setErrorElement(
      form.querySelector("input[name='name']"),
      "Tên không được để trống"
    );
    errorcount++;
  }
  var phonePattern = /^[0-9]{10}$/; // Adjust this pattern to match your phone number format
  if (!phone || !phonePattern.test(phone)) {
    setErrorElement(
      form.querySelector("input[name='phone']"),
      "Số điện thoại không hợp lệ"
    );
    errorcount++;
  }
  if (errorcount > 0) {
    return false;
  }
  // Build customer object
  var customer = {
    name: formData.get("name"),
    phone: formData.get("phone"),
    address: formData.get("address"),
    gender: formData.get("gender") === "true",
    birth_date: formData.get("birth_date"),
    city: formData.get("city"),
    country: formData.get("country"),
    fulladdress: formData.get("fulladdress"),
  };
  // Convert the customer object to JSON
  var customerJson = JSON.stringify(customer);
  axios
    .post("/admin/counter/customer", customerJson, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      $("#modelAddKhachHang").modal("hide");
      new Notify({
        status: "success",
        title: "Thành công",
        text: "Thêm khách hàng mới thành công",
        effect: "fade",
        speed: 300,
        customClass: "",
        customIcon: "",
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: "right top",
        customWrapper: "",
      });
    })
    .catch((error) => {
      console.error(error);
      new Notify({
        status: "error",
        title: "Lỗi",
        text: "Thêm khách hàng mới không thành công !!",
        effect: "fade",
        speed: 300,
        customClass: "",
        customIcon: "",
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 3000,
        gap: 20,
        distance: 20,
        type: 1,
        position: "right top",
        customWrapper: "",
      });
    });
}
// get adddress of customer
async function getAllCustomerprovide(citycode, orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectCity = thisOrder.querySelector("#city");
  await fetch(
    "https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        token: token,
      },
    }
  )
    .then((res) => res.json())
    .then((data) => {
      const defaultOption = document.createElement("option");
      defaultOption.value = -1; // Set the value as needed
      defaultOption.textContent = "Chọn Tỉnh"; // Set the text content
      // Set the 'disabled' and 'selected' attributes to make it the default option
      defaultOption.disabled = true;
      defaultOption.selected = true;
      selectCity.appendChild(defaultOption);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        // option.value = options[i].ProvinceID; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].ProvinceName; // Set the text of the option
        option.setAttribute("providecode", options[i].ProvinceID);
        if (citycode == options[i].ProvinceName) {
          option.selected = true;
        }
        selectCity.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
async function getAllCustomerDistrict(districtcode, orderId) {
  const selectCity = document.querySelector(`#hoaDon${orderId} #city`);
  const selectedOption = selectCity.options[selectCity.selectedIndex];
  const customAttribute = selectedOption.getAttribute("providecode");
  const provinceid = parseInt(customAttribute);
  const selectDistrict = document.querySelector(`#hoaDon${orderId} #district`);
  await fetch(
    "https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        token: token,
      },
      body: JSON.stringify({ province_id: provinceid }),
    }
  )
    .then((res) => res.json())
    .then((data) => {
      resetDistrict(orderId);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        option.value = options[i].DistrictID; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].DistrictName; // Set the text of the option
        option.setAttribute("districtcode", options[i].DistrictID);
        if (districtcode == options[i].DistrictName) {
          option.selected = true;
        }
        selectDistrict.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
async function getFullCustomerWardCode(wardcode, orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selecteDistrict = thisOrder.querySelector("#district");
  const selectWardCode = thisOrder.querySelector("#ward");
  const selectedOption = selecteDistrict.options[selecteDistrict.selectedIndex];
  const customAttribute = selectedOption.getAttribute("districtcode");
  const districtid = parseInt(customAttribute);
  await fetch(
    "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        token: token,
      },
      body: JSON.stringify({ district_id: districtid }),
    }
  )
    .then((res) => res.json())
    .then((data) => {
      //remove all child
      resetWard(orderId);
      const options = data.data;
      for (let i = 0; i < options.length; i++) {
        const option = document.createElement("option");
        option.value = options[i].WardCode; // Set the value of the option (you can change this to any value you want)
        option.text = options[i].WardName; // Set the text of the option
        if (wardcode == options[i].WardName) {
          option.selected = true;
        }
        selectWardCode.appendChild(option); // Add the option to the select element
      }
    })
    .catch((error) => console.error("Error:", error));
}
// end
