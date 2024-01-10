const totalMoney = document.getElementById("TotalMoney");
const carttable = document.getElementById("carttable");
const billApi = "/shop/order";
function selectAll(checked) {
  console.log(" All selected are: " + checked);
  // Add your logic here to handle the checkbox state
  document
    .querySelectorAll("input[type=checkbox].pdselect")
    .forEach(function (input) {
      input.checked = checked;
    });
  updateTotalMoney();
}
function selectProductDetails() {
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
  updateTotalMoney();
}
//   function checkoutpage() {
//   const CartDetailIds = [];
//   document
//     .querySelectorAll("input[type=checkbox].pdselect:checked")
//     .forEach((element) => {
//       if(element.getAttribute("id-cart-detail") === null
//           || element.getAttribute("id-cart-detail") === undefined
//           || element.getAttribute("id-cart-detail") === ""
//       ){
//         CartDetailIds.push(element.getAttribute("id-product-detail"));
//       }else{
//         CartDetailIds.push(element.getAttribute("id-cart-detail"));
//       }
//     });
//     console.log(CartDetailIds)
//   // Check if any items are selected
//   if (CartDetailIds.length > 0) {
//     const queryParams = "carts=" + CartDetailIds.join("&carts=");
//     const checkoutUrl = `${billApi}/checkout?` + queryParams;
//
//     // Redirect to the checkout URL
//     window.location.href = checkoutUrl;
//   } else {
//     // Handle the case where no items are selected
//     alert("Vui lòng chọn một sản phẩm để tiếp tục thanh toán.");
//   }
// }
async function updateQuantity(element) {
  const quantity = element.value;
  const idCartDetails = element.getAttribute("id-cartdetails");
  console.log("idCartDetails: " + idCartDetails);
  console.log("quantity: " + quantity);
  if (quantity <= 0) {
    deletedCart(idCartDetails);
  }else{
    await updateCartDetail(idCartDetails, quantity);
    await updateCartDetailUI(await getCartDetail(idCartDetails));
  }
  updateTotalMoney();
}

async function deletedCart(idcartdetail) {
  if (confirm("Bạn chắc chắn có muốn xóa không ?")) {
    await deleteCartDetail(idcartdetail);
    updateTotalMoney();
  }
}
function deletedCartUI(idcartdetail) {
  const cartdetailDiv = carttable.querySelector(`#cartdetail${idcartdetail}`);
  if (cartdetailDiv) {
    cartdetailDiv.remove();
  }
}
async function updateCartDetailUI(cartdetail) {
  const cartdetailDiv = carttable.querySelector(`#cartdetail${cartdetail.id}`);
  console.log(cartdetailDiv);
  if (cartdetailDiv) {
    const totalMoney = cartdetailDiv.querySelector(".totalMoney");
    const quantityinput = cartdetailDiv.querySelector(".quantityinput");
    quantityinput.value = cartdetail.quantity;
    totalMoney.innerHTML = formatToVND(cartdetail.totalMoeny);
  }
}
// Import Axios (make sure to include Axios in your project)

// Replace 'YOUR_API_BASE_URL' with the actual base URL of your Spring Boot application
var API_BASE_URL = "/test/api/cart";
const API_BASE_COOKIE_URL = "/test/api/cart/cookie";
// Function to add an item to the cart
const addToCart = async (productId, quantity) => {
  try {
    const response = await axios.put(
      `${API_BASE_URL}/add/${productId}?quantity=${quantity}`
    );

    await get_quantity_of_cart()
    // Handle the response as needed
  } catch (error) {
    console.error(
      "Error adding item to the cart:",
      error.response ? error.response.data : error.message
    );
    // Handle the error as needed
  }
};

const updateCartDetail = async (cartDetailId, newQuantity) => {
  try {
    const response = await axios.patch(
      `${API_BASE_URL}/update/${cartDetailId}/quantity/${newQuantity}`
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

const deleteCartDetail = async (cartDetailId) => {
  try {
    const response = await axios.delete(
      `${API_BASE_URL}/delete/${cartDetailId}`
    );
    deletedCartUI(cartDetailId);
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
const getCartDetail = async (cartDetailId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/cart/${cartDetailId}`);
    return response.data;
  } catch (error) {}
};
const updateTotalMoney = async () => {
  localStorage.setItem("type_cart", "login")
  const CartDetailIds = [];
  document
    .querySelectorAll("input[type=checkbox].pdselect:checked")
    .forEach((element) => {
      CartDetailIds.push(element.getAttribute("id-cart-detail"));
    });

  // Check if any items are selected
  if (CartDetailIds.length > 0) {
    const queryParams = "carts=" + CartDetailIds.join("&carts=");
    const totalMoneyurl = "totalmoney?" + queryParams;
    try {
      const response = await axios.get(`${API_BASE_URL}/${totalMoneyurl}`);
      document.getElementById("TotalMoney").innerHTML = formatToVND(response.data);
    } catch (error) {}
  } else {
    // Handle the case where no items are selected
    document.getElementById("TotalMoney").innerHTML = formatToVND(0);
  }
};
selectProductDetails();

const get_quantity_of_cart = () => {
  // UPDATE QUANTITY
  var quantity = 0;
  axios.get(
      `/client/cart/quantity`
  ).then((e) =>{
    console.log(e)
    quantity = e.data
  })
  setTimeout(() => {
    document.querySelector("#quantity").innerHTML = "(" + quantity + " sản phẩm)"
  }, 100)
}

// COOKIE

const cartcookie = document.getElementById("cartcookie");
const TotalCookieMoney = cartcookie.querySelector("#TotalCookieMoney");
const cartcookietable = cartcookie.querySelector("#cartcookietable");

async function updateQuantityCookie(element) {
  const quantity = element.value;
  const idProductdetails = element.getAttribute("id-productdetails");
  console.log("id-productdetails: " + idProductdetails);
  console.log("quantity: " + quantity);
  if (quantity <= 0) {
    deletedCartCookie(idProductdetails);
  }else{
    await updateCartDetailCookie(idProductdetails, quantity);
    await updateCartDetailUICookie(await getCartDetailCookie(idProductdetails));
  }
  updateTotalCookieMoney();
}

async function deletedCartCookie(idcartdetail) {
  if (confirm("Bạn chắc chắn có muốn xóa không ?")) {
    await deleteCartDetailCookie(idcartdetail);
    updateTotalCookieMoney();
    get_quantity_of_cart()
  }
}

const updateCartDetailCookie = async (cartDetailId, newQuantity) => {
  console.log(newQuantity)
  try {
    const response = await axios.patch(
        `${API_BASE_COOKIE_URL}/update/${cartDetailId}/quantity/${newQuantity}`
    );
    console.log(response)
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

const updateTotalCookieMoney = async () => {
  const CartDetailIds = [];
  localStorage.setItem("type_cart", "no_login")
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

async function updateCartDetailUICookie(cartdetail) {
  console.log(cartdetail)
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

const getCartDetailCookie = async (cartDetailId) => {
  try {
    const response = await axios.get(
        `${API_BASE_COOKIE_URL}/cart/${cartDetailId}`
    );
    return response.data;
  } catch (error) {}
};
//done

function selectProductDetailsCookie() {
  if (cartcookietable) {
    if (
        document.querySelectorAll("input[type=checkbox].pdselect").length ===
        document.querySelectorAll("input[type=checkbox].pdselect:checked").length
    ) {
      document.querySelector("input[type=checkbox]#selectAll").checked = true;
    } else {
      document.querySelector("input[type=checkbox]#selectAll").checked = false;
    }
    updateTotalCookieMoney();
  }
}
//done

function formatToVND(amount) {
  const formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
    minimumFractionDigits: 0, // Set to 0 to display whole numbers
  });
  return formatter.format(amount);
}

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

function checkoutpage() {
  const CartDetailIds = [];
  document
      .querySelectorAll("input[type=checkbox].pdselect:checked")
      .forEach((element) => {
        if(element.getAttribute("id-cart-detail") === null
            || element.getAttribute("id-cart-detail") === undefined
            || element.getAttribute("id-cart-detail") === ""
        ){
          CartDetailIds.push(element.getAttribute("id-product-detail"));
        }else{
          CartDetailIds.push(element.getAttribute("id-cart-detail"));
        }
      });
  // Check if any items are selected
  if (CartDetailIds.length > 0) {
    const queryParams = "carts=" + CartDetailIds.join("&carts=");
    const checkoutUrl = `${billApi}/checkout?` + queryParams;

    // getBill
    axios.get("/client/bill/checkout?" + queryParams).then(
        e => {
          localStorage.setItem("bill", JSON.stringify(e.data));
          localStorage.setItem("cart_details", JSON.stringify(CartDetailIds));
          setTimeout(() => {
            window.location.href = checkoutUrl;
          }, 400)
        }
    ).catch(error => {
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
    })
    // Redirect to the checkout URL
  } else {
    // Handle the case where no items are selected
    alert("Vui lòng chọn một sản phẩm để tiếp tục thanh toán.");
  }
}

