const totalSalesProduct = document.getElementById("totalSalesProduct");
const totalSalesIncome = document.getElementById("totalSalesIncome");
const totalSalesBill = document.getElementById("totalSalesBill");
const resentBillTable = document.getElementById("resentBillTable");
const topProductSales = document.getElementById("topProductSales");
function formatAsVND(amount) {
  const formatter = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  });

  return formatter.format(amount);
}
const filtertotalSalesProduct = document.getElementById(
  "filterTotalSalesProduct"
);
const filtertotalSalesIncome = document.getElementById(
  "filterTotalSalesIncome"
);
const filtertotalSalesBill = document.getElementById("filterTotalSalesBill");
const filterresentBillTable = document.getElementById("filterResentBillTable");
const filtertopProductSales = document.getElementById("filterTopProductSales");
// Today
async function getToDayTotalProductSale() {
  try {
    const response = await fetch("/api/thongke/todaytotalproductsale");
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
async function getToDayToTalIncome() {
  try {
    const response = await fetch("/api/thongke/todaydoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today total income:", error);
    totalSalesIncome.innerText = 0;
  }
}
async function getToDayTotalBill() {
  try {
    const response = await fetch("/api/thongke/todaytotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today total bill:", error);
  }
}
async function getToDayTopProductSale() {
  try {
    const response = await fetch("/api/thongke/todaytopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales");
    tbody.innerHTML = "";
    if (data.length === 0) {
      console.log("No data available.");
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");
      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const colorSpan = document.createElement("span");
      colorSpan.textContent = " - " + product.productDetail?.idColor?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(colorSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
    filtertopProductSales.innerHTML = ` | Hôm nay`;
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
}
// mouth
async function getThisMouthTotalProductSale() {
  try {
    const response = await fetch("/api/thongke/thismouthtotalproductsale");
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Tháng này`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
async function getThisMouthToTalIncome() {
  try {
    const response = await fetch("/api/thongke/thismouthdoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Tháng này`;
  } catch (error) {
    console.error("Error fetching this month total income:", error);
  }
}
async function getThisMouthTotalBill() {
  try {
    const response = await fetch("/api/thongke/thismouthtotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Tháng này`;
  } catch (error) {
    console.error("Error fetching this month total bill:", error);
  }
}
async function getThisMouthTopProductSale() {
  try {
    const response = await fetch("/api/thongke/thismouthtopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales"); // Assuming you have a tbody with id="topProductSales" in your HTML
    tbody.innerHTML = "";
    if (data.length === 0) {
      tbody.innerHTML = "";
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");

      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const colorSpan = document.createElement("span");
      colorSpan.textContent = " - " + product.productDetail?.idColor?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(colorSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
  filtertopProductSales.innerHTML = ` | Tháng nay`;
}
// This Year
async function getThisYearToTalIncome() {
  try {
    const response = await fetch("/api/thongke/thisyeardoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = formatAsVND(data.toString());
    filtertotalSalesIncome.innerText = ` | Năm nay`;
  } catch (error) {
    console.error("Error fetching this year total income:", error);
  }
}
async function getThisYearTotalBill() {
  try {
    const response = await fetch("/api/thongke/thisyeartotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
    filtertotalSalesBill.innerText = ` | Năm nay`;
  } catch (error) {
    console.error("Error fetching this year total bill:", error);
  }
}
async function getThisYearTopProductSale() {
  try {
    const response = await fetch("/api/thongke/thisyeartopproductsales");
    const data = await response.json();

    const tbody = document.getElementById("topProductSales");
    tbody.innerHTML = "";
    if (data.length === 0) {
      console.log("No data available.");
    }
    data.forEach((product) => {
      const row = document.createElement("tr");

      const imageCell = document.createElement("th");
      imageCell.setAttribute("scope", "row");

      if (
        product.productDetail?.idProduct?.imageProducts &&
        product.productDetail.idProduct.imageProducts.length > 0
      ) {
        const img = document.createElement("img");
        img.setAttribute(
          "src",
          product.productDetail.idProduct.imageProducts[0].img
        );
        img.setAttribute("alt", "");
        imageCell.appendChild(img);
      }
      row.appendChild(imageCell);

      const nameCell = document.createElement("td");
      const nameSpan = document.createElement("span");
      nameSpan.textContent = product.name;

      const colorSpan = document.createElement("span");
      colorSpan.textContent = " - " + product.productDetail?.idColor?.name;

      const sizeSpan = document.createElement("span");
      sizeSpan.textContent = " - " + product.productDetail?.idSize?.name;

      nameCell.appendChild(nameSpan);
      nameCell.appendChild(colorSpan);
      nameCell.appendChild(sizeSpan);

      row.appendChild(nameCell);

      const priceCell = document.createElement("td");
      const formattedPrice = formatAsVND(product.price);
      priceCell.textContent = formattedPrice;

      row.appendChild(priceCell);

      const quantityCell = document.createElement("td");
      quantityCell.classList.add("fw-bold");
      quantityCell.textContent = product.totalQuantity;

      row.appendChild(quantityCell);

      const totalPriceCell = document.createElement("td");
      const formattedTotalPrice = formatAsVND(product.totalPrice);
      totalPriceCell.textContent = formattedTotalPrice;

      row.appendChild(totalPriceCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
  filtertopProductSales.innerHTML = ` | Năm nay`;
}
async function getThisYearTotalProductSale() {
  try {
    const response = await fetch("/api/thongke/thisyeartotalproductsale");
    const data = await response.json();
    totalSalesProduct.innerText = data.toString();
    filtertotalSalesProduct.innerText = ` | Năm nay`;
  } catch (error) {
    console.error("Error fetching today total product sale:", error);
    totalSalesProduct.innerText = 0;
  }
}
document.addEventListener("DOMContentLoaded", function () {
  getToDayTotalProductSale();
  getToDayToTalIncome();
  getToDayTotalBill();
  getToDayTopProductSale();
});

async function getResentBill() {
  try {
    const response = await fetch("/api/thongke/recentbills");
    const data = await response.json();

    const tbody = document.getElementById("resentBillTable");
    tbody.innerHTML = ""; // Clear the existing content of tbody

    data.forEach((item) => {
      const row = document.createElement("tr");

      const codeCell = document.createElement("th");
      codeCell.setAttribute("scope", "row");
      const codeLink = document.createElement("a");
      codeLink.href = "#";
      codeLink.textContent = item.productName;
      codeCell.appendChild(codeLink);
      row.appendChild(codeCell);

      const employeeCell = document.createElement("td");
      employeeCell.textContent = item.employee?.name;
      row.appendChild(employeeCell);

      const codeTableCell = document.createElement("td");
      codeTableCell.textContent = item.code;
      row.appendChild(codeTableCell);

      const totalMoneyCell = document.createElement("td");
      totalMoneyCell.textContent = item.total_money;
      row.appendChild(totalMoneyCell);

      const statusCell = document.createElement("td");
      statusCell.textContent = item.status;
      row.appendChild(statusCell);

      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("Error fetching today's top product sales:", error);
  }
}

function getStatusBadge(status) {
  if (status == 5) {
    const badge = document.createElement("span");
    badge.className = "badge bg-success";
    badge.textContent = "Thành Công";
    return badge;
  }
  if (status == 1) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-warning";
    badge.textContent = "Chờ xác nhận";
    return badge;
  }
  if (status == 2) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-secondary";
    badge.textContent = "Chờ giao hàng";
    return badge;
  }
  if (status == 3) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-info";
    badge.textContent = "Đang giao hàng";
    return badge;
  }
  if (status == 4) {
    const badge = document.createElement("span");
    badge.className = "badge badge text-bg-light";
    badge.textContent = "Đã giao hàng";
    return badge;
  }
  if (status == -1 || status == -2) {
    const badge = document.createElement("span");
    badge.className = "badge text-bg-danger";
    badge.textContent = "Đã Hủy";
    return badge;
  }

  const badge = document.createElement("span");
  badge.className = "badge text-bg-dark";
  badge.textContent = "Không xác định";
  return badge;
}
