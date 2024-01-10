const cartuser = document.getElementById("cartuser");
const cartcookie = document.getElementById("cartcookie");
var totalMoney = document.getElementById("TotalMoney");
const TotalCookieMoney = cartcookie.querySelector("#TotalCookieMoney");
const cartcookietable = cartcookie.querySelector("#cartcookietable");
const billApi = "/shop/order";
function selectCookieAll(checked) {
  console.log(" All selected are: " + checked);
  // Add your logic here to handle the checkbox state
  document
    .querySelectorAll("input[type=checkbox].pdselect")
    .forEach(function (input) {
      input.checked = checked;
    });
  updateTotalCookieMoney();
}
//done
function selectProductDetailsCookie() {
  if (cartcookietable) {
    if (
      document.querySelectorAll("input[type=checkbox].pdselect").length ===
      document.querySelectorAll("input[type=checkbox].pdselect:checked").length
    ) {
      document.querySelector("input[type=checkbox]#selectAll").checked = true;
      console.log("select all: yes ser");
    } else {
      document.querySelector("input[type=checkbox]#selectAll").checked = false;
      console.log("select all: no ser");
    }
    updateTotalCookieMoney();
  }
}
//done


async function deletedCartCookie(idcartdetail) {
  if (confirm("Bạn chắc chắn có muốn xóa không ?")) {
    await deleteCartDetailCookie(idcartdetail);
    updateTotalCookieMoney();
    get_quantity_of_cart()
  }
}
//done

function deletedCartUICookie(idcartdetail) {
  const cartdetailDiv = cartcookietable.querySelector(
    `#productdetail${idcartdetail}`
  );
  if (cartdetailDiv) {
    cartdetailDiv.remove();
    get_quantity_of_cart()
  }
}
//done

async function updateCartDetailUICookie(cartdetail) {
  const cartdetailDiv = cartcookietable.querySelector(
    `#productdetail${cartdetail.productDetaill.id}`
  );
  console.log(cartdetailDiv);
  if (cartdetailDiv) {
    const totalMoney = cartdetailDiv.querySelector(".totalMoney");
    const quantityinput = cartdetailDiv.querySelector(".quantityinput");
    quantityinput.value = cartdetail.quantity;
    get_quantity_of_cart()
    totalMoney.innerHTML = formatToVND(
      cartdetail.quantity * cartdetail.productDetaill.price
    );
  }
}
//done
// Replace 'YOUR_API_BASE_COOKIE_URL' with the actual base URL of your Spring Boot application
var API_BASE_COOKIE_URL = "/test/api/cart/cookie";
//done

const updateCartDetailCookie = async (cartDetailId, newQuantity) => {
  try {
    const response = await axios.patch(
      `${API_BASE_COOKIE_URL}/update/${cartDetailId}/quantity/${newQuantity}`
    );
    await get_quantity_of_cart()
    new Notify({
      status: "success",
      title: "Thành công",
      text: "Cập nhật thông tin giỏ hàng thành công",
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: false,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
    return response.data;
  } catch (error) {
    new Notify({
      status: "error",
      title: "Thêm thất bại",
      text: error.response ? error.response.data : error.message,
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: false,
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
};
//done

const deleteCartDetailCookie = async (cartDetailId) => {
  try {
    const response = await axios.delete(
      `${API_BASE_COOKIE_URL}/delete/${cartDetailId}`
    );
    deletedCartUICookie(cartDetailId);
    await get_quantity_of_cart()
    new Notify({
      status: "success",
      title: "Thành công",
      text: "Cập nhật thông tin giỏ hàng thành công",
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: false,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
  } catch (error) {
    new Notify({
      status: "error",
      title: "Thêm thất bại",
      text: error.response ? error.response.data : error.message,
      effect: "fade",
      speed: 300,
      customClass: "",
      customIcon: "",
      showIcon: true,
      showCloseButton: false,
      autoclose: true,
      autotimeout: 3000,
      gap: 20,
      distance: 20,
      type: 1,
      position: "right top",
      customWrapper: "",
    });
  }
};
//done

const getCartDetailCookie = async (cartDetailId) => {
  try {
    const response = await axios.get(
      `${API_BASE_COOKIE_URL}/cart/${cartDetailId}`
    );
    return response.data;
  } catch (error) {}
};
//done

const updateTotalCookieMoney = async () => {
  const CartDetailIds = [];
  document
    .querySelectorAll("input[type=checkbox].pdselect:checked")
    .forEach((element) => {
      CartDetailIds.push(element.getAttribute("id-product-detail"));
    });

  // Check if any items are selected
  if (CartDetailIds.length > 0) {
    const queryParams = "carts=" + CartDetailIds.join("&carts=");
    const totalMoneyurl = "totalmoney?" + queryParams;
    try {
      const response = await axios.get(
        `${API_BASE_COOKIE_URL}/${totalMoneyurl}`
      );
      await get_quantity_of_cart()
      TotalCookieMoney.innerHTML = formatToVND(response.data);
    } catch (error) {}
  } else {
    // Handle the case where no items are selected
    TotalCookieMoney.innerHTML = formatToVND(0);
  }
};
//done
function checkoutpagecookie() {
  const CartDetailIds = [];
  document
    .querySelectorAll("input[type=checkbox].pdselect:checked")
    .forEach((element) => {
      CartDetailIds.push(element.getAttribute("id-cart-detail"));
    });

  // Check if any items are selected
  if (CartDetailIds.length > 0) {
    const queryParams = "carts=" + CartDetailIds.join("&carts=");
    const checkoutUrl = `${billApi}/checkout?` + queryParams;
    // Redirect to the checkout URL
    window.location.href = checkoutUrl;
  } else {
    // Handle the case where no items are selected
    alert("Please select items for checkout.");
  }
}

const get_quantity_of_cart = () => {
  // UPDATE QUANTITY
  var quantity = 0;
  axios.get(
      `/client/cart/quantity`
  ).then((e) =>{
    quantity = e.data
  })
  setTimeout(() => {
    document.querySelector("#quantity").innerHTML = "(" + quantity + " sản phẩm)"
  }, 100)
}
