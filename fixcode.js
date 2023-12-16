const phoneNumberRegex = /^(09|\d{2}[2-9])\d{7}$/;

async function handleOrderSubmit(event) {
  event.preventDefault();
  const formId = event.currentTarget.id;
  const idform = event.currentTarget.getAttribute("idofform");
  var errorCount = 0;
  const formData = {};
  const form = document.getElementById(formId);
  formData.employeeID = form.querySelector("select[name='employee']").value;
  formData.orderTypes = parseInt(
    form.querySelector("input[name='options-outlined']:checked").value,
    10
  );
  formData.customerName = form.querySelector("#tenKhachHang").value;
  formData.phoneNumber = form.querySelector("#soDienThoai").value;
  formData.city = form.querySelector("#city").value;
  formData.district = form.querySelector("#district").value;
  formData.ward = form.querySelector("#ward").value;
  formData.fullAddress = form.querySelector("#FullAddress").value;
  formData.specificAddress = form.querySelector("input#address").value;
  formData.note = form.querySelector("textarea#note").value;
  formData.voucherid = parseInt(
    form.querySelector("#voucher-choose").value,
    10
  );
  resetErrorElement(form.querySelector("#tenKhachHang"));
  resetErrorElement(form.querySelector("#soDienThoai"));
  resetErrorElement(form.querySelector("#city"));
  resetErrorElement(form.querySelector("#district"));
  resetErrorElement(form.querySelector("#ward"));
  resetErrorElement(form.querySelector("#FullAddress"));
  resetErrorElement(form.querySelector("#input#address"));
  resetErrorElement(form.querySelector("select[name='employee']"));
  if (formData.employeeID == "null") {
    errorCount++;
    setErrorElement(
      form.querySelector("select[name='employee']"),
      "Employee cần được chọn!!!"
    );
  }
  let totalMoney = 0;
  formData.products = Array.from(
    form.querySelectorAll("#cartTable tbody tr.table-body-row")
  ).map((row) => {
    const productId = row.getAttribute("idproduct");
    const quantityElement = row.querySelector(`input[name="quantity"]`);
    const productQuantity = parseInt(quantityElement.value, 10);
    resetErrorElement(row);
    if (productQuantity < 0 || productQuantity.isNaN()) {
      errorCount++;
      setErrorElement(row, "Số lượng sản phẩm không đúng");
    } else {
      const product = getProductDetails(productId);
      if (product) {
        totalMoney += product.price * productQuantity;
      } else {
        errorCount++;
        setErrorElement(row, "Sản phẩm có vấn đề!!!");
      }
    }
    return {
      id: parseInt(productId, 10),
      quantity: productQuantity,
    };
  });

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
  const discountValue = await checkVoucher(formId, totalMoney);
  formData.totalMoney = totalMoney - discountValue;
  formData.reductionAmount = 0;
  if (formData.orderTypes === 0) {
    formData.totalShip = form.querySelector(`#total-ship`).value;
    if (
      !isNaN(formData.cashMoney) &&
      !isNaN(formData.transferMoney) &&
      !isNaN(formData.totalMoney)
    ) {
      const allmoney = formData.cashMoney + formData.transferMoney;
      if (allmoney < formData.totalMoney) {
        errorCount++;
        // ("Tiền chưa đủ !!! \n");
      }
    }
  } else if (formData.orderTypes === 1) {
    formData.totalShip = 0;
    if (formData.customerName.trim().length == 0) {
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
    if (formData.district.trim() === "") {
      errorCount++;
      setErrorElement(
        form.querySelector("#district"),
        "Thành Phố không được thiếu !!!"
      );
    }
    if (formData.ward.trim() === "") {
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
    if (formData.totalShip < 0) {
      errorCount++;
      setErrorElement(
        form.querySelector(`#total-ship`),
        "Xem lại hình thức vận chuyển !!!\n"
      );
    }
  }
  if (errorCount > 0) {
  } else {
    if (confirm("Xác Nhận Thanh Toán")) {
      thanhtoan(JSON.stringify(formValuesJSON), idform);
    }
  }
}
async function thanhtoan(formValuesJSON, idform) {
  try {
    const response = await axios.post(
      "/rest/data/counter/checkout",
      formValuesJSON,
      {
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    );
    const data = response.data;
    if (data?.status === "BAD_REQUEST") {
      alert(data?.message);
      return;
    } else {
      if (confirm("Bạn có muốn in hoá đơn không?")) {
        handleOrderSuccess(idform);
      }
    }
  } catch (error) {
    console.error("Error:", error);
  }
}
function handleOrderSuccess(idform) {
  const indexToRemove = listtab.indexOf(idform);
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
  if (voucCherId != -1) {
    try {
      const response = await fetch(`/rest/data/counter/voucher/${voucCherId}`, {
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
      } else {
        if (data.min_order > totalMoney) {
          setErrorElement(
            voucherSelect,
            "Không đủ điều kiện để áp dụng voucher này"
          );
          getVoucherAble(formId);
          return 0;
        }
        if (data.quantily <= 0) {
          setErrorElement(voucherSelect, "Voucher đã hết hết số lượng");
          getVoucherAble(formId);
          return 0;
        }
        var currentTime = new Date().getTime();
        if (data.end_time <= currentTime) {
          setErrorElement(voucherSelect, "Voucher đã hết hạn sử dụng");
          getVoucherAble(formId);
          return 0;
        }
        if (data.status != 1) {
          setErrorElement(voucherSelect, "Voucher không còn");
          getVoucherAble(formId);
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
      setErrorElement(
        voucherSelect,
        "Voucher đã không hoạt động chọn voucher khác"
      );
      console.error("Error fetching data:", error);
    }
  } else {
    discountAmount.innerHTML = formatToVND(0);
    return 0;
  }
}
