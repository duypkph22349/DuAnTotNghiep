// sys data on local storage;
// Assume there is a global variable to store orders
function loadOrdersFromLocalStorage() {
  const storedOrders = sessionStorage.getItem("orders");
  return storedOrders ? JSON.parse(storedOrders) : null;
}
let orders = loadOrdersFromLocalStorage() || [];
function updateOrder(newData) {
  console.log("uodae");
  const orderIndex = orders.findIndex((order) => order.id === newData.id);
  console.log(newData);
  if (orderIndex !== -1) {
    // Update existing order
    orders[orderIndex] = { ...orders[orderIndex], ...newData };
    console.log("Order updated:", orders[orderIndex]);
  } else {
    // Add new order
    orders.push(newData);
    console.log("New order added:", orders[orders.length - 1]);
  }
  // Save updated orders to sessionStorage
  saveOrdersToLocalStorage();
}
async function updateHoaDon(id) {
  try {
    console.log("Updating order...");

    // Step 1: Retrieve form data
    const data = await buildFormData(id);
    console.log("Form Data:", data);

    // Step 2: Update order
    updateOrder(data);
    console.log("Order updated successfully");

    // Step 3: Save orders to local storage
    saveOrdersToLocalStorage();
    console.log("Orders saved to local storage");

    console.log("Update successful");
  } catch (error) {
    console.error("Error during update:", error);
  }
}
// Sample implementation of updateOrder and buildFormData functions

function removeOrder(idhoadon) {
  // Assuming you have a function to remove an order by its ID
  console.log(idhoadon);
  deleteOrder(idhoadon);
  console.log("removeOrder successfully");
}

function addSanPham(idhoadon, idproduct) {
  // Assuming you have a function to add a product to an order
  addProductToOrder(idhoadon, idproduct);
  console.log(`Add Product ${idproduct} successfully`);
}

function removeSanPham(idhoadon, idproduct) {
  // Assuming you have a function to remove a product from an order
  removeProductFromOrder(idhoadon, idproduct);
  console.log(`Remove Product ${idproduct} successfully`);
}

function restoreOrderPage() {
  console.log("Processing restore order...");
  // Assuming you have a function to restore the order page to its initial state
  orders.forEach((element) => {
    const id = element.id;
    renderOrderPage(id); // bor qua
    restoreInformation(element);
    listtab.push(id);
    getAllprovide(id);
    fillAllEmployee(id);
    getFirstProductPage(id);
    countdown(id);
    getVoucherAble(`hoaDon${id}`);
    const form = document.getElementById(`hoaDon${id}`);
    const productsOnOrder = form.querySelector("#cartTable tbody");
    element.productViews.forEach((product) => {
      productsOnOrder.appendChild(restoreProductOrder(id, product));
    });
    updateTongTien(id);
  });
  selectLastHoaDon();
}
function restoreInformation(element) {
  const form = document.getElementById(`hoaDon${element.id}`);
  const updateValue = (selector, value) => {
    const input = form.querySelector(selector);
    if (input) {
      input.value = value;
    }
  };
  if (element.customerID) {
    updateName(element.id, element.customerID);
  }
  updateValue("#tenKhachHang", element.customerName);
  updateValue("#soDienThoai", element.phoneNumber);
  updateValue("select[name='employee']", element.employeeID);
  updateValue("input[name='options-outlined']", element.orderTypes);
  //   updateValue("#city", element.city);
  //   updateValue("#district", element.district);
  //   updateValue("#ward", element.ward);
  //   updateValue("#FullAddress", element.fullAddress);
  updateValue("input#address", element.specificAddress);
  updateValue("input#transfer-amount", element.transferAmount);
  updateValue("input#surcharge-amount", element.surchargeAmount);
  updateValue("textarea#note", element.note);
}

function deleteOrder(orderId) {
  orderId = parseInt(orderId, 10);
  orders = orders.filter((order) => order.id !== orderId);
  saveOrdersToLocalStorage();
}
function addProductToOrder(orderId, productId) {
  const orderIndex = orders.findIndex((order) => order.id === orderId);
  if (orderIndex !== -1) {
    // Assuming an order has a property 'products' which is an array
    orders[orderIndex].products.push(productId);
    saveOrdersToLocalStorage();
  }
}
function saveOrdersToLocalStorage() {
  sessionStorage.setItem("orders", JSON.stringify(orders));
}
function restoreProductOrder(idorderdetail, product) {
  const newProductRow = document.createElement("tr");
  newProductRow.classList.add("table-body-row", "order-product");
  newProductRow.setAttribute("idproduct", product.id);
  newProductRow.innerHTML = `
          <td><strong>Tên:</strong> ${product.name} <strong>Hoa văn:</strong> ${
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
restoreOrderPage();
