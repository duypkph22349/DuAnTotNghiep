// 80 %
// globle Variable
const defaultImage =
  "https://res.cloudinary.com/da30qcqmf/image/upload/v1699778968/No-Image-Placeholder.svg_jlyb5v.png";
const token = "234a71c7-7b2c-11ee-af43-6ead57e9219a";
const shop_id = 4676018;
const districtform = 3440; // quận nam từ liêm
const districtto = 3308; // huyện trực ninh
const WardCodeninhcuong = "800083";
const listtab = [0];
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
  form.classList.add("taborder");
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
                                      <div>
                                          <button class="btn btn-danger" id="showTimeButton">
                                              <svg class="bi bi-bag-dash" fill="currentColor" height="16"
                                                  viewBox="0 0 16 16" width="16" xmlns="http://www.w3.org/2000/svg">
                                                  <path
                                                      d="M5.5 10a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5z"
                                                      fill-rule="evenodd"></path>
                                                  <path
                                                      d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z">
                                                  </path>
                                              </svg>
                                              Sản phẩm
                                          </button>
                                      </div>
                                      <br>
                                      <div class="product-cart">
                                          <table class="table table-hover" id="cartTable">
                                              <thead>
                                                  <tr>
                                                      <th>Tên sản phẩm</th>
                                                      <th>Số lượng</th>
                                                      <th>Tổng tiền</th>
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
                                                  class="form-control" id="searchInput" onkeyup="myFunction()"
                                                  placeholder="Tìm kiếm theo mã sản phẩm, tên sản phẩm, giá bán...."
                                                  type="text">
                                          </div>
                                          <!-- Sản phẩm-->
                                          <table class="table table-hover" id="cartTableProduct">
                                              <thead>
                                                  <tr>
                                                      <th>#</th>
                                                      <th>Tên sản phẩm</th>
                                                      <th>Code</th>
                                                      <th>Giá</th>
                                                      <th>Số lượng</th>
                                                      <th>Hoa Văn</th>
                                                      <th>màu sắc</th>
                                                      <th>Action</th>
                                                  </tr>
                                              </thead>
                                              <tbody></tbody>
                                          </table>
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
                                  <div class="processor" id="processor-insert-at">
                                      <div>Tạo lúc:</div>
                                      <div id="timeline"></div>
                                  </div>
                                  <br>
                                  <div class="processor" id="processor-assign-seller">
                                      <div id="title-assign-seller">NV xử lý:&nbsp;</div>
                                      <div style="display: flex;">
                                          <div class="processor-seller-at-shop">
                                              <div class="div-user">
                                                  <!-- Add Nhân Viên-->
                                                  <select name="employee" class="form-select form-select-sm" aria-label="Small select example" size="1">
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
                                                  id="success-outlined" name="options-outlined" type="radio">
                                              <label class="btn btn-outline-success" for="success-outlined"><svg
                                                      class="bi bi-house-door" fill="currentColor" height="16"
                                                      viewBox="0 0 16 16" width="16"
                                                      xmlns="http://www.w3.org/2000/svg">
                                                      <path
                                                          d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146ZM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5Z">
                                                      </path>
                                                  </svg> Tại quầy</label>
                                              <input type="radio" class="btn-check" name="options-outlined"  value="1"
                                                  id="danger-outlined" autocomplete="off">
                                              <label class="btn btn-outline-danger" for="danger-outlined"><svg
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
                                      <div class="col-6">
                                          <label for="birtdays" class="form-label">Ngày
                                              Sinh</label>
                                          <input type="date" class="form-control" id="birtdays"
                                              placeholder="1234 Main St">
                                      </div>
                                      <div class="col-md-6">
                                          <label for="gender" class="form-label">Giới
                                              Tính</label>
                                          <select name="gender" id="gender" class="form-control">
                                              <option value="0">Nam</option>
                                              <option value="1">Nữ</option>
                                          </select>
                                      </div>
                                      <!-- Địa chỉ -->
                                      <div class="col-md-4">
                                          <label class="form-label">Thành
                                              Phố</label>
                                          <select class="form-select" id="city" onchange="getAllDistrict(${id})">
                                              <option selected="">Chọn Thành Phố
                                              </option>
                                          </select>
                                      </div>
                                      <div class="col-md-4">
                                          <label class="form-label">Huyện</label>
                                          <select class="form-select" id="district" onchange="getFullWardCode(${id})">
                                              <option selected="">Chọn Huyện</option>
                                          </select>
                                      </div>
                                      <div class="col-md-4">
                                          <label class="form-label">Xã</label>
                                          <select class="form-select" id="ward" onchange="getFullAddress(${id})">
                                              <option selected="">Chọn Xã</option>
                                          </select>
                                      </div>
                                      <input type="hidden" name="" id="FullAddress">
                                      <div class="col-12">
                                          <label class="form-label">Địa
                                              Chỉ Cụ Thể</label>
                                          <input type="text" class="form-control" id="address" placeholder="Địa chỉ cụ thể">
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
                                  <div class="col-12">
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
                                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                  fill="currentColor" class="bi bi-bank" viewBox="0 0 16 16">
                                                  <path
                                                      d="m8 0 6.61 3h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.38l.5 2a.498.498 0 0 1-.485.62H.5a.498.498 0 0 1-.485-.62l.5-2A.501.501 0 0 1 1 13V6H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 3h.89L8 0ZM3.777 3h8.447L8 1 3.777 3ZM2 6v7h1V6H2Zm2 0v7h2.5V6H4Zm3.5 0v7h1V6h-1Zm2 0v7H12V6H9.5ZM13 6v7h1V6h-1Zm2-1V4H1v1h14Zm-.39 9H1.39l-.25 1h13.72l-.25-1Z">
                                                  </path>
                                              </svg>
                                              Tiền chuyển khoản
                                          </span>
                                          <input type="text" class="form-control" id="transfer-amount"
                                              placeholder="0₫">
                                      </div>
                                  </div>

                                  <div class="col-12">
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
                                              Phụ thu tiền mặt
                                          </span>
                                          <input type="text" class="form-control" id="surcharge-amount"
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
                                                  <div class="box-monney">
                                                      <span class="title">Tiền
                                                          Ship&nbsp;</span>
                                                      <span id="amount-ship" class="fw-500 red">0
                                                          ₫</span>
                                                  </div>
                                              </div>
                                              <div class="box-money-section s3">
                                                  <div class="box-monney"><span class="title">Cần
                                                          thanh toán</span>
                                                      <span class="fw-500" id="finalAmount">30.000
                                                          ₫</span>
                                                  </div>

                                                  <div class="box-monney"><span class="title">Tiền
                                                          khách đưa</span>
                                                      <span class="fw-500 blue" id="final-price">0
                                                          ₫</span>
                                                  </div>

                                                  <div class="box-monney"><span class="title">Còn
                                                          thiếu</span>
                                                      <span class="fw-500" id="changeAmount">30.000₫</span>
                                                  </div>
                                              </div>
                                              <div class="box-monney" style="padding-bottom: 0px;">
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
                      </section>
                  </div>
              </div>
          </div>
      </div>
      <div class="box-order-status">
          <div style="font-size: 16px;">
              <div>Cần thanh toán: <span style="font-weight: 500;" id="finalAmount2">30.000
                      ₫</span></div>
              <div>Còn thiếu: <span style="color: rgb(235, 87, 87); font-weight: 500;"
                      id="changeAmount2">30.000₫</span>
              </div>
          </div>
          <div style="display: flex;">
              <span class="order-action-button" style="margin-right: 8px;"><button type="button"
                      class="ant-btn ant-btn-primary"
                      style="font-size: 14px; height: 100%; background: rgb(250, 173, 20); border-color: rgb(250, 173, 20);"><span><span
                              role="img" aria-label="printer" class="anticon anticon-printer"><svg
                                  viewBox="64 64 896 896" focusable="false" data-icon="printer" width="1em"
                                  height="1em" fill="currentColor" aria-hidden="true">
                                  <path
                                      d="M820 436h-40c-4.4 0-8 3.6-8 8v40c0 4.4 3.6 8 8 8h40c4.4 0 8-3.6 8-8v-40c0-4.4-3.6-8-8-8zm32-104H732V120c0-4.4-3.6-8-8-8H300c-4.4 0-8 3.6-8 8v212H172c-44.2 0-80 35.8-80 80v328c0 17.7 14.3 32 32 32h168v132c0 4.4 3.6 8 8 8h424c4.4 0 8-3.6 8-8V772h168c17.7 0 32-14.3 32-32V412c0-44.2-35.8-80-80-80zM360 180h304v152H360V180zm304 664H360V568h304v276zm200-140H732V500H292v204H160V412c0-6.6 5.4-12 12-12h680c6.6 0 12 5.4 12 12v292z">
                                  </path>
                              </svg></span> In (F4)</span></button>
                  <button type="button" class="ant-btn ant-btn-primary" onclick="removeOrderPage(${id})"
                      style="font-size: 14px; height: 100%; background: rgb(248, 13, 13); border-color: rgb(250, 112, 20);"><span><span
                              role="img" aria-label="printer" class="anticon anticon-printer"><svg
                                  viewBox="64 64 896 896" focusable="false" data-icon="printer" width="1em"
                                  height="1em" fill="currentColor" aria-hidden="true">
                                  <path
                                      d="M820 436h-40c-4.4 0-8 3.6-8 8v40c0 4.4 3.6 8 8 8h40c4.4 0 8-3.6 8-8v-40c0-4.4-3.6-8-8-8zm32-104H732V120c0-4.4-3.6-8-8-8H300c-4.4 0-8 3.6-8 8v212H172c-44.2 0-80 35.8-80 80v328c0 17.7 14.3 32 32 32h168v132c0 4.4 3.6 8 8 8h424c4.4 0 8-3.6 8-8V772h168c17.7 0 32-14.3 32-32V412c0-44.2-35.8-80-80-80zM360 180h304v152H360V180zm304 664H360V568h304v276zm200-140H732V500H292v204H160V412c0-6.6 5.4-12 12-12h680c6.6 0 12 5.4 12 12v292z">
                                  </path>
                              </svg></span> Xóa (F5)</span></button>
                  <div id="order-print-div"></div>
                  <div></div>
              </span><span id="order-action-button" class="order-action-button"><button type="submit"
                      class="ant-btn ant-btn-primary" style="font-size: 14px; height: 100%;"><span><span role="img"
                              aria-label="save" class="anticon anticon-save"><svg viewBox="64 64 896 896"
                                  focusable="false" data-icon="save" width="1em" height="1em" fill="currentColor"
                                  aria-hidden="true">
                                  <path
                                      d="M893.3 293.3L730.7 130.7c-7.5-7.5-16.7-13-26.7-16V112H144c-17.7 0-32 14.3-32 32v736c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V338.5c0-17-6.7-33.2-18.7-45.2zM384 184h256v104H384V184zm456 656H184V184h136v136c0 17.7 14.3 32 32 32h320c17.7 0 32-14.3 32-32V205.8l136 136V840zM512 442c-79.5 0-144 64.5-144 144s64.5 144 144 144 144-64.5 144-144-64.5-144-144-144zm0 224c-44.2 0-80-35.8-80-80s35.8-80 80-80 80 35.8 80 80-35.8 80-80 80z">
                                  </path>
                              </svg></span> Thanh Toán</span></button>
                  <div></div>
              </span>
          </div>
      </div>
  </div>
</div>
`;
  return form;
}
// ui processtion
function addnewOrderPage() {
  const id = Math.max(...listtab) + 1;
  listtab.push(id);
  renderOrderPage(id);
  getAllprovide(id);
  fillAllEmployee(id);
  getFirstProductPage(id);
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
function removeOrderPage(orderId) {
  if (confirm("Bạn Muốn xóa sáo")) {
    const orderbtnRemove = document.getElementById(`hd${orderId}`);
    const orderbtnrmRemove = document.getElementById(`vieworder${orderId}`);
    const orderToRemove = document.getElementById(`hoaDon${orderId}`);
    if (orderToRemove) {
      orderToRemove.remove();
    } else {
      console.log(`Order with ID ${orderId} not found.`);
    }
    if (orderbtnrmRemove) {
      orderbtnrmRemove.remove();
    } else {
      console.log(`Order with ID ${orderId} not found.`);
    }
    if (orderbtnRemove) {
      orderbtnRemove.remove();
    } else {
      console.log(`Order with ID ${orderId} not found.`);
    }
  }
}

function findProdcut(orderId) {}

//calldata
function getProductDetail(id) {
  const products = {
    cc1: { stt: 1, code: "Jaclyn-XL-Melessa", gia: 43658.0 },
    cc2: { stt: 2, code: "Jaclyn-XL-Stacia", gia: 24849.0 },
  };
  return products[id];
}
// address, shipcode
function updateTotalPrirce(orderId) {}
function updateshipService(orderId) {}
function updateProvice(orderId) {}
function updateDistric(orderId) {}
function updateWardcode(orderId) {}
function getTotalShip(orderId) {}

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
      defaultOption.value = ""; // Set the value as needed
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
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectCity = thisOrder.querySelector("#city");
  const selectedOption = selectCity.options[selectCity.selectedIndex];
  const customAttribute = selectedOption.getAttribute("providecode");
  const provinceid = parseInt(customAttribute);
  const selectDistrict = thisOrder.querySelector("#district");
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
  console.log(fullAdress);
}
function resetDistrict(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const selectProvide = thisOrder.querySelector("#district");
  while (selectProvide.firstChild) {
    selectProvide.removeChild(selectProvide.firstChild);
  }
  const defaultOption = document.createElement("option");
  defaultOption.value = ""; // Set the value as needed
  defaultOption.textContent = "Chọn Quận/ Huyện"; // Set the text content
  defaultOption.disabled = true;
  defaultOption.selected = true;
  selectProvide.appendChild(defaultOption);
  resetWard(orderId);
}
function resetWard(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  const wardSelect = thisOrder.querySelector("#ward");
  while (wardSelect.firstChild) {
    wardSelect.removeChild(wardSelect.firstChild);
  }
  const defaultOption = document.createElement("option");
  defaultOption.value = ""; // Set the value as needed
  defaultOption.textContent = "Chọn Thị Trấn/ Xã/ Phường"; // Set the text content
  defaultOption.disabled = true;
  defaultOption.selected = true;
  wardSelect.appendChild(defaultOption);
}
function fillAllEmployee(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  var select = thisOrder.querySelector('select[name="employee"]');
  fetch("/rest/data/counter/customers", {
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
function getProduct(event, orderId) {}
function getFirstProductPage(orderId) {
  const thisOrder = document.getElementById(`hoaDon${orderId}`);
  var tbody = thisOrder.querySelector("#cartTableProduct tbody");

  fetch("/rest/data/counter/productDetails", {
    method: "GET",
  })
    .then((res) => res.json())
    .then((data) => {
      data.forEach(function (product) {
        var row = document.createElement("tr");
        var cells = [
          `<img src="${
            product.imageUrl || defaultImage
          }" class="image-fluid" style="height: 60px;">`,
          product.name,
          product.code,
          product.price,
          product.quantity,
          product.idPattern.name,
          product.idColor.name,
          `<button type="button" class="btn btn-primary"  onclick="openProductModal(${product.id})"><i class="far fa-eye"></i></button>
          <button type="button" class="btn btn-primary"  onclick="addProductIntoOrder(${orderId},${product.id})"><i class="far fa-plus"></i></button>
          `,
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
function openProductModal(id) {
  var product;
  fetch(`/rest/data/counter/productDetails/${id}`, {
    method: "GET",
  })
    .then((res) => res.json())
    .then((data) => {
      product = data;
    });
  var modalTitle = document.getElementById("exampleModalLabel");
  var modalBody = document.querySelector(".modal-body");
  modalTitle.textContent = product.name;
  var modalBodyContent = `
      <img src="${
        product.imageUrl || defaultImage
      }" class="img-fluid mb-3" alt="${product.name}">
      <p><strong>Price:</strong> ${product.price}</p>
      <p><strong>Quantity:</strong> ${product.quantity}</p>
      <p><strong>Description:</strong> ${product.description}</p>
      <p><strong>Material:</strong> ${product.material}</p>
      <p><strong>Size:</strong> ${product.size}</p>
      <p><strong>Brand:</strong> ${product.brand}</p>
      <p><strong>Note:</strong> ${product.note}</p>
    `;
  modalBody.innerHTML = modalBodyContent;
  var productModal = new bootstrap.Modal(
    document.getElementById("exampleModal")
  );
  productModal.show();
}

function renderCounterPage() {
  addnewOrderPage();
}
renderCounterPage();
async function addProductIntoOrder(idorderdetail, id) {
  const form = document.getElementById(`hoaDon${idorderdetail}`);

  try {
    const product = await getProductDetails(id);
    const productExists = await findInExitOrder(idorderdetail, product.id);

    if (productExists !== null) {
      increaseProductQuantity(productExists);
    } else {
      const productsOnOrder = form.querySelector("#cartTable tbody");
      const newProductRow = createProductRow(product, idorderdetail);
      productsOnOrder.appendChild(newProductRow);
    }
  } catch (error) {
    console.error(error);
  }
}

function createProductRow(product, idorderdetail) {
  const newProductRow = document.createElement("tr");
  newProductRow.classList.add("table-body-row", "order-product");
  newProductRow.setAttribute("idproduct", product.id);
  newProductRow.innerHTML = `
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td><input name="quantity" type="number" value=1 disabled></td>
        <td>
          <button type="button" class="btn btn-danger" onclick="removeProduct(${idorderdetail}, ${product.id})">Remove</button>
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

function increaseProductQuantity(productExists) {
  if (productExists instanceof Element) {
    const inputNumber = productExists.querySelector("td:nth-child(3) input");
    if (inputNumber instanceof Element) {
      const currentValue = parseInt(inputNumber.value, 10) || 0;
      inputNumber.value = currentValue + 1;
    } else {
      console.error("Input number element not found.");
    }
  } else {
    console.error("Product element not found.");
  }
}

async function getProductDetails(id) {
  try {
    const response = await fetch(`/rest/data/counter/productDetails/${id}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}

function handleOrderSubmit(event) {
  event.preventDefault();
  const formId = event.currentTarget.id;
  const formValuesJSON = getOrderDatailForm(formId);
  console.log(formValuesJSON);
  return false;
}
function getOrderDatailForm(formId) {
  const formData = {};
  const form = document.getElementById(formId);
  const carttable = form.querySelector("#cartTable tbody");
  const tableRows = carttable.querySelectorAll("tr.table-body-row");
  formData.products = [];
  tableRows.forEach((row) => {
    const productId = row.getAttribute("idproduct");
    const quantityElement = row.querySelector(`input[name="quantity"]`);
    const productQuantity = quantityElement.value;
    const productData = {
      id: productId,
      quantity: productQuantity,
    };
    formData.products.push(productData);
  });
  formData.customerName = form.querySelector("#tenKhachHang").value;
  formData.employeeID = form.querySelector("select[name='employee']").value;
  formData.orderTypes = form.querySelector(
    "input[name='options-outlined']:checked"
  ).value;
  formData.phoneNumber = form.querySelector("#soDienThoai").value;
  formData.birthdate = form.querySelector("#birtdays").value;
  formData.gender = form.querySelector("#gender option:checked").value;
  formData.city = form.querySelector("#city").value;
  formData.district = form.querySelector("#district").value;
  formData.ward = form.querySelector("#ward").value;
  formData.fullAddress = form.querySelector("#FullAddress").value;
  formData.specificAddress = form.querySelector("input#address").value;
  return JSON.stringify(formData);
}