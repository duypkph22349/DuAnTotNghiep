const totalSalesProduct = document.getElementById("totalSalesProduct");
const totalSalesIncome = document.getElementById("totalSalesIncome");
const totalSalesBill = document.getElementById("totalSalesBill");
const resentBillTable = document.getElementById("resentBillTable");
const topProductSales = document.getElementById("topProductSales");
// Today
async function getToDayToTalIncome() {
  try {
    const response = await fetch("/api/thongke/todaydoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching today total income:", error);
  }
}

async function getToDayTotalBill() {
  try {
    const response = await fetch("/api/thongke/todaytotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching today total bill:", error);
  }
}

async function getToDayTopProductSale() {
  try {
    const response = await fetch("/api/thongke/todaytopproductsales");
    const data = await response.json();
    // Assume topProductSales is a container for displaying top product sales
    topProductSales.innerHTML = "";

    // Iterate through the data and append to topProductSales
    data.forEach((item) => {
      const listItem = document.createElement("li");
      listItem.textContent = `${item.productName}: ${item.quantity}`;
      topProductSales.appendChild(listItem);
    });
  } catch (error) {
    console.error("Error fetching today top product sales:", error);
  }
}

async function getThisMouthToTalIncome() {
  try {
    const response = await fetch("/api/thongke/thismouthdoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching this month total income:", error);
  }
}

async function getThisMouthTotalBill() {
  try {
    const response = await fetch("/api/thongke/thismouthtotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching this month total bill:", error);
  }
}

async function getThisMouthTopProductSale() {
  try {
    const response = await fetch("/api/thongke/thismouthtopproductsales");
    const data = await response.json();
    topProductSales.innerHTML = "";
    data.forEach((item) => {
      const listItem = document.createElement("li");
      listItem.textContent = `${item.productName}: ${item.quantity}`;
      topProductSales.appendChild(listItem);
    });
  } catch (error) {
    console.error("Error fetching this month top product sales:", error);
  }
}

// This Year
async function getThisYearToTalIncome() {
  try {
    const response = await fetch("/api/thongke/thisyeardoanhthu");
    const data = await response.json();
    totalSalesIncome.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching this year total income:", error);
  }
}

async function getThisYearTotalBill() {
  try {
    const response = await fetch("/api/thongke/thisyeartotalbill");
    const data = await response.json();
    totalSalesBill.innerText = data.toString();
  } catch (error) {
    console.error("Error fetching this year total bill:", error);
  }
}

async function getThisYearTopProductSale() {
  try {
    const response = await fetch("/api/thongke/thisyeartopproductsales");
    const data = await response.json();
    topProductSales.innerHTML = "";

    data.forEach((item) => {
      const listItem = document.createElement("li");
      listItem.textContent = `${item.productName}: ${item.quantity}`;
      topProductSales.appendChild(listItem);
    });
  } catch (error) {
    console.error("Error fetching this year top product sales:", error);
  }
}

document.addEventListener("DOMContentLoaded", function () {
  getToDayTotalProductSale();
  getToDayToTalIncome();
  getToDayTotalBill();
  getToDayTopProductSale();
});

let pageno = 1,
  sortby = "createdAt",
  pagezise = 0;
function updatePageno(page) {
  pageno = page;
  getResentBill();
}
function updateSort(sort) {
  sortby = sort;
  getResentBill();
}
function updatePageSize(pages) {
  pagezise = pages;
  getResentBill();
}
function getResentBill() {}
