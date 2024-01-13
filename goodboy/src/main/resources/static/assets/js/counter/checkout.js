const token = "234a71c7-7b2c-11ee-af43-6ead57e9219a";
const shop_id = 4676018;
const districtform = 3440; // quận nam từ liêm
const districtto = 3308; // huyện trực ninh
const WardCodeninhcuong = "800083";
const selectCity = document.querySelector("#city");
const thisOrder = document.getElementById(`hoaDon${orderId}`);
const districtSelect = document.querySelector("#district");
const selectWardCode = document.querySelector("#ward");
function updateshipService(orderId) {
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
  const selectedOption = districtSelect.options[districtSelect.selectedIndex];
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
